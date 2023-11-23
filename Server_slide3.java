import java.io.*;
import java.net.*;

public class Server_slide3 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        int windowSize = 4;
        int base = 0;
        int nextSeqNum = 0;
        int numPackets = 10;
        boolean[] receivedPackets = new boolean[numPackets];
        for (int i = 0; i < numPackets; i++) {
            receivedPackets[i] = false;
        }
        System.out.print("Enter the window size: ");
        windowSize = in.readInt();
        while (base < numPackets) {
            try {
                socket.setSoTimeout(1000);
                int seqNum = in.readInt();
                System.out.println("Received packet " + seqNum);
                if (!receivedPackets[seqNum]) {
                    receivedPackets[seqNum] = true;
                    if (seqNum >= base && seqNum < base + windowSize) {
                        out.writeInt(seqNum);
                        out.flush();
                        System.out.println("Sent ACK " + seqNum);
                        if (seqNum == base) {
                            while (receivedPackets[base]) {
                                base++;
                            }
                        }
                    } else {
                        System.out.println("Packet discarded");
                    }
                } else {
                    System.out.println("Duplicate packet received");
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout occurred, resending packets");
            }
        }
    }
}
