package org.example.tennisscoreboard.models;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Player1", nullable = false)
    private Player Player1;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Player2", nullable = false)
    private Player Player2;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Winner", nullable = false)
    private Player Winner;

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
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Player1=" + Player1 +
                ", Player2=" + Player2 +
                ", Winner=" + Winner +
                '}';
    }
}
