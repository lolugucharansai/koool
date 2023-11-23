import java.util.*;
public class CRC_test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the data size:");
        int n = sc.nextInt();
        System.out.println("Enter the data :");
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }
        System.out.println("Enter the divisor size:");
        int m = sc.nextInt();
        int[] div = new int[m];
        System.out.println("Enter the divisor:");
        for (int i = 0; i < m; i++) {
            div[i] = sc.nextInt();
        }
        int[] crc = senddata(data, div, n, m);
        System.out.println("CRC DATA : " + Arrays.toString(crc));
        int[] rem = receivedata(crc, div);
        System.out.print("receiver remainder : " + Arrays.toString(rem));
    }

    public static int[] senddata(int[] data, int[] div, int n, int m) {
        int[] d = new int[m + n - 1];
        int[] temp = new int[m + n - 1];
        for (int i = 0; i < n; i++) {
            d[i] = data[i];
            temp[i] = data[i];
        }
        int[] rem = divide(temp, div);
        int[] crc = new int[m + n - 1];
        for (int i = 0; i < n + m - 1; i++) {
            crc[i] = d[i] | rem[i];
        }
        return crc;
    }

    public static int[] receivedata(int[] crc, int[] div) {
        return divide(crc, div);
    }

    public static int[] divide(int[] temp, int[] div) {
        int cur = 0;
        while (true) {
            for (int i = 0; i < div.length; i++) {
                temp[cur + i] = temp[cur + i] ^ div[i];
            }
            while (temp[cur] == 0 && cur < temp.length - 1)
                cur++;
            if (temp.length - cur < div.length)
                break;
        }
        return temp;
    }
}