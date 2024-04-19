package edu.gonzaga.Farkle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CombosTest {
    @Test
    void testCheckForSequenceTrue() {
        Combos combo = new Combos();
        
        int[] numEachDie = {1, 1, 1, 1, 1, 1};
        boolean expected = true;

        boolean actual = combo.checkForSequence(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckForSequenceFalse() {
        Combos combo = new Combos();
        int[] numEachDie = {1, 1, 2, 2, 0, 0};

        boolean expected = false;

        boolean actual = combo.checkForSequence(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor6KindTrue() {
        Combos combo = new Combos();

        int[] numEachDie = {6, 0, 0, 0, 0, 0};
        boolean expected = true;

        boolean actual = combo.checkFor6Kind(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor6KindFalse() {
        Combos combo = new Combos();

        int[] numEachDie = {1, 3, 2, 0, 0, 0};
        boolean expected = false;

        boolean actual = combo.checkFor6Kind(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor3PairTrue() {
        Combos combo = new Combos();

        int[] numEachDie = {2, 0, 2, 0, 2, 0};
        boolean expected = true;

        boolean actual = combo.checkFor3Pair(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor3Pairfalse() {
        Combos combo = new Combos();

        int[] numEachDie = {2, 2, 2, 2, 2, 2};
        boolean expected = false;

        boolean actual = combo.checkFor3Pair(numEachDie);

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor5Kind() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();

        int[] numEachDie = {1, 0, 0, 0, 5, 0};
        Die die1 = new Die(6, 5);
        Die die2 = new Die(6, 1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die2);
        combo.setDice(dice);

        int expected = 1500;

        combo.checkFor3to5Kind(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor4Kind() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();

        int[] numEachDie = {2, 0, 0, 4, 0, 0};
        Die die1 = new Die(6, 4);
        Die die2 = new Die(6, 1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die2);
        dice.add(die2);
        combo.setDice(dice);

        int expected = 800;

        combo.checkFor3to5Kind(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor3Kind() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();

        int[] numEachDie = {3, 0, 0, 0, 0, 0};
        Die die1 = new Die(6, 1);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);

        combo.setDice(dice);

        int expected = 1000;

        combo.checkFor3to5Kind(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor3Kind3() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();

        int[] numEachDie = {0, 0, 3, 0, 0, 0};
        Die die1 = new Die(6, 3);
        dice.add(die1);
        dice.add(die1);
        dice.add(die1);

        combo.setDice(dice);

        int expected = 300;

        combo.checkFor3to5Kind(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor1s() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();

        Die die1 = new Die(6, 1);
        Die die2 = new Die(6, 3);
        dice.add(die1);
        dice.add(die1);
        dice.add(die2);

        combo.setDice(dice);

        int expected = 200;

        combo.checkFor1sOr5s();
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor5s() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();
        
        Die die1 = new Die(6, 5);
        Die die2 = new Die(6, 3);
        dice.add(die1);
        dice.add(die2);

        combo.setDice(dice);

        int expected = 50;

        combo.checkFor1sOr5s();
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckFor1sand5s() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();
        
        Die die1 = new Die(6, 1);
        Die die2 = new Die(6, 5);
        Die die3 = new Die(6, 3);
        dice.add(die1);
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        combo.setDice(dice);

        int expected = 250;

        combo.checkFor1sOr5s();
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckForFullHouseTrue() {
        Combos combo = new Combos();
        
        int[] numEachDie = {2, 0, 3, 0, 1, 0};

        int expected = 1500;

        combo.checkForFullHouse(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckForFullHouseTrue2() {
        Combos combo = new Combos();
        
        int[] numEachDie = {0, 2, 0, 3, 1, 0};

        int expected = 1500;

        combo.checkForFullHouse(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void testCheckForFullHouseFalse() {
        Combos combo = new Combos();
        
        int[] numEachDie = {2, 0, 1, 1, 1, 0};

        int expected = 0;

        combo.checkForFullHouse(numEachDie);
        int actual = combo.getComboValue();

        assertEquals(expected, actual);
    }

    @Test
    void test3TripsHasHigherPrecedenceThanFullHouse() {
        Combos combo = new Combos();
        ArrayList<Die> dice = new ArrayList<>();
        
        int[] numEachDie = {3, 0, 0, 0, 0, 3};
        Die die1 = new Die(6, 1);
        Die die2 = new Die(6, 6);
        for(int i = 0; i < 3; i++) {
            dice.add(die1);
            dice.add(die2);
        }
        combo.setDice(dice);

        combo.checkMeldForCombos(numEachDie);
        Integer expected = 1600;
        Integer actual = combo.getComboValue();

        assertEquals(expected, actual);
    }
}
