import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cart, CartItem } from '../model/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8080/cart'; // URL del back-end

  constructor(private http: HttpClient) {}

  getCart(userId: number): Observable<Cart> {
    return this.http.get<Cart>(`${this.baseUrl}/${userId}`);
  }

  addToCart(userId: number, item: CartItem): Observable<Cart> {
    return this.http.post<Cart>(`${this.baseUrl}/${userId}/add`, item);
  }

  removeFromCart(userId: number, item: CartItem): Observable<Cart> {
    return this.http.post<Cart>(`${this.baseUrl}/${userId}/remove`, item);
  }
}
