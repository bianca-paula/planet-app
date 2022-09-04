import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddPlanetComponent } from '../add-planet/add-planet.component';
import { EditPlanetComponent } from '../edit-planet/edit-planet.component';
import { Planet } from '../models/Planet';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

  public planets: Planet[];

  constructor(private planetService: RestapiService, public dialog: MatDialog) { }

  public getPlanets() : void {
    this.planetService.getAllPlanets().subscribe(
      (response: Planet[]) => {
        this.planets = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }

    );
  }


  onOpenAddModal(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.height = "550px";
    dialogConfig.width = "650px";
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    this.dialog.open(AddPlanetComponent, dialogConfig);
  }

  onOpenEditModal(planet: Planet){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.height = "500px";
    dialogConfig.width = "650px";
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      selectedPlanet: planet
    };
    this.dialog.open(EditPlanetComponent, dialogConfig);
  }


  ngOnInit(): void {
    this.getPlanets();
  }

}
