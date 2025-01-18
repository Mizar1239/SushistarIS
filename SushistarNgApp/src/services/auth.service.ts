import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { UserDTO } from '../DTO/userDTO.model';
import { LoginDTO } from '../DTO/loginDTO.model';

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
    // const params = {username: email, password: password};

	console.log('in auth login: ', email, password);

	const dto: LoginDTO = {
		email: email,
		password: password
	};

    // return this.http.post<UserDTO>(`${this.baseUrl}/login`, null, {params});
    return this.http.post<UserDTO>(`${this.baseUrl}/login`, dto);
  }

  getUserRole(roleId: number) : string {
	return this.userRoles[roleId-1];
  }

  isAdmin() {

	console.log('auth isAdmin: ', sessionStorage.getItem('userRole'));

	return sessionStorage.getItem('userRole') == this.userRoles[1];
  }
}
