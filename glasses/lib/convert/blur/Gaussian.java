package glasses.lib.convert.blur;

import java.awt.image.BufferedImage;

import glasses.lib.ImageObject;
import glasses.lib.math.Calculator;

class Gaussian{
    private static int lvl = 1;
    private static int[][] getMatrix(BufferedImage obj, int x, int y){
      int startx=x-lvl,starty=y-lvl,endx=x+lvl,endy=y+lvl;
      BufferedImage im = obj;
      int res[][]=new int[2*lvl+1][2*lvl+1];
      int ctrx=0,ctry=0;
      //System.out.println("Height and width = "+obj.getHeight()+" & "+obj.getWidth());
      for(int i=starty; i < endy; i++){
        for(int j=startx;j<endx;j++){
          //System.out.println("X = "+j+" Y = "+i);
          try{res[i-starty][j-startx]=im.getRGB(j,i);}catch(Exception e){res[i-starty][j-startx]=0;}
        }
      }
      //for(int i=0;i<res.length;i++){
        //for(int j=0;j<res[0].length;j++)System.out.print(res[i][j]+" ");
        //System.out.println();
      //}
      return res;
    }
    public static ImageObject applyOn(ImageObject img){
        int height=0,width=0;
        BufferedImage temp=img.fetch();
        height = temp.getHeight();
        width = temp.getWidth();
        int arr[][]=new int[2*lvl+1][2*lvl+1];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                arr = getMatrix(temp,j,i);
                //for(int k=0;k<3;k++){for(int l=0;l<3;l++)System.out.print(" "+arr[k][l]);System.out.println();}
            }
        }
        ImageObject obj = new ImageObject(temp);
        return obj;
    }
    public static void main(String[] args){
      Gaussian.applyOn(new glasses.lib.ImageObject("result/frame1.png"));
    }
}
