import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';
import { BloodGroupService } from './blood-group.service';

@Component({
  selector: 'jhi-blood-group-update',
  templateUrl: './blood-group-update.component.html'
})
export class BloodGroupUpdateComponent implements OnInit {
  bloodGroup: IBloodGroup;
  isSaving: boolean;

  constructor(private bloodGroupService: BloodGroupService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bloodGroup }) => {
      this.bloodGroup = bloodGroup;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.bloodGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.bloodGroupService.update(this.bloodGroup));
    } else {
      this.subscribeToSaveResponse(this.bloodGroupService.create(this.bloodGroup));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IBloodGroup>>) {
    result.subscribe((res: HttpResponse<IBloodGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}
