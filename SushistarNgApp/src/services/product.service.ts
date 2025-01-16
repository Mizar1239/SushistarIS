import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL: string = "http://localhost:8080/product";

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseURL}/all`);
  }

  getProductByName(name: string): Observable<Product> {
    return this.http.get<Product>(`${this.baseURL}/find/${name}`);
  }

  getTopProducts(limit: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseURL}/topList`);
  }
}
