package glasses.lib;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class ImageObject{

    private BufferedImage image;

    public ImageObject(){}
    public ImageObject(BufferedImage b){image = b;}
    public ImageObject(BufferedImage b1,BufferedImage b2){image = segregated(b1,b2);}
    public ImageObject(File f){this(fetch(f));}
    public ImageObject(File f1, File f2){this(fetch(f1),fetch(f2));}
    public ImageObject(String fname){this(new File(fname));}
    public ImageObject(String fname1,String fname2){this(new File(fname1),new File(fname2));}
    public ImageObject(ImageObject im){this.image=im.fetch();}
    public ImageObject(ImageObject im1,ImageObject im2){this.image=segregated(im1.fetch(),im2.fetch());}
    private static BufferedImage fetch(File f){
        BufferedImage res=null;
        try{res= ImageIO.read(f);}catch(IOException e){e.printStackTrace();}
        return res;
    }
    private static BufferedImage segregated(BufferedImage b1, BufferedImage b2){
        int a=0,b=0,height=b1.getHeight(),width=b1.getWidth();
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        if(b2.getHeight()!=height||b2.getWidth()!=width){
            System.out.println("The images have inconsistent dimensions.");
            return temp;
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(b1.getRGB(i,j)==b2.getRGB(i,j))temp.setRGB(i,j,b1.getRGB(i,j));
            }
        }
        System.out.println("Changed pixels extracted.");
        ImageObject im = new ImageObject(temp);
        im.store("temp.png");
        return temp;
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
