import { NgFor, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../model/product';
import { ProductDTO } from '../../DTO/productDTO.model';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { CategoryService } from '../../services/category.service';
import { ProductCategory } from '../../model/product-category';

@Component({
  selector: 'app-product-registration',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
	NgIf,
	NgFor,
	ReactiveFormsModule,
	RouterLink,
	RouterLinkActive
],
  templateUrl: './product-registration.component.html',
  styleUrl: './product-registration.component.css'
})
export class ProductRegistrationComponent {
	productForm: any;
	@Output() hideEvent = new EventEmitter<void>();

	categories: ProductCategory[] = [];

	constructor(private formBuilder: FormBuilder,
		private productService: ProductService,
		private categoryService: CategoryService
	) {}

	ngOnInit(): void {
		this.productForm = this.formBuilder.group({
			productName: ['', Validators.required],
			description: ['', Validators.required],
			price: ['', [Validators.min(0), Validators.required]],
			amount: ['', [Validators.min(1), Validators.required]],
			categoryId: ['', Validators.required]
		});

		this.categoryService.getCategories().subscribe(
			{
				next: (result: ProductCategory[]) => {
					this.categories = result;
					console.log('categorie: ', this.categories);
				},
				error: (error) => console.log(error)
			}
		);
	}

	submitForm(): void {

		let dto: ProductDTO = this.productForm.value;

		this.productService.addProduct(dto)
			.subscribe(
			{
				next: (result: Product) => {
					console.log('TUTTO OK', result)
				},
				error: (error) => console.log('ERROR', error)
			}
		);		
	}

	checkValue(value: string) : boolean {
		return this.productForm?.get(value)?.invalid && this.productForm?.get(value)?.touched;
	}

	hideForm() : void {
		this.hideEvent.emit();
	}
}
