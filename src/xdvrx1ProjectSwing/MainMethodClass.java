package xdvrx1ProjectSwing;

/** 
* Java Swing is a great GUI framework of Java.
* This is the lighweight version of AWT.
* JavaFX, on the other hand, is the most
* recent GUI framework particularly for
* mobile apps.
*/

//importing the three packages
//so that there will be no error in compiling
//javax.swing is widely used today than java.awt
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * initializing a class that contains the main method
 * and throws Exception
 */
public class MainMethodClass  
{  
   public static void main(String[] args) 
      throws Exception {    
      
      //the background image  
      BufferedImageCustom bufferedImageCustom = new BufferedImageCustom();
      Image bg = bufferedImageCustom.imageReturn();
      
      //a DefaultListModel is needed to hold the 
      //items that will be displayed on JList
      //the type is String      
      DefaultListModel<String> items = new DefaultListModel<String>();      
      
      //setting the fonts 
      Font f = new Font("Consolas",Font.PLAIN, 24);   
      Font f2 = new Font("Comic Sans MS",Font.ITALIC, 24);   
      
      //a List View component creation through JList with the items,      
      //the type is String also, so not to issue a warning
      //from the compiler
      JList<String> returnResultJL= new JList<String>(items);
      DefaultListCellRenderer renderer = 
         (DefaultListCellRenderer)returnResultJL.getCellRenderer();
      
      renderer.setHorizontalAlignment(SwingConstants.CENTER);
      
      returnResultJL.setFont(f2); 
      
      JFrame frame = new JFrame("Converter");
      frame.setIconImage(bg);     
      
      //a command to simply close the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
      
      //creation of a JLabel to hold some string message
      JLabel text = new JLabel("");
      text.setText("Centimeters:"); 
      text.setFont(f);     
      
      //JTextField named 'cm' was called here
      //it belongs to the same class, under the same
      //constructor, so the object can be called
      //and 'cm' was set to the font f   
      JTextField cm = new JTextField();             
      cm.setFont(f);          
      cm.setColumns(10);
      cm.setHorizontalAlignment(JTextField.CENTER);
      cm.setDocument(new JTextFieldLimit(8));
      
      FlowLayout flowLayout = new FlowLayout();
      int align = flowLayout.CENTER;
      
      JPanel textPanel = new JPanel(flowLayout);      
      textPanel.setOpaque(false);      
      //after a textPanel was created, text label will be added 
      textPanel.add(text,BorderLayout.PAGE_START );
      //also the 'cm' JTextField was added
      textPanel.add(cm);
      
      //a creation of a JButton with a text "Convert"     
      JButton b = new JButton ("Convert");
      //changing the font to the specified font
      b.setFont(f);
      b.setOpaque(false);
      textPanel.add(b);    
      
      //a creation of a JScrollPane
      //so that if the items are out of 
      //range, it can still be seen
      JScrollPane scroll = new JScrollPane(returnResultJL);
      scroll.setPreferredSize (new Dimension( 700, 110 ));
      
      //another JPanel to hold the scroll               
      JPanel convertPanel = new JPanel(flowLayout);
      convertPanel.add(scroll);
      convertPanel.setOpaque(false);
      
      //another JButton for reset function      
      JButton b2 = new JButton("Reset");      
      b2.setFont(f);
      
      //another creation of a JPanel to hold the second button
      JPanel resetPanel = new JPanel ();
      resetPanel.add(b2);
      resetPanel.setOpaque(false);   
            
      //at last the main panel
      JPanel mainPanel = new PanelWithBackgroundImage(bg);
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));      
      //adding all the panels here in order
      mainPanel.add(Box.createVerticalStrut( 110 ));
      mainPanel.add(textPanel);  
      mainPanel.add(Box.createVerticalStrut(30));
      mainPanel.add(convertPanel);
      mainPanel.add(Box.createVerticalStrut( 30 ));
      mainPanel.add(resetPanel);
      mainPanel.add(Box.createVerticalStrut( 210 ));
      
      //and finally adding the main panel to the frame
      frame.add(mainPanel);
      
      //setting the frame behavior
      frame.setResizable(false);
      frame.setVisible(true);
      frame.pack();
      frame.setLocationRelativeTo(null);    
            
      //in this part is where after an action
      //there will be a corresponding event    
      b.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){
            
            double feet, inches, remainder;
            
            final double CM_PER_INCH = 2.54;        
            final double IN_PER_FOOT = 12.00;
            
            //the input assigned to `cmFormatted`
            String cmFormatted = cm.getText();
            
            if (cmFormatted.contains("d")) {
               JOptionPane.showMessageDialog(frame,
                                             "Malformed number or empty",
                                             "Error",
                                             JOptionPane.ERROR_MESSAGE);
            } else if (cmFormatted.contains("e")) {
               JOptionPane.showMessageDialog(frame,
                                             "Malformed number or empty",
                                             "Error",
                                             JOptionPane.ERROR_MESSAGE);
            } else if (cmFormatted.contains("f")) {
               JOptionPane.showMessageDialog(frame,
                                             "Malformed number or empty",
                                             "Error",
                                             JOptionPane.ERROR_MESSAGE);               
            } else {
               
               try {
                  //creating a variable that has the type double
                  //it will parse the string data
                  //from 'cm' textfield                  
                  double cmC = Double.parseDouble(cmFormatted);                  
                  
                  //I decided to limit the lowest
                  //possible number to 1,
                  //because those .001 and the likes
                  //they are returning very large numbers
                  if (cmC < 1) {
                     JOptionPane.showMessageDialog(frame,
                                                   "Number too small.",
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE);               
                  } else {
                     
                     //we'll do some math here :)  
                     inches = cmC / CM_PER_INCH;               
                     feet = inches / IN_PER_FOOT;               
                     remainder = inches % IN_PER_FOOT; 
                     
                     //get the integer
                     String stringVal = String.valueOf(feet);
                     int indexOfDecimal = stringVal.indexOf(".");  
                     String integerPart = stringVal.substring(0, indexOfDecimal);
                     //get the decimal part
                     String remainderFormatted = String.format("%.5g", remainder);
                     
                     //`convertedResult` will be the computed value
                     String convertedResult = cmFormatted + " cm" + " = " 
                        + integerPart + "\'" + " " + remainderFormatted + "\"";
                     
                     //at this point, the formatted string will
                     //be added to the Listview
                     items.add(0,convertedResult); 
                     returnResultJL.grabFocus();
                     returnResultJL.setSelectedIndex(0);
                     returnResultJL.ensureIndexIsVisible(0);
                     
                     //the 'cm' should be set to blank, 
                     //to enable receiving inputs again 
                     cm.setText("");
                  }
                  
               } catch (NumberFormatException nf2) {
                  
                  JOptionPane.showMessageDialog(frame,
                                                "Malformed number or empty.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);       
                  
               } catch (IndexOutOfBoundsException outB) {
                  
                  JOptionPane.showMessageDialog(frame,
                                                "Out of Bounds.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);                  
               }
            }
         }
      });
            
      b2.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){
            cm.setText("");
            items.clear(); 
            cm.grabFocus();
         }
      });    
      
   }
}
