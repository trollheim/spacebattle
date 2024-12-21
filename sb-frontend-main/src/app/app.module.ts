import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppbuilderComponent } from './appbuilder/appbuilder.component';
 import { EditorComponent } from './appeditor/editor.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule,HttpClientXsrfModule } from '@angular/common/http';
import { NgbdModalContent } from './appeditor/battle-result-modal'

 import { FormsModule } from '@angular/forms'; 
 
 import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { MonacoEditorModule } from 'ngx-monaco-editor';
import { RegistrationComponent } from './registration/registration.component';
@NgModule({
  declarations: [
    AppComponent,
    AppbuilderComponent,
    EditorComponent,
    NgbdModalContent,
    RegistrationComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-CSRF-TOKEN'
    }),
    MonacoEditorModule.forRoot()
  ],
 // providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
