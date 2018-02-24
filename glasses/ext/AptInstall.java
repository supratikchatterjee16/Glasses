package glasses.ext;

import glasses.ext.lib.*;
import java.io.File;

class AptInstall{
    public static void run(String str){
        File f = new File("result");
        if(!f.exists())f.mkdir();
        Exec.run("sudo apt  install -y "+str);
    }
    
    public static void main(String args[]){
        run("ffmpeg");
    }
}
