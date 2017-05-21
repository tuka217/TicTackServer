package main.java;

import java.util.*;

/**
 * Created by ania on 21.05.17.
 */
public class ServerCommunication {
    private List<String> userNames = new ArrayList<>();
    private Map<AbstractMap.SimpleImmutableEntry<String, String>, Game> userGames = new HashMap<>();
    private Game game;
    private static ServerCommunication instance;
    private String[] users;

    private final Object registerToGameLock = new Object();
    private final Object getPointsLock = new Object();
    private final Object getUserLock = new Object();
    private final Object getCurrentSymbol = new Object();
    private final Object makeAMoveLock = new Object();
    private final Object startLock = new Object();
    private final Object getFieldLock = new Object();
    private final Object getUsersLock = new Object();
    private final Object unregisterFromServiceLock = new Object();

    public static ServerCommunication getInstance() {
        if (instance == null) {
            instance = new ServerCommunication();
        }
        return instance;
    }

    public int registerToGame(String userName) {
        synchronized(registerToGameLock) {
            if(userNames.size() < 2) {
                userNames.add(userName);
            }

            if(userNames.size() == 2) {
                game.setUsers(userNames);
            }
        }
        return 1;
    }

    public int unregisterFromGame(String userName) {
        synchronized(unregisterFromServiceLock) {
            userNames.remove(userName);

            Set<AbstractMap.SimpleImmutableEntry<String, String>> keys = userGames.keySet();
            ArrayList<AbstractMap.SimpleImmutableEntry<String, String>> chatsToRemove = new ArrayList<>();

            for (AbstractMap.SimpleImmutableEntry<String, String> key : keys) {
                if (key.getKey().equals(userName) || key.getValue().equals(userName)) {
                    chatsToRemove.add(key);
                }
            }

            for (AbstractMap.SimpleImmutableEntry<String, String> key : chatsToRemove) {
                userGames.remove(key);
            }
        }

        return 1;
    }

    public int makeAMove(String playerName, int x, int y) {
        synchronized(makeAMoveLock) {
            game.takeATurn(playerName, x, y);
        }

        return 1;
    }

    public int getCurrentSymbol()
    {
        synchronized (getCurrentSymbol) {
            return game.getCurrentMove();
        }
    }

    public String getCurrentPlayer()
    {
        synchronized (getUserLock) {
            return game.getCurrentPlayer();
        }
    }

    public boolean canStart()
    {
        synchronized (startLock) {
            if (userNames.size() == 2) {
                return true;
            }

            return false;
        }
    }

    public int[] getFields() {
        synchronized(getFieldLock) {
            initGame();
            return game.getGameFields();
        }
    }

    public int[] getPoints() {
        synchronized(getPointsLock) {
            initGame();
            return game.getPoints();
        }
    }

    public String[] getUsers()
    {
        synchronized(getUsersLock) {
            initUsers();
            for (int i = 0; i < userNames.size(); i ++) {
                users[i] = userNames.get(i);
            }

            return users;
        }
    }

    private void initUsers()
    {
        if (users == null) {
            users = new String[2];
            users[0] = " ";
            users[1] = " ";
        }
    }

    private void initGame() {
        if (game == null) {
            game = new Game();
        }
    }
}
