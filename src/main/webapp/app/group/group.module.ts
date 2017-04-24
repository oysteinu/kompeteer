import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KompeteerSharedModule } from '../shared';

import { GROUP_ROUTE, GroupComponent } from './';


@NgModule({
    imports: [
        KompeteerSharedModule,
        RouterModule.forRoot([ GROUP_ROUTE ], { useHash: true })
    ],
    declarations: [
        GroupComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KompeteerGroupModule {}
