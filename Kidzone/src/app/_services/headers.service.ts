import { Injectable } from '@angular/core';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class HeadersService {

  constructor(private tokenStorageService : TokenStorageService) { }

  getHeadersOptionsWithToken(): any {
    const headers = this.getHeadersWithToken();
    return { headers: {'Authorization': headers['Authorization']} };
  }

  getHeadersWithToken(): any {
    return {
      'Authorization':'Bearer ' + this.tokenStorageService.getToken(),
      'Content-Type': 'application/json',
    };
  }

}
