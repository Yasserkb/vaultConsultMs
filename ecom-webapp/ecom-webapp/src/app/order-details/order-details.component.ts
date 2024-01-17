import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe, DecimalPipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-order-details',
  standalone: true,
  templateUrl: './order-details.component.html',
  imports: [
    NgIf,
    NgForOf,
    DatePipe,
    DecimalPipe,
    HttpClientModule
  ],
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderDetails :any;
  orderId!:number;
  constructor(private http:HttpClient, private router: Router, private route:ActivatedRoute) {
    this.orderId=route.snapshot.params['orderId'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/order-service/fullOrder/"+this.orderId)
      .subscribe({
        next : (data)=>{
          this.orderDetails=data;
        },
        error : (err)=>{}
      });
  }

  getOrderDetails(o: any) {
    this.router.navigateByUrl("/order-details/"+o.id);
  }
}
