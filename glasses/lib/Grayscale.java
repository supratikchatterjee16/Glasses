package glasses.lib;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Grayscale extends ImageObject{
    private BufferedImage image;
    public Grayscale(BufferedImage i){
        image = i;
        super.image = luminosity(i);
    }
    public Grayscale(BufferedImage i, int ctr){
        image = i;
        switch(ctr){
            case 0:super.image = luminosity(i);break;
            case 1:super.image = lightness(i);break;
            case 2:super.image = average(i);break;
            default:;
        }
    }
    private static BufferedImage average(BufferedImage img){
        int height=img.getHeight(),width = img.getWidth();
        int red=0,green=0,blue=0,temp=0;
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                Color c =new Color(img.getRGB(x,y));
                red=c.getRed();green = c.getGreen();blue = c.getBlue();
                temp = (red+green+blue)/3;
                c = new Color(temp,temp,temp,temp);
                img.setRGB(x,y,c.getRGB());
            }
        }
        return img;
    }
    private static BufferedImage lightness(BufferedImage img){
        int height=img.getHeight(),width = img.getWidth();
        int red=0,green=0,blue=0,max=0,min=0,temp=0;
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                Color c =new Color(img.getRGB(x,y));
                red=c.getRed();green = c.getGreen();blue = c.getBlue();
                if(red>green){max=red;min=green;}
                else {min=red;max=green;}
                if(blue>max){max=blue;}
                if(blue<min){min=blue;}
                temp = (max+min)/2;
                c =new Color(temp,temp,temp,temp);
                img.setRGB(x,y,c.getRGB());
            }
        }
        return img;
    }
    private static BufferedImage luminosity(BufferedImage img){
        int height=img.getHeight(),width = img.getWidth();
        int r=0,g=0,b=0,temp1=0,temp2=0;
        BufferedImage temp=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                temp1=img.getRGB(x,y);
                r=(temp1>>16)& 0x000000FF;
                g=(temp1 >>8 ) & 0x000000FF;
                b=(temp1) & 0x000000FF;
                //System.out.println(r+","+g+","+b+"|");
                temp2=(int)(0.21*r+0.72*g+0.07*b);
                Color c =new Color(temp2,temp2,temp2,temp2);
                temp.setRGB(x,y,c.getRGB());
            }
        }
        return temp;
    }
    public BufferedImage getBufferedGrayscale(){return super.image;}
    public BufferedImage getBufferedImage(){return image;}
    public static void main(String[] args){
        try{
            Grayscale gimg1 = new Grayscale(ImageIO.read(new File("result/frame1.png")),Integer.parseInt(args[0])),
                            gimg2= new Grayscale(ImageIO.read(new File("result/frame2.png")),Integer.parseInt(args[0]));
            ImageObject io = new ImageObject(gimg1,gimg2);
            ImageIO.write(io.fetch(),"png",new File("gray.png"));
        }catch(Exception e){e.printStackTrace();}
    }
}
