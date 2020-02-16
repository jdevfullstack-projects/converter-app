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

import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * initializing a class that throws Exception
 */
public class UIClass  
{  
   BufferedImageCustom bufferedImageCustom;
   Image bg;
   ImageIcon imageIcon;
   
   //a DefaultListModel is needed to hold the 
   //items that will be displayed on JList
   //the type is String      
   DefaultListModel<String> items;
   
   Font f;
   Font f2;
   
   //a List View component creation through JList with the items,      
   //the type is String also, so not to issue a warning
   //from the compiler   
   JList<String> returnResultJL;
   
   DefaultListCellRenderer renderer;
   JFrame frame;
   
   //creation of a JLabel to hold some string message   
   JLabel text;
   
   //JTextField named 'cm' was called here
   //it belongs to the same class, under the same
   //constructor, so the object can be called  
   JTextField cm;
   
   FlowLayout flowLayout;
   
   JPanel textPanel; 
   //a creation of a JButton with a text "Convert"    
   JButton b;
   
   //a creation of a JScrollPane
   //so that if the items are out of 
   //range, it can still be seen   
   JScrollPane scroll;
   
   JPanel convertPanel;
   JButton b2;
   
   //another creation of a JPanel to hold the second button 
   JPanel resetPanel;
   
   //at last the main panel
   JPanel mainPanel;
   
   UIClass() throws Exception {
      bufferedImageCustom = new BufferedImageCustom();
      bg = bufferedImageCustom.imageReturn();
      imageIcon = new ImageIcon(bg);
      items = new DefaultListModel<String>(); 
      f = new Font("Consolas",Font.PLAIN, 24);
      f2 = new Font("Comic Sans MS",Font.ITALIC, 24);
      returnResultJL= new JList<String>(items);
      renderer = (DefaultListCellRenderer)returnResultJL.getCellRenderer();
      frame = new JFrame("Converter");
      text = new JLabel("");
      cm = new JTextField();   
      flowLayout = new FlowLayout();
      textPanel = new JPanel(flowLayout); 
      b = new JButton ("Convert");
      scroll = new JScrollPane(returnResultJL); 
      convertPanel = new JPanel(flowLayout);      
      b2 = new JButton("Reset");
      resetPanel = new JPanel ();      
      mainPanel = new PanelWithBackgroundImage(bg);
   }
   
   void init()  {    
      
      renderer.setHorizontalAlignment(SwingConstants.CENTER);      
      returnResultJL.setFont(f2);      
      
      frame.setIconImage(imageIcon.getImage());     
      
      //a command to simply close the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      text.setText("Centimeters:"); 
      text.setFont(f);     
      
      cm.setFont(f);          
      cm.setColumns(10);
      cm.setHorizontalAlignment(JTextField.CENTER);
      cm.setDocument(new JTextFieldLimit(8));
      
      int align = flowLayout.CENTER;
      
      textPanel.setOpaque(false);      
      //after a textPanel was created, text label will be added 
      textPanel.add(text,BorderLayout.PAGE_START );
      //also the 'cm' JTextField was added
      textPanel.add(cm);
      
      //changing the font to the specified font
      b.setFont(f);
      b.setOpaque(false);
      textPanel.add(b);    
      
      scroll.setPreferredSize (new Dimension( 700, 110 ));
      
      convertPanel.add(scroll);
      convertPanel.setOpaque(false);
      
      b2.setFont(f);
      
      resetPanel.add(b2);
      resetPanel.setOpaque(false);   
      
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
