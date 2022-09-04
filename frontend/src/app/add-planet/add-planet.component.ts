import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Crew } from '../models/Crew';
import { Planet } from '../models/Planet';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-add-planet',
  templateUrl: './add-planet.component.html',
  styleUrls: ['./add-planet.component.css']
})
export class AddPlanetComponent implements OnInit {
  selectedCrew : any;
  AddForm : FormGroup;
  public crews: Crew[];
  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<AddPlanetComponent>,
    private planetService: RestapiService
  ) { }

  ngOnInit(): void {
    this.getCrews();
    this.dialogRef.updatePosition();
    this.AddForm = new FormGroup({
      name: new FormControl('', Validators.required),
      status: new FormControl('', Validators.required),
      image: new FormControl('', Validators.required),
      crew: new FormControl('', Validators.required)
    });
  }

  onCancel() : void {
    this.dialogRef.close();
  }

  get formControls(){
    return this.AddForm.controls;
  }

  onAdd() : void {
    if (this.AddForm.invalid){
      return;
    }
    else{  
      var addFormValues = this.formControls;
      var newPlanet = new Planet(
        '',
        addFormValues['name'].value,
        addFormValues['image'].value,
        addFormValues['status'].value,
        addFormValues['crew'].value
      );

      this.planetService.addPlanet(newPlanet).subscribe(
        (response: Planet) => {
          alert("Planet was added successfully!");
        },
        (error: HttpErrorResponse) =>{
          alert(error.message);
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
