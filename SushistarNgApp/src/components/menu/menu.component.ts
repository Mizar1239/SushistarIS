import { Component } from '@angular/core';
import {HeaderComponent} from '../header/header.component';
import {FooterComponent} from '../footer/footer.component';
import {ListDishesComponent} from '../list-dishes/list-dishes.component';
import {NgIf} from '@angular/common';
import {ProductService} from '../../services/product.service';
import {Product} from '../../model/product';
import {DropdownMenuComponent} from '../dropdown-menu/dropdown-menu.component';
import {SearchBarComponent} from '../search-bar/search-bar.component';
import { ProductRegistrationComponent } from '../product-registration/product-registration.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    ListDishesComponent,
    NgIf,
    DropdownMenuComponent,
    SearchBarComponent,
	ProductRegistrationComponent
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  allDishes: Product[] = []; // Tutti i piatti caricati dal database
  filteredDishes: Product[] = []; // Piatti filtrati in base alla ricerca o alla categoria

  isAddProductVisibile: boolean = false;

  constructor(private productService: ProductService,
	private authService: AuthService
  ) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe(
      (data) => {
        // console.log(data)
        this.allDishes = data; // Carica tutti i piatti
        this.filteredDishes = data; // Inizializza i piatti filtrati
      },
      (error) => {
        console.error('Errore nel recupero dei prodotti:', error);
      }
    );
  }

  searchProduct(searchTerm: string): void {
    if (searchTerm.trim() === '') {
      this.filteredDishes = this.allDishes; // Mostra tutti i piatti se non c'è ricerca
    } else {
      this.filteredDishes = this.allDishes.filter(dish =>
        dish.productName.toLowerCase().includes(searchTerm.toLowerCase())
      ); // Filtra i piatti in base al nome
    }
  }

  showAddProductForm() : void {
	this.isAddProductVisibile = true;
  }

  hideAddProductForm() : void {
	this.isAddProductVisibile = false;
  }

  isUserAdmin() : boolean {
	return this.authService.isAdmin();
  }
}
