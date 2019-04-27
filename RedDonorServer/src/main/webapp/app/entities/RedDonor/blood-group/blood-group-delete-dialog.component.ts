import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';
import { BloodGroupService } from './blood-group.service';

@Component({
  selector: 'jhi-blood-group-delete-dialog',
  templateUrl: './blood-group-delete-dialog.component.html'
})
export class BloodGroupDeleteDialogComponent {
  bloodGroup: IBloodGroup;

  constructor(private bloodGroupService: BloodGroupService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bloodGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bloodGroupListModification',
        content: 'Deleted an bloodGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blood-group-delete-popup',
  template: ''
})
export class BloodGroupDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bloodGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BloodGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.bloodGroup = bloodGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
