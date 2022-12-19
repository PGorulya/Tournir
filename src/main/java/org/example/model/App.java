//У нас есть класс игроки
//У игроков есть имена и возраст
//Есть Энам который хранит лиги
//Есть класс который хранит в листе всех игроков
//Есть класс который проводит игру среди игроков одной лиги
//Каждый игрок в каждой лиге должен сыграть 25 раз со своей же лигой
//Есть метод который считает очки у каждого игрока
//Вам необходимо написать все это  + метод который будет брать из нашего листа игроков
// и проводить с ними игру в одной лиге так что бы все сыграли одинаковое количество раз.
// После того как отыграют все игроки в каждой лиге, надо найти трех дидеров в каждой лиге
// и если ЕСТЬ ВОЗМОЖНОСТЬ ПЕРЕМЕСТИТЬ ЛИДЕР В ЛИГУ ВЫШЕ СДЕЛАТЬ ЭТО ЕСЛИ НЕТ ТО НЕ НАДО.
//Найти трех лузеров и если есть возможносмть переместить в лигу ниже то сделать это.
//Прогнать такую игру 5 раз и вывести на экран
//Тех кто заработал больше всего очков
//Тех кто перешел в лигу выше
//Тех кто перешел в лигу ниже

//В каждой лиге минимум 25 человек
//Сделать класс который генерирует Участников - использовать faker библиотеку
// Лиг минимум 3

//Написать метод который по ИТОГУ ИГРЫ помещает их в мапу .
//Ключ - ЛИГА, значение - Игроки этой лиги
//Вывести это все на экран циклом for each

package org.example.model;

import org.example.db.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<Player> leaguePrime;
        List<Player> leagueSecond;
        List<Player> leagueThird;

        Map<League, List<Player>> leagueListMap = new HashMap<>();

        DataBase db = new DataBase();
        Generator generator = new Generator();
        Game game = new Game();

// Generation of the Players
        generator.makePlayers(db);

        int numOfRounds = 5;            // number 0f Rounds Of the Tournir
// make 5 rounds of games in each league
        for (int i = 0; i < numOfRounds; i++) {

// Lists of the Leagues
            leaguePrime = db.getPlayersByLeague(League.PRIME);
            leagueSecond = db.getPlayersByLeague(League.SECOND);
            leagueThird = db.getPlayersByLeague(League.THIRD);

// Games for each League
            game.makeGame(leaguePrime);
            game.makeGame(leagueSecond);
            game.makeGame(leagueThird);

//            System.out.println("NEW GAME " + i);
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            game.printPlayers(leaguePrime);
//            game.printPlayers(leagueSecond);
//            game.printPlayers(leagueThird);


// Transfers BestPlayers to more high leagues
            game.makeTransferBestPlayers(db.getAllPlayers(), leagueThird, League.SECOND);
            game.makeTransferBestPlayers(db.getAllPlayers(), leagueSecond, League.PRIME);

// Transfers WorstPlayers to more low leagues
            game.makeTransferWorstPlayers(db.getAllPlayers(), leagueSecond, League.THIRD);
            game.makeTransferWorstPlayers(db.getAllPlayers(), leaguePrime, League.SECOND);

            db.makeResetLeagueScores();     // Reset scoreInLeague to all the Leagues



            if (i == (numOfRounds - 1)) {
                leaguePrime = db.getPlayersByLeague(League.PRIME);
                leagueSecond = db.getPlayersByLeague(League.SECOND);
                leagueThird = db.getPlayersByLeague(League.THIRD);

                System.out.println();
                System.out.println("BEST PLAYERS");
                System.out.println("=======================================================================");
                game.printPlayers(game.bestPlayers(db.getAllPlayers()));

                System.out.println();
                System.out.println("HIGH TRANSFERS OF THE PLAYERS");
                System.out.println("=======================================================================");
                game.printPlayers(game.highTransfers(db.getAllPlayers()));

                System.out.println();
                System.out.println("LOW TRANSFERS OF THE PLAYERS");
                System.out.println("=======================================================================");
                game.printPlayers(game.lowTransfers(db.getAllPlayers()));

                leagueListMap = game.createLeagueListMap(leaguePrime, leagueSecond, leagueThird);

                for (Map.Entry<League, List<Player>> entry : leagueListMap.entrySet()) {
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    System.out.println("League: " + entry.getKey() + " from HashMap");
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    game.printPlayers(entry.getValue());
                }
            }

        }
    }
}
