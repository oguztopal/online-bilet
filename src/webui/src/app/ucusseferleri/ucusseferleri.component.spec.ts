import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcusseferleriComponent } from './ucusseferleri.component';

describe('UcusseferleriComponent', () => {
  let component: UcusseferleriComponent;
  let fixture: ComponentFixture<UcusseferleriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcusseferleriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcusseferleriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
