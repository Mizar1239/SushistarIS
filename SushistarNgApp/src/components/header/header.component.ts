import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {LoginModalComponent} from '../login-modal/login-modal.component';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    LoginModalComponent,
    NgIf,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isLoginModalVisible = false;

  constructor(private router: Router) {}

  // Getter per verificare se l'utente è loggato
  get isLoggedIn(): boolean {
    return !!localStorage.getItem('email'); // Controlla se l'email è presente
  }

  openLoginModal(): void {
    if (this.isLoggedIn) {
      this.router.navigate(['/user']); // Reindirizza al profilo se loggato
    } else {
      this.isLoginModalVisible = true; // Mostra la modale di login se non loggato
    }
  }

  closeLoginModal(): void {
    this.isLoginModalVisible = false; // Chiude la modale di login
  }

  showCartWarning(event: Event): void {
    if (!this.isLoggedIn) {
      event.preventDefault(); // Previene la navigazione
      alert('Devi effettuare il login per accedere al carrello!');
    }
  }

  isUserAdmin() : boolean {
	return true;
  }
}
