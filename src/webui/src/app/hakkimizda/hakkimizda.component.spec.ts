import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HakkimizdaComponent } from './hakkimizda.component';

describe('HakkimizdaComponent', () => {
  let component: HakkimizdaComponent;
  let fixture: ComponentFixture<HakkimizdaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HakkimizdaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HakkimizdaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
