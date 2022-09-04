import { Component, OnInit } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  authRequest: any = {
    "userName": "user1",
    "password": "password1"
};

  constructor(private service: JwtClientService) { }

  ngOnInit(): void {
    this.getAccessToken(this.authRequest);
  }

  public getAccessToken(authRequest){
    let resp = this.service.generateToken(authRequest);
    resp.subscribe(data => console.log("Token :"+data));

  }

}
