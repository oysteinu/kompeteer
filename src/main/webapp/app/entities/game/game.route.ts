import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { GameComponent } from './game.component';
import { GameDetailComponent } from './game-detail.component';
import { GamePopupComponent } from './game-dialog.component';
import { GameDeletePopupComponent } from './game-delete-dialog.component';

import { Principal } from '../../shared';


export const gameRoute: Routes = [
  {
    path: 'game',
    component: GameComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.game.home.title'
    }
  }, {
    path: 'game/:id',
    component: GameDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.game.home.title'
    }
  }
];

export const gamePopupRoute: Routes = [
  {
    path: 'game-new',
    component: GamePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.game.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'game/:id/edit',
    component: GamePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.game.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'game/:id/delete',
    component: GameDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.game.home.title'
    },
    outlet: 'popup'
  }
];
