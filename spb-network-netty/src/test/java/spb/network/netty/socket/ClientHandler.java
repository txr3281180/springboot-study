package spb.network.netty.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by xinrui.tian on 2019/7/4.
 */
public class ClientHandler {

    public static final int MAX_DATA_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("客户端接入");
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart(){
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                byte [] bytes = new byte[MAX_DATA_LEN];
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    String message = new String(bytes, 0, len);
                    System.out.println("客户端传来消息: " + message);
                    socket.getOutputStream().write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
