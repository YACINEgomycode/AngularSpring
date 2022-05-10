import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './pages/header/header.component';
import { FooterComponent } from './pages/footer/footer.component';
import { PostComponent } from './post/post.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AppointmentComponent } from './pages/appointment/appointment.component';
import { DaycaresComponent } from './pages/daycares/daycares.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PostDetailsComponent } from './pages/post-details/post-details.component';
import { DatePipe } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { JwPaginationModule } from 'jw-angular-pagination';
import { NgSelectModule } from '@ng-select/ng-select';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { ProfileComponent } from './profile/profile.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';
import { OnlyLoggedInUsersGuard } from './_services/auth.guard';
import { AuthService } from './_services/auth.service';
import { ToastNoAnimationModule, ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    PostComponent,
    LoginComponent,
    LandingPageComponent,
    NotFoundComponent,
    AppointmentComponent,
    DaycaresComponent,
    PostDetailsComponent,
    RegisterComponent,
    HomeComponent,
    ListUsersComponent,
    UpdateUserComponent,
    ProfileComponent,
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    NgChartsModule,
    JwPaginationModule,
    FormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    ToastNoAnimationModule.forRoot(),
    ToastrModule

  ],
  providers: [DatePipe,AuthService, OnlyLoggedInUsersGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
