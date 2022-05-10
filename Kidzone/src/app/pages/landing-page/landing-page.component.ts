import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {LandingService} from "./landing.service";

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  constructor(private landing: LandingService) { }

  ngOnInit(): void {
  }


  signUp(){
    this.landing.authUSer().subscribe((data:any) => {
      console.log('data',data)
    })
  }
}
