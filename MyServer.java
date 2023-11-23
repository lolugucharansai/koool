import java.net.*;  
import java.io.*;  
class MyServer{  
    public static void main(String args[])throws Exception{  
        ServerSocket ss=new ServerSocket(3333);  
        Socket s=ss.accept();  
        System.out.println("Server is running...");

        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        dout.writeUTF("Hello Client Lets start the chat");
        String str="",str2="";  
        while(!str.equals("stop"))
        {  
            str=din.readUTF();  
            System.out.println("client says: "+str);  
            System.out.println("Enter your message: ");
            str2=br.readLine();  
            try {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            dout.writeUTF(str2);  
            dout.flush();  
        }  
        din.close();  
        s.close();  
        ss.close();  
    }
}