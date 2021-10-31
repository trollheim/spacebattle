import { RegistrationComponent } from './registration/registration.component';
import { EditorComponent } from './appeditor/editor.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotReadyComponent } from './not-ready/not-ready.component';
 
 
const routes: Routes = [
  { path: '', redirectTo: '/code', pathMatch: 'full' },
  { path: 'code', component: EditorComponent },
  { path: 'notready', component: NotReadyComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
