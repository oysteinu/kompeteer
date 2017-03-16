import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Groups } from './groups.model';
import { GroupsPopupService } from './groups-popup.service';
import { GroupsService } from './groups.service';
import { Player, PlayerService } from '../player';
@Component({
    selector: 'jhi-groups-dialog',
    templateUrl: './groups-dialog.component.html'
})
export class GroupsDialogComponent implements OnInit {

    groups: Groups;
    authorities: any[];
    isSaving: boolean;

    players: Player[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private groupsService: GroupsService,
        private playerService: PlayerService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['groups']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.playerService.query().subscribe(
            (res: Response) => { this.players = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.groups.id !== undefined) {
            this.groupsService.update(this.groups)
                .subscribe((res: Groups) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.groupsService.create(this.groups)
                .subscribe((res: Groups) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Groups) {
        this.eventManager.broadcast({ name: 'groupsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }

    trackPlayerById(index: number, item: Player) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-groups-popup',
    template: ''
})
export class GroupsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private groupsPopupService: GroupsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.groupsPopupService
                    .open(GroupsDialogComponent, params['id']);
            } else {
                this.modalRef = this.groupsPopupService
                    .open(GroupsDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
