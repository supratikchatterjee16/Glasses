package glasses;

import glasses.lib.ImageObject;
import java.util.Scanner;

class Driver{
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        glasses.ext.FrameBuilder.run(args[0]);
        ImageObject obj1=new ImageObject("result/frame1.png");
        ImageObject obj2=null;
        ImageObject temp=obj1;
        System.out.print("Driver init. Enter limit : ");
        int ctr=sc.nextInt(),ctr2=0;
        for(int i=1;i<ctr;i+=2){
            obj1=new ImageObject("result/frame"+Integer.toString(i)+".png");
            obj2=new ImageObject("result/frame"+Integer.toString(i+1)+".png");
            obj1.differential(obj2);
            System.out.print("#");
            obj1.store("working/differential"+Integer.toString(ctr2++)+".png");
        }
        System.out.println("Completed");
    }
}
