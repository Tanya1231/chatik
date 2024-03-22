import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ServerSocket serverSocket;
    ArrayList<Client> clients = new ArrayList<>();
    // создаем серверный сокет на порту 1234


    ChatServer() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
