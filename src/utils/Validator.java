package utils;


public class Validator {
    public void validateRolling(String input){
        if(!(input.equals("rolling") || input.equals("quit"))) {
            throw new IllegalArgumentException("rolling 혹은 quit 를 입력해주세요");
        }
    }

    public void validateNumber(int number) {
        if(number == 0) {
            throw new IllegalArgumentException("사용자 숫자는 0 이상이어야 합니다. ");
        }
    }
}
