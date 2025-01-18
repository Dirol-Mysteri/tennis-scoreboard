package org.example.tennisscoreboard.models;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Player1", nullable = false)
    private Player Player1;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Player2", nullable = false)
    private Player Player2;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Winner")
    private Player Winner;

    public Match() {
    }

    public Match(Long id, Player player1, Player player2, Player winner) {
        this.id = id;
        Player1 = player1;
        Player2 = player2;
        Winner = winner;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer1() {
        return Player1;
    }

    public void setPlayer1(Player player1) {
        Player1 = player1;
    }

    public Player getPlayer2() {
        return Player2;
    }

    public void setPlayer2(Player player2) {
        Player2 = player2;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id && Objects.equals(Player1, match.Player1) && Objects.equals(Player2, match.Player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Player1, Player2);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Player1=" + Player1 +
                ", Player2=" + Player2 +
                ", Winner=" + Winner +
                '}';
    }
}
