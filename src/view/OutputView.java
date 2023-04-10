package view;

import Model.User;
import Model.UserRepository;
import network.HelperMethods;

public class OutputView {
    public void printErrorMessage(User user, String message) {
        HelperMethods.sendMessage(user.socketChannel, "rolling 혹은 quit 를 입력해주세요.");
    }
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void sendResultMessage(String message) {
        for(User user : UserRepository.users) {
            HelperMethods.sendMessage(user.socketChannel, message);
        }
    }
}
