import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationApiService {

  constructor(private http: HttpClient) { }

  async  createUser(registrationDto: any){
    return await this.http.post("/userAPI/register", registrationDto).toPromise();
   }
}
