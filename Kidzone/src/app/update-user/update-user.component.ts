import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {User} from "../user";
import {AdminService} from "../_services/admin.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {HeadersService} from "../_services/headers.service";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id! : number;
  user : User = new User();
  updateForm!: FormGroup;
  constructor( private service : AdminService , private route : ActivatedRoute,
               private router: Router,private headers: HeadersService,
               private toast: ToastrService) { }

  ngOnInit(): void {
    this.id =  this.route.snapshot.params['id'];
    this.service.getUserById(this.id).subscribe (data =>{
      this.user =data });
    this.updateForm = new FormGroup({
      'firstName' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]),
      'lastName' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]) ,
      'username' : new FormControl(null, [Validators.required,Validators.minLength(4),Validators.maxLength(20)]),
      'email' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'image'   : new FormControl(null)
    });

  }

  onSubmit(){
    this.service.updateUser(this.id, this.user ).subscribe( data =>{
      this.goToEmployeeList();
        this.toast.success('Utilisateur modifié avec sucées','')
      },
      (error) => {
        this.toast.error('Probléme lors de la modification','')
      });
  }
  goToEmployeeList(){
    this.router.navigate(['/list-users'],this.headers.getHeadersOptionsWithToken());
  }
}
