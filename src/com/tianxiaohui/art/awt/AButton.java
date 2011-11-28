package com.tianxiaohui.art.awt;

import java.awt.Button; 
import java.applet.Applet; 
 
public class AButton extends Applet { 
  public void init() { 
    // STEP 1: Compose the GUI 
    Button beepButton = new Button("Beep");  
    add(beepButton); 
 
    // STEP 2: Setup Event handlers 
    beepButton.addActionListener(new Beeper()); 
 
    // STEP 3: Display the GUI (automatic -- this is an applet) 
  } 
} 