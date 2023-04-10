package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import network.HelperMethods;

public class User implements Runnable {
    private int currentLocation = 0; //사용자의 현재 위치
    private int diceNumber = 0;
    private String name = "";
    private boolean isSkipTurn = false; //스킵 여부
    private boolean isWinner = false;
    public SocketChannel socketChannel;

    public User() {
    }

    public User (String name, SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.name = name;
    }

    public String userName() {
        return name;
    }

    public int moveLocation(int location) {
        currentLocation += location;
        return currentLocation;
    }
    public void addDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }
    public int readCurrentLocation() {
        return currentLocation;
    }

    public void skipTurn() {
        this.isSkipTurn = !isSkipTurn;
    }

    public void winner() {
        isWinner = true;
    }

    public boolean readIsWinner() {
        return isWinner;
    }

    public boolean readSkipTurn() {
        return isSkipTurn;
    }

    public void sendMessage(String message) throws IOException {
        HelperMethods.sendMessage(socketChannel, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("님 ").append("\n");
        sb.append("주사위 번호 : ").append(diceNumber).append("\n");
        sb.append("현재 위치 : ").append(currentLocation).append("\n");
        return sb.toString();
    }

    @Override
    public void run() {
    }
}
