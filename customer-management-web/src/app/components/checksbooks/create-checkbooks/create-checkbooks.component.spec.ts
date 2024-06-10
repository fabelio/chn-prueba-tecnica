import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCheckbooksComponent } from './create-checkbooks.component';

describe('CreateCheckbooksComponent', () => {
  let component: CreateCheckbooksComponent;
  let fixture: ComponentFixture<CreateCheckbooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCheckbooksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCheckbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
