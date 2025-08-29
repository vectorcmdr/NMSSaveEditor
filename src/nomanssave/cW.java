package nomanssave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.Utilities;

public class cW extends JPanel implements PropertyChangeListener, CaretListener, DocumentListener {
  public static final float gx = 0.0F;
  
  public static final float gy = 0.5F;
  
  public static final float gz = 1.0F;
  
  private static final Border gA = new MatteBorder(0, 0, 0, 2, Color.GRAY);
  
  private static final int HEIGHT = 2146483647;
  
  private JTextComponent gB;
  
  private boolean gC;
  
  private int gD;
  
  private Color gE;
  
  private float gF;
  
  private int gG;
  
  private int gH;
  
  private int gI;
  
  private int gJ;
  
  private HashMap gK;
  
  public cW(JTextComponent paramJTextComponent) {
    this(paramJTextComponent, 3);
  }
  
  public cW(JTextComponent paramJTextComponent, int paramInt) {
    this.gB = paramJTextComponent;
    setFont(paramJTextComponent.getFont());
    y(5);
    a(Color.RED);
    a(1.0F);
    z(paramInt);
    paramJTextComponent.getDocument().addDocumentListener(this);
    paramJTextComponent.addCaretListener(this);
    paramJTextComponent.addPropertyChangeListener("font", this);
  }
  
  public boolean aD() {
    return this.gC;
  }
  
  public void b(boolean paramBoolean) {
    this.gC = paramBoolean;
  }
  
  public int aE() {
    return this.gD;
  }
  
  public void y(int paramInt) {
    this.gD = paramInt;
    EmptyBorder emptyBorder = new EmptyBorder(0, paramInt, 0, paramInt);
    setBorder(new CompoundBorder(gA, emptyBorder));
    this.gH = 0;
    aI();
  }
  
  public Color aF() {
    return (this.gE == null) ? getForeground() : this.gE;
  }
  
  public void a(Color paramColor) {
    this.gE = paramColor;
  }
  
  public float aG() {
    return this.gF;
  }
  
  public void a(float paramFloat) {
    this.gF = (paramFloat > 1.0F) ? 1.0F : ((paramFloat < 0.0F) ? -1.0F : paramFloat);
  }
  
  public int aH() {
    return this.gG;
  }
  
  public void z(int paramInt) {
    this.gG = paramInt;
    aI();
  }
  
  private void aI() {
    Element element = this.gB.getDocument().getDefaultRootElement();
    int i = element.getElementCount();
    int j = Math.max(String.valueOf(i).length(), this.gG);
    if (this.gH != j) {
      this.gH = j;
      FontMetrics fontMetrics = getFontMetrics(getFont());
      int k = fontMetrics.charWidth('0') * j;
      Insets insets = getInsets();
      int m = insets.left + insets.right + k;
      Dimension dimension = getPreferredSize();
      dimension.setSize(m, 2146483647);
      setPreferredSize(dimension);
      setSize(dimension);
    } 
  }
  
  public void paintComponent(Graphics paramGraphics) {
    super.paintComponent(paramGraphics);
    FontMetrics fontMetrics = this.gB.getFontMetrics(this.gB.getFont());
    Insets insets = getInsets();
    int i = (getSize()).width - insets.left - insets.right;
    Rectangle rectangle = paramGraphics.getClipBounds();
    int j = this.gB.viewToModel(new Point(0, rectangle.y));
    int k = this.gB.viewToModel(new Point(0, rectangle.y + rectangle.height));
    while (j <= k) {
      try {
        if (A(j)) {
          paramGraphics.setColor(aF());
        } else {
          paramGraphics.setColor(getForeground());
        } 
        String str = B(j);
        int m = fontMetrics.stringWidth(str);
        int n = b(i, m) + insets.left;
        int i1 = a(j, fontMetrics);
        paramGraphics.drawString(str, n, i1);
        j = Utilities.getRowEnd(this.gB, j) + 1;
      } catch (Exception exception) {
        break;
      } 
    } 
  }
  
  private boolean A(int paramInt) {
    int i = this.gB.getCaretPosition();
    Element element = this.gB.getDocument().getDefaultRootElement();
    return (element.getElementIndex(paramInt) == element.getElementIndex(i));
  }
  
  protected String B(int paramInt) {
    Element element1 = this.gB.getDocument().getDefaultRootElement();
    int i = element1.getElementIndex(paramInt);
    Element element2 = element1.getElement(i);
    return (element2.getStartOffset() == paramInt) ? String.valueOf(i + 1) : "";
  }
  
  private int b(int paramInt1, int paramInt2) {
    return (int)((paramInt1 - paramInt2) * this.gF);
  }
  
  private int a(int paramInt, FontMetrics paramFontMetrics) {
    Rectangle rectangle = this.gB.modelToView(paramInt);
    int i = paramFontMetrics.getHeight();
    int j = rectangle.y + rectangle.height;
    int k = 0;
    if (rectangle.height == i) {
      k = paramFontMetrics.getDescent();
    } else {
      if (this.gK == null)
        this.gK = new HashMap<>(); 
      Element element1 = this.gB.getDocument().getDefaultRootElement();
      int m = element1.getElementIndex(paramInt);
      Element element2 = element1.getElement(m);
      for (byte b = 0; b < element2.getElementCount(); b++) {
        Element element = element2.getElement(b);
        AttributeSet attributeSet = element.getAttributes();
        String str1 = (String)attributeSet.getAttribute(StyleConstants.FontFamily);
        Integer integer = (Integer)attributeSet.getAttribute(StyleConstants.FontSize);
        String str2 = String.valueOf(str1) + integer;
        FontMetrics fontMetrics = (FontMetrics)this.gK.get(str2);
        if (fontMetrics == null) {
          Font font = new Font(str1, 0, integer.intValue());
          fontMetrics = this.gB.getFontMetrics(font);
          this.gK.put(str2, fontMetrics);
        } 
        k = Math.max(k, fontMetrics.getDescent());
      } 
    } 
    return j - k;
  }
  
  public void caretUpdate(CaretEvent paramCaretEvent) {
    int i = this.gB.getCaretPosition();
    Element element = this.gB.getDocument().getDefaultRootElement();
    int j = element.getElementIndex(i);
    if (this.gJ != j) {
      repaint();
      this.gJ = j;
    } 
  }
  
  public void changedUpdate(DocumentEvent paramDocumentEvent) {
    aJ();
  }
  
  public void insertUpdate(DocumentEvent paramDocumentEvent) {
    aJ();
  }
  
  public void removeUpdate(DocumentEvent paramDocumentEvent) {
    aJ();
  }
  
  private void aJ() {
    SwingUtilities.invokeLater(new cX(this));
  }
  
  public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent) {
    if (paramPropertyChangeEvent.getNewValue() instanceof Font)
      if (this.gC) {
        Font font = (Font)paramPropertyChangeEvent.getNewValue();
        setFont(font);
        this.gH = 0;
        aI();
      } else {
        repaint();
      }  
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cW.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */