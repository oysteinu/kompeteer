import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

interface GroupQuery {
    group: any;
}

const GroupById = gql`
  query GroupById($groupId: Long!) {
    group (id: $groupId) {
      id
      name
    }
  }
`;

@Component({
    selector: 'jhi-group',
    templateUrl: './group.component.html',
    styleUrls: [
        'group.scss'
    ]

})
export class GroupComponent implements OnInit {
    private group: any;
    private subscription: any;

    constructor(
        private route: ActivatedRoute,
        private jhiLanguageService: JhiLanguageService,       
        private eventManager: EventManager,
        private apollo: Apollo
    ) {
        this.jhiLanguageService.setLocations(['home']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(+params['id']);
        });
    }

    load(groupId: number) {
        this.group = this.apollo.watchQuery<GroupQuery>(
            { 
                query: GroupById,
                variables: {
                    "groupId": groupId
                }
            }).subscribe(({data}) => {
                this.group = data.group;
            });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }
}
