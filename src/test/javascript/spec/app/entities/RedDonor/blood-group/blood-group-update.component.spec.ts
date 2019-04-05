/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { RedDonorTestModule } from '../../../../test.module';
import { BloodGroupUpdateComponent } from 'app/entities/RedDonor/blood-group/blood-group-update.component';
import { BloodGroupService } from 'app/entities/RedDonor/blood-group/blood-group.service';
import { BloodGroup } from 'app/shared/model/RedDonor/blood-group.model';

describe('Component Tests', () => {
  describe('BloodGroup Management Update Component', () => {
    let comp: BloodGroupUpdateComponent;
    let fixture: ComponentFixture<BloodGroupUpdateComponent>;
    let service: BloodGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RedDonorTestModule],
        declarations: [BloodGroupUpdateComponent]
      })
        .overrideTemplate(BloodGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BloodGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BloodGroupService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new BloodGroup(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.bloodGroup = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.update).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );

      it(
        'Should call create service on save for new entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new BloodGroup();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.bloodGroup = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.create).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );
    });
  });
});
