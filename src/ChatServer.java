import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static final int DEFAULT_PORT = 1234;
    ServerSocket serverSocket;
    ArrayList<Client> clients = new ArrayList<>();

    ChatServer() throws IOException {
        serverSocket = new ServerSocket(DEFAULT_PORT);
    }

    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
