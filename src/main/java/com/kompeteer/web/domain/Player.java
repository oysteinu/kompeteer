package com.kompeteer.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Player.
 */
@Entity
@Table(name = "player")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "white")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Game> whiteGames = new HashSet<>();

    @OneToMany(mappedBy = "black")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Game> blackGames = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "player_tournament",
               joinColumns = @JoinColumn(name="players_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="tournaments_id", referencedColumnName="id"))
    private Set<Tournament> tournaments = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "player_group",
               joinColumns = @JoinColumn(name="players_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="groups_id", referencedColumnName="id"))
    private Set<Groups> groups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Player lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Player email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public Player user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Game> getWhiteGames() {
        return whiteGames;
    }

    public Player whiteGames(Set<Game> games) {
        this.whiteGames = games;
        return this;
    }

    public Player addWhiteGame(Game game) {
        this.whiteGames.add(game);
        game.setWhite(this);
        return this;
    }

    public Player removeWhiteGame(Game game) {
        this.whiteGames.remove(game);
        game.setWhite(null);
        return this;
    }

    public void setWhiteGames(Set<Game> games) {
        this.whiteGames = games;
    }

    public Set<Game> getBlackGames() {
        return blackGames;
    }

    public Player blackGames(Set<Game> games) {
        this.blackGames = games;
        return this;
    }

    public Player addBlackGame(Game game) {
        this.blackGames.add(game);
        game.setBlack(this);
        return this;
    }

    public Player removeBlackGame(Game game) {
        this.blackGames.remove(game);
        game.setBlack(null);
        return this;
    }

    public void setBlackGames(Set<Game> games) {
        this.blackGames = games;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public Player tournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
        return this;
    }

    public Player addTournament(Tournament tournament) {
        this.tournaments.add(tournament);
        tournament.getPlayers().add(this);
        return this;
    }

    public Player removeTournament(Tournament tournament) {
        this.tournaments.remove(tournament);
        tournament.getPlayers().remove(this);
        return this;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public Player groups(Set<Groups> groups) {
        this.groups = groups;
        return this;
    }

    public Player addGroup(Groups groups) {
        this.groups.add(groups);
        groups.getPlayers().add(this);
        return this;
    }

    public Player removeGroup(Groups groups) {
        this.groups.remove(groups);
        groups.getPlayers().remove(this);
        return this;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        if (player.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", lastName='" + lastName + "'" +
            ", email='" + email + "'" +
            '}';
    }
}
