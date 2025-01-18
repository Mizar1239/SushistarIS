import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CurrencyPipe} from '@angular/common';
import {CartItem} from '../../model/cart';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [
    CurrencyPipe
  ],
  templateUrl: './cart-item.component.html',
})
export class CartItemComponent {
  @Input() item!: CartItem;
  @Output() increase = new EventEmitter<CartItem>();
  @Output() decrease = new EventEmitter<CartItem>();
  @Output() remove = new EventEmitter<CartItem>();
}
