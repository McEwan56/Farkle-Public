/** Class Name: Round
 *  Desc: Round has a player and a boolean variable roundOver. newRound is used to run a single round of farkle. 
 *        Round provides the user interface and calls functions from other classes to run a round of farkle. Currently a round 
 *        only rolls dice one time. No rerolls.
 *  Notes: -_-
 */
package edu.gonzaga.Farkle;

import java.util.Scanner;

public class Round {
    private Player user;
    private boolean roundOver;

    /* Default Constructor for the Round class!
     * Calls the player default value constructor and sets round over to false
     */
    public Round() {
        user = new Player();
        roundOver = false;
    }


    /* Method Name: newRound()
     * Returns: Boolean
     * Desc: Runs a single round of farkle. Prints out users hand and meld. Allows to switch dice between hand and meld. 
     *       Checks if farkle and ends round if true. Scores melds and adds to players bank. Only returns false when player quits
     *       Also allows for rerolling of dice.
     */
    public boolean newRound() {
        boolean firstRoll = true, hotHand = false, hotHand2 = false;;
        char choice = ' ';
        int temp = 0;
        this.roundOver = false;

        Scanner userIn = new Scanner(System.in);

        System.out.print(user.getName());
        System.out.println(", it is your turn! Rolling your dice....");
        while(!this.roundOver){
            if(this.user.checkFarkle()) {
                printHandMeld();
                System.out.println("FARKLE!");
                this.user.setRoundPoints(0);
                this.roundOver = true;
            } else if (firstRoll) {
                if(this.user.checkHotHand()) {
                    hotHand = true;
                    printHandMeld();
                    System.out.print("Current Round Score: ");
                    System.out.println(this.user.getRoundPoints());
                    System.out.print("Bank: ");
                    System.out.println(this.user.getBank());
                    System.out.println("!!!HOT HAND!!!");
                    while(hotHand == true) {
                        System.out.println("Would you like to Bank or Reroll?");
                        System.out.println("r) Reroll");
                        System.out.println("g) Bank");
                        choice = userIn.next().charAt(0);
                    
                        if(choice == 'r') {
                            this.user.reRollHand(6);
                            this.roundOver = false;
                            hotHand = false;
                            hotHand2 = true;
                        } else if(choice == 'g') {
                            temp = this.user.getCurrCombo();
                            this.user.updateBank(temp);
                            System.out.println("Round Over");
                            this.user.setRoundPoints(0);
                            this.user.setCurrCombo(0);
                            return true;
                        } else {
                            System.out.println("Invalid Entry, Try Again");
                        }
                    }
                }
                if(hotHand2) {
                    hotHand2 = false;
                    firstRoll = true;
                } else {
                    firstRoll = false;
                }
            } else {
                printHandMeld();
                System.out.print("Current Round Score: ");
                System.out.println(this.user.getRoundPoints());
                System.out.print("Bank: ");
                System.out.println(this.user.getBank());
                System.out.println("Please Select Dice to add/remove to Meld!");
                System.out.print("a) ");
                System.out.println(this.user.getHandDieSide(0));
                if(this.user.getHandSize() >= 2){
                    System.out.print("b) ");
                    System.out.println(this.user.getHandDieSide(1));
                }
                if(this.user.getHandSize() >= 3){
                    System.out.print("c) ");
                    System.out.println(this.user.getHandDieSide(2));
                }
                if(this.user.getHandSize() >= 4){
                    System.out.print("d) ");
                    System.out.println(this.user.getHandDieSide(3));
                }
                if(this.user.getHandSize() >= 5){
                    System.out.print("e) ");
                    System.out.println(this.user.getHandDieSide(4));
                }
                if(this.user.getHandSize() == 6){
                    System.out.print("f) ");
                    System.out.println(this.user.getHandDieSide(5));
                }
                System.out.println("g) Bank");
                System.out.println("r) Reroll");
                System.out.println("q) Quit");

                choice = userIn.next().charAt(0);

                //Note: I tried using a switch statement instead of all the if elses, but something about it wasn't working properly
                if(choice == 'a') {
                    this.user.swapHandandMeld(0);
                } else if (choice == 'b' && this.user.getHandSize() >= 2) {
                    this.user.swapHandandMeld(1);
                } else if (choice == 'c' && this.user.getHandSize() >= 3) {
                    this.user.swapHandandMeld(2);
                } else if (choice == 'd' && this.user.getHandSize() >= 4) {
                    this.user.swapHandandMeld(3);
                } else if (choice == 'e' && this.user.getHandSize() >= 5) {
                    this.user.swapHandandMeld(4);
                } else if (choice == 'f' && this.user.getHandSize() == 6) {
                    this.user.swapHandandMeld(5);
                } else if (choice == 'g') {
                    temp = this.user.getCurrCombo();
                    temp += this.user.getRoundPoints();
                    if(this.user.checkCombos() || user.getRoundPoints() > 0){
                        this.user.updateBank(temp);
                        System.out.println("Round Over");
                        this.user.setRoundPoints(0);
                        this.user.setCurrCombo(0);
                        this.roundOver = true;
                    } else {
                        System.out.println("!! Invalid Meld !! One or More Dice Unused !!");
                    }  
                } else if (choice == 'q') {
                    System.out.println("Quiting....");
                    return false;
                } else if(choice == 'r') {
                    this.user.updateComboDice(this.user.meld.dice);
                    if(this.user.checkCombos()) {
                        this.reRollHand();
                    } else {
                        System.out.println("!! Invalid Meld !! One or More Dice Unused !!");
                    }  
                } else {
                    System.out.println("!!Invalid Input, Try Again!!");
                }
                this.user.updateComboDice(this.user.meld.dice);
                this.user.checkCombos();
            }
        }
        return true;
    }

