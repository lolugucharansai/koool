import java.io.*;
import java.net.*;

public class server1{
    public static void main(String[] args) {
        int port = 6789;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                // Receive data from client
                String data = reader.readLine();

                // Perform CRC algorithm
                int[] dataArr = new int[data.length()];
                for (int i = 0; i < data.length(); i++) {
                    dataArr[i] = Character.getNumericValue(data.charAt(i));
                }
                int[] divArr = {1,0, 1};
                int[] crcArr = CRC.senddata(dataArr, divArr, data.length(), divArr.length);
                String crcData = "";
                for (int i : crcArr) {
                    crcData += i;
                }

                // Send response to client
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(crcData);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
