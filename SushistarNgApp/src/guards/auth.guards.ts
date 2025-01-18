import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const isLoggedIn = !!sessionStorage.getItem('email'); // Verifica se l'utente è loggato
    if (!isLoggedIn) {
      this.router.navigate(['/home']); // Redirige alla pagina di login se non loggato
      return false;
    }
    return true;
  }
}
