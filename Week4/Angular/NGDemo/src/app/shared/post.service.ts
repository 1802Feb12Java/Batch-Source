import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class PostService {

  constructor(private http: Http) { }

  public getPosts(): Observable<any> {
    return this.http.get('http://jsonplaceholder.typicode.com/posts').map(res => res.json());
  }

}