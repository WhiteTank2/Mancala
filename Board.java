import java.util.*;

public class Board
/*
 * creates and fills the board
 */
{
    private int TOTAL_ROW = 2;
    private int TOTAL_COL = 8;
    private String reg = "";
    private int PEBBLES = 3;
    private int[][] boardArray = new int[TOTAL_ROW][TOTAL_COL];
    private int value;
    private int homeCup;

    public void createMainBoard()
    /*
     * creates board and supplies them with value 3
     */
    {                
        for (int row = 0; row < TOTAL_ROW; row++)
        {            
            for (int col = 0; col < TOTAL_COL; col++)
            {
                boardArray[row][col] = PEBBLES;
            }

            switch (row)
            {
                case 0: boardArray[row][0] = 0;
                break;
                case 1: boardArray[row][7] = 0;
                break;
            }
        }
    }  

    public int seePebbles(int row, int cupNum)
    /*
     * checks the number of pepples in a cup
     * @param: row - what row the cup belongs to
     * @param: cupNum - what cup to check
     * @return: val - # of pebbles in a cup
     */
    {
        int val;
        val = boardArray[row][cupNum];

        return val;
    }

    public int getPebbles(int row, int cupNum)
    /*
     * grabs pebbles and empties cup
     * @param: row - what row to grab from
     * @param: cupNum - what cup to empty
     * @return: value - # of pebbles taken
     */
    {
        value = boardArray[row][cupNum];        
        boardArray[row][cupNum] -= value;

        return value;
    }

    public void addPebbles(int row, int cupNum)
    /*
     * adds pebbles to cups
     * @param: row - which row it will start in
     * @param: cupNum - where to drop the pebbles
     */
    {
        boardArray[row][cupNum] += 1;
    }

    public void addToHomeCup(int homeRow, int pebbles)
    /*
     * adds pebbles to homeCups
     * @param: homeRow - which row is a player's homeRow
     * @param: pebbles - # of pebbles to be deposited in homeCup
     */
    {
        if(homeRow == 0)
        {
            homeCup = 0;
        }
        else
        {
            homeCup = 7;
        }

        boardArray[homeRow][homeCup] += pebbles;
    }

    public int getHomeCupPoints(int playerNum)
    /*
     * get total points in homeCup, to date
     * @param: playerNum - to ascertain which one is the player's homeCup
     * @return: totalScore - player's current total score
     */
    {
        int totalScore;

        if(playerNum == 1)
        {
            totalScore = boardArray[playerNum][7];
        }
        else
        {
            totalScore = boardArray[0][0];
        }

        return totalScore;
    }

    public boolean gameOver()
    /*
     * checks if game is over by checking all cups if empty (if a row is empty and
     * other isn't
     * @return: whether game is over or not
     */
    {
        boolean gameOver;
        int totalRowOne = 0;
        int totalRowTwo = 0;
        int summationOne = 0;
        int summationTwo = 0;
        
        for (int col = 1; col < 7; col++)
        {
            totalRowOne = boardArray[0][col];
            summationOne += totalRowOne;                
        }

        for (int col = 1; col < 7; col++)
        {
            totalRowTwo = boardArray[1][col];
            summationTwo += totalRowTwo;
        }

        if (summationOne > 0 && summationTwo > 0)
        {
            gameOver = false;
        }
        else if(summationOne > 0 && summationTwo == 0)
        {
            boardArray[0][0] += summationOne;
            gameOver = true;
        }
        else if(summationOne == 0 && summationTwo > 0)
        {
            boardArray[1][7] += summationTwo;
            gameOver = true;
        }
        else
        {
            gameOver = true;
        }

        return gameOver;
    }

    private String createe(int rowNum)
    /*
     * creates string of a row
     * @param: rowNum - different formats per row
     * @return: string of a row
     */
    {
        String string = "";

        if (rowNum == 0)
        {
            for(int col = 0; col < 8; col++)
            {
                if (col == 0)
                {
                    string+= String.format("|%4d| ", boardArray[rowNum][col]); 
                }
                else if (col > 0 && col < 7)
                {
                    string+= String.format("|%4d", boardArray[rowNum][col]);
                }
            }
        }
        else if (rowNum == 1)
        {
            for (int col = 0; col < 8; col++)
            {
                if(col > 0 && col < 7)
                {
                    string+= String.format("|%4d",boardArray[rowNum][col]);
                }
                else if (col == 7)
                {
                    string+= String.format("| |%4d", boardArray[rowNum][col]);
                }
            }
        }

        return string;
    }

    public void printBoard()
    /*
     * prints the board on terminal window with updates
     */
    {
        System.out.print("\u000c");
        System.out.print("\t");
        for (int col = 1; col < TOTAL_COL - 1; col++)
        {
            System.out.print(String.format("%4d ", col));
        } 

        System.out.println();
        System.out.println("+----+ +----+----+----+----+----+----+");
        System.out.println(createe(0) + "|");
        System.out.println("+----+ +----+----+----+----+----+----+ +----+");
        System.out.println("       " + createe(1) + "|");
        System.out.println("       +----+----+----+----+----+----+ +----+"); ;
    }
}

