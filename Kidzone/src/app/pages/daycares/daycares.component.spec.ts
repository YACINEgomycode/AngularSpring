import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DaycaresComponent } from './daycares.component';

describe('DaycaresComponent', () => {
  let component: DaycaresComponent;
  let fixture: ComponentFixture<DaycaresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DaycaresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DaycaresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