    /* Method Name: printHandMeld()
     * Returns: N/A (Void)
     * Desc: Prints out users hand and meld to the console in a formated order
     * Also prints current bank and meld value
     */
    public void printHandMeld() {
        String[] letterStrings = {"a) ", "b) ", "c) ", "d) ", "e) ", "f) "};
        System.out.println("*****************************************");
        System.out.println("     Your Hand:     *        Melds       ");
        System.out.println("*****************************************");
        for(int i = 0; i < this.user.getHandSize(); i++){
            System.out.print(letterStrings[i]);
            System.out.print(this.user.getHandDieSide(i)); 
            System.out.print("                *        ");
            System.out.print(this.user.getMeldDieSide(i));
            System.out.println("           ");
        } 
        System.out.println("*****************************************");
        System.out.print("Current Meld Worth: ");
        System.out.println(this.user.getCurrCombo());
    }

    /* Method Name: getPlayerName()
     * Returns: A string
     * Desc: Calls a Player method (getName) to get user's name
     */
    public String getPlayerName() { 
        return user.getName();
    }

    /* Method Name: setPlayerName()
     * Returns: N/A (Void)
     * Desc: Calls a Player method to set user's name to a given String
     */
    public void setPlayerName(String newName) {
        user.setName(newName);
    }

    /* Method Name: reRollHand()
     * Returns: N/A (Void)
     * Desc: Uses a series of member variables methods to reroll hand and reset meld
     */
    public void reRollHand() {
        Integer newHandSize = 0;
        
        for(int i = 0; i < this.user.getHandSize(); i++) {
            if(this.user.getHandDieSide(i) != 0) {
                newHandSize++;
            }
        }
        
        //newHandSize less than old hand size, if newHandSize == 0, all dice have score and all dice can be rerolled.
        if(newHandSize == 0) {
            this.user.reRollHand(6);
            for(int i = 0; i < 6; i++) {
                this.user.clearMeld();
            }
        } else if(newHandSize > 0 && newHandSize < this.user.getHandSize()) {
            this.user.reRollHand(newHandSize);
            this.user.clearMeld();
        } else {
            System.out.println("!! Invalid Reroll !!");
        }
    }

    public void newHand() {
        this.user.clearMeld();
        this.user.reRollHand(6);
    }

    /* Method Name: getPlayerPoints()
     * Returns: An Int
     * Desc: Returns the integer value of users point bank
     */
    public int getPlayerPoints() {
        return this.user.getBank();
    }

    /* Method Name: setRoundPoints()
     * Returns: N/A (Void)
     * Desc: Sets users round points to a given integer
     */
    public void setRoundPoints(int x) {
        this.user.setRoundPoints(x);
    }

    /* Method Name: getPlayer()
     * Returns: a Player
     * Desc: A getter used to get current round player info for multiplayer
     */
    public Player getPlayer() {
        return this.user;
    }

    /* Method Name: setPlayer()
     * Returns: N/A (Void)
     * Desc: A setter used to update current player info for multiplayer
     */
    public void setPlayer(Player newPlayer) {
        this.user = newPlayer;
    }
}