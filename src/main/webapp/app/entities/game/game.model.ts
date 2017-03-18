
const enum GameStatus {
    'PENDING',
    'COMPLETE'

};

const enum GameResult {
    'WHITE',
    'BLACK',
    'DRAW'

};
export class Game {
    constructor(
        public id?: number,
        public status?: GameStatus,
        public result?: GameResult,
        public whiteId?: number,
        public blackId?: number,
        public tournamentId?: number,
    ) {
    }
}
