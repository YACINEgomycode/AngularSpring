import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
              private router: Router, private toast: ToastrService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
    this.form = new FormGroup({
      'username' : new FormControl(null, [Validators.required,Validators.minLength(4)]),
      'password' : new FormControl(null, [Validators.required,Validators.minLength(4)])
    });



    }
    redirectTo(uri:string){
      this.router.navigateByUrl('/post', {skipLocationChange: true}).then(()=>
      this.router.navigate([uri]));
   }
  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.toast.success('Login succeeded','')
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        let index = data.roles.findIndex( (x:any) => x === "ADMIN");
        if (index >= 0){
          this.redirectTo('/list-users')
        }else{
          this.redirectTo('/profile')
        }
      },
      err => {
        this.toast.error("Login failed please try again !")
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

}
