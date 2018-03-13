package glasses.lib.math;

public class Calculator{
    
    private static int valCount =0;
    
    private static int[] convert(int arr[][]){
        int temp[]=new int[arr.length*arr[0].length];
        for(int i =0;i<arr.length;i++){
            for(int j =0;j<arr.length;j++){
                temp[i*10+j]=arr[i][j];
            }
        }
        return temp;
    }
    
    public static double mean(int arr[],boolean flag){
        double temp=0;
        int ctr=0;
        for(int i =0;i<arr.length;i++){
            temp += arr[i];
            if(arr[i]!=0&&flag)ctr++;
        }
        valCount = ctr;
        return (temp/ctr);
    }
    public static double mean(int arr[]){return mean(arr,true);}
    public static double mean(int arr[][]){return mean(convert(arr));}
    public static double variance(int arr[]){
        int mean = (int)mean(arr);
        double temp=0,res=0;
        for(int i=0;i<arr.length;i++){
            temp=arr[i]-mean;
            temp*=temp;
            res += temp/valCount;
        }
        return res;
    }
    public static double variance(int arr[][]){return variance(convert(arr));}
    public static double standardDeviation(int arr[]){return Math.sqrt(variance(arr));}
    public static double standardDeviation(int arr[][]){return standardDeviation(convert(arr));}
}
