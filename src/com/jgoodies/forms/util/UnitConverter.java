package com.jgoodies.forms.util;

import java.awt.Component;

public interface UnitConverter {
   int inchAsPixel(double var1, Component var3);

   int millimeterAsPixel(double var1, Component var3);

   int centimeterAsPixel(double var1, Component var3);

   int pointAsPixel(int var1, Component var2);

   int dialogUnitXAsPixel(int var1, Component var2);

   int dialogUnitYAsPixel(int var1, Component var2);
}
