import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Check } from '../models/check';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckService {
  private apiUrl = 'http://localhost:9090/checks';
  constructor(private http: HttpClient) { }

  public updateStatusCheck(checkbookId: string, statusCode: string, reason: string): Observable<Check> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Check>(`${this.apiUrl}/${checkbookId}/status/${statusCode}?reason=${reason}`, null, { headers });
  }
}
