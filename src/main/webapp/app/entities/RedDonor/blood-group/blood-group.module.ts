import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RedDonorSharedModule } from 'app/shared';
import {
  BloodGroupComponent,
  BloodGroupDetailComponent,
  BloodGroupUpdateComponent,
  BloodGroupDeletePopupComponent,
  BloodGroupDeleteDialogComponent,
  bloodGroupRoute,
  bloodGroupPopupRoute
} from './';

const ENTITY_STATES = [...bloodGroupRoute, ...bloodGroupPopupRoute];

@NgModule({
  imports: [RedDonorSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BloodGroupComponent,
    BloodGroupDetailComponent,
    BloodGroupUpdateComponent,
    BloodGroupDeleteDialogComponent,
    BloodGroupDeletePopupComponent
  ],
  entryComponents: [BloodGroupComponent, BloodGroupUpdateComponent, BloodGroupDeleteDialogComponent, BloodGroupDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RedDonorBloodGroupModule {}
