import { Component, OnInit } from '@angular/core';
import { Subscription, TypeSubscription } from '../Models/Subscription';
import { SubscriptionService } from '../subscription.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent  implements OnInit {
  Sub = new Subscription(0,new Date(),new Date(),0,TypeSubscription.ANNUAL)
  message: any;
  ngOnInit() {
   }
   constructor(
     private service: SubscriptionService,
 
   ) {}
   AddSub() {
     let resp = this.service.AddSubscription(this.Sub);
     resp.subscribe((data) => {
       this.message = data;
       window.location.reload(); // Reload the page after adding the product
 
     });
   }
 }
 