import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        int windowSize = 4;
        int base = 0;
        int nextSeqNum = 0;
        int numFrames = 10;
        int[] received = new int[numFrames];

        while (base < numFrames) {
            while (nextSeqNum < base + windowSize && nextSeqNum < numFrames) {
                if (in.available() > 0) {
                    int seqNum = in.readInt();
                    if (seqNum >= base && seqNum < base + windowSize) {
                        received[seqNum] = 1;
                        out.writeInt(seqNum);
                    }
                }
                nextSeqNum++;
            }
        }

        socket.close();
    }
}