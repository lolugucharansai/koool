public class CRCErrorDetection {
    // CRC polynomial (x^3 + x + 1)
    private static final int POLYNOMIAL = 0b1011;
    
    public static void main(String[] args) {
        int message = 0b110110; // 6 bits message
        int key = 0b101; // 3 bits key
        
        int encodedMessage = encodeMessage(message, key);
        System.out.println("Encoded Message: " + Integer.toBinaryString(encodedMessage));
        
        boolean isCorrect = checkMessage(encodedMessage, key);
        if (isCorrect) {
            System.out.println("No errors detected.");
        } else {
            System.out.println("Error detected.");
        }
    }
    
    // Encodes the message with CRC algorithm
    private static int encodeMessage(int message, int key) {
        int keyLength = Integer.toBinaryString(key).length();
        int paddedMessage = message << (keyLength - 1); // Padding with zeroes
        
        int remainder = paddedMessage;
        int divisor = key << (keyLength - 1);
        
        for (int i = 0; i < Integer.toBinaryString(paddedMessage).length() - keyLength + 1; i++) {
            if ((remainder & (1 << (keyLength + i - 1))) != 0) {
                remainder ^= divisor;
            }
            divisor >>= 1;
        }
        
        return (paddedMessage | remainder);
    }
    
    // Checks if the message has errors using CRC algorithm
    private static boolean checkMessage(int encodedMessage, int key) {
        int keyLength = Integer.toBinaryString(key).length();
        
        int remainder = encodedMessage;
        int divisor = key << (keyLength - 1);
        
        for (int i = 0; i < Integer.toBinaryString(encodedMessage).length() - keyLength + 1; i++) {
            if ((remainder & (1 << (keyLength + i - 1))) != 0) {
                remainder ^= divisor;
            }
            divisor >>= 1;
        }
        
        return remainder == 0;
    }
}
