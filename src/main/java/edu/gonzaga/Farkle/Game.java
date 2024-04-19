/** Class Name: Game
 *  Desc: Manages a game of farkle! A couple methods that mostly just output formating stuffs to console
 *        Has a round and calls round.newRound() to initiate gameplay!
 *  Notes: []_[]
 */
package edu.gonzaga.Farkle;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Round round;
    private Integer numPlayers;

    // Game default value contstructor
    public Game() {
        round = new Round();
        numPlayers = 1;
    }

    /* Method Name: startGame()
     * Returns: N/A (void)
     * Desc: Displays an ASCII startup banner, asks player for a username
     */
    public void startGame() {
        Integer pointTotal = 0, playerPoints;
        boolean contRound = true;
        this.displayBanner();
        this.setNumPlayers();
        pointTotal = this.getPointTotal();
        if(this.numPlayers == 1) {
            this.getPlayerNameFromUser();
            playerPoints = round.getPlayerPoints();
            while(playerPoints < pointTotal) {
                contRound = round.newRound();
                if(contRound == false) {
                   break;
                }
                playerPoints = round.getPlayerPoints();
                this.round.newHand();
                this.round.setRoundPoints(0);
            }
            System.out.println("Game Over!");
            System.out.print("Final Score: ");
            System.out.println(round.getPlayerPoints());
        } else {
            doMultiplayer(pointTotal);
        }
        
    }

    /* Method Name: displayBanner()
     * Returns: N/A (Void)
     * Desc: Outputs an ASCII Startup banner to console
     */
    public void displayBanner() {
        System.out.println("*****************************************");
        System.out.println("*            CPSC 224 Farkle            *");
        System.out.println("*        Created By: McEwan Bain        *");
        System.out.println("*****************************************");
    }

    /* Method Name: getPlayerNameFromUser()
     * Returns: N/A (Void)
     * Desc: Gets a user inputed player name and sets round.user.name = that input using a round method (setPlayerName)
     *       If user's inputed name = "", does nothing (Keeps default name of "Unkown Player")
     */
    public void getPlayerNameFromUser() {
        //Cant close this or i close System.in i guess ¯\_(ツ)_/¯
        Scanner userIn = new Scanner(System.in);
        String name = "";

        System.out.print("Username: ");

        name = userIn.nextLine();
        //Making sure name isn't an empty string
        if(name.length() != 0) {
            round.setPlayerName(name);
        }

        //For formating :P
        System.out.println();
    }

    /* Method Name: getPlayerNameFromUser()
     * Returns: a string
     * Desc: Gets a user inputed player name and returns it so that player name can be set for multiplayer
     *       If entered name is default or invalid, returns "Unknown Player" + player number;
     */
    public String getPlayerNameFromUser(int playerNum) {
        //Cant close this or i close System.in i guess ¯\_(ツ)_/¯
        Scanner userIn = new Scanner(System.in);
        String name = "";

        System.out.print("Username: ");

        name = userIn.nextLine();
        //Making sure name isn't an empty string
        if(name.length() != 0) {
            //Nothin
        } else {
            playerNum += 1;
            name = "Unkown Player " + playerNum;
        }
        return name;
    }

    /* Method Name: getPointTotal()
     * Returns: Integer
     * Desc: Gets a user inputed point total and uses that to play rounds until a player reaches that 
     *       point total (default of 10,000). Returns that number as an Integer
     */
    public Integer getPointTotal() {
        Scanner userIn = new Scanner(System.in);
        String temp = "";
        Integer pointTotal = 10000;
        
        System.out.print("How many points would you like to play to? ");
        temp = userIn.nextLine();
        if(temp.length() > 0) {
            pointTotal = Integer.parseInt(temp);
            if(pointTotal > 0) {
                return pointTotal;
            }
        }
        return 10000;
    }


/* Method Name: setNumPlayers()
 * Returns: N/A (Void)
 * Desc: Gets a user inputed number of players and sets games member numPlayers to that number. If that number is invalid (<1) defaults to 1
 */

 public void setNumPlayers() {
    Scanner userIn = new Scanner(System.in);
        Integer NewNumPlayers = 0;

        System.out.print("How many players do you have: ");

        NewNumPlayers = userIn.nextInt();
        //Making sure we get a valid number of player
        if(numPlayers > 0) {
            this.numPlayers = NewNumPlayers;
        } else {
            System.out.println("Invalid Input, Defaulting to 1 Player...");
            this.numPlayers = 1;
        }
    }

    /* Method Name: doMultiplayer()
     * Returns: N/A (Void)
     * Desc: Runs a multiplayer game of farkle
     *       creates an array list of players and sets the names for each of those players
     *       Loops through the array list and sets rounds player to a player in list, then after round updates players info in list
     *       After each players round, checks if they have reached point total, if not continues
     * Note: Believe it or not, the following code came to me in a dream at 3 AM October 29th 2023 (the day this is due):P
     */
    public void doMultiplayer(int pointTotal) {
        Integer currPlayerPoints = 0, winningPlayerIndex = 0;
        Boolean contRound = true, pointTotalReached = false, exit = false;
        ArrayList<Player> players = new ArrayList<>();

        //Getting player names:
        for(int i = 0; i < numPlayers; i++) {
            Player newPlayer = new Player();
            newPlayer.setName(getPlayerNameFromUser(i));
            players.add(newPlayer);
        }


        while(!exit) {
            for(int i = 0; i < numPlayers; i++) {
                //Updating which players turn it is
                round.setPlayer(players.get(i));

                //Play a round like normal 
                contRound = round.newRound();
                if(contRound == false) {
                    exit = true;
                    break;
                }
                currPlayerPoints = round.getPlayerPoints();
                this.round.newHand();
                this.round.setRoundPoints(0);
                players.set(i, round.getPlayer());
                if(currPlayerPoints >= pointTotal) {
                    pointTotalReached = true;
                    exit = true;
                    winningPlayerIndex = i;
                    break;
                }
                System.out.println();
            }
            System.out.println("Current Scores:");
            for(int i = 0; i < numPlayers; i++) {
                System.out.print(players.get(i).getName());
                System.out.print(": ");
                System.out.println(players.get(i).getBank());
            }
            System.out.println();
        }
        System.out.println("Game Over!");
        System.out.println("Final Scores: ");
        for(int i = 0; i < numPlayers; i++) {
            System.out.print(players.get(i).getName());
            System.out.print(": ");
            System.out.println(players.get(i).getBank());
        }
        if(pointTotalReached) {
            System.out.print(players.get(winningPlayerIndex).getName());
            System.out.println(" wins!");
        }
    }
}