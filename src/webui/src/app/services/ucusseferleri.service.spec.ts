import { TestBed } from '@angular/core/testing';

import { UcusseferleriService } from './ucusseferleri.service';

describe('UcusseferleriService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UcusseferleriService = TestBed.get(UcusseferleriService);
    expect(service).toBeTruthy();
  });
});
