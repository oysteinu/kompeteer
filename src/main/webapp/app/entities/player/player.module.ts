import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KompeteerSharedModule } from '../../shared';
import { KompeteerAdminModule } from '../../admin/admin.module';

import {
    PlayerService,
    PlayerPopupService,
    PlayerComponent,
    PlayerDetailComponent,
    PlayerDialogComponent,
    PlayerPopupComponent,
    PlayerDeletePopupComponent,
    PlayerDeleteDialogComponent,
    playerRoute,
    playerPopupRoute,
} from './';

let ENTITY_STATES = [
    ...playerRoute,
    ...playerPopupRoute,
];

@NgModule({
    imports: [
        KompeteerSharedModule,
        KompeteerAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PlayerComponent,
        PlayerDetailComponent,
        PlayerDialogComponent,
        PlayerDeleteDialogComponent,
        PlayerPopupComponent,
        PlayerDeletePopupComponent,
    ],
    entryComponents: [
        PlayerComponent,
        PlayerDialogComponent,
        PlayerPopupComponent,
        PlayerDeleteDialogComponent,
        PlayerDeletePopupComponent,
    ],
    providers: [
        PlayerService,
        PlayerPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KompeteerPlayerModule {}
