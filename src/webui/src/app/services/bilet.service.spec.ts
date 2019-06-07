import { TestBed } from '@angular/core/testing';

import { BiletService } from './bilet.service';

describe('BiletService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BiletService = TestBed.get(BiletService);
    expect(service).toBeTruthy();
  });
});
