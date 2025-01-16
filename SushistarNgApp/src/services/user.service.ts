import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user'; // URL del backend

  constructor(private http: HttpClient) {}

  getUserDetails(email: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/details?email=${email}`);
  }
}
