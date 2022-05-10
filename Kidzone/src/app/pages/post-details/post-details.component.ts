import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from 'src/app/Shared/service/comment-service';
import { PostService } from 'src/app/Shared/service/post-service';
import {DatePipe, formatDate} from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  currentUser: any;
  userType:any;
  nbrOfComments:any;
  listComments:any;
  post:any;
  Comment: FormGroup;
  updatedComment: FormGroup;



  constructor(config: NgbModalConfig, private modalService: NgbModal,    private token: TokenStorageService, 
    private datePipe : DatePipe, private formBuilder: FormBuilder , private _Activatedroute:ActivatedRoute , private postService : PostService, private commentService : CommentService) {  config.backdrop = 'static';
    config.keyboard = false;}

  ngOnInit(): void {

    this.currentUser=this.token.getUser().id;
    this.userType=this.token.getUser().role;
    this.getOnePost(this._Activatedroute.snapshot.paramMap.get("id"));
    this.getComments(this._Activatedroute.snapshot.paramMap.get("id"));
    this.Comment = this.formBuilder.group({
        idComment:[''],
        commentContent:[''],
        commentDate:[''],
        userComment:[''],
    });
    this.updatedComment = this.formBuilder.group({
      idComment:[''],
      commentContent:[''],
      commentDate:[''],
      userComment:[''],
      post:[''],
  });
  
  }


  onSubmit(): void {
    console.log('date',this.datePipe.transform(new Date(),"dd-MM-yyyy"))
    this.Comment.controls['commentDate'].setValue(this.datePipe.transform(new Date(),"yyyy-MM-dd"));
    this.Comment.controls['userComment'].setValue(this.token.getUser().id);

    this.commentService.addComment(this.Comment.value,this._Activatedroute.snapshot.paramMap.get("id")).subscribe(res=>
    {
      this.getComments(this._Activatedroute.snapshot.paramMap.get("id"));
    })
    this.Comment.reset();
  }

  getOnePost(id){
    this.postService.getPost(id).subscribe(res => {
      this.post = res;
    })
  }
  getComments(idP){
    this.commentService.getCommsByPost(idP).subscribe(res => {
      this.listComments = res ;
      this.nbrOfComments =Object.keys(this.listComments).length;
      
    })
  }

  deleteComment(idComment){
    this.commentService.deleteComment(idComment).subscribe(res=>{
      this.getComments(this._Activatedroute.snapshot.paramMap.get("id"));
    });
  }

  updateComment():void{
    this.updatedComment.controls['commentDate'].setValue(this.datePipe.transform(new Date(),"yyyy-MM-dd"));
    this.updatedComment.controls['userComment'].setValue(this.token.getUser().id);

    this.commentService.editComment(this.updatedComment.value).subscribe(res=>
    {
      this.getComments(this._Activatedroute.snapshot.paramMap.get("id"));
    })
    this.updatedComment.reset();
  }

  open(content,c) {
    this.modalService.open(content); 
    this.updatedComment.controls['idComment'].setValue(c.idComment);
    this.updatedComment.controls['commentContent'].setValue(c.commentContent);
    this.updatedComment.controls['post'].setValue(this.post);


  }
}
