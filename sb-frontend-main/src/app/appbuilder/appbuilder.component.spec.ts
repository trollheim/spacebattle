import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppbuilderComponent } from './appbuilder.component';

describe('AppbuilderComponent', () => {
  let component: AppbuilderComponent;
  let fixture: ComponentFixture<AppbuilderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppbuilderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppbuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
