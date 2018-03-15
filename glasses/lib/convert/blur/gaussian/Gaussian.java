package glasses.lib.convert.blur.gaussian;

import java.awt.Color;
import java.awt.image.BufferedImage;

import glasses.lib.ImageObject;
import glasses.lib.math.Calculator;

public class Gaussian{
    private static int lvl = 1,kernelSize=3;
    private static int[][] getMatrix(BufferedImage bi, int x, int y){
       int startx = x - (kernelSize/2),
        starty = y - (kernelSize/2),
        endx = x + (kernelSize/2),
        endy= y + (kernelSize/2);
        int width = bi.getWidth(),height=bi.getHeight();
        int temp=0,ctr1=0,ctr2=0;
        int arr[][]=new int[kernelSize][kernelSize];
        for(int i=startx;i<=endx;i++){
            for(int j=starty;j<=endy;j++){
                if(i>=0&&i<width&&j>=0&&j<height)arr[i-startx][j-starty]=bi.getRGB(i,j);//(bi.getRGB(i,j)>>16)& 0x000000FF;
                else arr[i-startx][j-starty]=0;
            }
        }
        return arr;
    }
    public static ImageObject applyOn(ImageObject img){
        int height=0,width=0;
        BufferedImage temp=img.fetch();
        height = temp.getHeight();
        width = temp.getWidth();
        int arr[][]=new int[2*lvl+1][2*lvl+1];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                arr= getMatrix(img.fetch(),x,y);
                int avg = (int)glasses.lib.math.Calculator.mean(arr);
                Color c = new Color(avg,avg,avg);
                temp.setRGB(x,y,c.getRGB());
            }
        }
        ImageObject obj = new ImageObject(temp);
        return obj;
    }
    public static void main(String[] args){
      ImageObject obj = Gaussian.applyOn(new glasses.lib.Grayscale("result/frame1.png"));
      obj.store("gauss.png");
    }
}
