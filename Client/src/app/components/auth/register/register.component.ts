import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

interface RegisterDto {
  username?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  dateOfBirth?: String;
  phoneNumber?: string;
  address?: string;
  accountType?: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{

  registerDto:RegisterDto = {};

  constructor(private authService : AuthService, private router : Router) { }

  register() {
    this.registerDto.dateOfBirth += 'T00:00:00'; //Convert to ISO format
    this.authService.register(this.registerDto).subscribe({
      next : () => {
        this.router.navigateByUrl("/");
      },
      error : error => {
        console.log(error);
      }
    });
  }

}
