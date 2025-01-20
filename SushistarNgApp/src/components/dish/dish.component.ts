import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RouterLink} from '@angular/router';
import {CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-dish',
  standalone: true,
  imports: [
    RouterLink,
    CurrencyPipe
  ],
  templateUrl: './dish.component.html',
  styleUrls: ['./dish.component.css']
})
export class DishComponent {
  @Input() id: number = 0; // Aggiungi l'ID come input
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() image: string = '';
  @Input() price: number = 0;

  @Output() addToCart = new EventEmitter<{
    id: number,
    name: string,
    price: number,
    quantity: number,
    imageUrl: string
  }>();
  @Output() addToFavorite = new EventEmitter<number>();

  onAddToFavorite(): void {
    this.addToFavorite.emit(this.id);
  }

  onAddToCart(): void {
    this.addToCart.emit({
      id: this.id, // Includi l'ID del prodotto
      name: this.name,
      price: this.price,
      quantity: 1, // Imposta la quantità a 1 per il prodotto aggiunto
      imageUrl: this.image
    });
  }
}
