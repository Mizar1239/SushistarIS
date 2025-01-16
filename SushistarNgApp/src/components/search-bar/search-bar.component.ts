import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {
  searchTerm: string = ''; // Campo per memorizzare il termine di ricerca

  @Output() searchEvent = new EventEmitter<string>(); // Emittente dell'evento di ricerca

  onSearch(): void {
    this.searchEvent.emit(this.searchTerm); // Emette il termine di ricerca
  }
}
