import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './components/customer/customers/customers.component';
import { CreateCustomersComponent } from './components/customer/create-customers/create-customers.component';
import { AccountsComponent } from './components/accounts/accounts/accounts.component';
import { ChecksComponent } from './components/checks/checks/checks.component';
import { CheckbooksComponent } from './components/checksbooks/checkbooks/checkbooks.component';
import { CreateCheckbooksComponent } from './components/checksbooks/create-checkbooks/create-checkbooks.component';
import { FormsModule } from '@angular/forms';
import { CreateAccountsComponent } from './components/accounts/create-accounts/create-accounts.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import { ConfirmDialogComponent } from './components/dialogs/confirm-dialog/confirm-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { ChangeStatusDialogComponent } from './components/dialogs/change-status-dialog/change-status-dialog.component';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    CreateCustomersComponent,
    AccountsComponent,
    ChecksComponent,
    CheckbooksComponent,
    CreateCheckbooksComponent,
    CreateAccountsComponent,
    ConfirmDialogComponent,
    ChangeStatusDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormField,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
