import { Component } from '@angular/core';
import { Customer } from '../../../models/customer';
import { Account } from '../../../models/account';
import { Checkbook } from '../../../models/checkbook';
import { NgForm } from '@angular/forms';
import { AccountType } from '../../../models/account-type';
import { Router } from '@angular/router';
import { AccountService } from '../../../services/account.service';
import { CheckbookService } from '../../../services/checkbook.service';

@Component({
  selector: 'app-create-accounts',
  // standalone: true,
  // imports: [],
  templateUrl: './create-accounts.component.html',
  styleUrl: './create-accounts.component.scss'
})
export class CreateAccountsComponent {
  customer!: Customer;
  account: Account = new Account();
  checkbook: Checkbook = new Checkbook();
  accountForm!: NgForm;
  isSubmitted: boolean = false;
  accountTypes: AccountType[] = [];
  accountType!: AccountType;
  constructor(private router: Router, private accountService: AccountService, private checkbookService: CheckbookService) {
    if (history.state != undefined)
      this.customer = history.state;
  }

  ngOnInit(): void {
    this.getAccountTypes();
  }

  createAccount(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {
      console.log(this.accountType)
      this.account.customer = this.customer;
      this.accountService.createAccount(this.account).subscribe(async accountCreated => {
        if (accountCreated != null) {
          this.createCheckbook(accountCreated);
          this.router.navigate(['accounts'], { state: this.customer });
        }
      }, async error => {
        alert(error);
      });
    }
  }

  getAccountTypes() {
    this.accountService.listAccountTypes().subscribe(
      accountTypes => {
        this.accountTypes = accountTypes;
      }
    );
  }

  bloquearCuenta(account: Account) {

  }

  activarCuenta(account: Account) {

  }

  createCheckbook(account: Account) {
    this.checkbook.account = account;
    this.checkbookService.createCheckbook(this.checkbook).subscribe(
      checkbook => {
        return checkbook;
      });
  }
}
