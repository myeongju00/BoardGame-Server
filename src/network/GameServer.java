package network;

import Model.User;
import Model.UserRepository;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class GameServer{
    public void startServer(int userNumber){
        try {
            //여기서 포트 열고 기다리기
            ServerSocketChannel sschannel = ServerSocketChannel.open();
            sschannel.socket().bind(new InetSocketAddress(3005));
            int i = 0;
            while( i < userNumber) {
                System.out.println("플레이어를 기다리는 중..(" + i + "/" + userNumber + ")");
                SocketChannel client = sschannel.accept();
                String name = HelperMethods.receiveMessage(client);
                informNew(name);

                User user = new User(name, client);
                Thread thread = new Thread(user);
                UserRepository.add(user);

                System.out.println("사용자 추가 : " + name);
                thread.start();
                i++;
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
    private void informNew(String name) throws IOException {
        for(User user : UserRepository.users) {
            HelperMethods.sendMessage(user.socketChannel, name + "is entered");
        }
    }
}
