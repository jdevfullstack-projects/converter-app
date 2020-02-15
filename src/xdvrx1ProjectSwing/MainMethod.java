package xdvrx1ProjectSwing;

class MainMethod {
   public static void main(String[] args) {
      UIClass app = new UIClass();
      try {
         app.init();
      }        
      catch (Exception e) {
      System.out.println(e.getMessage());   
      }
   }
}
  
