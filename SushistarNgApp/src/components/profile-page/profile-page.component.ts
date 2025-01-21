import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../model/user';
import {UserInfoComponent} from '../user-info/user-info.component';
import {HeaderComponent} from '../header/header.component';
import {OrderHistoryComponent} from '../order-history/order-history.component';
import {UserMenuComponent} from '../user-menu/user-menu.component';
import {FooterComponent} from '../footer/footer.component';
import {NgForOf, NgIf} from '@angular/common';
import {FavoriteService} from '../../services/favorite.service';
import {Product} from '../../model/product';
import {DishComponent} from '../dish/dish.component';


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
    NgIf,
    DishComponent,
    NgForOf
  ],
})
export class ProfilePageComponent implements OnInit {
  activeSection: string = 'orders'; // Sezione attiva iniziale
  userData: User | null = null; // Dati utente
  favoriteProducts: Product[] = [];

  constructor(private userService: UserService, private favoriteService: FavoriteService) {
  }

  ngOnInit(): void {
    this.loadFavorites();
    const email = sessionStorage.getItem('email'); // Recupera l'email dal --localStorage-- sessionStorage
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

  loadFavorites(): void {
    const userId = 1; // ID utente, dinamico
    this.favoriteService.viewFavorite(userId).subscribe({
      next: (response) => {
        // Rimuovi duplicati basandoti sull'ID
        this.favoriteProducts = response.products.filter(
          (product: { id: any; }, index: any, self: any[]) =>
            index === self.findIndex((p) => p.id === product.id)
        );
      },
      error: (err) => {
        console.error('Errore nel caricamento dei preferiti:', err);
      }
    });
  }

}
