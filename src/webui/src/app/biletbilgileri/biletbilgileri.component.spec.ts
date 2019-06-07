import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BiletbilgileriComponent } from './biletbilgileri.component';

describe('BiletbilgileriComponent', () => {
  let component: BiletbilgileriComponent;
  let fixture: ComponentFixture<BiletbilgileriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BiletbilgileriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BiletbilgileriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
