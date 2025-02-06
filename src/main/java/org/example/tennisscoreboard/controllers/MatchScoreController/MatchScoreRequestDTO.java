package org.example.tennisscoreboard.controllers.MatchScoreController;

//public class MatchScoreRequest {
//    UUID uuid;
//    Long winnerID;
//
//    public UUID getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(UUID uuid) {
//        this.uuid = uuid;
//    }
//
//    public Long getWinnerID() {
//        return winnerID;
//    }
//
//    public void setWinnerID(Long winnerID) {
//        this.winnerID = winnerID;
//    }
//
//    @Override
//    public String toString() {
//        return "MatchScoreRequest{" +
//                "uuid=" + uuid +
//                ", winnerID=" + winnerID +
//                '}';
//    }
//}

public record MatchScoreRequestDTO(Long winnerID) {
}
