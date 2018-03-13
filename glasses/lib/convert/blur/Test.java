package glasses.lib.convert.blur;

import java.awt.image.BufferedImage;

import glasses.lib.ImageObject;
import glasses.lib.math.Calculator;


class Test{
    double mat[][]={{0., 0.3, 0.1},
                        {0.3, 0.7, 0.3},
                        {0.1, 0.3, 0.1}};
    public static int[][] getMatrix(BufferedImage bi, int x, int y){
        int temp[][]=new int[3][3];
        int ctry=0;
        for(int i=-1;i<=1;i++){
            int ctrx=0;
            for(int j=-1;j<=1;j++){
                try{
                temp[ctrx++][ctry]=bi.getRGB(x+j,y+i);
                }catch(Exception e){temp[ctrx++][ctry]=0;}
            }
        }
        return temp;
    }
    public static void gaussianOn(ImageObject obj){
            BufferedImage bi =obj.fetch();
            
            int gaussKernel[][]=getMatrix(bi,0,0);
            for(int i=0;i<gaussKernel.length;i++){
                for(int j=0;j<gaussKernel[0].length;j++)System.out.print(gaussKernel[i][j]+"  ");
                System.out.println("");
            }
    }
    public static void main(String[] args){
        ImageObject im = new ImageObject(args[0]);
        gaussianOn(im);
    }
}
