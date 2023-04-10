package service;

import Model.Board;
import Model.Dice;
import Model.Item;
import Model.User;
import Model.UserRepository;
import view.OutputView;

public class BoardGame {
    private static int finishNum = 25;
    private static Dice dice = new Dice();
    private static Board board;


    //TODO(보드판 생성하기)
    public void makeGameBoard() {
        int boardSize = (int) Math.sqrt(finishNum);
        board = new Board(boardSize);
    }

    //종료 조건
    public boolean isFinsh() {
        for(User user : UserRepository.users) {
            if(user.readIsWinner()) {
                return true;
            }
        }
        return false;
    }

    //TODO("주사위 굴린만큼 이동하기")
    public void move(User user){
        if(user.readSkipTurn()) {
            user.skipTurn();
            return;
        }
        int diceNumber = rollingDice();
        int currentLocation = user.readCurrentLocation() + diceNumber;
        user.addDiceNumber(diceNumber);

        //if 아이템이 존재한다면
        Item item = Item.calculateItem(currentLocation);
        if (item != null) {
            if(item == Item.SKIP_TURN) {
                user.skipTurn();
                return;
            }
            OutputView outputView = new OutputView();
            outputView.sendResultMessage(item.message() + "\n");
            user.moveLocation(item.move());
        }
        user.moveLocation(diceNumber);

        if(user.readCurrentLocation() >= finishNum) {
            System.out.println("finish");
            user.winner();

            for (User u : UserRepository.users) {
                System.out.println(u.toString() + u.readIsWinner());
            }
        }
    }

    /* 주사위 굴리기 */
    public int rollingDice() {
        return dice.generate();
    }

    public String printStatus(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(board.toString()).append("\n");
        sb.append(user.toString());
        return sb.toString();
    }

    public String printResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- 게임 결과 --- ").append("\n");
        for (User user : UserRepository.users) {
            if(user.readIsWinner()) {
                sb.append("winner : ").append(user.userName());
            }
        }
        return sb.toString();
    }
}