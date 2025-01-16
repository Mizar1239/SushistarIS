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
  styleUrl: './dish.component.css'
})
export class DishComponent {
  @Input() name: string = '';
  @Input() description: string = '';
  @Input() image: string = '';
  @Input() price: number = 0;

  @Output() addToCart = new EventEmitter<{ name: string, price: number, quantity: number, imageUrl: string }>();

  onAddToCart(): void {
    this.addToCart.emit({
      name: this.name,
      price: this.price,
      quantity: 1,
      imageUrl: this.image
    });
  }
}
