import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderUserComponent } from './frontOffice/header-user/header-user.component';
import { BodyUserComponent } from './frontOffice/body-user/body-user.component';
import { FooterUserComponent } from './frontOffice/footer-user/footer-user.component';
import { AllTemplateUserComponent } from './frontOffice/all-template-user/all-template-user.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SubscriptionComponent } from './subscription/subscription.component';
import { SubscriptionListComponent } from './subscription-list/subscription-list.component';




@NgModule({
  declarations: [
    AppComponent,
    HeaderUserComponent,
    BodyUserComponent,
    FooterUserComponent,
    AllTemplateUserComponent,

    SubscriptionComponent,
    SubscriptionListComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
