import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../user";
import { Observable } from 'rxjs';
import {HeadersService} from "./headers.service";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly API_URL = 'http://localhost:8082/PiCloud/UserController';

  constructor(private http: HttpClient,private headers: HeadersService) {}

  public getUserById(id : number):Observable<any>
  {
    return this.http.get<User>(`${this.API_URL}/afficherUser/${id}`, this.headers.getHeadersOptionsWithToken());
  }

  getAllUsers(): Observable<any>{
    return this.http.get(`${this.API_URL}/afficherUsers`, this.headers.getHeadersOptionsWithToken());
  }

  public updateUser(id: number , user: User ): Observable<Object> {
    return this.http.put(`${this.API_URL}/updateUser`, user, this.headers.getHeadersOptionsWithToken());
  }

  public deleteUser(Id: number) : Observable<Object> {
    return this.http.delete(`${this.API_URL}/deleteUser/${Id}`, this.headers.getHeadersOptionsWithToken());
  }

}


