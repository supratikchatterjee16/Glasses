package glasses.lib.convert.lib;

import java.awt.image.BufferedImage;

public class Extract{
    private static int kernelSize=3;
    public static int[][] matrix(BufferedImage bi, int x, int y){
        int startx = x - (kernelSize/2),
        starty = y - (kernelSize/2),
        endx = x + (kernelSize/2),
        endy= y + (kernelSize/2);
        int width = bi.getWidth(),height=bi.getHeight();
        int temp=0,ctr1=0,ctr2=0;
        int arr[][]=new int[kernelSize][kernelSize];
        for(int i=startx;i<=endx;i++){
            for(int j=starty;j<=endy;j++){
                if(i>=0&&i<width&&j>=0&&j<height)arr[i-startx][j-starty]=(bi.getRGB(i,j)>>16)& 0x000000FF;
                else arr[i-startx][j-starty]=0;
            }
        }
        return arr;
    }
}
