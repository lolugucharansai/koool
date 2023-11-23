import java.io.*;
import java.net.*;

public class Server_file_trans {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();

        File file = new File("source.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        OutputStream os = socket.getOutputStream();

        byte[] contents;
        long fileLength = file.length(); 
        long current = 0;

        while(current != fileLength){
            int size = 10000;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis.read(contents, 0, size); 
            os.write(contents);
        }   

        os.flush(); 
        socket.close();
        System.out.println("File sent successfully!");
    }
}