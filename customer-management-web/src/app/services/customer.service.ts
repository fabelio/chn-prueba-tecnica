import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';
import { Account } from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:9090/customers';

  constructor(private http: HttpClient) { }

  public listCustomers(): Observable<Customer[]>{
    return this.http.get<Customer[]>(this.apiUrl);
  }

  public createCustomer(customer: Customer): Observable<Customer>{
    console.log("Customer to create");
    console.log(customer);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Customer>(this.apiUrl, customer, {headers});
  }

  public updateCustomer(customer: Customer):Observable<Customer>{
    return this.http.put<Customer>(this.apiUrl, customer);
  }

  public deleteCustomer(customer: Customer): Observable<any>{
    return this.http.delete(`${this.apiUrl}/${customer.id}`);
  }

  public listCustomerAccounts(customer: Customer):Observable<Account[]>{
    return this.http.get<Account[]>(`${this.apiUrl}/${customer.id}/accounts`)
  }
}
