import { Component } from '@angular/core';
import {UserService} from "../user.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  constructor(private userService: UserService) {
  }

  users: any[] = [];
  dtTrigger: Subject<any>= new Subject();

  ngOnInit(){
    this.userService.getUsers().subscribe(data =>{
      this.users = data;
    });
  }

}
