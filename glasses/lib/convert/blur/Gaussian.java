package glasses.lib.convert.blur;

import glasses.lib.math.Calculator;

class Gaussian{
    private static lvl = 1;
    public static BufferedImage applyOn(BufferedImage img){
        int height=img.getHeight(),width=img.getWidth();
        int arr[][]=new int[2*lvl+1][2*lvl+1];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;i++){
                arr = getMatrix(img,j,i);
            }
        }
    }
    public static void main(String[] args){
        
    }
}
