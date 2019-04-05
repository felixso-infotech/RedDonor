/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RedDonorTestModule } from '../../../../test.module';
import { BloodGroupDetailComponent } from 'app/entities/RedDonor/blood-group/blood-group-detail.component';
import { BloodGroup } from 'app/shared/model/RedDonor/blood-group.model';

describe('Component Tests', () => {
  describe('BloodGroup Management Detail Component', () => {
    let comp: BloodGroupDetailComponent;
    let fixture: ComponentFixture<BloodGroupDetailComponent>;
    const route = ({ data: of({ bloodGroup: new BloodGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RedDonorTestModule],
        declarations: [BloodGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BloodGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BloodGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bloodGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
