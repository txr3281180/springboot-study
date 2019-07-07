package spb.network.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

/**
 * Created by xinrui.tian on 2019/7/6.
 */
public class NettyClient {

    private final int port = 9000;

    @Test
    public void clientStart() {
        connect("127.0.0.1", port);
    }

    public void connect(String host, int port) {
        EventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        /*EventLoop的组*/
        b.group(worker);

        /*用于构造SocketChannel工厂*/
        b.channel(NioSocketChannel.class);

        /* 设置选项 */
        b.option(ChannelOption.SO_KEEPALIVE, true);

        /*自定义客户端Handle*/
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        //.addLast(new ClientHandler());
                        .addLast(new ClientTestHandler());
            }
        });

        /* 开启客户端监听*/
        try {
            ChannelFuture f = b.connect(host, port).sync();
            /*等待数据直到客户端关闭*/
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
