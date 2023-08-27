package com.maldahleh.sportbetter.sports.mlb.models;

public record Game(String id, String coverage, String scheduled, String homeTeam, String awayTeam,
                   int attendance, String duration) {
}
