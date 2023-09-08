import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/users?offset=0&page_size=10&sort_by=id";
  constructor(private http: HttpClient) { }

  getUsers(): Observable<any>{
    return this.http.get(`${this.baseUrl}`);
  }

  save(user: User){
    return this.http.post<User>(`${this.baseUrl}`, user);
  }
}
