import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../models/account';
import { Observable } from 'rxjs';
import { Checkbook } from '../models/checkbook';
import { AccountType } from '../models/account-type';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:9090/accounts';

  constructor(private http: HttpClient) { }

  public createAccount(account: Account): Observable<Account> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Account>(this.apiUrl, account, { headers });
  }

  public updateAccount(account: Account): Observable<Account> {
    return this.http.put<Account>(this.apiUrl, account);
  }

  public listAccountCheckbooks(account: Account): Observable<Checkbook[]> {
    return this.http.get<Checkbook[]>(`${this.apiUrl}/${account.id}/checkbooks`)
  }

  public listAccountTypes(): Observable<AccountType[]> {
    return this.http.get<AccountType[]>(`${this.apiUrl}/types`);
  }

  public updateStatusAccount(accountId: string, statusCode: string, reason: string): Observable<Account> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Account>(`${this.apiUrl}/${accountId}/status/${statusCode}?reason=${reason}`, null, { headers });
  }

  public collectCheckAccount(accountId: string, checkbookId: string, checkId: string, amount: number): Observable<Account> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Account>(`${this.apiUrl}/${accountId}/checkbook/${checkbookId}/check/${checkId}/collect?amount=${amount}`, null, { headers });
  }
}
