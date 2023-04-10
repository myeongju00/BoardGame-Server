package controller;

import Model.UserRepository;
import network.GameServer;
import service.BoardGame;
import utils.Validator;
import view.InputView;
import view.OutputView;

public class GameController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Validator validator = new Validator();
    BoardGame boardGame = new BoardGame();
    GameServer gameServer = new GameServer();
    boolean gameEndFlag = false;
    int userNumber = 1;
    public void setGameSettings() {
        userNumber = setUserNumber(inputView.inputGameSetting());
        startServer();
    }
    private int setUserNumber(int inputGameSetting) {
        try {
            validator.validateNumber(userNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(UserRepository.users.get(0), e.getMessage());
            System.out.println(e.getMessage());
            inputGameSetting = inputView.inputGameSetting();
            setUserNumber(inputGameSetting);
        }
        return inputGameSetting;
    }
    public void startServer() {
        gameServer.startServer(userNumber);
        startGame();
    }
    public void startGame() {
        boardGame.makeGameBoard();
        int indexNumber = userNumber;
        while (!boardGame.isFinsh()) {
            if(gameEndFlag) break;
            rollingDice(indexNumber % userNumber);
            indexNumber++;
        }
        outputView.sendResultMessage(boardGame.printResult());
        outputView.sendResultMessage("exit");
    }
    public void rollingDice(int index){
        String rollingMessage = setRollingMessage(index, inputView.inputRollingOrQuit(
                UserRepository.users.get(index), gameServer));
        if(rollingMessage.equals("rolling")) {
            boardGame.move(UserRepository.users.get(index));
        }
        if(rollingMessage.equals("quit")) gameEndFlag = true;
        outputView.sendResultMessage(boardGame.printStatus(UserRepository.users.get(index)));
    }
    private String setRollingMessage(int index, String rollingOrQuit){
        try {
            validator.validateRolling(rollingOrQuit);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(UserRepository.users.get(index), e.getMessage());
            System.out.println(e.getMessage());
            rollingOrQuit = inputView.inputRollingOrQuit(
                    UserRepository.users.get(index), gameServer);
            setRollingMessage(index, rollingOrQuit);
        }
        return rollingOrQuit;
    }
}