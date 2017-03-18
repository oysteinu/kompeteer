import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Tournament } from './tournament.model';
import { TournamentPopupService } from './tournament-popup.service';
import { TournamentService } from './tournament.service';
import { Game, GameService } from '../game';
import { Player, PlayerService } from '../player';
@Component({
    selector: 'jhi-tournament-dialog',
    templateUrl: './tournament-dialog.component.html'
})
export class TournamentDialogComponent implements OnInit {

    tournament: Tournament;
    authorities: any[];
    isSaving: boolean;

    games: Game[];

    players: Player[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private tournamentService: TournamentService,
        private gameService: GameService,
        private playerService: PlayerService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['tournament']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.gameService.query().subscribe(
            (res: Response) => { this.games = res.json(); }, (res: Response) => this.onError(res.json()));
        this.playerService.query().subscribe(
            (res: Response) => { this.players = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.tournament.id !== undefined) {
            this.tournamentService.update(this.tournament)
                .subscribe((res: Tournament) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.tournamentService.create(this.tournament)
                .subscribe((res: Tournament) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Tournament) {
        this.eventManager.broadcast({ name: 'tournamentListModification', content: 'OK'});
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

    trackGameById(index: number, item: Game) {
        return item.id;
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
    selector: 'jhi-tournament-popup',
    template: ''
})
export class TournamentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private tournamentPopupService: TournamentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.tournamentPopupService
                    .open(TournamentDialogComponent, params['id']);
            } else {
                this.modalRef = this.tournamentPopupService
                    .open(TournamentDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
