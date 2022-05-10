import { Injectable } from '@angular/core';
import { Post } from '../model/post';
import {HttpHeaders, HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class PostService {
    readonly API_URL = 'http://localhost:8082/PiCloud'


    constructor(private http: HttpClient) { }

    getAllPosts(): Observable<any>{
      return this.http.get(`${this.API_URL}/retrieve-all-Posts`);
    }
    getAllPostP  (pageNo:any , pageSize:any): Observable<any>{
      return this.http.get(`${this.API_URL}/retrieve-AllPosts/${pageNo}/${pageSize}`);
    }

    addPost(post : any){
        return this.http.post(`${this.API_URL}/addP` , post)      
    }

    editPost(post : any){
        return this.http.put(`${this.API_URL}/modifierPost` , post);
    }

    deletePost(idPost : any){
        return this.http.delete(`${this.API_URL}/remove-Post/${idPost}`);
    }
    getPost(idPost : any){
      return this.http.get(`${this.API_URL}/get-Post/${idPost}`);
  }
}
