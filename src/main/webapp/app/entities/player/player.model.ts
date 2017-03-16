import { User } from '../../shared';
import { Game } from '../game';
import { Groups } from '../groups';
export class Player {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public rating?: number,
        public user?: User,
        public whiteGame?: Game,
        public blackGame?: Game,
        public group?: Groups,
    ) {
    }
}
