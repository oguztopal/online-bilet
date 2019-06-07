import { TestBed } from '@angular/core/testing';

import { HavalimanlariService } from './havalimanlari.service';

describe('HavalimanlariService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HavalimanlariService = TestBed.get(HavalimanlariService);
    expect(service).toBeTruthy();
  });
});
