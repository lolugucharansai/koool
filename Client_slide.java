import java.io.*;
import java.net.*;
import java.util.*;

public class Client_slide {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        int windowSize = 4;
        int base = 0;
        int nextSeqNum = 0;
        int numPackets = 10;
        int[] acks = new int[numPackets];
        boolean[] acked = new boolean[numPackets];
        for (int i = 0; i < numPackets; i++) {
            acks[i] = -1;
            acked[i] = false;
        }
        while (base < numPackets) {
            while (nextSeqNum < base + windowSize && nextSeqNum < numPackets) {
                out.writeInt(nextSeqNum);
                out.flush();
                System.out.println("Sent packet " + nextSeqNum);
                nextSeqNum++;
            }
            try {
                socket.setSoTimeout(1000);
                int ack = in.readInt();
                System.out.println("Received ACK " + ack);
                if (acks[ack] == -1) {
                    acks[ack] = ack;
                    acked[ack] = true;
                }
                if (ack == base) {
                    while (acked[base]) {
                        base++;
                    }
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout occurred, resending packets");
                nextSeqNum = base;
            }
        }
    }
}
