import { TestBed } from '@angular/core/testing';

import { AppRestServiceService } from './app-rest-service.service';

describe('AppRestServiceService', () => {
  let service: AppRestServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppRestServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
