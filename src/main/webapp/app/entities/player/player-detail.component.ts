import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Player } from './player.model';
import { PlayerService } from './player.service';

@Component({
    selector: 'jhi-player-detail',
    templateUrl: './player-detail.component.html'
})
export class PlayerDetailComponent implements OnInit, OnDestroy {

    player: Player;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private playerService: PlayerService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['player']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.playerService.find(id).subscribe(player => {
            this.player = player;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
