package com.jgoodies.forms.util;

import java.awt.Component;

public interface UnitConverter {
  int inchAsPixel(double paramDouble, Component paramComponent);
  
  int millimeterAsPixel(double paramDouble, Component paramComponent);
  
  int centimeterAsPixel(double paramDouble, Component paramComponent);
  
  int pointAsPixel(int paramInt, Component paramComponent);
  
  int dialogUnitXAsPixel(int paramInt, Component paramComponent);
  
  int dialogUnitYAsPixel(int paramInt, Component paramComponent);
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\UnitConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */