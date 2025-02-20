package org.example.tennisscoreboard.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerOne_id", nullable = false)
    private Player playerOne;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerTwo_id", nullable = false)
    private Player playerTwo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id")
    private Player winner;

    public Match() {
    }

    public Match(Player playerOne, Player playerTwo, Player winner) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        if (winner != null && !winner.equals(playerOne) && !winner.equals(playerTwo)) {
            throw new IllegalArgumentException("Winner must be either playerOne or playerTwo");
        } else {
            this.winner = winner;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        if (winner != null && !winner.equals(playerOne) && !winner.equals(playerTwo)) {
            throw new IllegalArgumentException("Winner must be either playerOne or playerTwo");
        }
        this.winner = winner;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", winner=" + winner +
                '}';
    }
}