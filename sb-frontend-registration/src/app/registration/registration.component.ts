import { ReturnStatement } from '@angular/compiler';
import { Component, OnInit, Input } from '@angular/core';
import {RegistrationApiService} from '../registration-api.service'

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private rest:RegistrationApiService) { }

  errors = []

  request = {
    username : "",
    password : "",
    verify : "",
    inviteCode :""
  }

  createUser(){
    
    if (this.request.username.length === 0){
      this.errors.push("Username not populated")
    }
    if (this.request.password.length === 0){
      this.errors.push("Password not populated")
    }
    if (this.request.verify.length === 0){
      this.errors.push("Password verify not populated")
    }
    if (this.request.inviteCode.length === 0){
      this.errors.push("Invite Code not populated")
    }
    if (this.request.password !== this.request.verify){
      this.errors.push(" Password does not match")
    }

    if (this.errors.length !== 0){
      return
    }

    var self = this;
    this.rest.createUser(this.request).then(result => self.handleResponse(result)).catch(err => self.errors = [err["message"]])
    
  }

  handleResponse(response){
    if (response["RESULT"] !== "OK"){
      this.errors = [response["MESSAGE"]];
      return;
    }
    //TODO popup

    window.location.href = "/"
  }

   

  ngOnInit(): void {
  }

}
