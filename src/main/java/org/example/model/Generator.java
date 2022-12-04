package org.example.model;

import com.github.javafaker.Faker;
import org.example.db.DataBase;

import java.util.Random;
import java.util.UUID;

public class Generator {

    public void makePlayers(DataBase db){

        Random rnd = new Random();
        Faker faker = new Faker();


        for (int i = 0; i < 25; i++) {
            db.addPlayer(new Player(UUID.randomUUID(),
                    faker.name().lastName(),
                    rnd.nextInt(15, 20),
                    League.THIRD));
        }

        for (int i = 0; i < 25; i++) {
            db.addPlayer(new Player(UUID.randomUUID(),
                    faker.name().lastName(),
                    rnd.nextInt(21, 30),
                    League.SECOND));
        }

        for (int i = 0; i < 25; i++) {
            db.addPlayer(new Player(UUID.randomUUID(),
                    faker.name().lastName(),
                    rnd.nextInt(31, 80),
                    League.PRIME));
        }


        //        db.printAllPlayers();
    }

}
