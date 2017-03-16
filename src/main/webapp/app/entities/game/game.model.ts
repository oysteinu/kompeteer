
const enum GameResult {
    'WHITE',
    'BLACK',
    'DRAW'

};
import { Player } from '../player';
export class Game {
    constructor(
        public id?: number,
        public result?: GameResult,
        public white?: Player,
        public black?: Player,
    ) {
    }
}
