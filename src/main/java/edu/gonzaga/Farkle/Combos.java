/** Class Name: Combos
 *  Desc: Takes from meld and gets a point value based on wether ot not meld has any valid combos!
 *  Notes: WHY GOD WHY!!!!!!
 *         On a real note, next assginment, investegate combining the trips, quads, fives, and six of a kind if trees into one
 *         using multiplication and tracking how many of each die there is. 
 */
package edu.gonzaga.Farkle;

import java.util.ArrayList;

public class Combos {
    private Integer comboValue;
    ArrayList<Integer> comboDice = new ArrayList<>();
    
    /* Default constructor for the Combos class
     * Sets comboValue = 0
     */
    public Combos() {
        comboValue = 0;
    }

    /* Method Name: getComboValue()
     * Returns: a Integer
     * Desc: Simple getter to get combos comboValue
     */
    public Integer getComboValue() {
        return this.comboValue;
    }

    /* Method Name: setComboValue()
     * Returns: void
     * Desc: Simple setter to set combos comboValue
     */
    public void setComboValue(int num) {
        this.comboValue = num;
    }

    /* Method Name: printComboDice
     * Returns: N/A (Void)
     * Desc: Prints out comboDice in a sequence for debug purposes
     */
    public void printComboDice() {
        if(comboDice.size() > 0) {
            for(int i = 0; i < comboDice.size()-1; i++){
                System.out.print(comboDice.get(i));
                System.out.print(", ");
            }
            System.out.println(comboDice.get(comboDice.size()-1));
        }
    }

    /* Method Name: setDice()
     * Returns: N/A (Void)
     * Desc: Accepts an array of dice, gets rid of any dice with side value 0 and adds the remaing dice's sides to comboDice
     */
    public void setDice(ArrayList<Die> dice){
        this.comboDice.clear();
        for(int i = 0; i < dice.size(); i++){
            if(dice.get(i).getSideUp() != 0){
                this.comboDice.add(dice.get(i).getSideUp());
            }
        }
    }

    /* Method Name: removeDice()
     * Returns: N/A (Void)
     * Desc: Accepts a dieSide integer and removes all values = to that int from comboDice
     */
    public void removeDice(int dieSide) {
        ArrayList<Integer> placeholder = new ArrayList<>();
        dieSide += 1;
        
        for(int i = 0; i < comboDice.size(); i++) {
            if(comboDice.get(i) != dieSide){
                placeholder.add(comboDice.get(i));
            }
        }
        comboDice.clear();
        comboDice = placeholder;
    }

    /* Method Name: checkMeldForCombos()
     * Returns: boolean value
     * Desc: This is the big one! Checks combo dice for potential meld combos. If a combo is not found or a dice goes unused, returns false
     * and has user re-select meld. If combo(s) are found and all dice are used, sets comboValue to the value of all the combos. 
     * Every time a combo is found, removes dice used from comboDice arraylist, and continues down the if-statement tower of death >:)
     * Also accepts an array containing the number of each kind of dice
     */
    public boolean checkMeldForCombos(int[] numEachDie) {
        this.comboValue = 0;
        //The begining of my torment and very ineffiecent code for doing this
        //Combos will be calced in order of highest to lowest precendce

        if(comboDice.size() == 6) {
            //Sequences
            if(checkForSequence(numEachDie)) {
                return true;
            }
            //3 Pairs
            if(checkFor3Pair(numEachDie)) {
                return true;
            }
            //6 of a kind
            if(checkFor6Kind(numEachDie)) {
                return true;
            }
        }

        //Checking for full house (dice size must be 5 or greater)
        if(comboDice.size() >= 5) {
            checkForFullHouse(numEachDie);
        }
        //Checking for any 3, 4, 5, or 6 of a kind
        checkFor3to5Kind(numEachDie);
        //If there happens to be 2 3 of a kinds, we run the following just in case
        if(comboDice.size() == 3) {
            checkFor3to5Kind(numEachDie);
        }
        
        //1s and 5s
        checkFor1sOr5s();

        //Making sure meld is valid before returning
        if(comboDice.size() > 0) {
            //comboValue = 0;
            return false;
        }
        return true;
    }

    /* Method Name: checkForSequence()
     * Returns: Boolean 
     * Desc: Accepts an array of numEachDie and checks for a sequence combo. Returns true/false based on whether combo is found. 
     *       If combo is found, sets combo value to 1000.
     */
    public boolean checkForSequence(int[] numEachDie) {
        if(numEachDie[0] == 1 && numEachDie[1] == 1 && numEachDie[2] == 1 && numEachDie[3] == 1 && numEachDie[4] == 1) {
            comboDice.clear();
            this.comboValue = 1000;
            //We can just return here because we know there are no more dice in comboDice
            return true;
        } else {
            return false;
        }
    }

