package com.kompeteer.web.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.kompeteer.web.domain.enumeration.GameStatus;

import com.kompeteer.web.domain.enumeration.GameResult;

/**
 * A Game.
 */
@Entity
@Table(name = "game")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GameStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private GameResult result;

    @ManyToOne
    private Player white;

    @ManyToOne
    private Player black;

    @ManyToOne
    private Tournament tournament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Game status(GameStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameResult getResult() {
        return result;
    }

    public Game result(GameResult result) {
        this.result = result;
        return this;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

    public Player getWhite() {
        return white;
    }

    public Game white(Player player) {
        this.white = player;
        return this;
    }

    public void setWhite(Player player) {
        this.white = player;
    }

    public Player getBlack() {
        return black;
    }

    public Game black(Player player) {
        this.black = player;
        return this;
    }

    public void setBlack(Player player) {
        this.black = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Game tournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        if (game.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Game{" +
            "id=" + id +
            ", status='" + status + "'" +
            ", result='" + result + "'" +
            '}';
    }
}
