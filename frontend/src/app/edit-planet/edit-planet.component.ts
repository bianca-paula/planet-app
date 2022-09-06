import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Crew } from '../models/Crew';
import { Planet } from '../models/Planet';
import { RestapiService } from '../service/restapi.service';

@Component({
  selector: 'app-edit-planet',
  templateUrl: './edit-planet.component.html',
  styleUrls: ['./edit-planet.component.css']
})
export class EditPlanetComponent implements OnInit {
  selectedPlanet: Planet;
  public crews: Crew[];
  EditForm : FormGroup;
  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<EditPlanetComponent>,
    private planetService: RestapiService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedPlanet = data.selectedPlanet;
    }

  ngOnInit(): void {
    this.getCrews();
    this.dialogRef.updatePosition();
    this.EditForm = new FormGroup({
      id: new FormControl(this.selectedPlanet.id),
      name: new FormControl(this.selectedPlanet.name),
      status: new FormControl(this.selectedPlanet.status, Validators.required),
      image: new FormControl(this.selectedPlanet.image),
      crew: new FormControl(this.selectedPlanet.crew['id'])
    });
    
  }

  onCancel() : void {
    this.dialogRef.close();
  }

  get formControls(){
    return this.EditForm.controls;
  }

  onEdit() : void {
    if (this.EditForm.invalid){
      return;
    }
    else{  
      var editFormValues = this.formControls;
      var newPlanet = new Planet(
        editFormValues['id'].value,
        editFormValues['name'].value,
        editFormValues['image'].value,
        editFormValues['status'].value,
        editFormValues['crew'].value
      );

      this.planetService.updatePlanet(newPlanet).subscribe(
        (response: Planet) => {
          alert("Planet was updated succesfully!");
        },
        (error: HttpErrorResponse) =>{
          alert("There was a problem updating the planet!");
        }
  
      );
      this.dialogRef.close();
    }
  }

  public getCrews() : void {
    this.planetService.getAllCrews().subscribe(
      (response: Crew[]) => {
        this.crews = response;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }

    );
  }

}