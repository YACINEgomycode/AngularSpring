import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../user";
import { ToastrService } from 'ngx-toastr';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  registerForm!: FormGroup;
  user!: User;
  selectedCityIds=[];

  cities2 = [
    {id: 1, label: 'PARENT',name : 'parent'},
    {id: 2, label: 'ADMIN',name: 'admin'},
    {id: 3, label: 'GOWNER',name: 'gowner'},
  ];
  constructor(private authService: AuthService,
              private toast: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      'firstName' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]),
      'lastName' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]) ,
      'username' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]),
      'email' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'image'   : new FormControl(null),
      'role'   : new FormControl(null),
    });
    this.user = {
      firstName: null ,
      lastName: null,
      email: null ,
      username: null ,
      password: null,
      image: null,
      role: [] = []
    }
  }
  register(user : User) : void {
    this.user.image = 'insert image';
    this.authService.register(this.user).subscribe( () => {
      this.toast.success('Utilisateur crée avec sucées','')
      this.router.navigate(['/login']);
    }, (error : any) => {
      this.toast.error(error.error.message,'')

    }
  );
  }
 /* onSubmit(): void {
   // this.user = new User(this.registerForm.firstName,)
    //const {firstName, lastName, username, email, password, image} = this.form;
    this.authService.register(this.user).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
      //  this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
    console.log(this.registerForm)
  }*/

}
