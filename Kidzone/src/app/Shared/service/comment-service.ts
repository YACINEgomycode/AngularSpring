import { Injectable } from '@angular/core';
import {HttpHeaders, HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
    providedIn: 'root'
  })


export class CommentService {


    readonly API_URL = 'http://localhost:8082/PiCloud'
    constructor(private http: HttpClient) { }

    getCommsByPost(idPost : any){
        return this.http.get(`${this.API_URL}/retrieve-Comms-by-Post/${idPost}`);
    }

    getAllComms(){
        return this.http.get(`${this.API_URL}/retrieve-AllComments`);
    }

    addComment(comment : any, idPost : any){
        return this.http.post(`${this.API_URL}/add-Comment/${idPost}` , comment)      
    }

    deleteComment(idComment : any){
        return this.http.delete(`${this.API_URL}/remove-Comment/${idComment}`);
    }

    editComment(comment : any){
        return this.http.put(`${this.API_URL}/AlterComment` , comment);
    }

}
