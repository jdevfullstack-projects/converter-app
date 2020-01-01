package xdvrx1ProjectSwing;

import junit.framework.TestCase;
import org.junit.*;
import java.io.*;

/**
 * Just test here whether BufferedImageCustom
 * will successfully return an object  
 */
public class BufferedImageCustomTest 
   extends TestCase {
   
   @Test
   public void testObjectReturn() throws IOException   
   {      
      BufferedImageCustom sample = new BufferedImageCustom();  
      
      assertNotNull("Object null", sample);
      assertNotNull("Object null", sample.imageReturn());
   }   
}
