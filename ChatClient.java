import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException 
    {
        Socket socket = new Socket("10.1.88.86", 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        new Thread(new Runnable() {
            public void run() {
                try {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
        }

        stdIn.close();
        out.close();
        in.close();
        socket.close();
    }
}