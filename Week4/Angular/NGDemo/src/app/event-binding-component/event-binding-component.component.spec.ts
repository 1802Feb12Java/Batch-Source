import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventBindingComponentComponent } from './event-binding-component.component';

describe('EventBindingComponentComponent', () => {
  let component: EventBindingComponentComponent;
  let fixture: ComponentFixture<EventBindingComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventBindingComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventBindingComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
