import { Component, OnInit } from '@angular/core';
import { ProductService } from '../shared/product.service';
import { Product } from '../shared/Product';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product;

  constructor(private productService: ProductService, private route: ActivatedRoute) { }

  ngOnInit() {
    let prodId: number = parseInt(this.route.snapshot.params['productId']);
    this.product = this.productService.getProductById(prodId);
  }

}
