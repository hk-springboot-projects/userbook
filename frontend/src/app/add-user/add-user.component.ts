import { Component } from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {

  user: User;
  constructor(private userService: UserService, private router: Router) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => console.log(result));
  };

  redirectToUsersList() {
    this.router.navigate(['/users']).then(r => console.log(r));
  }

}
