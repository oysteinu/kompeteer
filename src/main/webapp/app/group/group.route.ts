import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { GroupComponent } from './';

export const GROUP_ROUTE: Route = {
  path: 'group/:id',
  component: GroupComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  },
  canActivate: [UserRouteAccessService]
};
