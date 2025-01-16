import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-user-menu',
  standalone: true,
  imports: [],
  templateUrl: './user-menu.component.html',
})
export class UserMenuComponent {
  @Output() sectionChange = new EventEmitter<string>(); // Emittente per notificare il cambiamento di sezione
  activeSection: string = 'orders'; // Imposta 'orders' come sezione iniziale

  selectSection(section: string): void {
    this.activeSection = section; // Aggiorna la sezione attiva
    this.sectionChange.emit(section); // Emette la sezione selezionata
  }

  logout(): void {
    localStorage.removeItem('email'); // Rimuove l'email dal localStorage
    window.location.href = '/home';
  }
}
