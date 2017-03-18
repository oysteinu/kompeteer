import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Tournament } from './tournament.model';
import { TournamentPopupService } from './tournament-popup.service';
import { TournamentService } from './tournament.service';

@Component({
    selector: 'jhi-tournament-delete-dialog',
    templateUrl: './tournament-delete-dialog.component.html'
})
export class TournamentDeleteDialogComponent {

    tournament: Tournament;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private tournamentService: TournamentService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['tournament']);
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.tournamentService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tournamentListModification',
                content: 'Deleted an tournament'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tournament-delete-popup',
    template: ''
})
export class TournamentDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private tournamentPopupService: TournamentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.tournamentPopupService
                .open(TournamentDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
