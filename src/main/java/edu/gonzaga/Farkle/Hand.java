/** Class Name: Hand
 *  Desc: A hand contains an arraylist of six dice and a list of the number of each die and contains several methods to manipulate,
 *        get, and set these values.
 *  Notes: :P
 */
package edu.gonzaga.Farkle;

import java.util.ArrayList;

public class Hand {
    
    ArrayList<Die> dice = new ArrayList<>();
    int[] numEachDieArr;

    /* Default Value Contructor for Hand class
     * Creates a array list of 6 randomly rolled dice
     */
    public Hand() {
        for(int i = 0; i < 6; i++){
            Die die = new Die();
            die.roll();
            dice.add(die);
        }
        numEachDieArr = numEachDie();
    }

    /* A Constructor for hand 
     * Lets one make a hand of 6 6-sided dice all with side x up
     */
    public Hand(int x) {
        for(int i = 0; i < 6; i++){
            Die die = new Die(6, x);
            dice.add(die);
        }
    }

    /* A Constructor for Hand
     * Used for testing purposes and only initalizes an array of NumEachDie.
     */
    public Hand(int[] y) {
        numEachDieArr = y;
    }

    /* Method Name: printHand()
     * Returns: N/A (Void)
     * Desc: Prints each element in dice arrayList
     * Mainly used for testing and debug purposes
     */
    public void printHand() {
        for(int i = 0; i < dice.size() - 1; i++){
            System.out.print(getDieSide(i));
            System.out.print(", ");
        }
        System.out.println(getDieSide(dice.size() - 1));
    }

    /* Method Name: getDiceArrayList()
     * Returns: N/A (Void)
     * Desc: Returns the array list containing the dice in hand. Used when checking for hothand
     */
    public ArrayList<Die> getDiceArrayList() {
        return this.dice;
    }

    /* Method Name: getDieSide()
     * Returns: an int
     * Desc: A simple getter function that accepts a int dieNumber used as as index to get the side value of a die in arrayList 
     * and return that side value
     */
    public int getDieSide(int dieNumber) {
        return (dice.get(dieNumber)).getSideUp();
    }

    /* Method Name: getDie()
     * Returns: a Die
     * Desc: A simple getter function that accepts a int dieNumber used as as index to get the die in arrayList 
     * and return die
     */
    public Die getDie(int dieNumber) {
        return dice.get(dieNumber);
    }

    /* Method Name: changeDie(...)
     * Returns: N/A (Void)
     * Desc: Accepts a die and an index and changes the die at dice[index] to the newly inputed die.
     */
    public void changeDie (Die die, int index) {
        dice.set(index, die);
    }

    /*Method Name: numEachDie()
     * Returns: an int array length 6
     * Desc: Loops through each die in dice and counts how many time each side is the same
     * Used for finding combos and checking if farkle
     */
    public int[] numEachDie() {
        int[] numEachDieArr = {0, 0, 0, 0, 0, 0};
        for(int i = 0; i < dice.size(); i++) {
            if(getDieSide(i) == 1){
                numEachDieArr[0] += 1;
            } else if(getDieSide(i) == 2){
                numEachDieArr[1] += 1;
            } else if(getDieSide(i) == 3){
                numEachDieArr[2] += 1;
            } else if(getDieSide(i) == 4){
                numEachDieArr[3] += 1;
            } else if(getDieSide(i) == 5){
                numEachDieArr[4] += 1;
            } else {
                numEachDieArr[5] += 1;
            }
        }
        return numEachDieArr;
    }

    /* Method Name: isFarkle()
     * Returns: A boolean value
     * Desc: Accepts an array of integers containing the number of each kind of dice in hand
     * Checks that array for a series of conditions to determine if it is a farkle 
     */
    public boolean isFarkle() {
        //Important to note that numEachDie is counting num each die 1-6 however its indexing goes from 0-5
        int numPairs = 0;
        //Not a farkle if any of the following are true
        //Contains a 1 or more 1s or 5s
        if(numEachDieArr[0] >= 1 || numEachDieArr[4] >= 1) {
            return false;
        }
        //Has 3 or more of any kind of die - This also accounts for full houses
        for(int i = 0; i < 6; i++) {
            if(numEachDieArr[i] >= 3) {
                return false;
            }
            if(numEachDieArr[i] == 2) {
                numPairs++;
            }
        }
        //There are 3 pairs of die (2 of 3 kinds of die)
        if(numPairs == 3){
            return false;
        }
        //One of each kind of die (Note we should only have to check the first 5 values and not the 6th (if first 5 == 1 so will the 6th))
        if(numEachDieArr[0] == 1 && numEachDieArr[1] == 1 && numEachDieArr[2] == 1 && numEachDieArr[3] == 1 && numEachDieArr[4] == 1){
            return false;
        }

        return true;
    }

    /* Method Name: reRoll()
     * Returns: N/A (Void)
     * Desc: Clears ArrayList of dice, rerolls Int x (1-6) number of dice for new hand, and calls numEachDie for the newHand
     */
    public void reRoll(Integer numDice) {
        this.dice.clear();
        for(int i = 0; i < numDice; i++) {
            Die die = new Die();
            die.roll();
            dice.add(die);
        }
        numEachDieArr = numEachDie();
    }
}
