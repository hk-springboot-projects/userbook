import { Component } from '@angular/core';
import {UserService} from "../user.service";
import {Subject} from "rxjs";
import {User} from "../user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  constructor(private userService: UserService) {
  }

  users: User[] = [];
  dtTrigger: Subject<any>= new Subject();
  LINKS ={
    USERS: "users",
    ADD_USER: "add-user",
    VIEW_USER: "view-user"
  }

  ngOnInit(){
    this.userService.getUsers().subscribe(data =>{
      this.users = data;
    });
  }

}
