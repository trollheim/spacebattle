import { Injectable } from '@angular/core';
 import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppRestServiceService {

  constructor(private http: HttpClient) { }
  


  async  saveSketch(sketch: string){
    return await this.http.post("sketchAPI/save", {"payload" :sketch}).toPromise();
   }
  

  async test(){
    return await this.http.get<string>("sketchAPI/test").toPromise();
  }

  async  loadSketch(){
    return await this.http.get<string>("sketchAPI/load").toPromise();
  }

  async  loadHelp(){
    return await this.http.get<String>("assets/help.json").toPromise();
  }


}
