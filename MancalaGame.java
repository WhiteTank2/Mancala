import java.util.*;

public class MancalaGame
/*<main gamePlay>
 * @author Carille Mendoza
 * @version 1.0
 * Last Modified: Feb 17, 2014 - fixedGameOver
 */
{
    private Player player;
    private int chosenCup;
    private Scanner scanner = new Scanner(System.in);
    private boolean gameOver = true;

    public void run()
    /* 
     * starts and prompts game, check if game is over or not 
     */
    {
        Board buildBoard = new Board();
        int playerNum;
        gameOver = false;
        Player player1;
        Player player2;

        System.out.println("Welcome to Mancala! Play as player 1 or 2?");
        playerNum = scanner.nextInt();

        while (playerNum < 0 || playerNum > 2)
        {
            System.out.println("Inconceivable option. Play as player 1 or 2?");
            playerNum = scanner.nextInt();
        }

        buildBoard.createMainBoard();
        buildBoard.printBoard();

        while(!gameOver)
        {
            player1 = playerTurn(playerNum, buildBoard);
            play(player1, buildBoard);

            while (player1.turnOver(playerNum) != true)
            {
                play(player1, buildBoard);
            }

            if(player1.turnOver(playerNum) == true)
            {
                switch(playerNum)
                {
                    case 1: playerNum = 2;
                    break;
                    case 2: playerNum = 1;
                    break;
                }
            }

            player2 = playerTurn(playerNum, buildBoard);
            play(player2, buildBoard);

            while(player2.turnOver(playerNum) != true)
            {
                play(player2, buildBoard);
            }

            if(player2.turnOver(playerNum) == true)
            {
                switch(playerNum)
                {
                    case 1: playerNum = 2;
                    break;
                    case 2: playerNum = 1;
                    break;
                }
            }

            if (buildBoard.gameOver() == true)
            {
                gameOver = true;
            }
        }

        if(gameOver == true)
        {
            System.out.println("Game Over!");
            System.out.println("Player1: " + player.getTotalPoints(1));
            System.out.println("Player2: " + player.getTotalPoints(2));

            if(player.getTotalPoints(1) > player.getTotalPoints(2))
            {
                System.out.println("WINNER: PLAYER 1");
                System.out.println("Points: " + player.getTotalPoints(2));                
            }
            else if (player.getTotalPoints(1) < player.getTotalPoints(2))
            {
                System.out.println("WINNER: PLAYER 2");
                System.out.println("Points: " + player.getTotalPoints(2));
            }
            else
            {
                System.out.println("Draw." + player.getTotalPoints(1));
            }
        }
    }

    public Player playerTurn(int playerNumber, Board gameBoard)
    /*
     * creates a new player
     * @param playerNumber - either player 1 or two
     * @param gameBoard - reference to Board object
     * @return a new player
     */
    {
        player = new Player(playerNumber, gameBoard);

        return player;
    }

    public void play(Player player, Board gameBoard)
    /*
     * avoids code duplication, play method of player
     * @param player - which player is going to play
     * @param gameBoard - where the game is happening
     */
    {
        int chosenCup;
        gameBoard.printBoard();
        System.out.println("Player" + player.getPlayerNum() + ": Choose cup to start.");
        chosenCup = scanner.nextInt();

        while (chosenCup < 0 || chosenCup > 6)
        {
            System.out.print("Cup not found. Pick from cups 1 to 6 only.");
            chosenCup = scanner.nextInt();
        }

        player.traverseBoard(chosenCup);
        gameBoard.printBoard();    
    }
}