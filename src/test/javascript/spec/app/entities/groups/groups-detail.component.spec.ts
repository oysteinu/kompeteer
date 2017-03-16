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
import { GroupsDetailComponent } from '../../../../../../main/webapp/app/entities/groups/groups-detail.component';
import { GroupsService } from '../../../../../../main/webapp/app/entities/groups/groups.service';
import { Groups } from '../../../../../../main/webapp/app/entities/groups/groups.model';

describe('Component Tests', () => {

    describe('Groups Management Detail Component', () => {
        let comp: GroupsDetailComponent;
        let fixture: ComponentFixture<GroupsDetailComponent>;
        let service: GroupsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [GroupsDetailComponent],
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
                    GroupsService
                ]
            }).overrideComponent(GroupsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GroupsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Groups(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.groups).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
