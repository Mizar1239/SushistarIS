import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import { UserDTO, UserRoleEnum } from '../../DTO/userDTO.model';

@Component({
  selector: 'app-login-modal',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './login-modal.component.html',
  styleUrl: './login-modal.component.css'
})
export class LoginModalComponent {
  @Input() isVisible = false;
  @Output() close = new EventEmitter<void>();

  email = '';
  password = '';
  errorMessage = '';

	roles = UserRoleEnum;

  closeModal() {
    this.close.emit();
  }

  constructor(private authService: AuthService) {
  }

  onSubmit() {
    this.authService.login(this.email, this.password).subscribe(
      (response: UserDTO) => {
        console.log('Login successful:', response);
        localStorage.setItem('email', this.email); // Salva il nome utente
		localStorage.setItem('userRole', this.getUserRole(response.roleId)); // Salva il nome utente
        this.closeModal(); // Chiudi la modal
        window.location.href = '/user'; // Redirige alla pagina del profilo utente
      },
      (error) => {
        console.error('Errore di login:', error);
        if (error.status === 401) {
          this.errorMessage = 'Credenziali non valide. Riprova.'; // Mostra messaggio di errore
        } else {
          this.errorMessage = 'Si è verificato un errore. Riprova più tardi.';
        }
      }
    );
  }

  getUserRole(roleId: number) : string {

	let role: string = '';

	switch(roleId) {
		case this.roles.customer: role = 'customer';
									break;
		case this.roles.admin: role = 'admin';
									break;
	}

	return role;
  }
}
