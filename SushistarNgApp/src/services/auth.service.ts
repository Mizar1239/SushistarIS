import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { UserDTO } from '../DTO/userDTO.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/user'; // URL del backend

  userRoles: string[] = [
	'customer',
	'admin'
  ]

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<UserDTO> {
    const params = {username: email, password: password};

	let dto: UserDTO = {
		email: email,
		password: password,
		roleId: 0,
		username: ''
	};

    // return this.http.post<UserDTO>(`${this.baseUrl}/login`, null, {params});
    return this.http.post<UserDTO>(`${this.baseUrl}/login`, dto);
  }

  getUserRole(roleId: number) : string {
	return this.userRoles[roleId-1];
  }

  isAdmin() {
	return localStorage.getItem('userRole') == this.userRoles[1];
  }
}
