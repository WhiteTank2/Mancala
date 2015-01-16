public class Player
/* shows the methods available to a player
 * @author Carille Mendoza
 * @version 1.0
 * Last Modified: Feb 17, 2014 - fixed capture method
 */
{
    private int playerNumber;
    private int homeRow;
    private Board gameBoard;
    private int chosenCupPebbles; 
    private boolean playerMoveOver = false;
    private int row;
    private int endCup;
    private int endRow;
    private int cupVal;

    public Player(int playerNum, Board gameBoard)
    /*
     * assigning values to instance variables
     */
    
    {
        this.playerNumber = playerNum;
        this.gameBoard = gameBoard;
        this.homeRow = playerNum;
    }

    public int getPlayerNum()
    /*
     * gets playerNumber
     * @return playerNumber in board's "vocabulary"
     */
    {
        return playerNumber;
    }

    private int getHomeRow(int playerNum)
    /*
     * gets a player's homeRow using its playerNumber
     * @param: playerNum - player number corresponds homeRow
     * @return: homeRow - based on playerNum
     */
    {
        if(playerNumber == 2)
        {
            homeRow = playerNumber - 2;
        }

        return homeRow;
    }

    public int getTotalPoints(int playerNum)
    /*
     * gets number of pebbles in home cup
     * @param: playerNum - points depends on playerNum
     * @return: no of pebbles in player's homeCup
     */    
    {
        int score = gameBoard.getHomeCupPoints(playerNumber);

        return score;
    }

    public int chooseCup(int cupNum)
    /*
     * picks pebbles from chosen cup
     * @param: cupNum - which cup to pick from
     * @return: # of pebbles in cup
     */
    {        
        homeRow = getHomeRow(homeRow);
        chosenCupPebbles = gameBoard.getPebbles(homeRow, cupNum);

        return chosenCupPebbles;
    }    

    public boolean turnOver(int playerNum)
    /*
     * checks if player's turn is over
     * @param: playerNum - check if particular player's turn is over
     * @return: result - either it's over or it's not
     */
    {
        boolean result;
        result = capture(endCup);

        return result;
    }

    public void traverseBoard(int cupNum)
    /*
     * traverses the board and adds pebbles where necessary
     */
    {
        row = getHomeRow(homeRow);
        chosenCupPebbles = gameBoard.getPebbles(homeRow, cupNum);

        if(playerNumber == 1)
        {
            for(int count = chosenCupPebbles; count > 0; count--)
            {
                if(cupNum < 6)
                {                    
                    gameBoard.addPebbles(row, cupNum + 1);
                }
                cupNum++;

                if(cupNum == 7)
                {
                    gameBoard.addPebbles(homeRow, cupNum);
                }

                if (count > 0 && cupNum > 7)
                {
                    switch(row)
                    {
                        case 1: row = 0;
                        cupNum = 6;
                        break;
                    }

                    for (int docount = count; docount > 0; docount--)
                    {
                        gameBoard.addPebbles(row, cupNum);
                        cupNum--;
                        count--;
                    }
                }
            }
        }
        else
        {
            for (int count = chosenCupPebbles; count > 0; count--)
            {
                if (cupNum > 1)
                {
                    gameBoard.addPebbles(row, cupNum - 1);
                }
                cupNum--;

                if(cupNum == 0)
                {
                    gameBoard.addPebbles(homeRow, cupNum);
                }

                if(count > 0 && cupNum < 0)
                {
                    switch(row)
                    {
                        case 0: row = 1;
                        cupNum = 1;
                        break;
                    }

                    for(int docount = count; docount > 0; docount--)
                    {
                        gameBoard.addPebbles(row, cupNum);
                        cupNum++;
                        count--;
                    }
                }
            }
        }

        endRow = row;
        endCup = cupNum;
    }

    public boolean capture(int cupNum)
    /*
     * checks if capture is possible and if so, gets the steal and puts it at
     * the correct homeCup
     * @return: boolean - if player's move is over or not depending on if
     * capture is possible
     */
    {
        boolean capture = false;

        if (endRow == homeRow)
        {
            if(cupNum != 7 && cupNum != 0)
            {
                cupVal = gameBoard.seePebbles(endRow, cupNum);

                if (cupVal > 1)
                {
                    playerMoveOver = true;
                }
                else
                {
                    switch(endRow)
                    {
                        case 0: endRow = 1;
                        break;
                        case 1: endRow = 0;
                        break;
                    }

                    cupVal = gameBoard.seePebbles(endRow, cupNum);

                    if (cupVal > 0)
                    {
                        playerMoveOver = false;
                        capture = true;
                    }
                    else
                    {
                        playerMoveOver = true;
                        capture = false;
                    }
                }
            }
            else
            {
                playerMoveOver = false;
            }
        }
        else
        {
            playerMoveOver = true;
        }

        if(capture == true)
        {
            cupVal = gameBoard.getPebbles(endRow, cupNum);

            switch(endRow)
            {
                case 0: endRow = 1;
                break;
                case 1: endRow = 0;
                break;
            }

            cupVal += gameBoard.getPebbles(endRow, cupNum);
            gameBoard.addToHomeCup(endRow, cupVal);
        }

        return playerMoveOver;
    }
}

