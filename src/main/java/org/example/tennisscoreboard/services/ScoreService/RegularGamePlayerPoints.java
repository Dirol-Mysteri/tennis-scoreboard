package org.example.tennisscoreboard.services.ScoreService;

public enum RegularGamePlayerPoints implements PlayerPoints {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String pointCode;

    RegularGamePlayerPoints(String pointCode) {
        this.pointCode = pointCode;
    }

    public RegularGamePlayerPoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Cannot call next() on ADVANTAGE");
        } else {
            return RegularGamePlayerPoints.values()[this.ordinal() + 1];
        }
    }

    public String getPointCode() {
        return pointCode;
    }

    public static RegularGamePlayerPoints findByPointCode(String code) {
        for (RegularGamePlayerPoints p : RegularGamePlayerPoints.values()) {
            if (p.getPointCode().equals(code)) {
                return p;
            }
        }
        return null;
    }
}
