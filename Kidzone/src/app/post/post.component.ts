import { Component, OnInit } from '@angular/core';
import { Post } from '../Shared/model/post';
import { PostService } from '../Shared/service/post-service';
import { CommentService } from '../Shared/service/comment-service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ChartData, ChartOptions } from 'chart.js';
import { DatePipe } from '@angular/common';
import {formatDate} from '@angular/common';
import { TokenStorageService } from '../_services/token-storage.service';



@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  currentUser: any;

  pageOfItems: Array<any>;
  items = [];
  pageSize: number = 3;
  listPosts:any;
  listComments:any;
  filteredArray:any;
  filteredArrayComms:any;
  form : boolean = false;
  closeResult! : string;
  Post: FormGroup;
  salesData: ChartData<'line'>;
  chartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Monthly Stats',
      },
    },
    animations: {
      tension: {
        duration: 1000,
        easing: 'linear',
        from: 1,
        to: 0,
        loop: true
      }
    },
      scales: {
          y: {
              suggestedMin: 0,
              suggestedMax: 3
          }
      }
  };

  constructor(private datePipe : DatePipe ,
    private token: TokenStorageService,
              private postsService : PostService,
              private formBuilder: FormBuilder,
              private modalService: NgbModal,
              private commentService : CommentService ) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    console.log('user',this.currentUser);
    this.getPage();
    this.getAllComments();
    this.getAllPosts();
    this.Post = this.formBuilder.group({
      idPost:[''],
        title:[''],
        description:[''],
        postDate:[''],
        image:[''],
    });
  }

  pageClick(pageOfItems:Array<any>){
    this.pageOfItems = pageOfItems;
    console.log(pageOfItems)
  }
  
getPage (){
  this.postsService.getAllPostP(1 ,this.pageSize).subscribe(res=>this.items=res)
}

  getAllComments(){
    this.commentService.getAllComms().subscribe(res => { this.listComments = res; console.log("comms",res); })
  }
  getAllPosts(){
    this.postsService.getAllPosts().subscribe(res =>{
      this.listPosts = res;
      this.try()
    });
    
  }


  upload(event) {
    const file = event.target.files[0];
    this.Post.get('image').setValue(file)
 }
  onSubmit(): void {
    const formData= new FormData(); 
    formData.append("title",this.Post.get("title").value);
    formData.append("userPost",this.currentUser.id);

    formData.append("postDate",new Date().toLocaleDateString('en-GB'))
    formData.append("file",this.Post.get("image").value);
    console.log("hgjk",formData)

    this.postsService.addPost(formData).subscribe(() => {
      this.getAllPosts();//ba3ed ajout yjib el post
      this.form = false;
    });
    this.Post.reset();
   
  }


 pad(num, size) {
    num = num.toString();
    while (num.length < size) num = "0" + num;
    return num;
}
 try(){

    const PostMonth = [0,0,0,0,0,0,0,0,0,0,0,0];
    const CommentMonth = [0,0,0,0,0,0,0,0,0,0,0,0];
    this.filteredArray = this.listPosts.map((itm) => itm.postDate.slice(8, 10));
    this.filteredArrayComms = this.listComments.map((itm) => itm.commentDate.slice(5, 7));
    console.log("hhhhhh",this.filteredArrayComms);


  
  //Posts Numbers monthly
  for (let i = 0; i < this.filteredArray.length; i++) {
    for (let j = 1; j <= 12; j++) {
    if(this.filteredArray[i]==this.pad(j,2)){PostMonth[j-1]=PostMonth[j-1]+1}
    }}

    console.log("news",PostMonth)  


  //Comments by Month
  for (let i = 0; i < this.filteredArrayComms.length; i++) {
    for (let j = 1; j <= 12; j++) {
    if(this.filteredArrayComms[i]==this.pad(j,2)){CommentMonth[j-1]=CommentMonth[j-1]+1}
    }}
    console.log("newss",CommentMonth)  
    this.salesData= {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
      datasets: [
        { label: 'Posts', data: PostMonth},
        { label: 'Comments', data: CommentMonth},
      
      ],
    
    };

 }
 
 

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    }
    
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
    }
    closeForm(){
  
    }
    cancel(){
      this.form = false;
    }

}

