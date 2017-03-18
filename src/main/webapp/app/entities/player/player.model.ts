export class Player {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public userId?: number,
        public whiteGameId?: number,
        public blackGameId?: number,
        public tournamentId?: number,
        public groupId?: number,
    ) {
    }
}
