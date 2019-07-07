package spb.network.netty.socket;

/**
 * Created by xinrui.tian on 2019/7/4.
 */
public class ServerBoot {

    public static void main(String[] args) {
        Server server = new Server(8000);
        server.start();
    }
}
