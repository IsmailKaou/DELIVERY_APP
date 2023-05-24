import { TestBed } from '@angular/core/testing';

import { UsreAuthService } from './usre-auth.service';

describe('UsreAuthService', () => {
  let service: UsreAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsreAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
