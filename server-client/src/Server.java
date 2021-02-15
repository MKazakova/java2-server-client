import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String ... args) {
        //startTextServer();
        startObjectServer();
    }

    private static void startTextServer() {
        try(ServerSocket serverSocket = new ServerSocket(8180)){
            System.out.println("Server is listening");
            try(Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream())
            ){
                System.out.println("Client is connected");
                out.println("Hello client");
                out.flush();
                String message = "";
                do{
                    message = in.readLine();
                    out.println("Recieved : "+message);
                    out.flush();
                    //out.println("Recieved2 : "+message);
                    //out.flush();
                }while(!message.equalsIgnoreCase("stop"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startObjectServer() {
        try(ServerSocket serverSocket = new ServerSocket(8180);
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
            Cat recievedCat = (Cat)in.readObject();
            System.out.println(recievedCat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
