package glasses.lib;

import java.awt.image.BufferedImage;

class BasicDetector{
  protected static BufferedImage changed(BufferedImage b1, BufferedImage b2){
      int a=0,b=0,height=b1.getHeight(),width=b1.getWidth();
      BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      if(b2.getHeight()!=height||b2.getWidth()!=width){
          System.out.println("The images have inconsistent dimensions.");
          return temp;
      }
      for(int i=0;i<width;i++){
          for(int j=0;j<height;j++){
              if(b1.getRGB(i,j)!=b2.getRGB(i,j))temp.setRGB(i,j,b1.getRGB(i,j));
          }
      }
      //System.out.println("Changed pixels extracted.");
      return temp;
  }
  protected static BufferedImage linearChanged(BufferedImage b1){
    int a=0,b=0,height=b1.getHeight(),width=b1.getWidth();
    int lastPixel=0;
    BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    for(int i=0;i<width;i++){
        for(int j=0;j<height;j++){
            if(b1.getRGB(i,j)!=lastPixel)temp.setRGB(i,j,b1.getRGB(i,j));
            else temp.setRGB(i,j,0);
            lastPixel = b1.getRGB(i,j);
        }
    }
    for(int i=0;i<height;i++){
        for(int j=0;j<width;j++){
            if(temp.getRGB(j,i)!=lastPixel)temp.setRGB(j,i,temp.getRGB(j,i));
            else temp.setRGB(j,i,0);
            lastPixel= b1.getRGB(j,i);
        }
    }
    return temp;
  }
  protected static BufferedImage evaluate(BufferedImage b1, BufferedImage b2){
    return linearChanged(changed(b1,b2));
  }
  public static void main(String[] args){
    ImageObject obj1 = new ImageObject("result/frame"+args[0]+".png");
    ImageObject obj2=new ImageObject("result/frame"+args[1]+".png");
    obj1 = new ImageObject(linearChanged(obj1.fetch()));
    obj2 = new ImageObject(linearChanged(obj2.fetch()));
    obj1 = new ImageObject(obj1,obj2);
    obj1.store("temp1.png");
    obj2.store("temp2.png");
  }
}
