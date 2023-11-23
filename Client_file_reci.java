import java.io.*;
import java.net.*;

public class Client_file_reci {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);

        byte[] contents = new byte[10000];

        FileOutputStream fos = new FileOutputStream("destination.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();

        int bytesRead = 0;
        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead); 

        bos.flush();
        socket.close(); 

        System.out.println("File saved successfully!");
    }
}