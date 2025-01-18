import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../../services/product.service';
import {FooterComponent} from '../footer/footer.component';
import {HeaderComponent} from '../header/header.component';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-product-page',
  standalone: true,
  imports: [
    FooterComponent,
    HeaderComponent,
    NgIf
  ],
  templateUrl: './product-page.component.html',
  styleUrl: './product-page.component.css'
})
export class ProductPageComponent implements OnInit{
  quantity: number = 1;
  product: Product | null = null; // Dettagli del prodotto

  constructor(private route: ActivatedRoute, private productService: ProductService) {
  }

  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');
    if (name) {
      this.loadProduct(name);
    }
  }

  loadProduct(name: string): void {
    this.productService.getProductByName(name).subscribe(
      (data: Product) => {
        this.product = data;
        console.log('Dettagli del prodotto:', this.product);
      },
      (error) => {
        console.error('Errore nel recupero del prodotto:', error);
      }
    );
  }

  loadProductById(id: number): void {
    this.productService.getProductById(id).subscribe(
		{
			next: (data: Product) => {
				this.product = data;
				console.log('Dettagli del prodotto:', this.product);
			},
			error: (error) => {
				console.error('Errore nel recupero del prodotto:', error);
			}
		}
    );
  }

  increaseQuantity(): void {
    this.quantity++;
  }

  decreaseQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  addToCart(): void {
    if (this.product) {
      console.log(`Aggiunto al carrello: ${this.quantity} x ${this.product.productName}`);
    }
  }

  addToFavorites(): void {
    console.log(`Aggiunto ai preferiti: ${this.product?.productName}`);
  }

}
