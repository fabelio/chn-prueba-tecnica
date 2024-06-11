import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './components/customer/customers/customers.component';
import { CreateCustomersComponent } from './components/customer/create-customers/create-customers.component';
import { AccountsComponent } from './components/accounts/accounts/accounts.component';
import { CreateAccountsComponent } from './components/accounts/create-accounts/create-accounts.component';
import { CheckbooksComponent } from './components/checksbooks/checkbooks/checkbooks.component';
import { CreateCheckbooksComponent } from './components/checksbooks/create-checkbooks/create-checkbooks.component';
import { ChecksComponent } from './components/checks/checks/checks.component';

const routes: Routes = [
  { path: '/', component: CustomersComponent },
  { path: 'customer', component: CreateCustomersComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'account', component: CreateAccountsComponent },
  { path: 'checkbooks', component: CheckbooksComponent },
  { path: 'checkbook', component: CreateCheckbooksComponent },
  { path: 'checks', component: ChecksComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
