import { Component, OnInit } from '@angular/core';
import {AdminService} from "../_services/admin.service";
import {User} from "../user";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {


  constructor(private admin:AdminService, private  router: Router,
              private toast: ToastrService) { }

  List_Users: any;
  user!: User;

  ngOnInit(): void {
    this.Get_ALLusers();
    this.user={ firstName:null, lastName:null, username:null, email:null, password:null, image:null, role: []}
  }

  Get_ALLusers(){
    this.admin.getAllUsers().subscribe(us =>this.List_Users=us)


  }

  UpdateUser(id:number)
  {
    this.router.navigate(['update-user',id]);
  }

  DeleteUser(id:number)
  {
    return this.admin.deleteUser(id).subscribe(data =>{
      this.Get_ALLusers();
      this.toast.success('Utilisateur supprimer avec sucées','')
    }, (error)=> {
      this.toast.error('Probléme lors de la suppression','')

    })
  }


}
