/** Assignment: Farkle! v1.0
 *  Name: McEwan Bain
 *  Class: 224 01 Software Development
 *  Notes: :P
 */

package edu.gonzaga.Farkle;

/*
*  This is the main class for the Farkle project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

/** Main program class for launching Farkle program. */
public class Farkle {
    // This main is where your Farkle game starts execution for general use.
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
