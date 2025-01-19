import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FavoriteService {
  private baseUrl = 'http://localhost:8080/favorite_products';

  constructor(private http: HttpClient) {}

  addToFavorite(userId: number, productId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${userId}/add`, { id: productId });
  }

  removeFromFavorite(userId: number, productId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${userId}/remove`, { id: productId });
  }

  viewFavorite(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${userId}`);
  }
}
