import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { JhiLanguageService } from 'ng-jhipster';
import { MockLanguageService } from '../../../helpers/mock-language.service';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TournamentDetailComponent } from '../../../../../../main/webapp/app/entities/tournament/tournament-detail.component';
import { TournamentService } from '../../../../../../main/webapp/app/entities/tournament/tournament.service';
import { Tournament } from '../../../../../../main/webapp/app/entities/tournament/tournament.model';

describe('Component Tests', () => {

    describe('Tournament Management Detail Component', () => {
        let comp: TournamentDetailComponent;
        let fixture: ComponentFixture<TournamentDetailComponent>;
        let service: TournamentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [TournamentDetailComponent],
                providers: [
                    MockBackend,
                    BaseRequestOptions,
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    {
                        provide: Http,
                        useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                            return new Http(backendInstance, defaultOptions);
                        },
                        deps: [MockBackend, BaseRequestOptions]
                    },
                    {
                        provide: JhiLanguageService,
                        useClass: MockLanguageService
                    },
                    TournamentService
                ]
            }).overrideComponent(TournamentDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TournamentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TournamentService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Tournament(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tournament).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
