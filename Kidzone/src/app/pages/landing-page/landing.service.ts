import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LandingService {

  constructor(private http: HttpClient) { }

  authUSer(){
    let user;
    user = {
      "username":"saaaaaaliimou",
      "email":"slim.bhs@esprit.tn",
      "password":"wissem0000",
      "role": ["admin"],
      "firstName":"wissem",
      "lastName":"Ben Houria",
      "image":"imageggggdsd"
    }
    console.log('user test', user)
    console.log('url', environment.BASEURL + environment.SIGNUP);
    return this.http.post(environment.BASEURL + environment.SIGNUP, user);

  }
}
