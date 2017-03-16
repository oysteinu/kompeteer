import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Groups } from './groups.model';
import { GroupsService } from './groups.service';

@Component({
    selector: 'jhi-groups-detail',
    templateUrl: './groups-detail.component.html'
})
export class GroupsDetailComponent implements OnInit, OnDestroy {

    groups: Groups;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private groupsService: GroupsService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['groups']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.groupsService.find(id).subscribe(groups => {
            this.groups = groups;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
