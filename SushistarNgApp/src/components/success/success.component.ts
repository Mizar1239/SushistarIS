import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {FooterComponent} from '../footer/footer.component';
import {HeaderComponent} from '../header/header.component';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  imports: [
    FooterComponent,
    HeaderComponent
  ],
  standalone: true
})

export class SuccessComponent {
  constructor(private router: Router) {}

  goToHome(): void {
    this.router.navigate(['/']); // Naviga alla home
  }
}
