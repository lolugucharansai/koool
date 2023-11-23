import java.util.*;
public class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int []data=getInputData(sc,"data");
        int []div=getInputData(sc,"divisor");
        int []crc=calculateCRC(data,div);
        System.out.println("CRC data:"+Arrays.toString(crc));
        int []rem=divide(crc,div);
        System.out.println("Receiver remainder: "+Arrays.toString(rem));
    }
    public static int[] getInputData(Scanner sc,String datatype){
        System.out.println("enter the "+datatype+"Size");
        int size=sc.nextInt();
        System.out.println("Enter the "+datatype+":");
        int []inputData=new int[size];
        for(int i=0;i<size;i++){
            inputData[i]=sc.nextInt();
        }
        return inputData;
    }
    public static int[] calculateCRC(int []data,int []div){
        int totallength=data.length + div.length -1;
        int dividend[]=new int[totallength];
        System.arraycopy(data,0,dividend,0,data.length);
        int []remainder=divide(dividend.clone(),div);
        for(int i=0;i<data.length;i++){
            dividend[i]=data[i];
        }
        return dividend;
    }
    public static int[] divide(int []dividend,int []divisor){
        int ci=0;
        while(true){
            for(int i=0;i<divisor.length;i++){
                dividend[ci+i]^=divisor[i];
            }
            while(dividend[ci]==0 && ci<dividend.length-1){
                ci++;
            }
            if(dividend.length-ci<divisor.length){
                break;
            }
            
        }
        return dividend;
    }
}