
const enum GameStatus {
    'PENDING',
    'COMPLETE'

};

const enum GameResult {
    'PLAYER1',
    'PLAYER2',
    'DRAW'

};
export class Game {
    constructor(
        public id?: number,
        public status?: GameStatus,
        public result?: GameResult,
        public player1Id?: number,
        public player2Id?: number,
        public tournamentId?: number,
    ) {
    }
}
