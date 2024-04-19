/** Class Name: Player
 *  Desc: A player has a hand, a combo, a meld, and a point bank. Player has a variety of getters/setters used to call or get different methods
 *        and/or members from its own member variables classes.
 *  Notes: 0.0
 */
package edu.gonzaga.Farkle;

import java.util.ArrayList;

public class Player {
    private String name;
    private Hand hand;
    private Integer pointBank, roundPoints;
    public Hand meld;
    public Combos combo;

    /* Default Contructor for Player class
     * Calls intializes Hand, Combos, and Meld by calling their respective default contructors
     * Also sets pointBank = 0 and name = "Unknown Player"
     */
    public Player() {
        this.name = "Unkown Player";
        this.hand = new Hand();
        this.meld = new Hand(0);
        this.pointBank = 0;
        this.roundPoints = 0;
        this.combo = new Combos();
    }

    /* MethodName setName()
     * Returns: N/A (void)
     * Desc: Basic setter function to set player name = a given string
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /* MethodName getName()
     * Returns: a string containing player.name
     * Desc: Basic getter function to get player name
     */
    public String getName() {
        return this.name;
    }

    /* Method Name: updateComboDice()
     * Returns: N/A (Void)
     * Desc: Returns sets comboDice = dice in meld
     */
    public void updateComboDice(ArrayList<Die> dice) {
        this.combo.setDice(dice);
    }

    /* Method Name: getHand()
     * Returns: N/A (void)
     * Desc: Prints players hand in comma seperated order
     */
    public void getHand() {
        this.hand.printHand();
    }
    
    /* Method Name: getHandSize()
     * Returns: An int contaning hands size
     * Desc: Gets the size of players hand and returns it as an int
     */
    public int getHandSize() {
        return this.hand.dice.size();
    }

    /* Method Name: getHandDieSide()
     * Returns: An int
     * Desc: Accepts an integer dieNum to be used as an index to return the side value of the dieNum'th die in hands dice array
     */
    public int getHandDieSide(int dieNum) {
        return this.hand.getDieSide(dieNum);
    }

    /* Method Name: getMeldDieSide()
     * Returns: An int
     * Desc: Accepts an integer dieNum to be used as an index to return the side value of the dieNum'th die in melds dice array
     */
    public int getMeldDieSide(int dieNum) {
        return this.meld.getDieSide(dieNum);
    }

    /* Method Name: checkFarkle()
     * Returns: A boolean Value
     * Desc: Used to call hands isFarkle member function, returns the true/false value that isFarkle returns
     * Note: The consequence of using private member variables -\(-_-)/-
     */
    public boolean checkFarkle() {
        return (this.hand.isFarkle());
    }

    /* Method Name: updateBank()
     * Returns: N/A (void)
     * Desc: Adds a given int to players pointBank
     */
    public void updateBank(Integer num2Add) {
        this.pointBank += num2Add;
    }

    /* Method Name: setBank()
     * Returns: N/A (void)
     * Desc: Sets pointBank to a given int num
     */
    public void setBank(Integer num) {
        this.pointBank = num;
    }

    /* Method Name: getBank()
     * Returns: a Integer
     * Desc: Simple getter to get players pointBank value
     */
    public Integer getBank() {
        return this.pointBank;
    }

    /* Method Name: setRoundPoints()
     * Returns: N/A (void)
     * Desc: Sets roundPoints to a given int num
     */
    public void setRoundPoints(Integer num) {
        this.roundPoints = num;
    }

    /* Method Name: getRoundPoints()
     * Returns: a Integer
     * Desc: Simple getter to get players roundPoints value
     */
    public Integer getRoundPoints() {
        return this.roundPoints;
    }

    /* Method Name: updateRoundPoints()
     * Returns: N/A (void)
     * Desc: Adds a given int to players roundPoints
     */
    public void updateRoundPoints(Integer num2Add) {
        this.roundPoints += num2Add;
    }

    /* Method Name: swapHandandMeld();
     * Returns: N/A (Void)
     * Desc: Takes an index and swaps the dice at that index in players hand with the dice at that index in players meld
     * Uses a placeholder to temporarily hold hands die as it gets swapped.
     */
    public void swapHandandMeld(int index){
        Die placeholder;
        placeholder = this.hand.getDie(index);
        this.hand.changeDie(this.meld.getDie(index), index);
        this.meld.changeDie(placeholder, index);
    }

    /* Method Name: getCurrCombo()
     * Returns: an int
     * Desc: gets combos current comboValue
     */
    public int getCurrCombo() {
        return this.combo.getComboValue();
    }

    /* Method Name: checkCombos()
     * Returns: a boolean
     * Desc: runs the combo classes checkMeldForCombos() method and returns the result.
     */
    public boolean checkCombos() {
        return this.combo.checkMeldForCombos(hand.numEachDieArr);
    }

    /* Method Name:reRollHand()
     * Returns: N/A (Void)
     * Desc: Calls hand's reRoll() method to reroll hand with a given number of dice (should be 1-6)
     *       Updates roundPoints with combo value and Sets combo value == 0
     */
    public void reRollHand(Integer numDice) {
        this.roundPoints += combo.getComboValue();
        this.combo.setComboValue(0);
        this.hand.reRoll(numDice);
        this.hand.numEachDieArr = this.hand.numEachDie();
    }

    /* method Name: checkForHotHand() 
     * Returns: Boolean
     * Desc: Calls hand and combos methods to check if hand is a "Hot Hand", returns the true/false value that isHotHand returns
     */
    public boolean checkHotHand() {
        combo.setDice(hand.getDiceArrayList());
        if(combo.checkMeldForCombos(hand.numEachDieArr)){
            return true;
        } else {
            combo.setComboValue(0);
            return false;
        }
    }

    /* Method Name: clearMeld();
     * Returns: N/A (Void)
     * Desc: resets all values in melds array of dice to 0 by recalling dvc
     */
    public void clearMeld() {
        this.meld = new Hand(0);
    }

    public void setCurrCombo(int x) {
        this.combo.setComboValue(0);
    }
}
