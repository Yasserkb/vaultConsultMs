import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-products',
  standalone: true,
  templateUrl: './products.component.html',
  imports: [
    NgIf,
    NgForOf,
    HttpClientModule
  ],
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products : any;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/inventory-service/products?projection=fullProduct")
      .subscribe({
      next : (data)=>{
        this.products=data;
      },
      error : (err)=>{}
    });
  }

}
