import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../model/user';
import {UserInfoComponent} from '../user-info/user-info.component';
import {HeaderComponent} from '../header/header.component';
import {OrderHistoryComponent} from '../order-history/order-history.component';
import {UserMenuComponent} from '../user-menu/user-menu.component';
import {FooterComponent} from '../footer/footer.component';
import {NgIf} from '@angular/common';


@Component({
    selector: 'app-profile-page',
    standalone: true,
    templateUrl: './profile-page.component.html',
    imports: [
        UserInfoComponent,
        HeaderComponent,
        OrderHistoryComponent,
        UserMenuComponent,
        FooterComponent,
        NgIf
    ],
})
export class ProfilePageComponent implements OnInit {
    activeSection: string = 'orders'; // Sezione attiva iniziale
    userData: User | null = null; // Dati utente

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    const email = localStorage.getItem('email'); // Recupera l'email dal localStorage
    if (email) {
      this.userService.getUserDetails(email).subscribe(
        (data: User) => {
          this.userData = data;
          console.log('Dati utente:', this.userData);
        },
        (error) => {
          console.error('Errore nel recupero dei dati utente:', error);
        }
      );
    } else {
      console.warn('Nessuna email trovata nel localStorage.');
    }
  }

  onSectionChange(section: string): void {
    this.activeSection = section; // Cambia la sezione attiva
  }

}
