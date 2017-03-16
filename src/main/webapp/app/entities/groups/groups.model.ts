import { Player } from '../player';
export class Groups {
    constructor(
        public id?: number,
        public name?: string,
        public player?: Player,
    ) {
    }
}
