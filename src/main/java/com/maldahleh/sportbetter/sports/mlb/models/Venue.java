package com.maldahleh.sportbetter.sports.mlb.models;

public record Venue(String id, String name, String market, int capacity, String address,
                    String city, String state, String zip, String country) {
}