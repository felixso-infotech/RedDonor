import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';

@Component({
  selector: 'jhi-blood-group-detail',
  templateUrl: './blood-group-detail.component.html'
})
export class BloodGroupDetailComponent implements OnInit {
  bloodGroup: IBloodGroup;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bloodGroup }) => {
      this.bloodGroup = bloodGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
