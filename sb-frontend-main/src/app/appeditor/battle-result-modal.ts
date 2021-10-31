import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: 'battle-result-modal.html'
})
export class NgbdModalContent {
  @Input() title;
  @Input() pages;

  constructor(public activeModal: NgbActiveModal) {

  }
}

 