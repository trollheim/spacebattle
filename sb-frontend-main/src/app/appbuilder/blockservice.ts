import { Injectable } from '@angular/core';
import * as Blockly from 'blockly';

@Injectable({
  providedIn: 'root',
})
export class ToolboxService {
    toolbox: string = `
    <xml id="toolbox" style="display: none">
 
    <category name="Ship" colour="red">
      <block type="scannerreadings"></block>
      <block type="readObjectValue"></block>
      <block type="distribute_engine_power"></block>
      <block type="position"></block>

      <block type="position"></block>
   
    </category>
    <category name="Logic" colour="210">
      <block type="controls_if"></block>
      <block type="logic_compare"></block>
      <block type="logic_operation"></block>
      <block type="logic_negate"></block>
      <block type="logic_boolean"></block>
      <block type="logic_null"></block>
      <block type="logic_ternary"></block>
    </category>
    <category name="Loop" colour="120">
      <block type="controls_repeat_ext">
        <value name="TIMES">
          <shadow type="math_number">
            <field name="NUM">10</field>
          </shadow>
        </value>
      </block>
      <block type="controls_whileUntil"></block>
      <block type="controls_for">
        <value name="FROM">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
        <value name="TO">
          <shadow type="math_number">
            <field name="NUM">10</field>
          </shadow>
        </value>
        <value name="BY">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
      </block>
      <block type="controls_forEach"></block>
      <block type="controls_flow_statements"></block>
    </category>
    <category name="Math" colour="230">
      <block type="math_number"></block>
      <block type="math_arithmetic">
        <value name="A">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
        <value name="B">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
      </block>
      <block type="math_single">
        <value name="NUM">
          <shadow type="math_number">
            <field name="NUM">9</field>
          </shadow>
        </value>
      </block>
      <block type="math_trig">
        <value name="NUM">
          <shadow type="math_number">
            <field name="NUM">45</field>
          </shadow>
        </value>
      </block>
      <block type="math_constant"></block>
      <block type="math_number_property">
        <value name="NUMBER_TO_CHECK">
          <shadow type="math_number">
            <field name="NUM">0</field>
          </shadow>
        </value>
      </block>
      <block type="math_round">
        <value name="NUM">
          <shadow type="math_number">
            <field name="NUM">3.1</field>
          </shadow>
        </value>
      </block>
      <block type="math_on_list"></block>
      <block type="math_modulo">
        <value name="DIVIDEND">
          <shadow type="math_number">
            <field name="NUM">64</field>
          </shadow>
        </value>
        <value name="DIVISOR">
          <shadow type="math_number">
            <field name="NUM">10</field>
          </shadow>
        </value>
      </block>
      <block type="math_constrain">
        <value name="VALUE">
          <shadow type="math_number">
            <field name="NUM">50</field>
          </shadow>
        </value>
        <value name="LOW">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
        <value name="HIGH">
          <shadow type="math_number">
            <field name="NUM">100</field>
          </shadow>
        </value>
      </block>
      <block type="math_random_int">
        <value name="FROM">
          <shadow type="math_number">
            <field name="NUM">1</field>
          </shadow>
        </value>
        <value name="TO">
          <shadow type="math_number">
            <field name="NUM">100</field>
          </shadow>
        </value>
      </block>
      <block type="math_random_float"></block>
    </category>
    <category name="Text" colour="160">
      <block type="text"></block>
      <block type="text_join"></block>
      <block type="text_append">
        <value name="TEXT">
          <shadow type="text"></shadow>
        </value>
      </block>
      <block type="text_length">
        <value name="VALUE">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
      <block type="text_isEmpty">
        <value name="VALUE">
          <shadow type="text">
            <field name="TEXT"></field>
          </shadow>
        </value>
      </block>
      <block type="text_indexOf">
        <value name="VALUE">
          <block type="variables_get">
            <field name="VAR">{textVariable}</field>
          </block>
        </value>
        <value name="FIND">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
      <block type="text_charAt">
        <value name="VALUE">
          <block type="variables_get">
            <field name="VAR">{textVariable}</field>
          </block>
        </value>
      </block>
      <block type="text_getSubstring">
        <value name="STRING">
          <block type="variables_get">
            <field name="VAR">{textVariable}</field>
          </block>
        </value>
      </block>
      <block type="text_changeCase">
        <value name="TEXT">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
      <block type="text_trim">
        <value name="TEXT">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
      <block type="text_print">
        <value name="TEXT">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
      <block type="text_prompt_ext">
        <value name="TEXT">
          <shadow type="text">
            <field name="TEXT">abc</field>
          </shadow>
        </value>
      </block>
    </category>
    <category name="{catLists}" colour="260">
      <block type="lists_create_with">
        <mutation items="0"></mutation>
      </block>
      <block type="lists_create_with"></block>
      <block type="lists_repeat">
        <value name="NUM">
          <shadow type="math_number">
            <field name="NUM">5</field>
          </shadow>
        </value>
      </block>
      <block type="lists_length"></block>
      <block type="lists_isEmpty"></block>
      <block type="lists_indexOf">
        <value name="VALUE">
          <block type="variables_get">
            <field name="VAR">{listVariable}</field>
          </block>
        </value>
      </block>
      <block type="lists_getIndex">
        <value name="VALUE">
          <block type="variables_get">
            <field name="VAR">{listVariable}</field>
          </block>
        </value>
      </block>
      <block type="lists_setIndex">
        <value name="LIST">
          <block type="variables_get">
            <field name="VAR">{listVariable}</field>
          </block>
        </value>
      </block>
      <block type="lists_getSublist">
        <value name="LIST">
          <block type="variables_get">
            <field name="VAR">{listVariable}</field>
          </block>
        </value>
      </block>
      <block type="lists_split">
        <value name="DELIM">
          <shadow type="text">
            <field name="TEXT">,</field>
          </shadow>
        </value>
      </block>
      <block type="lists_sort"></block>
    </category>
    <category name="{catColour}" colour="20">
      <block type="colour_picker"></block>
      <block type="colour_random"></block>
      <block type="colour_rgb">
        <value name="RED">
          <shadow type="math_number">
            <field name="NUM">100</field>
          </shadow>
        </value>
        <value name="GREEN">
          <shadow type="math_number">
            <field name="NUM">50</field>
          </shadow>
        </value>
        <value name="BLUE">
          <shadow type="math_number">
            <field name="NUM">0</field>
          </shadow>
        </value>
      </block>
      <block type="colour_blend">
        <value name="COLOUR1">
          <shadow type="colour_picker">
            <field name="COLOUR">#ff0000</field>
          </shadow>
        </value>
        <value name="COLOUR2">
          <shadow type="colour_picker">
            <field name="COLOUR">#3333ff</field>
          </shadow>
        </value>
        <value name="RATIO">
          <shadow type="math_number">
            <field name="NUM">0.5</field>
          </shadow>
        </value>
      </block>
    </category>
    <sep></sep>
    <category name="{catVariables}" colour="330" custom="VARIABLE"></category>
    <category name="{catFunctions}" colour="290" custom="PROCEDURE"></category>
  </xml>
    `;

