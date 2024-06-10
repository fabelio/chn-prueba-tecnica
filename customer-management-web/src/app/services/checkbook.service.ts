import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Checkbook } from '../models/checkbook';
import { Observable } from 'rxjs';
import { Check } from '../models/check';

@Injectable({
  providedIn: 'root'
})
export class CheckbookService {

  private apiUrl = 'http://localhost:9090/checkbooks';
  constructor(private http: HttpClient) { }

  public createCheckbook(checkbook: Checkbook): Observable<Checkbook> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Checkbook>(this.apiUrl, checkbook, { headers });
  }

  public updateCheckbook(checkbook: Checkbook): Observable<Checkbook> {
    return this.http.put<Checkbook>(this.apiUrl, checkbook);
  }

  public listCheckCheckbook(checkbook: Checkbook): Observable<Check[]> {
    return this.http.get<Check[]>(`${this.apiUrl}/${checkbook.id}/checks`)
  }

  public updateStatusCheckbook(checkbookId: string, statusCode: string, reason: string): Observable<Checkbook> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Checkbook>(`${this.apiUrl}/${checkbookId}/status/${statusCode}?reason=${reason}`, null, { headers });
  }
}
