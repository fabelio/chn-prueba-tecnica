import { Component } from '@angular/core';
import { Customer } from '../../../models/customer';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-create-customers',
  // standalone: true,
  // imports: [],
  templateUrl: './create-customers.component.html',
  styleUrl: './create-customers.component.scss'
})
export class CreateCustomersComponent {
  customer: Customer = new Customer;
  customerForm!: NgForm;
  isSubmitted: boolean = false;
  constructor(private router: Router, private customerService: CustomerService) {
    if (history.state != undefined)
      this.customer = history.state;
  }

  ngOnInit(): void {
  }

  createCustomer(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {

      this.customerService.createCustomer(this.customer).subscribe(async customerCreated => {
        if (customerCreated != null) {
          this.router.navigate(['home'])
        }
      }, async error => {
        alert(error);
      });
    }
  }
}
