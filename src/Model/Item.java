package Model;

public enum Item {
    MOVE_THREE(5,3, "아이템을 획득했습니다 : 3칸 전진"),
    MOVE_BACK_THREE(10,-3, "아이템을 획득했습니다 : 3칸 후진"),
    SKIP_TURN(15,0, "아이템을 획득했습니다 : 한 턴 쉬기")
    ;
    private int location;
    private int number;
    private String message;

    Item(int location, int number, String message) {
        this.location = location;
        this.number = number;
        this.message = message;
    }

    public static Item calculateItem(int location) {
        for(Item item : Item.values()) {
            if(item.location == location) {
                System.out.println(item.message);
                return item;
            }
        }
        return null;
    }

    public int move() {
        return number;
    }

    public String message() {
        return message;
    }
}
