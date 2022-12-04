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

package org.example.model;

import org.example.db.DataBase;

import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Player> leaguePrime;
        List<Player> leagueSecond;
        List<Player> leagueThird;

        DataBase db = new DataBase();
        Generator generator = new Generator();
        Game game = new Game();

// Generation of the Players
        generator.makePlayers(db);

        for (int i = 0; i < 5; i++) {

// Lists of the Leagues
            leagueThird = db.getPlayersByLeague(League.THIRD);
            leagueSecond = db.getPlayersByLeague(League.SECOND);
            leaguePrime = db.getPlayersByLeague(League.PRIME);

// Games for each League
            game.makeGame(leagueThird);
            game.makeGame(leagueSecond);
            game.makeGame(leaguePrime);

//            System.out.println("New game " + i);
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            game.printPlayers(leagueThird);
//            game.printPlayers(leagueSecond);
//            game.printPlayers(leaguePrime);

// Transfers BestPlayers to more high leagues
            game.makeTransferBestPlayers(db.getAllPlayers(), leagueThird, League.SECOND);
            game.makeTransferBestPlayers(db.getAllPlayers(), leagueSecond, League.PRIME);

// Transfers WorstPlayers to more low leagues
            game.makeTransferWorstPlayers(db.getAllPlayers(), leagueSecond, League.THIRD);
            game.makeTransferWorstPlayers(db.getAllPlayers(), leaguePrime, League.SECOND);

//            System.out.println();
//            System.out.println("After Transfer " + i);
//            System.out.println("================================================================");
//            leagueThird = db.getPlayersByLeague(League.THIRD);
//            leagueSecond = db.getPlayersByLeague(League.SECOND);
//            leaguePrime = db.getPlayersByLeague(League.PRIME);
//            game.printPlayers(leagueThird);
//            game.printPlayers(leagueSecond);
//            game.printPlayers(leaguePrime);

            db.makeResetLeagueScores();     // Reset scoreInLeague to all the Leagues

        }

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

    }
}
