package com.jgoodies.forms.layout;

import java.awt.Container;
import java.util.List;

public interface Size {
  int maximumSize(Container paramContainer, List paramList, FormLayout.Measure paramMeasure1, FormLayout.Measure paramMeasure2, FormLayout.Measure paramMeasure3);
  
  boolean compressible();
  
  String encode();
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\Size.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */