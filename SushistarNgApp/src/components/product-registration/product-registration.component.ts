import { NgFor, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../model/product';
import { ProductDTO } from '../../DTO/productDTO.model';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
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

	priceErrorMsg: string = 'Per favore inserisci il prezzo in un formato corretto: 00.00€'

	selectedImg: File | null = null;
	imgUrl: any
	_isImgUp: boolean = false;
	imgUploadedName: string = '';

	constructor(private formBuilder: FormBuilder,
		private productService: ProductService,
		private categoryService: CategoryService,
		private router: Router
	) {}

	ngOnInit(): void {
		this.productForm = this.formBuilder.group({
			productName: ['', Validators.required],
			description: ['', Validators.required],
			price: ['', [Validators.min(0), Validators.required, Validators.pattern('^\d*(\.\d{0,2})?$')]],
			amount: ['', [Validators.min(1), Validators.required]],
			categoryId: ['', Validators.required],
			image: [null, Validators.required]
		});

		this.initCategories();
	}

	initCategories() : void {
		this.categoryService.getCategories().subscribe(
			{
				next: (result: ProductCategory[]) => {
					this.categories = result;
					// console.log('categorie: ', this.categories);
				},
				error: (error) => console.log(error)
			}
		);
	}

	submitForm(): void {

		let dto: ProductDTO = this.productForm.value;
		dto.imgPath = this.imgUploadedName;

		this.productService.addProduct(dto)
			.subscribe(
			{
				next: (result: Product) => {
					console.log('TUTTO OK', result);
					this.router.navigate(['/product', result.id]);
				},
				error: (error) => console.log('ERROR', error)
			}
		);		
	}

	checkValue(value: string) : boolean {
		return this.productForm?.get(value)?.invalid && this.productForm?.get(value)?.touched;
	}

	resetForm() : void {
		this.productForm.reset();
	}

	hideForm() : void {
		this.hideEvent.emit();
	}

	onFileSelected(event: any) {
		console.log('image uploaded', event);

		const img = event.target.files[0];

		if (img) {

			const reader = new FileReader();
			reader.readAsDataURL(img);
			reader.onload = (_e) => {
				this.imgUrl = reader.result;
			}
			this._isImgUp = true;
			this.imgUploadedName = img.name;
		}
	}

}
