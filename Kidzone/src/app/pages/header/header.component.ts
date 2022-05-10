import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../_services/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  existToken: any;
  constructor(private token: TokenStorageService,
              private router: Router) {
    this.verifToken();
  }

  ngOnInit(): void {
    this.verifToken();
  }

  verifToken(){
    this.existToken = this.token.getToken();
    console.log('test',this.existToken)
  }
  signOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(["/"]);
    this.existToken = null;
    this.verifToken();
  }
}
