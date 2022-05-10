import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppointmentComponent } from './pages/appointment/appointment.component';
import { DaycaresComponent } from './pages/daycares/daycares.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import {ListUsersComponent} from "./list-users/list-users.component";
import {UpdateUserComponent} from "./update-user/update-user.component";
import {ProfileComponent} from "./profile/profile.component";
import {OnlyLoggedInUsersGuard} from "./_services/auth.guard";
import { PostDetailsComponent } from './pages/post-details/post-details.component';
import { PostComponent } from './post/post.component';

const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {path: 'daycares', component: DaycaresComponent},
  {path: 'appointment', component: AppointmentComponent},
  {path: 'post', component: PostComponent },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: 'list-users', component: ListUsersComponent, canActivate :[OnlyLoggedInUsersGuard]},
  { path: 'update-user/:id', component: UpdateUserComponent},
  { path: 'profile', component: ProfileComponent, canActivate :[OnlyLoggedInUsersGuard] },
  {path: 'postDetails/:id', component: PostDetailsComponent},
  { path: '**', component: NotFoundComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
