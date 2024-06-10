import { Component } from '@angular/core';
import { Customer } from '../../../models/customer';
import { Account } from '../../../models/account';
import { CustomerService } from '../../../services/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ChangeStatusDialogComponent } from '../../dialogs/change-status-dialog/change-status-dialog.component';
import { AccountService } from '../../../services/account.service';

@Component({
  selector: 'app-accounts',
  // standalone: true,
  // imports: [],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.scss'
})
export class AccountsComponent {
  customer!: Customer;
  accountList: Account[] = [];

  constructor(private customerService: CustomerService, private route: Router, private dialog: MatDialog, private accountService: AccountService) {
    if (history.state != undefined)
      this.customer = history.state;
  }
  ngOnInit(): void {
    this.getAccounts();
  }

  async getAccounts() {
    this.customerService.listCustomerAccounts(this.customer).subscribe(accounts => {
      this.accountList = accounts;
    }, error => {
      alert(error)
    });
  }

  createAccount() {
    this.route.navigate(['account'], { state: this.customer });
  }


  changeStatusAccountConfirmation(account: Account, statusCode: string, statusName: string) {
    this.dialog.open(ChangeStatusDialogComponent, {
      data: { message: `Desea actualizar estado de la cuenta no. ${account.number} a ${statusName}`, collect: false },
      width: '300px'
    }).afterClosed().subscribe((reason: string) => {
      console.log(reason)
      if (reason)
        this.accountService.updateStatusAccount(account.id!.toString(), statusCode, reason).subscribe(
          account => {
            this.getAccounts();
          },
          error => {
            alert(error)
          });
    });
  }


}
