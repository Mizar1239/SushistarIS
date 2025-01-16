import {Component, Input} from '@angular/core';
import {DishComponent} from '../dish/dish.component';
import {Product} from '../../model/product';
import {CartService} from '../../services/cart.service';
import {CartItem} from '../../model/cart';
import {Router} from '@angular/router';
import {NgForOf} from '@angular/common';

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

  constructor(private cartService: CartService, private router: Router) {
  }

  addToCart(dish: { name: string; price: number; quantity: number; imageUrl: string }): void {
    const userId = 1;
    const cartItem: CartItem = {
      id: 0,
      name: dish.name,
      price: dish.price,
      quantity: 1,
      imageUrl: dish.imageUrl
    };

    this.cartService.addToCart(userId, cartItem).subscribe({
      next: () => {
        console.log(`Aggiunto al carrello: ${dish.name}`);
        this.router.navigate(['/cart']); // Naviga alla pagina del carrello dopo l'aggiunta
      },
      error: (err) => {
        console.error('Errore nell\'aggiunta al carrello:', err);
      }
    });
  }
}
