import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Groups } from './groups.model';
import { GroupsPopupService } from './groups-popup.service';
import { GroupsService } from './groups.service';

@Component({
    selector: 'jhi-groups-delete-dialog',
    templateUrl: './groups-delete-dialog.component.html'
})
export class GroupsDeleteDialogComponent {

    groups: Groups;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private groupsService: GroupsService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['groups']);
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.groupsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'groupsListModification',
                content: 'Deleted an groups'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-groups-delete-popup',
    template: ''
})
export class GroupsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private groupsPopupService: GroupsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.groupsPopupService
                .open(GroupsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
