package com.jgoodies.forms.factories;

import javax.swing.JComponent;
import javax.swing.JLabel;

public interface ComponentFactory {
   JLabel createLabel(String var1);

   JLabel createTitle(String var1);

   JComponent createSeparator(String var1, int var2);
}
