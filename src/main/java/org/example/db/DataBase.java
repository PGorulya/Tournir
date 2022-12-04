package org.example.db;

import org.example.model.League;
import org.example.model.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBase{

    private  List<Player>  allPlayers;

    public DataBase(){
        allPlayers = new ArrayList<Player>();
    }


    public boolean addPlayer(Player player) {
        for(Player plr: allPlayers) {
            if (player.getPlayerId().equals(plr.getPlayerId()))
                return false;
        }
//        if(assignLeague(player))
                allPlayers.add(player);

        return true;
    }

    public List<Player> getAllPlayers () {
        return allPlayers;
    }

    public  List<Player> getPlayersByLeague(League league){
        List<Player> playersByLeagueList = new LinkedList<>();

        for(Player player: allPlayers) {
            if (player.getLeague().equals(league))
                playersByLeagueList.add(player);
        }
        return playersByLeagueList;
    }

    public void makeResetLeagueScores() {
        for(Player player: allPlayers){
            player.setScoreInLeague(0);
        }
    }

    public void printAllPlayers() {
        System.out.println("allPlayers +++++++++++++++++++++++++++++++++++");
        for(Player player: allPlayers){
            System.out.println("Name: " + player.getName()
                    + " Age: " + player.getAge()
                    + " League: " + player.getLeague());
        }
        System.out.println("allPayers -------------------------------------");
    }

}
