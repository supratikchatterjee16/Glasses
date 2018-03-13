package glasses.lib.detect.edge;

import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.io.File;

class EdgeTest{
    public static BufferedImage on(BufferedImage bi,int factor){
        BufferedImage temp=new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
        int sum=0;
        for(int i=1;i<bi.getWidth()-1;i++){
            for(int j=1;j<bi.getHeight()-1;j++){
                double mat[][]={{bi.getRGB(i-1,j-1),bi.getRGB(i,j-1),bi.getRGB(i+1,j-1)},
                                {bi.getRGB(i-1,j  ),bi.getRGB(i,j  ),bi.getRGB(i+1,j  )},
                                {bi.getRGB(i-1,j+1),bi.getRGB(i,j+1),bi.getRGB(i+1,j+1)}};
                //for(int k=0;k<3;k++){for(int l=0;l<3;l++)System.out.print(mat[k][l]+"|");System.out.println();}
                int ctr=0;
                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        sum+=(int)mat[k][l];
                        if(mat[k][l]==0)ctr++;
                    }
                }
                sum/=9;
                
                if(ctr>factor)sum=0;
                //System.out.println("Sum = "+sum);
                if(sum!=bi.getRGB(i,j  ))temp.setRGB(i,j,-100000);
                else temp.setRGB(i,j,0);
                sum=0;
            }
        }
        return temp;
    }
    public static void main(String[] args){
        try{
            ImageIO.write(on(ImageIO.read(new File(args[0])),Integer.parseInt(args[1])),"png",new File("output.png"));
        }catch(Exception e){System.err.println("No file found");}
    }
}
