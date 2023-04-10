package view;

import Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import network.GameServer;
import network.HelperMethods;

public class InputView {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //주사위를 굴릴껀지, 게임을 나갈것인지 물어보는 메서드
    public String inputRollingOrQuit(User user, GameServer gameServer) {
        HelperMethods.sendMessage(user.socketChannel, "rolling 혹은 quit 를 입력해주세요.");
        String message = HelperMethods.receiveMessage(user.socketChannel);
        return message;
    }
    public int inputGameSetting(){
        try {
            System.out.print("접속할 사용자 수를 입력해주세요 : ");
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
