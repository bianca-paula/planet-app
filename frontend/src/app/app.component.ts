import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Planet } from './models/Planet';
import { RestapiService } from './restapi.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

}
