import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Tournament } from './tournament.model';
import { TournamentService } from './tournament.service';

@Component({
    selector: 'jhi-tournament-detail',
    templateUrl: './tournament-detail.component.html'
})
export class TournamentDetailComponent implements OnInit, OnDestroy {

    tournament: Tournament;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private tournamentService: TournamentService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['tournament']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.tournamentService.find(id).subscribe(tournament => {
            this.tournament = tournament;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
