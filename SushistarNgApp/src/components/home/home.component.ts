import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../header/header.component';
import {FooterComponent} from '../footer/footer.component';
import {AboutUsComponent} from '../about-us/about-us.component';
import {HeroComponent} from '../hero/hero.component';
import {ListDishesComponent} from '../list-dishes/list-dishes.component';
import {Product} from '../../model/product';
import {ProductService} from '../../services/product.service';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    AboutUsComponent,
    HeroComponent,
    ListDishesComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  popularDishes: Product[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadPopularDishes();
  }

  loadPopularDishes(): void {
    this.productService.getTopProducts(4).subscribe({
      next: (products) => {
        this.popularDishes = products;
      },
      error: (err) => {
        console.error('Errore nel caricamento dei piatti popolari:', err);
      }
    });
  }
}
