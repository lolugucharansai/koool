import java.util.*;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] data = getInputData(sc, "data");
        int[] div = getInputData(sc, "divisor");
        
        int[] crc = calculateCRC(data, div);
        System.out.println("CRC DATA : " + Arrays.toString(crc));
        
        int[] rem = divide(crc, div);
        System.out.print("Receiver remainder : " + Arrays.toString(rem));
    }

    public static int[] getInputData(Scanner sc, String dataType) {
        System.out.println("Enter the " + dataType + " size:");
        int size = sc.nextInt();
        
        System.out.println("Enter the " + dataType + ":");
        int[] inputData = new int[size];
        for (int i = 0; i < size; i++) {
            inputData[i] = sc.nextInt();
        }
    
        return inputData;
    }

    public static int[] calculateCRC(int[] data, int[] div) {
        int totalLength = data.length + div.length - 1;
        int[] dividend = new int[totalLength];
        System.arraycopy(data, 0, dividend, 0, data.length);
        
        int[] remainder = divide(dividend.clone(), div);
        
        for (int i = 0; i < data.length; i++) {
            dividend[i] = data[i];
        }
        
        for (int i = 0; i < remainder.length; i++) {
            dividend[data.length + i] = remainder[i];
        }
        
        return dividend;
    }
    

    public static int[] divide(int[] dividend, int[] divisor) {
        int currentIndex = 0;
    
        while (true) {
            while (currentIndex < dividend.length && dividend[currentIndex] == 0) {
                currentIndex++;
            }
    
            if (currentIndex >= dividend.length - divisor.length) {
                break;
            }
    
            for (int i = 0; i < divisor.length; i++) {
                dividend[currentIndex + i] ^= divisor[i];
            }
        }
    
        return Arrays.copyOfRange(dividend, dividend.length - divisor.length + 1, dividend.length);    }
    
}
