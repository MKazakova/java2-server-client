import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultipleServer {
    public static void main(String[] args) {
        startTextServer();
    }

    private static void startTextServer() {
        try(ServerSocket serverSocket = new ServerSocket(8180)){
            System.out.println("Server is listening");
            while(true) {
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())){
            System.out.println("Client is connected");
            out.println("Hello client");
            out.flush();
            String message = "";
            do{
                message = in.readLine();
                out.println("Recieved : "+message);
                out.flush();
            }while(!message.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
                System.out.println("Client leaved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
