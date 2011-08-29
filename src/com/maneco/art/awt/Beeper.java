package com.maneco.art.awt;

import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import java.awt.Component; 
 
public class Beeper implements ActionListener { 
  public void actionPerformed(ActionEvent event) { 
    Component c = (Component)event.getSource(); 
    c.getToolkit().beep(); 
  } 
}