    constructor() {

    var BlocklyJS = (Blockly as any).JavaScript;

    //add additional behaviour
    Blockly .Blocks['scannerreadings'] = {
      init: function() {
        this.jsonInit({
          "type": "scannerresult",
          "message0": "get Scanner Result",
          "inputsInline": true,
          "output": "Array",
          "colour": "red",
          "tooltip": "Returns nearby objects",
          "helpUrl": ""
        });

      }
    };
    
    BlocklyJS['scannerreadings'] = function(block) {
      // TODO: Assemble JavaScript into code variable.
      var code = 'scannerResult';
   
      return [code, BlocklyJS.ORDER_NONE];
    };
 
 
 
      Blockly.Blocks['readObjectValue'] = {
        init: function() {
          
          this.appendValueInput("ITEM")
              .appendField('value of object')
              .setCheck(null);
          this.appendDummyInput()
              .appendField(new (Blockly as any).FieldDropdown([["Position X","x"], ["Position Y","y"], ["Team","team"]]), "FIELD");
          this.setOutput(true, null);
          this.setColour("red");
       this.setTooltip("");
       this.setHelpUrl("");
        }
      };


      BlocklyJS['readObjectValue'] = function(block) {
      var item_name = BlocklyJS.valueToCode(block, 'ITEM', BlocklyJS.ORDER_ATOMIC);
      var dropdown_name = block.getFieldValue('FIELD');
      
      var code = item_name+'['+dropdown_name+']';
      return [code, BlocklyJS.ORDER_NONE];
    };
    //
    Blockly.Blocks['position'] = {
      init: function() {
        this.appendDummyInput()
             .appendField("Position");
        this.appendValueInput("X")
            .setCheck(null)
            .appendField("X");
        this.appendValueInput("Y")
            .setCheck(null)
            .appendField("Y");
        this.setInputsInline(true);
        this.setOutput(true, null);
        this.setColour(230);
     this.setTooltip("");
     this.setHelpUrl("");
      }
    };
    ///

    
 
    Blockly.Blocks['readPositionValue'] = {
      init: function() {
        
        this.appendValueInput("ITEM")
            .appendField('value of object')
            .setCheck(null);
        this.appendDummyInput()
            .appendField(new (Blockly as any).FieldDropdown([["Position X","x"], ["Position Y","y"]]), "FIELD");
        this.setOutput(true, null);
        this.setColour("red");
     this.setTooltip("");
     this.setHelpUrl("");
      }
    };


    BlocklyJS['readPositionValue'] = function(block) {
    var item_name = BlocklyJS.valueToCode(block, 'ITEM', BlocklyJS.ORDER_ATOMIC);
    var dropdown_name = block.getFieldValue('FIELD');
    
    var code = item_name+'['+dropdown_name+']';
    return [code, BlocklyJS.ORDER_NONE];
  };
  //
  Blockly.Blocks['position'] = {
    init: function() {
      this.appendDummyInput()
           .appendField("Position");
      this.appendValueInput("X")
          .setCheck(null)
          .appendField("X");
      this.appendValueInput("Y")
          .setCheck(null)
          .appendField("Y");
      this.setInputsInline(true);
      this.setOutput(true, null);
      this.setColour(230);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };

    ///

    BlocklyJS['position'] = function(block) {
      var value_x = BlocklyJS.valueToCode(block, 'X', BlocklyJS.ORDER_ATOMIC);
      var value_y = BlocklyJS.valueToCode(block, 'Y', BlocklyJS.ORDER_ATOMIC);
      var code = JSON.stringify({"x" : value_x, "y" : value_y});
      
      return [code, BlocklyJS.ORDER_NONE];
    };

    //
    Blockly.Blocks['distribute_engine_power'] = {
      init: function() {
        this.appendValueInput("Engines")
            .setCheck(null)
            .appendField("Set Engine Power");
        this.appendValueInput("Shields")
            .setCheck(null)
            .appendField("Set Shield Power");
        this.appendValueInput("Lasers")
            .setCheck(null)
            .appendField("Set Laser Power");
        this.appendValueInput("Direction")
            .setCheck(null)
            .appendField("Set Direction");
        this.appendValueInput("Target")
            .setCheck(null)
            .appendField("Set Target");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
     this.setTooltip("");
     this.setHelpUrl("");
      }
    };
  
    BlocklyJS['distribute_engine_power'] = function(block) {
      var value_engines = BlocklyJS.valueToCode(block, 'Engines', BlocklyJS.ORDER_ATOMIC);
      var value_shields = BlocklyJS.valueToCode(block, 'Shields', BlocklyJS.ORDER_ATOMIC);
      var value_lasers = BlocklyJS.valueToCode(block, 'Lasers', BlocklyJS.ORDER_ATOMIC);
      var value_direction = BlocklyJS.valueToCode(block, 'Direction', BlocklyJS.ORDER_ATOMIC);
      var value_target = BlocklyJS.valueToCode(block, 'Target', BlocklyJS.ORDER_ATOMIC);
      // TODO: Assemble JavaScript into code variable.
      var code = "{ 'engine' : '"+value_engines+"', 'shields' : '0'}";//TODO
      return code;
    };
        
    // Start blocl
    
    Blockly .Blocks['init'] = {
      init: function() {
        this.jsonInit({
              "type": "init",
              "message0": "%1",
              "args0": [
                  {
                    "type": "init_input",
                    "name": "start function"
                  }
               ],
              "inputsInline": true,
                "colour": 120,
              "tooltip": "",
              "helpUrl": ""
            });
          }
        };
        
        BlocklyJS['start'] = function(block) {
          var content = BlocklyJS.statementToCode(block, 'init_input');
          // TODO: Assemble JavaScript into code variable.
          var code = 'var roundFunction = function(scannerResult){\n'+content+'\n}';
          return code;
        };  
  
    }

  getToolbar() {
 
    return this.toolbox
  }
}