package main.java;

import java.util.List;

/**
 * Created by ania on 21.05.17.
 */
public class Game
{
    private int[][]  gameFields;
    private int currentMove;

    private String firstPlayerName;
    private String secondPlayerName;

    private int firstPlayerPoints;
    private int secondPlayerPoints;

    public Game() {
        gameFields = new int[3][3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 gameFields[i][j] = 0;
            }
        }
        
        currentMove = 1;
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
    }

    public void setUsers(List<String> userNames) {
        firstPlayerName = userNames.get(0);
        secondPlayerName = userNames.get(1);
    }

    public void takeATurn(String player, int x, int y) {
        int position =  gameFields[x][y];
        if (position == 0) {
            if (currentMove == 1 && player.equals(firstPlayerName)) {
                 gameFields[x][y] = 1;
                currentMove = 2;
            }
            else if (currentMove == 2 && player.equals(secondPlayerName)) {
                 gameFields[x][y] = 2;
                currentMove = 1;
            }

            if (currentSetupWins()) {
                System.out.println("WINNER: " + currentMove);
                if (currentMove == 1) {
                    secondPlayerPoints++;
                }
                else {
                    firstPlayerPoints++;
                }

                resetGame();
            }
            else if (isDraw()) {
                System.out.println("DRAW");
                resetGame();
            }
        }
    }

    public int[] getGameFields() {
        int[] symbolsInt = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                symbolsInt[3* i + j] = gameFields[i][j];
            }
        }
        return symbolsInt;
    }

    public int[] getPoints() {
        int[] points = new int[2];
        points[0] = firstPlayerPoints;
        points[1] = secondPlayerPoints;
        return points;
    }

    public int getCurrentMove()
    {
        return currentMove;
    }

    public String getCurrentPlayer()
    {
        if(currentMove == 1) {
            return firstPlayerName;
        }

        return secondPlayerName;
    }

    private boolean currentSetupWins() {
        if ( gameFields[0][0] ==  gameFields[1][0] &&  gameFields[1][0] ==  gameFields[2][0] &&  gameFields[0][0] != 0 ||
                 gameFields[0][1] ==  gameFields[1][1] &&  gameFields[1][1] ==  gameFields[2][1] &&  gameFields[0][1] != 0 ||
                 gameFields[0][2] ==  gameFields[1][2] &&  gameFields[1][2] ==  gameFields[2][2] &&  gameFields[0][2] != 0 ||

                 gameFields[0][0] ==  gameFields[0][1] &&  gameFields[0][1] ==  gameFields[0][2] &&  gameFields[0][0] != 0 ||
                 gameFields[1][0] ==  gameFields[1][1] &&  gameFields[1][1] ==  gameFields[1][2] &&  gameFields[1][0] != 0 ||
                 gameFields[2][0] ==  gameFields[2][1] &&  gameFields[2][1] ==  gameFields[2][2] &&  gameFields[2][0] != 0 ||

                 gameFields[0][0] ==  gameFields[1][1] &&  gameFields[1][1] ==  gameFields[2][2] &&  gameFields[0][0] != 0 ||
                 gameFields[2][0] ==  gameFields[1][1] &&  gameFields[1][1] ==  gameFields[0][2] &&  gameFields[2][0] != 0

                ) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( gameFields[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 gameFields[i][j] = 0;
            }
        }
        currentMove = 1;
    }
}
