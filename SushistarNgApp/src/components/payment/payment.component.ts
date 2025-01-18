import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { Cart, CartItem } from '../../model/cart';
import { CartService } from '../../services/cart.service';
import { Router } from '@angular/router';
import {FooterComponent} from '../footer/footer.component';
import {HeaderComponent} from '../header/header.component';
import {CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {CartItemComponent} from '../cart-item/cart-item.component';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  standalone: true,
  imports: [
    FooterComponent,
    HeaderComponent,
    ReactiveFormsModule,
    NgIf,
    NgForOf,
    FormsModule,
    CurrencyPipe,
    CartItemComponent
  ],
})
export class PaymentComponent implements OnInit {
  paymentForm: FormGroup;
  cart: Cart = { products: [], total: 0 };
  deliveryType: 'pickup' | 'delivery' = 'pickup'; // Valore predefinito
  loading = true;

  constructor(
    private fb: FormBuilder,
    private cartService: CartService,
    private router: Router
  ) {
    this.paymentForm = this.fb.group({
      cardOwner: [
        '',
        [Validators.required, Validators.pattern('^[A-Za-zÀ-ÖØ-öø-ÿ ]+$')]
      ],
      cardNumber: [
        '',
        [Validators.required, Validators.pattern('^\\d{13,19}$')]
      ],
      expiry: [
        '',
        [Validators.required, Validators.pattern('^(0[1-9]|1[0-2])/\\d{2}$')]
      ],
      cvv: [
        '',
        [Validators.required, Validators.pattern('^\\d{3}$')]
      ],
      deliveryAddress: ['', [Validators.required]],
      postalCode: ['', [Validators.required, Validators.pattern('^\\d{5}$')]],
      pickupTime: ['']
    });

  }

  ngOnInit(): void {
    this.loadCart(); // Carica i dati del carrello
  }

  loadCart(): void {
    const userId = 1; // Sostituire con ID dinamico dell'utente
    this.cartService.getCart(userId).subscribe({
      next: (response) => {
        this.cart.products = response.products.map((product: any) => ({
          id: product.id || 0,
          name: product.name,
          price: product.price,
          quantity: 1,
          imageUrl: product.imageUrl || ''
        }));
        this.updateCartTotal();
        this.loading = false;
      },
      error: (err) => {
        console.error('Errore nel caricamento del carrello:', err);
        this.loading = false;
      }
    });
  }

  // cambio tipo di consegna
  onDeliveryTypeChange(type: 'pickup' | 'delivery'): void {
    this.deliveryType = type;

    // Resetta i campi non necessari per la scelta selezionata
    if (type === 'pickup') {
      this.paymentForm.patchValue({
        deliveryAddress: '',
        postalCode: ''
      });
    } else if (type === 'delivery') {
      this.paymentForm.patchValue({
        pickupTime: ''
      });
    }
  }

  // Cambia il costo totale dinamicamente
  updateCartTotal(): void {
    this.cart.total = this.cart.products.reduce((sum: number, item: { price: number; quantity: number; }) => sum + item.price * item.quantity, 0);
  }

  removeItem(item: CartItem): void {
    this.removeFromCart(item); // Utilizza il metodo esistente per la rimozione
  }

  // Rimuove prodotto dal carrello
  removeFromCart(item: CartItem): void {
    this.cart.products = this.cart.products.filter((cartItem: { id: number; }) => cartItem.id !== item.id);
    this.updateCartTotal();
  }

  // Conferma di pagamento
  submitPayment(): void {
    if (this.paymentForm.invalid) {
      return;
    }

    console.log('Dettagli del pagamento:', this.paymentForm.value);
    console.log('Ordine:', this.cart);

    // Simula il pagamento e svuota il carrello
    this.cart.products = [];
    this.cart.total = 0;

    alert('Pagamento completato con successo!');
    this.router.navigate(['/success']); // Naviga alla pagina di successo
  }

  formatExpiry(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/\D/g, ''); // Rimuove tutto tranne i numeri
    if (value.length > 2) {
      value = value.substring(0, 2) + '/' + value.substring(2, 4);
    }
    input.value = value; // Aggiorna il valore dell'input
    this.paymentForm.get('expiry')?.setValue(value); // Sincronizza col form
  }

  increaseQuantity(item: CartItem): void {
    item.quantity++;
    this.updateCart();
  }

  decreaseQuantity(item: CartItem): void {
    if (item.quantity > 1) {
      item.quantity--;
      this.updateCart();
    } else {
      this.removeFromCart(item); // Modificato per rimuovere direttamente dal carrello
    }
  }

  updateCart(): void {
    this.cart.total = this.cart.products.reduce((sum: number, item: {
      price: number;
      quantity: number;
    }) => sum + item.price * item.quantity, 0);
    console.log('Carrello aggiornato:', this.cart);
  }

  success(): void {
    this.router.navigate(['/success']);
  }

  // Formatta automaticamente la data di scadenza
  onExpiryInput(event: any): void {
    const input = event.target.value.replace(/\D/g, '');
    if (input.length <= 2) {
      event.target.value = input;
    } else {
      event.target.value = input.slice(0, 2) + '/' + input.slice(2, 4);
    }
  }
}
