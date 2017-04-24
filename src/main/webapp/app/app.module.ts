import './vendor.ts';

import { provideApolloClient } from './blocks/apollo-client.provider';
import { ApolloModule } from 'apollo-angular';

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { LocalStorageService, SessionStorageService, Ng2Webstorage } from 'ng2-webstorage';

import { KompeteerSharedModule, UserRouteAccessService } from './shared';
import { KompeteerHomeModule } from './home/home.module';
import { KompeteerGroupModule } from './group/group.module';
import { KompeteerAdminModule } from './admin/admin.module';
import { KompeteerAccountModule } from './account/account.module';
import { KompeteerEntityModule } from './entities/entity.module';

import { LayoutRoutingModule } from './layouts';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        ApolloModule.forRoot(provideApolloClient),
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        KompeteerSharedModule,
        KompeteerHomeModule,
        KompeteerGroupModule,
        KompeteerAdminModule,
        KompeteerAccountModule,
        KompeteerEntityModule
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        { provide: Window, useValue: window },
        { provide: Document, useValue: document },
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class KompeteerAppModule {}
