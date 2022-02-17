import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

import java.nio.charset.StandardCharsets;

public class TcpClient {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        NetClientOptions options = new NetClientOptions().
//                setReconnectAttempts(10).
//                setReconnectInterval(500).
//                setSsl(true).setTrustAll(true);
        NetClient client = vertx.createNetClient();

        client.connect(4321, "localhost",res ->{
           if(res.succeeded()){
               System.out.println("Connected!");
               NetSocket socket = res.result();
               //writing
               socket.write(Buffer.buffer("I am TCP Client！！"));
               socket.handler(buffer -> {
                   System.out.println(buffer.toString(StandardCharsets.UTF_8));
               });
           }
           else {
               System.out.println("Failed to connect" + res.cause().getMessage());
           }
        });
    }
}
