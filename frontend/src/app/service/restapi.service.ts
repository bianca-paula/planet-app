import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Crew } from '../models/Crew';
import { Planet } from '../models/Planet';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) { }

  public generateToken(request){
    return this.http.post("http://localhost:8080/authenticate",request,
    {responseType: 'text' as 'json'});

  }

  public login(username : string, password: string){
    let authRequest: any = {
      "userName": username,
      "password": password
  };
    return this.http.post("http://localhost:8080/authenticate",authRequest,
    {responseType: 'text' as 'json'});
  }

  public getPlanets(token){
    let username="user1";
    let password="password1"; 
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.get("http://localhost:8080/planet/all", {headers, responseType: 'text' as 'json'})
  }

  public getAllPlanets() : Observable<Planet[]>{
    let token = localStorage.getItem("jwt_token")
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.get<Planet[]>("http://localhost:8080/planet/all", {headers});

  }

  public getAllCrews() : Observable<Crew[]>{
    let token = localStorage.getItem("jwt_token")
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.get<Crew[]>("http://localhost:8080/crew/all", {headers});

  }

  public addPlanet(planet: Planet) : Observable<Planet>{
    let token = localStorage.getItem("jwt_token")
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.post<Planet>("http://localhost:8080/planet/add", planet, {headers});

  }

  public updatePlanet(planet: Planet) : Observable<Planet>{
    let token = localStorage.getItem("jwt_token")
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.put<Planet>("http://localhost:8080/planet/update",planet, {headers});

  }

  public deletePlanet(planetID: number) : Observable<void>{
    let token = localStorage.getItem("jwt_token")
    let tokenStr = 'Bearer '+token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.delete<void>(`http://localhost:8080/planet/delete/${planetID}`, {headers});

  }
}
