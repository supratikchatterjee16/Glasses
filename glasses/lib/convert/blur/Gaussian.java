package glasses.lib.convert.blur;

import java.awt.image.BufferedImage;

import glasses.lib.ImageObject;
import glasses.lib.math.Calculator;

class Gaussian{
    private static int lvl = 1;
    private static int[][] getMatrix(ImageObject obj, int x, int y){
      int startx=x-lvl,starty=y-lvl,endx=x+lvl,endy=y+lvl;
      BufferedImage im = obj.fetch();
      int res[][]=new int[2*lvl+1][2*lvl+1];
      for(int i=starty; i < endy; i++){
        for(int j=startx;j<endx;j++){
          if(x<im.getMinX()||y<im.getMinY()||x>im.getWidth()||y<im.getHeight())res[i-startx][j-starty]=0;
          else res[i-startx][j-starty]=im.getRGB(i,j);
        }
      }
      return res;
    }
    public static ImageObject applyOn(ImageObject img){
        int height=0,width=0;
        ImageObject temp=img;
        int arr[][]=new int[2*lvl+1][2*lvl+1];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;i++){
                arr = getMatrix(img,j,i);
                for(int k=0;k<3;k++)for(int l=0;l<3;l++)System.out.println(" "+arr[k][l]);
                break;
            }
            break;
        }
        return temp;
    }
    public static void main(String[] args){
      Gaussian.applyOn(new glasses.lib.ImageObject("result/frame1.png","result/frame2.png"));
    }
}
