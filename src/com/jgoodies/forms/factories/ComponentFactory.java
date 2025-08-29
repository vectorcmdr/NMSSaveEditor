package com.jgoodies.forms.factories;

import javax.swing.JComponent;
import javax.swing.JLabel;

public interface ComponentFactory {
  JLabel createLabel(String paramString);
  
  JLabel createTitle(String paramString);
  
  JComponent createSeparator(String paramString, int paramInt);
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\factories\ComponentFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */