package xdvrx1ProjectSwing;

class MainMethod {
   public static void main(String[] args) {
      try {
         UIClass app = new UIClass();         
         app.init();
      }        
      catch (Exception e) {
         System.out.println(e.getMessage());   
      }
   }
}
