package glasses.ext;

import glasses.ext.lib.*;
import java.io.File;

public class FrameBuilder{
    public static void run(String str){
        File f = new File("result");
        if(!f.exists())f.mkdir();
        Exec.run("ffmpeg -i "+str+" result/frame%d.png");
    }
    
    public static void main(String args[]){
        run(args[0]+".mp4");
    }
}
