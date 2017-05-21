package main.java;

/**
 * Created by ania on 12.05.17.
 */
public class Communication {
    public int registerToGame(String userName) {
        return ServerCommunication.getInstance().registerToGame(userName);
    }

    public int unregisterFromGame(String userName) {
        return ServerCommunication.getInstance().unregisterFromGame(userName);
    }

    public int makeAMove(String playerName, int x, int y) {
        return ServerCommunication.getInstance().makeAMove(playerName, x, y);
    }

    public int getCurrentSymbol()
    {
        return ServerCommunication.getInstance().getCurrentSymbol();
    }

    public String getCurrentPlayer()
    {
        return ServerCommunication.getInstance().getCurrentPlayer();
    }

    public boolean canStart()
    {
        return ServerCommunication.getInstance().canStart();
    }

    public int[] getFields()
    {
        return ServerCommunication.getInstance().getFields();
    }

    public int[] getPoints()
    {
        return ServerCommunication.getInstance().getPoints();
    }

    public String[] getUsers()
    {
        return ServerCommunication.getInstance().getUsers();

    }
}
