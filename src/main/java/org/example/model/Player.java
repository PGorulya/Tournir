package org.example.model;

import java.util.UUID;

public class Player {
    private UUID playerId;
    private String name;
    private int age;
    League league;
    int scoreInLeague;  // scores within league
    int totalScore;     // scores pf all tournir

    int transfer;       //transfer >0 (to high league)  <0 (to low league)

    public Player(UUID playerId, String name, int age, League league) {
        this.name = name;
        this.playerId = playerId;
        this.age = age;
        this.league = league;
        this.scoreInLeague = 0;
        this.totalScore = 0;
        this.transfer = 0;
    }

    public String getName() {
        return name;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getAge() {
        return age;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public int getScoreInLeague() {
        return scoreInLeague;
    }

    public void setScoreInLeague(int score){
        this.scoreInLeague = score;
    }

    public void addScoreInLeague(int delta){
        this.scoreInLeague += delta;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addTotalScore(int delta){
        this.totalScore += delta;
    }

    public void addTransfer(int delta){
    // delta +1 (to high league)
    //       -1 (to low league
        this.transfer += delta;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", league=" + league +
                ", scoreInLeague=" + scoreInLeague +
                ", totalScore=" + totalScore +
                ", transfer=" + transfer +
                '}';
    }
}
