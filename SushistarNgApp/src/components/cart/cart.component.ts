import {Component, OnInit} from '@angular/core';
import {CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {HeaderComponent} from '../header/header.component';
import {FooterComponent} from '../footer/footer.component';
import {Cart, CartItem} from '../../model/cart';
import {CartService} from '../../services/cart.service';
import {CartItemComponent} from '../cart-item/cart-item.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  templateUrl: './cart.component.html',
  imports: [
    CartItemComponent,
    NgForOf,
    CurrencyPipe,
    FooterComponent,
    HeaderComponent,
    NgIf
  ],
})
export class CartComponent implements OnInit {
  cart: Cart = {products: [], total: 0}; // Inizializza il carrello vuoto
  loading = true;

  constructor(private cartService: CartService, private router: Router) {}

  ngOnInit(): void {
    this.loadCart(); // Carica il carrello dal back-end
  }

  loadCart(): void {
    const userId = 1; // Sostituire con l'ID dell'utente dinamico
    this.cartService.getCart(userId).subscribe({
      next: (response) => {
        console.log(response);

        // Mappare i prodotti dal formato fornito
        this.cart.products = response.products.map((product: any) => ({
          id: product.id || 0, // ID predefinito se mancante
          name: product.productName, // Nome del prodotto
          price: product.price, // Prezzo del prodotto
          quantity: product.quantity || 1, // Usa la quantità già presente nel carrello
          imageUrl: product.imgPath || '' // Percorso immagine (opzionale)
        }));

        // Unire gli oggetti che hanno lo stesso ID (se sono duplicati nel carrello)
        this.cart.products = this.mergeDuplicateProducts(this.cart.products);

        // Calcolare il totale
        this.cart.total = this.cart.products.reduce((sum: number, item: { price: number; quantity: number }) => sum + item.price * item.quantity, 0);

        this.loading = false; // Disattivare il caricamento
      },
      error: (err) => {
        console.error('Errore nel caricamento del carrello:', err);
        this.loading = false;
      }
    });
  }

// Funzione per unire i prodotti duplicati nel carrello
  mergeDuplicateProducts(products: CartItem[]): CartItem[] {
    const mergedProducts: { [key: number]: CartItem } = {};
    products.forEach((product) => {
      if (mergedProducts[product.id]) {
        mergedProducts[product.id].quantity += product.quantity; // Somma la quantità
      } else {
        mergedProducts[product.id] = product;
      }
    });
    return Object.values(mergedProducts); // Restituisce una lista di prodotti senza duplicati
  }




  // Metodo per rimuovere un elemento dal carrello
  removeFromCart(item: CartItem): void {
    const userId = 1; // Sostituire con l'ID dell'utente dinamico
    this.cartService.removeFromCart(userId, item).subscribe({
      next: () => {
        // Rimuove solo il prodotto selezionato in base all'id
        this.cart.products = this.cart.products.filter((cartItem: { id: number; }) => cartItem.id !== item.id);
        // Ricalcola il totale
        this.cart.total = this.cart.products.reduce((sum: number, cartItem: { price: number; quantity: number; }) => sum + cartItem.price * cartItem.quantity, 0);
      },
      error: (err) => {
        console.error('Errore nella rimozione dal carrello:', err);
      }
    });
  }



  increaseQuantity(item: CartItem): void {
    item.quantity++;
    this.updateCart();
  }

  decreaseQuantity(item: CartItem): void {
    if (item.quantity > 1) {
      item.quantity--;
      this.updateCart();
    } else {
      this.removeFromCart(item); // Modificato per rimuovere direttamente dal carrello
    }
  }

  removeItem(item: CartItem): void {
    this.removeFromCart(item); // Utilizza il metodo esistente per la rimozione
  }

  updateCart(): void {
    this.cart.total = this.cart.products.reduce((sum: number, item: {
      price: number;
      quantity: number;
    }) => sum + item.price * item.quantity, 0);
    console.log('Carrello aggiornato:', this.cart);
  }

  checkout(): void {
    this.router.navigate(['/payment']);
  }

}
