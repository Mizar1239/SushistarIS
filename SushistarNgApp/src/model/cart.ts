export interface CartItem {
  id: number;           // Identificativo dell'elemento nel carrello
  productId?: number;   // (Opzionale) Identificativo del prodotto associato
  name: string;
  price: number;
  quantity: number;
  imageUrl: string;     // URL dell'immagine del prodotto
}

export interface Cart {
  products: any;
  cartId?: number;      // (Opzionale) Identificativo del carrello
  total: number;        // Totale del carrello
}
