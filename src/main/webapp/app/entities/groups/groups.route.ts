import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { GroupsComponent } from './groups.component';
import { GroupsDetailComponent } from './groups-detail.component';
import { GroupsPopupComponent } from './groups-dialog.component';
import { GroupsDeletePopupComponent } from './groups-delete-dialog.component';

import { Principal } from '../../shared';


export const groupsRoute: Routes = [
  {
    path: 'groups',
    component: GroupsComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.groups.home.title'
    }
  }, {
    path: 'groups/:id',
    component: GroupsDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.groups.home.title'
    }
  }
];

export const groupsPopupRoute: Routes = [
  {
    path: 'groups-new',
    component: GroupsPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.groups.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'groups/:id/edit',
    component: GroupsPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.groups.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'groups/:id/delete',
    component: GroupsDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'kompeteerApp.groups.home.title'
    },
    outlet: 'popup'
  }
];
