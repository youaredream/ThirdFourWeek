import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import java.nio.charset.Charset;

public class TcpServer extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        NetServerOptions options = new NetServerOptions().setSsl(true).setKeyStoreOptions(
//                new JksOptions().
//                        setPath("/path/to/your/server=keystore.jks").
//                        setPassword("password-of-your-keystore")
//        );
        NetServer server = vertx.createNetServer();

        //Reading data from the socket
        server.connectHandler(socket ->{

            socket.handler(buffer -> {
                System.out.println("I received some bytes:" + buffer.length());
                System.out.println("data is :" + buffer.toString());
            });
            //Writing data to a socket
            Buffer buffer = Buffer.buffer().appendString("data received!");
            socket.write(buffer.toString());
//            socket.write("some data");
            socket.closeHandler(v -> {
                System.out.println("The socket has benn closed");
            });
            //Handling exceptions
            socket.exceptionHandler(e ->{
                System.out.println("there is a error"+ e.getCause());
            });

        });

        server.listen(4321,"localhost",res ->{
            if(res.succeeded()){
                System.out.println("Server is now listening!");
            }else{
                System.out.println("Failed to bind!");
            }
        });




    }
}
