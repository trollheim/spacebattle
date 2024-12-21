import { Component, OnInit } from '@angular/core';
import * as Blockly from 'blockly';
import { AppRestServiceService } from '../app-rest-service.service';
import { ToolboxService } from './blockservice';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-appbuilder',
  templateUrl: './appbuilder.component.html',
  styleUrls: ['./appbuilder.component.css']
})


export class AppbuilderComponent implements OnInit {
 
  
  constructor(private toolboxService: ToolboxService, private rest:AppRestServiceService,private modalService: NgbModal) {
    this.toolbox = toolboxService.getToolbar();
    this.rest=rest;
   }
  
  private toolbox:any;
  

  private BlocklyJS = (Blockly as any).JavaScript;

  ngOnInit(): void {

 
    const blocklyDiv = document.getElementById('blocklyDiv');
    this.BlocklyJS.addReservedWords('code');
    this.toolbox = Blockly.inject(blocklyDiv, {
      readOnly: false,
      media: 'media/',
      trashcan: true,
      move: {
        scrollbars: false,
        drag: true,
        wheel: true
      },
      toolbox: this.toolbox
    } as Blockly.BlocklyOptions);
  }


  save(){
    var xml = Blockly.Xml.workspaceToDom(this.toolbox);
    var code = Blockly.Xml.domToText(xml);
    this.rest.saveSketch(code)
  }

  load(){
    // console.log("a")
    // Blockly.mainWorkspace.clear();
    // console.log("v")
    //  var xml_text = this.rest.loadSketch();
    // console.log(xml_text)
    // var xml = Blockly.Xml.textToDom(xml_text );
    // console.log(xml)

    // Blockly.Xml.domToWorkspace(xml, this.toolbox);
  }

  test(){
    console.log("code")
    console.log(this.BlocklyJS.workspaceToCode(this.toolbox));
    console.log("code");
  alert ("not implemented yet")
  }

  showCode(){
    console.log(1);
    var xml = Blockly.Xml.workspaceToDom(this.toolbox);
    var code = Blockly.Xml.domToText(xml);
    console.log(2);
    //var code = Blockly.Xml.domToText(this.toolbox);
    console.log(code);
    //var code = this.BlocklyJS.workspaceToCode(this.toolbox);
    alert(code)

    /*
    var xml = Blockly.Xml.textToDom(xml_text);
Blockly.Xml.domToWorkspace(xml, workspace);
    */
  }

}
