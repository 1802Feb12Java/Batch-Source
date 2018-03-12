import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyBindingComponentComponent } from './property-binding-component.component';

describe('PropertyBindingComponentComponent', () => {
  let component: PropertyBindingComponentComponent;
  let fixture: ComponentFixture<PropertyBindingComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyBindingComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyBindingComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
