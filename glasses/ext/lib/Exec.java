package glasses.ext.lib;

import java.io.*;

public class Exec{
    public static void run(String str){
        Process p=null;
        BufferedReader brin=null, brie=null;
        try{
            p=Runtime.getRuntime().exec(str);
            p.waitFor();
            System.out.println("Process "+str.substring(0,str.indexOf(" "))+" exited with exit code "+p.exitValue());
            brin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            brie = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            int n =0;
            //while((n=brin.read())!=-1)System.out.print((char)n);
            //while((n=brie.read())!=-1)System.out.print((char)n);
            if(p.exitValue()==0)System.out.println("Execution complete");
            else System.out.println("Error while executing");
        }catch(Exception e){e.printStackTrace();}
    }
    public static void main(String args[]){
        
    }
}
