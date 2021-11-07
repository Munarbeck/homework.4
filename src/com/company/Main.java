package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {270, 280, 240, 690, 400};
    public static int[] heroesDamage = {20, 15, 25, 15, 0};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "medicEnergetic", "Golem"};
    public static int medicEnergetic = 69;

    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // РЅР° РІСЃСЏРєРёР№ СЃР»СѓС‡Р°Р№
            bossHits();
        }
        golem();
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(19); //0,1,2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                    for (int j = 0; j < heroesHealth[3]; j++) {
                        if (heroesHealth[3] < 100) {
                            continue;
                        }
                        if (heroesHealth[i] < 100) {
                            heroesHealth[i] += 365;
                        }
                        break;
                    }

                }
            }
        }
    }


    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("\n" + round_number + "ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________");


    }

    public static void golem() {
        /*Random random = new Random();
        int Random = random.nextInt(3) + 1;
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] + 12;
        }
        heroesHealth[17] = 17 - 72;
        System.out.println("Golem использовал супер способность");
*/
        int partDamage = bossDamage / 5; //1 / 5 урона от босса
        int aliveHeroes = 0; //счетчик живых героев (чтобы голем собирал с остальных 1/5 часть урона)

        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0) {
                    aliveHeroes++; //2
                    heroesHealth[i] = heroesHealth[i] + partDamage;
                }
            }
            heroesHealth[4] = heroesHealth[4] - (partDamage * aliveHeroes); // 10 * (колчество живых героев)
            System.out.println("Golem take " + (partDamage * aliveHeroes));
        }
    }

    /*Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар.
     Может принимать на себя 1/5 часть урона исходящего от босса по другим игрокам.*/
}
