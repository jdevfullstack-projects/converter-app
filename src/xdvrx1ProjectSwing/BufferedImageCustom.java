package xdvrx1ProjectSwing;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

/**
 *This class will return an image
 *from a binary data.
 */
class BufferedImageCustom {
   public Image imageReturn() 
      throws IOException {
      Image image;
      
      //the image here is not even an image file
      //but rather a binary data
      InputStream bis = getClass().getResourceAsStream("/resources/base64_decrypt.bin");
      BufferedImage bImage2 = ImageIO.read(bis);
      image = bImage2;
      
      return image;
   }       
}
