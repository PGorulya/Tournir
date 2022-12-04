package org.example.model;

import java.util.*;

public class Game {

    public void printPlayers(List<Player> players){

        for(Player player: players){
            System.out.printf("League: %s name: %s  age: %d  scoreInLeague: %d totalScore: %d transfer %d\n",
                    player.league, player.getName(), player.getAge(),
                    player.scoreInLeague, player.totalScore, player.transfer);
        }

    }

    public void makeGame(List<Player> players) {

        for (int i = 0; i < players.size() - 1; i++) {
            Player p1 = players.get(i);
            for (int j = i + 1; j < players.size(); j++) {
                Player p2 = players.get(j);
                resultGame(p1, p2);
            }
        }
        Collections.sort(players, new Comparator<>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScoreInLeague() - o1.getScoreInLeague();
            }
        });

    }

    public void resultGame(Player p1, Player p2) {
        if (Math.random() > 0.5) {
            p2.addScoreInLeague(1);
            p2.addTotalScore(1);
        }
        else {
            p1.addScoreInLeague(1);
            p1.addTotalScore(1);
        }
    }

    public List<Player> getBestPlayersByLeague(List<Player> players) {
        List<Player> bestPlayersList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bestPlayersList.add(players.get(i));
        }
        return bestPlayersList;
    }

    public List<Player> getWorstPlayersByLeague(List<Player> players) {
        List<Player> worstPlayersList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            worstPlayersList.add(players.get(players.size() - 1 - i));
        }
        return worstPlayersList;
    }

    public void makeTransferBestPlayers(List<Player> players, List<Player> leaguePlayers, League newLeague) {
        for (int i = 0; i < 3; i++) {
            UUID bestPlayerId = leaguePlayers.get(i).getPlayerId();
            for(Player player: players) {
                if (player.getPlayerId().equals(bestPlayerId)) {
                    player.setLeague(newLeague);
                    player.addTransfer(1);
                }
            }
        }
    }

    public void makeTransferWorstPlayers(List<Player> players, List<Player> leaguePlayers, League newLeague) {
        for (int i = 0; i < 3; i++) {
            UUID worstPlayerId = leaguePlayers.get(leaguePlayers.size() - 1 - i).getPlayerId();
            for(Player player: players) {
                if (player.getPlayerId().equals(worstPlayerId)) {
                    player.setLeague(newLeague);
                    player.addTransfer(-1);
                }
            }

        }
    }

    public List<Player> bestPlayers(List<Player> players) {
        List<Player> bestPlayersList = new ArrayList<>();

        Collections.sort(players, new Comparator<>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getTotalScore() - o1.getTotalScore();
            }
        });
        for (int i = 0; i < 3; i++) {
            bestPlayersList.add(players.get(i));
        }
        return bestPlayersList;
    }

    public List<Player> highTransfers(List<Player> players) {
        List<Player> highTransferList = new ArrayList<>();
        for(Player player: players) {
            if(player.transfer > 0)   highTransferList.add(player);
            }

        return highTransferList;
    }

    public List<Player> lowTransfers(List<Player> players) {
        List<Player> lowTransferList = new ArrayList<>();
        for(Player player: players) {
            if(player.transfer < 0)   lowTransferList.add(player);
        }

        return lowTransferList;
    }
}
