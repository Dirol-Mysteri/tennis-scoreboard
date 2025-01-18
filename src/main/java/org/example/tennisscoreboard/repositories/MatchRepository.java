package org.example.tennisscoreboard.repositories;

import org.example.tennisscoreboard.models.Match;

public class MatchRepository extends BaseRepository<Long, Match> {
    public MatchRepository() {
        super(Match.class);
    }
}
