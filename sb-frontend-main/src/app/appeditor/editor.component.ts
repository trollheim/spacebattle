import { Component, OnInit } from '@angular/core';
import { AppRestServiceService } from '../app-rest-service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbdModalContent } from './battle-result-modal';
import * as helpData from './help.json';
 
@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent {

  private initialCode: string = 'var execRound = function (readings) {\n'+
                                '\n    //Put your code here... \n\n'+
                                '    return {\n'+
                                '        "engines" : { "power": 0, "direction": {"x": 1.0, "y":1.0 } },\n'+
                                '        "weapons" : { "power": 0, "direction": {"x": 1.0, "y":1.0 } },\n'+
                                '        "shields" : { "power": 0 },\n'+
                                '        "scanners": { "power": 0 },\n'+
                                '        "repair"  : { "power": 0 },\n'+
                                '    }\n}';

  constructor(private rest: AppRestServiceService, private modalService: NgbModal) {

  }

  editorOptions = { theme: 'vs-dark', language: 'javascript', minHeight: 1600 };
  code: string = this.initialCode;

  clear() {
    this.code = this.initialCode;
  }



  save() {
    var self = this;
    this.rest.saveSketch(this.code).then(a => alert("saved")).catch(err => self.showError(err))

  }

  load() {

    var self = this;
    this.rest.loadSketch().then(function (a) { console.log("ok"); console.log(a); console.log(a["result"]); self.code = a["result"]; }).catch(err => self.showError(err))

  }

  test() {
    var self = this;
    this.rest.test().then(result => self.showReport(result["result"])).catch(err => self.showError(err))
  }

  showReport(report: any) {
    console.log(JSON.stringify(report))

    const modalRef = this.modalService.open(NgbdModalContent, { size: 'xl' });
    modalRef.componentInstance.title = "Batte Report"
    modalRef.componentInstance.pages = report;
  }


  showHelp() {
    var self = this;
 
      console.log(helpData)

      const modalRef = this.modalService.open(NgbdModalContent,{size : 'xl'});
      modalRef.componentInstance.title = "Batte Report"
      modalRef.componentInstance.pages = helpData["default"];
   

  }

  showError(error) {
    alert(error["message"])

  }
}
