package xdvrx1ProjectSwing;

import junit.framework.TestCase;
import org.junit.*;

/**
 * We just want to test whether
 * JTextFieldLimit is actually created.
 */
public class JTextFieldLimitTest 
   extends TestCase {
   
   @Test
   public void testObjectShouldNotBeNull() {      
      
      int limit = 8;
      
      JTextFieldLimit limitText = new JTextFieldLimit(limit);          
      
      assertNotNull("Object null.", limitText);  
            
   }   
}
