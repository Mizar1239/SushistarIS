import { Component } from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-order-history',
  standalone: true,
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './order-history.component.html',
})
export class OrderHistoryComponent {
  orders = [
    {
      numero: '001',
      totale: 30,
      articoli: [
        { nome: 'Sushi Roll', quantita: 2, prezzo: 10},
        { nome: 'Tempura', quantita: 1, prezzo: 10 }
      ],
      dataAcquisto: '10/01/2025',
      status:"Molto bello"
    },
    {
      numero: '002',
      totale: 50,
      articoli: [
        { nome: 'Sushi Roll', quantita: 3, prezzo: 12 },
        { nome: 'Ramen', quantita: 1, prezzo: 14 }
      ],
      dataAcquisto: '11/01/2025',
      status:"Molto bello"
    }
  ];

  selectedOrder: any = null;

  toggleOrderDetails(order: any): void {
    if (this.selectedOrder === order) {
      this.selectedOrder = null; // Se l'ordine è già selezionato, lo deselezioniamo
    } else {
      this.selectedOrder = order; // Altrimenti selezioniamo l'ordine
    }
  }
}
