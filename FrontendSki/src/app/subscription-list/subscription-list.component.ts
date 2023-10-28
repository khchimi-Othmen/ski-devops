import { Component, OnInit } from '@angular/core';
import { Subscription } from '../Models/Subscription';
import { SubscriptionService } from '../subscription.service';

@Component({
  selector: 'app-subscription-list',
  templateUrl: './subscription-list.component.html',
  styleUrls: ['./subscription-list.component.css']
})
export class SubscriptionListComponent implements OnInit {
  subscriptions: Subscription[] = [];
  filteredSubscriptions: Subscription[] = [];
  selectedType = "ANNUAL"; // Default selection

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    const selectedType = 'ANNUAL'; // or any other subscription type
    this.subscriptionService.getSubscriptionsByType(selectedType).subscribe((data) => {
      this.subscriptions = data;
      this.filteredSubscriptions = data;
    });
  }

  onTypeChange(selectedType: string) {
    // Fetch subscriptions based on the selected type
    this.subscriptionService.getSubscriptionsByType(selectedType).subscribe((data) => {
      this.filteredSubscriptions = data;
    });
  }
}