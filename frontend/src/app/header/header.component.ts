import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  LINKS ={
    USERS: "users",
    ADD_USER: "add-user",
    VIEW_USER: "view-user"
  }
}
