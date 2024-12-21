import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
 
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { PostregistrationComponent } from './postregistration/postregistration.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    PostregistrationComponent,
    
  ],
  imports: [
    BrowserModule,
    
    NgbModule,
    FormsModule,
    HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
