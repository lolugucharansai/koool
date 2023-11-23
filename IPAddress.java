import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPAddress {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            short netmask = network.getInterfaceAddresses().get(0).getNetworkPrefixLength();

            System.out.println("Current IP address : " + ip.getHostAddress());
            System.out.println("Network mask : " + netmask);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the subnet bits: ");
            int subnetBits = scanner.nextInt();

            String subnetIP = getSubnetIP(ip.getHostAddress(), subnetBits);
            System.out.println("Subnet IP address : " + subnetIP);

        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    private static String getSubnetIP(String ip, int subnetBits) {
        String[] parts = ip.split("\\.");
        int firstPart = Integer.parseInt(parts[0]);
        // a=[ip[i]&sbnet[i] for i in range(len(ip))]
        // print(a)
        if (firstPart < 128) {
            return "Class A, Mask: 255.0.0.0";
        } else if (firstPart < 192) {
            return "Class B, Mask: 255.255.0.0";
        } else if (firstPart < 224) {
            return "Class C, Mask: 255.255.255.0";
        } else if (firstPart < 240) {
            return "Class D";
        } else {
            return "Class E";
        }
      
    }
}