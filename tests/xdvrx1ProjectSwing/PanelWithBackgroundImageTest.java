package xdvrx1ProjectSwing;

import junit.framework.TestCase;
import org.junit.*;

import java.awt.Image;
import javax.swing.*;

/**
 * The object of JPanel should be not null,
 * although the Image inserted is actually null
 */
public class PanelWithBackgroundImageTest 
   extends TestCase {

   @Test
   public void testObjectShouldNotBeNull() {
      
      Image bg = null;
      JPanel renderedJPanelWithImage = new PanelWithBackgroundImage(bg);
      
      assertNotNull("Object `renderedImage` null", renderedJPanelWithImage);
   }
   
}
