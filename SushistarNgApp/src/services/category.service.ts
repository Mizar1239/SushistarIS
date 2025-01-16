import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductCategory} from '../model/product-category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<ProductCategory[]> {
    return this.http.get<ProductCategory[]>(this.baseUrl);
  }
}