    /* Method Name: checkFor3Pair
     * Returns: Boolean
     * Desc: Loops though numEachDie and if there are 3 pairs (2 shows up 3 times), adds 750 to combo and returns true
     */
    public boolean checkFor3Pair(int[] numEachDie) {
        int numPairs = 0;
        for(int i = 0; i < 6; i++) {
            if(numEachDie[i] == 2) {
                numPairs++;
            }
        }
        if(numPairs == 3) {
            this.comboValue = 750;
            return true;
        } else {
            return false;
        }
    }

    /* Method Name: checkFor6Kind
     * Returns: Boolean
     * Desc: Loops through numEachDie and if any die appears 6 times, awards appropriate amount of points and returns true. Otherwise returns false
     */
    public boolean checkFor6Kind(int[] numEachDie) {
        for(int i = 0; i < 6; i++) {
            if(numEachDie[i] == 6) {
                if(i == 0) {
                    this.comboValue = 1300;
                    return true;
                } else if (i == 1) {
                    this.comboValue = 800;
                    return true;
                } else if (i == 2) {
                    this.comboValue = 1200;
                    return true;
                } else if (i == 3) {
                    this.comboValue = 1600;
                    return true;
                } else if (i == 4) {
                    this.comboValue = 2000;
                    return true;
                } else if (i == 5) {
                    this.comboValue = 2400;
                    return true;
                }
            }
        }
        return false;
    }

    /* Method Name: checkFor1sOr5s()
    * Returns: N/A (Void)
    * Desc: Loops through die list and counts how many 1s and 5s appear. Add points appropiately and removes the 1's and 5's from the list
    */
    public void checkFor1sOr5s() {
    Integer num1s = 0, num5s = 0;
        for(int i = 0; i < comboDice.size(); i++) {
            if(comboDice.get(i) == 1) {
                num1s++;
            } else if(comboDice.get(i) == 5) {
                num5s++;
            }
        }
        //Gets rid of any 1s or 5s that are left
        removeDice(0);
        removeDice(4);
        this.comboValue += 100 * num1s;
        this.comboValue += 50 * num5s;
    }

    /* Method Name: checkFor3to5Kind()
    * Returns: N/A (Void)
    * Desc: Kind of complicated function here. First we loop through numEachDie and check if any values are >= 3. Only checks for one at a time.
            Then saves the die side and the number of die if it meets the requirments. Sends those to another function for scoring (For simplicity sake)
    */
    public void checkFor3to5Kind(int[] numEachDie) {
        Integer dieSide = 0, numDie = 0, numDieCheck = 0, index = 0;
        
        //Switch from a for loop to a while loop here to fix an issue with game not detecting 2 trips

        for(index = 0; index < 6; index++) {
            if(numEachDie[index] >= 3 && dieSide == 0) {
                for(int j = 0; j < comboDice.size(); j++) {
                    if(index + 1 == comboDice.get(j)) {
                        numDieCheck++;
                        if(numDieCheck == numEachDie[index]) {
                            dieSide = index + 1;
                            numDie = numEachDie[index];
                            score35Kind(dieSide, numDie);
                            return;
                        }
                    }
                }    
            }
        }
    }

    /* Method Name: score35Kind()
     * Returns: N/A (Void)
     * Desc: Used to score 3, 4, and 5 of a kind. Called by the function that finds 3-5 kind.
     *       Had to make because I had trouble getting 2 trips to detect properly w/o breaking multiple rounds
     */
    public void score35Kind (int dieSide, int numDie) {
        removeDice(dieSide - 1);
        
        //Scoring
        if(numDie > 0) {
            //We subtract 2 here so the multiplication works out properly in scoring
            numDie = numDie - 2;
            if(dieSide == 1) {
                this.comboValue += 1000 + ((numDie - 1) * 100);
            } else if (dieSide == 2) {
                this.comboValue += numDie * 200;
            } else if (dieSide == 3) {
                this.comboValue += numDie * 300;
            } else if (dieSide == 4) {
                this.comboValue += numDie * 400;
            } else if (dieSide == 5) {
                this.comboValue += numDie * 500;
            } else if (dieSide == 6) {
                this.comboValue += numDie * 600;
            }
        }
    }

    /* Method Name: checkForFullHouse()
     * Returns: N/A (Void)
     * Desc: loops through numEachDie and checks if there are any die that appear 3 or 2 times.
     *       If there is a die that appears 3 times and another that appears 2x, adds appropriate points
     *       and removes used dice.
     */
    public void checkForFullHouse(int[] numEachDie) {
        Integer tripsIndex = -1, doubleIndex = -1;
        Boolean tripsFound = false, doubleFound = false;

        for (int i = 0; i < 6; i++) {
            if(numEachDie[i] == 3) {
                tripsFound = true;
                tripsIndex = i;
            }
            if(numEachDie[i] == 2) {
                doubleFound = true;
                doubleIndex = i;
            }
        }

        if(tripsFound && doubleFound) {
            this.comboValue = 1500;
            removeDice(tripsIndex);
            removeDice(doubleIndex);
        }
    }
}   
