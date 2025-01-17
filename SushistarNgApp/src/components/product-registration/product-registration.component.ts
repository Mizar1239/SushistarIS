import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../model/product';
import { ProductDTO } from '../../DTO/productDTO.model';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-product-registration',
  standalone: true,
  imports: [
	NgIf,
	ReactiveFormsModule,
	RouterLink,
	RouterLinkActive
],
  templateUrl: './product-registration.component.html',
  styleUrl: './product-registration.component.css'
})
export class ProductRegistrationComponent {
	productForm: any;

	constructor(private formBuilder: FormBuilder,
		private productService: ProductService
	) {}

	ngOnInit(): void {
		this.productForm = this.formBuilder.group({
			productName: ['', Validators.required],
			description: ['', Validators.required],
			price: ['', [Validators.min(0), Validators.required]],
			amount: ['', [Validators.min(1), Validators.required]],
			categoryId: ['', Validators.required]
		})
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


}
