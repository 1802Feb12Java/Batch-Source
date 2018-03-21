import { Injectable } from '@angular/core';
import { Product } from './Product';

@Injectable()
export class ProductService {

  getProducts(): Product[] {
    return products;
  }

  getProductById(productId: number): Product {
    return products.find(p => p.id === productId);
  }
}

const products = [
  {
    'id': 0,
    'title': 'Ragdoll',
    'price': 3.00
  },
  {
    'id': 1,
    'title': 'Manecoon',
    'price': 100000
  },
  {
    'id': 2,
    'title': 'Tabby',
    'price': 2.00
  }
];
