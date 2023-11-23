import java.io.*;
import java.net.*;
import java.util.Random;
import Acknowledgment;

public class Stop_and_Wait {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        Random random = new Random();
        double lossProbability = 0.2; // 20% packet loss probability

        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            int sequenceNumber = 0;
            int messageNumber = 1;

            while (true) {
                // Simulate sending a message
                String message = "Message " + messageNumber;
                MessagePacket packet = new MessagePacket(sequenceNumber, message);

                // Simulate packet loss
                if (random.nextDouble() < lossProbability) {
                    System.out.println("Message " + messageNumber + " is lost.");
                } else {
                    // Send the packet to the server
                    out.writeObject(packet);
                    System.out.println("Sent: " + message);

                    // Receive acknowledgment from the server
                    Acknowledgment ack = (Acknowledgment) in.readObject();
                    System.out.println("Received Acknowledgment for message " + messageNumber);
                    sequenceNumber = 1 - sequenceNumber;
                    messageNumber++;
                }

                // Simulate a waiting time
                int waitingTime = random.nextInt(5); // Random wait time between 0 to 4 seconds
                System.out.println("Waiting for " + waitingTime + " seconds...");
                Thread.sleep(waitingTime * 1000);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class Stop_and_Wait {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        Random random = new Random();
        double lossProbability = 0.2; // 20% packet loss probability

        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            int sequenceNumber = 0;
            int messageNumber = 1;

            while (true) {
                // Simulate sending a message
                String message = "Message " + messageNumber;
                MessagePacket packet = new MessagePacket(sequenceNumber, message);

                // Simulate packet loss
                if (random.nextDouble() < lossProbability) {
                    System.out.println("Message " + messageNumber + " is lost.");
                } else {
                    // Send the packet to the server
                    out.writeObject(packet);
                    System.out.println("Sent: " + message);

                    // Receive acknowledgment from the server
                    Acknowledgment ack = (Acknowledgment) in.readObject();
                    System.out.println("Received Acknowledgment for message " + messageNumber);
                    sequenceNumber = 1 - sequenceNumber;
                    messageNumber++;
                }

                // Simulate a waiting time
                int waitingTime = random.nextInt(5); // Random wait time between 0 to 4 seconds
                System.out.println("Waiting for " + waitingTime + " seconds...");
                Thread.sleep(waitingTime * 1000);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
