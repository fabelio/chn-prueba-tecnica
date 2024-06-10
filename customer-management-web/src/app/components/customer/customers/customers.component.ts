import { Component } from '@angular/core';
import { Customer } from '../../../models/customer';
import { CustomerService } from '../../../services/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../dialogs/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-customers',
  // standalone: true,
  // imports: [],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.scss'
})
export class CustomersComponent {
  customerList: Customer[] = [];
  constructor(private customerService: CustomerService, private route: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  async getCustomers() {
    this.customerService.listCustomers().subscribe(customers => {
      this.customerList = customers;
    }, error => {
      console.error(error);
    });
  }

  createCustomer() {
    this.route.navigate(['customer'])
  }

  deleteCustomerConfirmation(customer: Customer) {
    this.dialog.open(ConfirmDialogComponent, {
      data: `Desea eliminar al cliente ${customer.name} ${customer.lastname}`,
      width: '250px'
    }).afterClosed().subscribe((confirmation: boolean) => {
      if (confirmation)
        this.customerService.deleteCustomer(customer).subscribe(
          data => {
            this.getCustomers();
          },
          error => {
            alert(error)
          }
        );
    });

  }

}
