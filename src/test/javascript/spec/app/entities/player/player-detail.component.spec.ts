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
import { PlayerDetailComponent } from '../../../../../../main/webapp/app/entities/player/player-detail.component';
import { PlayerService } from '../../../../../../main/webapp/app/entities/player/player.service';
import { Player } from '../../../../../../main/webapp/app/entities/player/player.model';

describe('Component Tests', () => {

    describe('Player Management Detail Component', () => {
        let comp: PlayerDetailComponent;
        let fixture: ComponentFixture<PlayerDetailComponent>;
        let service: PlayerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [PlayerDetailComponent],
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
                    PlayerService
                ]
            }).overrideComponent(PlayerDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PlayerDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlayerService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Player(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.player).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
