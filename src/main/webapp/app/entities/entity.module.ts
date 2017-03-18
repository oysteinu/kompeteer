import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { KompeteerGameModule } from './game/game.module';
import { KompeteerGroupsModule } from './groups/groups.module';
import { KompeteerPlayerModule } from './player/player.module';
import { KompeteerTournamentModule } from './tournament/tournament.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        KompeteerGameModule,
        KompeteerGroupsModule,
        KompeteerPlayerModule,
        KompeteerTournamentModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KompeteerEntityModule {}
