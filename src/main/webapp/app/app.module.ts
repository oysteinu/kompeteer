import './vendor.ts';

import { ApolloClient, createNetworkInterface  } from 'apollo-client';
import { ApolloModule } from 'apollo-angular';

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { LocalStorageService, SessionStorageService, Ng2Webstorage } from 'ng2-webstorage';

import { KompeteerSharedModule, UserRouteAccessService } from './shared';
import { KompeteerHomeModule } from './home/home.module';
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

const networkInterface = createNetworkInterface(
    {
        uri: '/api/graphql'
    }
);

networkInterface.use([{
  /**   
   * Add auth token to GraphQL requests.
   */
  applyMiddleware(req, next) {
    let token = localStorage.getItem('jhi-authenticationtoken') || sessionStorage.getItem('jhi-authenticationtoken');
    
    if (!!token) {
        // Replace double quotes
        token = token.replace(/['"]+/g, '');
        
        req.options.headers = {
            "Authorization": 'Bearer ' + token
        };
    }    

    next();
  }
}]);

const client = new ApolloClient({
    networkInterface: networkInterface
});

export function provideClient(): ApolloClient {
  return client;
}

@NgModule({
    imports: [
        BrowserModule,
        ApolloModule.forRoot(provideClient),
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        KompeteerSharedModule,
        KompeteerHomeModule,
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
