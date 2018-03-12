import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbursementsAllComponent } from './reimbursements-all.component';

describe('ReimbursementsAllComponent', () => {
  let component: ReimbursementsAllComponent;
  let fixture: ComponentFixture<ReimbursementsAllComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReimbursementsAllComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbursementsAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
