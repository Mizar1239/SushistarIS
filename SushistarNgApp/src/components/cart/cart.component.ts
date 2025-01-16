import {Component, OnInit} from '@angular/core';
import {Cart, CartItem} from '../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/model/cart';
import {CartService} from '../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/services/cart.service';
import {CartItemComponent} from '../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/components/cart-item/cart-item.component';
import {CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {FooterComponent} from '../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/components/footer/footer.component';
import {HeaderComponent} from '../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/components/header/header.component';

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
  styleUrls: ['../../../../../RepositoryTestSushistar/SushistarNgVecchia/src/components/cart/cart.component.css']
})
export class CartComponent implements OnInit {
  cart: Cart = {products: [], total: 0}; // Inizializza il carrello vuoto
  loading = true;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.loadCart(); // Carica il carrello dal back-end
  }

  loadCart(): void {
    const userId = 1; // Sostituire con l'ID dell'utente dinamico
    this.cartService.getCart(userId).subscribe({
      next: (response) => {
        this.cart.products = response.products.map((product: any) => ({
          id: product.id || 0, // ID predefinito se mancante
          name: product.name,
          price: product.price,
          quantity: 1, // Imposta la quantità predefinita
          imageUrl: product.imageUrl || '' // Aggiungi imageUrl per CartItem
        }));
        this.cart.total = this.cart.products.reduce((sum: number, item: {
          price: number;
          quantity: number;
        }) => sum + item.price * item.quantity, 0);
        this.loading = false;
      },
      error: (err) => {
        console.error('Errore nel caricamento del carrello:', err);
        this.loading = false;
      }
    });
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
}
