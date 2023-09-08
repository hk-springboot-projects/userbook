import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ViewUserComponent} from "./view-user/view-user.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {UserListComponent} from "./user-list/user-list.component";
import {MainPageComponent} from "./main-page/main-page.component";

const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'users', component: UserListComponent },
  { path: 'view-user', component: ViewUserComponent },
  { path: 'add-user', component: AddUserComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
