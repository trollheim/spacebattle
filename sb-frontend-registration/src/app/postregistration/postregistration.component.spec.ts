import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostregistrationComponent } from './postregistration.component';

describe('PostregistrationComponent', () => {
  let component: PostregistrationComponent;
  let fixture: ComponentFixture<PostregistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostregistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostregistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
