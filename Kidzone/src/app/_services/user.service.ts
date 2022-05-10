import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const API_URL = 'http://localhost:8082/Pidev/api/test/';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }
  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }
  getVisitorAccess(): Observable<any> {
    return this.http.get(API_URL + 'visitor', { responseType: 'text' });
  }
  getAdminAccess(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
  getParentAccess(): Observable<any> {
    return this.http.get(API_URL + 'parent', { responseType: 'text' });
  }
  getGownerAccess(): Observable<any> {
    return this.http.get(API_URL + 'gowner', { responseType: 'text' });
  }
}
