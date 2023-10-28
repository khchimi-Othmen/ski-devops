

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AllTemplateUserComponent} from "./frontOffice/all-template-user/all-template-user.component";
import {BodyUserComponent} from "./frontOffice/body-user/body-user.component";
import { SubscriptionComponent } from './subscription/subscription.component';
import { SubscriptionListComponent } from './subscription-list/subscription-list.component';

const routes: Routes = [
  { path :"", redirectTo:"admin", pathMatch:"full"},
  { path :'Subscription', component:AllTemplateUserComponent,
    children:[
      { path :'AddSub', component:SubscriptionComponent},
      { path :'ListSubs', component:SubscriptionListComponent},

      {path:"",component:BodyUserComponent}
    ]},

  {path:"**",component:AllTemplateUserComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
