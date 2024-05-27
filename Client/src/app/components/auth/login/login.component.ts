import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
      public authService : AuthService,
      private router : Router,
      private toastr : ToastrService  ) { }

  
  loginDto: any = {};

  login() {
    this.authService.login(this.loginDto).subscribe({
      next : (user) => {
        this.router.navigateByUrl("/")
        console.log(user);
      },
      error : errorMsg => {
        console.log(errorMsg);
        for(const element of errorMsg) {
          this.toastr.error(element);
        }
      }
    });
  }
}
