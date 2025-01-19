import {Component, Input} from '@angular/core';
import {DishComponent} from '../dish/dish.component';
import {Product} from '../../model/product';
import {CartService} from '../../services/cart.service';
import {CartItem} from '../../model/cart';
import {Router} from '@angular/router';
import {NgForOf} from '@angular/common';
import {FavoriteService} from '../../services/favorite.service';

@Component({
  selector: 'app-list-dishes',
  standalone: true,
  imports: [
    DishComponent,
    NgForOf
  ],
  templateUrl: './list-dishes.component.html',
  styleUrl: './list-dishes.component.css'
})
export class ListDishesComponent {
  @Input() dishes!: Product[];

  constructor(private cartService: CartService, private router: Router, private favoriteService:FavoriteService) {
  }

  addToCart(dish: { id: number; name: string; price: number; quantity: number; imageUrl: string }): void {
    const userId = 1; // Id dell'utente, da sostituire con quello dinamico
    if (!dish.id || dish.id <= 0) {
      console.error('ID prodotto non valido:', dish.id);
      return; // Non aggiungere al carrello se l'ID non è valido
    }

    const cartItem: CartItem = {
      id: dish.id, // Usa l'ID del prodotto
      name: dish.name,
      price: dish.price,
      quantity: dish.quantity, // Usa la quantità fornita (1 di default nel componente)
      imageUrl: dish.imageUrl
    };

    // Aggiungi cartItem al carrello (esempio di chiamata al servizio per aggiungere al carrello)
    this.cartService.addToCart(userId, cartItem).subscribe({
      next: (updatedCart) => {
        console.log('Carrello aggiornato:', updatedCart);
        this.router.navigate(['/cart']);
      },
      error: (err) => {
        console.error('Errore nell\'aggiunta al carrello:', err);
      }
    });
  }

  onAddToFavorite(productId: number): void {
    if (!productId || productId <= 0) {
      console.error('ID prodotto non valido:', productId);
      return;
    }

    this.favoriteService.addToFavorite(1,productId).subscribe({
      next: (response) => {
        console.log('Prodotto aggiunto ai preferiti:', response);
        // Reindirizza al profilo utente dopo l'aggiunta ai preferiti
        this.router.navigate(['/user']);
      },
      error: (error) => {
        console.error('Errore nell\'aggiunta ai preferiti:', error);
      }
    });
  }



}
