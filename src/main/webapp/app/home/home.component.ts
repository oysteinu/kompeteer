import { Component, OnInit } from '@angular/core';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

interface PlayerQuery {
  firstName: string;
}

interface MeQuery {
  me: PlayerQuery;
}

const PlayerForCurrentUser = gql`
  query PlayerForCurrentUser {
    me {
      firstName
    }
  }
`;

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    name: String;
    modalRef: NgbModalRef;
    player: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: EventManager,
        private apollo: Apollo
    ) {
        this.jhiLanguageService.setLocations(['home']);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;            
        });
        this.registerAuthenticationSuccess();

        if (this.isAuthenticated()) {
            this.player = this.apollo.watchQuery<MeQuery>({ query: PlayerForCurrentUser }).subscribe(({data}) => {
                this.player = data.me;
            });
        }
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
