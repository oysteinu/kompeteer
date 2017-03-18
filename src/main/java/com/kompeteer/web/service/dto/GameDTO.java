package com.kompeteer.web.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.kompeteer.web.domain.enumeration.GameStatus;
import com.kompeteer.web.domain.enumeration.GameResult;

/**
 * A DTO for the Game entity.
 */
public class GameDTO implements Serializable {

    private Long id;

    private GameStatus status;

    private GameResult result;

    private Long player1Id;

    private Long player2Id;

    private Long tournamentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long playerId) {
        this.player1Id = playerId;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long playerId) {
        this.player2Id = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameDTO gameDTO = (GameDTO) o;

        if ( ! Objects.equals(id, gameDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
            "id=" + id +
            ", status='" + status + "'" +
            ", result='" + result + "'" +
            '}';
    }
}
