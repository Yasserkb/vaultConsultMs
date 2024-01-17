import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-customers',
  standalone: true,
  templateUrl: './customers.component.html',
  imports: [

    NgIf,
    NgForOf,
    HttpClientModule
  ],
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers :any;
  constructor(private http:HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/customer-service/customers?projection=fullCustomer")
      .subscribe({
        next : (data)=>{
          this.customers=data;
        },
        error : (err)=>{}
      });
  }

  getOrders(c: any) {
    this.router.navigateByUrl("/orders/"+c.id);
  }
}
