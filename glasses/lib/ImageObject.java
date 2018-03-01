package glasses.lib;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class ImageObject{

    private BufferedImage image;

    public ImageObject(){}
    public ImageObject(BufferedImage b){image = b;}
    public ImageObject(BufferedImage b1,BufferedImage b2){image = BasicDetector.changed(b1,b2);}
    public ImageObject(File f){this(fetch(f));}
    public ImageObject(File f1, File f2){this(fetch(f1),fetch(f2));}
    public ImageObject(String fname){this(new File(fname));}
    public ImageObject(String fname1,String fname2){this(new File(fname1),new File(fname2));}
    public ImageObject(ImageObject im){this.image=im.fetch();}
    public ImageObject(ImageObject im1,ImageObject im2){this.image=BasicDetector.changed(im1.fetch(),im2.fetch());}
    private static BufferedImage fetch(File f){
        BufferedImage res=null;
        try{res= ImageIO.read(f);}catch(IOException e){e.printStackTrace();}
        return res;
    }
    public BufferedImage fetch(){return this.image;}
    public synchronized int store(File f){
      try{
        ImageIO.write(this.image,"png",f);
      }catch(IOException e){return 0;}
      return 1;
    }
    public synchronized int store(String str){return store(new File(str));}
}
