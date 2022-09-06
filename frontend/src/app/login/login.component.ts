import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { RestapiService } from '../service/restapi.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  username:string;
  password:string;
  message:any;

  constructor(private service: RestapiService, private router: Router) { }

  ngOnInit(): void {
  }

  doLogin(){
    let resp=this.service.login(this.username, this.password);
    resp.subscribe(authResponse => {
      console.log(authResponse)
      localStorage.setItem("jwt_token", authResponse.toString());
    });
    this.router.navigate(["/home"]);
  }

}
