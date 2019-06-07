import { TestBed } from '@angular/core/testing';

import { KullaniciService } from './kullanici.service';

describe('KullaniciService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KullaniciService = TestBed.get(KullaniciService);
    expect(service).toBeTruthy();
  });
});
