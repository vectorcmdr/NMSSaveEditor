/*     */ package com.jgoodies.forms.factories;
/*     */ 
/*     */ import com.jgoodies.forms.layout.Sizes;
/*     */ import com.jgoodies.forms.util.FormUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.accessibility.AccessibleContext;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultComponentFactory
/*     */   implements ComponentFactory2
/*     */ {
/*  61 */   private static final DefaultComponentFactory INSTANCE = new DefaultComponentFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final char MNEMONIC_MARKER = '&';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DefaultComponentFactory getInstance() {
/*  79 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLabel createLabel(String textWithMnemonic) {
/* 100 */     JLabel label = new FormsLabel();
/* 101 */     setTextAndMnemonic(label, textWithMnemonic);
/* 102 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLabel createReadOnlyLabel(String textWithMnemonic) {
/* 127 */     JLabel label = new ReadOnlyLabel();
/* 128 */     setTextAndMnemonic(label, textWithMnemonic);
/* 129 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JLabel createTitle(String textWithMnemonic) {
/* 149 */     JLabel label = new TitleLabel();
/* 150 */     setTextAndMnemonic(label, textWithMnemonic);
/* 151 */     label.setVerticalAlignment(0);
/* 152 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent createSeparator(String textWithMnemonic) {
/* 173 */     return createSeparator(textWithMnemonic, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent createSeparator(String textWithMnemonic, int alignment) {
/* 197 */     if (textWithMnemonic == null || textWithMnemonic.length() == 0) {
/* 198 */       return new JSeparator();
/*     */     }
/* 200 */     JLabel title = createTitle(textWithMnemonic);
/* 201 */     title.setHorizontalAlignment(alignment);
/* 202 */     return createSeparator(title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent createSeparator(JLabel label) {
/* 235 */     if (label == null)
/* 236 */       throw new NullPointerException("The label must not be null."); 
/* 237 */     int horizontalAlignment = label.getHorizontalAlignment();
/* 238 */     if (horizontalAlignment != 2 && horizontalAlignment != 0 && horizontalAlignment != 4)
/*     */     {
/*     */       
/* 241 */       throw new IllegalArgumentException("The label's horizontal alignment must be one of: LEFT, CENTER, RIGHT.");
/*     */     }
/*     */     
/* 244 */     JPanel panel = new JPanel(new TitledSeparatorLayout(!FormUtils.isLafAqua()));
/* 245 */     panel.setOpaque(false);
/* 246 */     panel.add(label);
/* 247 */     panel.add(new JSeparator());
/* 248 */     if (horizontalAlignment == 0) {
/* 249 */       panel.add(new JSeparator());
/*     */     }
/* 251 */     return panel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTextAndMnemonic(JLabel label, String textWithMnemonic) {
/* 273 */     int markerIndex = textWithMnemonic.indexOf('&');
/*     */     
/* 275 */     if (markerIndex == -1) {
/* 276 */       label.setText(textWithMnemonic);
/*     */       return;
/*     */     } 
/* 279 */     int mnemonicIndex = -1;
/* 280 */     int begin = 0;
/*     */     
/* 282 */     int length = textWithMnemonic.length();
/* 283 */     int quotedMarkers = 0;
/* 284 */     StringBuffer buffer = new StringBuffer();
/*     */     while (true) {
/*     */       int end;
/* 287 */       if (markerIndex + 1 < length && textWithMnemonic.charAt(markerIndex + 1) == '&') {
/*     */         
/* 289 */         end = markerIndex + 1;
/* 290 */         quotedMarkers++;
/*     */       } else {
/* 292 */         end = markerIndex;
/* 293 */         if (mnemonicIndex == -1) {
/* 294 */           mnemonicIndex = markerIndex - quotedMarkers;
/*     */         }
/*     */       } 
/* 297 */       buffer.append(textWithMnemonic.substring(begin, end));
/* 298 */       begin = end + 1;
/* 299 */       markerIndex = (begin < length) ? textWithMnemonic.indexOf('&', begin) : -1;
/*     */ 
/*     */       
/* 302 */       if (markerIndex == -1) {
/* 303 */         buffer.append(textWithMnemonic.substring(begin));
/*     */         
/* 305 */         String text = buffer.toString();
/* 306 */         label.setText(text);
/* 307 */         if (mnemonicIndex != -1 && mnemonicIndex < text.length()) {
/* 308 */           label.setDisplayedMnemonic(text.charAt(mnemonicIndex));
/*     */           
/* 310 */           label.setDisplayedMnemonicIndex(mnemonicIndex);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class FormsLabel
/*     */     extends JLabel
/*     */   {
/*     */     private FormsLabel() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AccessibleContext getAccessibleContext() {
/* 335 */       if (this.accessibleContext == null) {
/* 336 */         this.accessibleContext = new AccessibleFormsLabel();
/*     */       }
/* 338 */       return this.accessibleContext;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final class AccessibleFormsLabel
/*     */       extends JLabel.AccessibleJLabel
/*     */     {
/*     */       private final DefaultComponentFactory.FormsLabel this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       private AccessibleFormsLabel() {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public String getAccessibleName() {
/* 360 */         if (this.accessibleName != null) {
/* 361 */           return this.accessibleName;
/*     */         }
/* 363 */         String text = DefaultComponentFactory.FormsLabel.this.getText();
/* 364 */         if (text == null) {
/* 365 */           return super.getAccessibleName();
/*     */         }
/* 367 */         return text.endsWith(":") ? text.substring(0, text.length() - 1) : text;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class ReadOnlyLabel
/*     */     extends FormsLabel
/*     */   {
/*     */     private ReadOnlyLabel() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 383 */     private static final String[] UIMANAGER_KEYS = new String[] { "Label.disabledForeground", "Label.disabledText", "Label[Disabled].textForeground", "textInactiveText" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void updateUI() {
/* 390 */       super.updateUI();
/* 391 */       setForeground(getDisabledForeground());
/*     */     }
/*     */ 
/*     */     
/*     */     private Color getDisabledForeground() {
/* 396 */       for (int i = 0; i < UIMANAGER_KEYS.length; i++) {
/* 397 */         String key = UIMANAGER_KEYS[i];
/* 398 */         Color foreground = UIManager.getColor(key);
/* 399 */         if (foreground != null)
/*     */         {
/* 401 */           return foreground;
/*     */         }
/*     */       } 
/* 404 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class TitleLabel
/*     */     extends FormsLabel
/*     */   {
/*     */     private TitleLabel() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void updateUI() {
/* 424 */       super.updateUI();
/* 425 */       Color foreground = getTitleColor();
/* 426 */       if (foreground != null) {
/* 427 */         setForeground(foreground);
/*     */       }
/* 429 */       setFont(getTitleFont());
/*     */     }
/*     */     
/*     */     private Color getTitleColor() {
/* 433 */       return UIManager.getColor("TitledBorder.titleColor");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Font getTitleFont() {
/* 447 */       return FormUtils.isLafAqua() ? UIManager.getFont("Label.font").deriveFont(1) : UIManager.getFont("TitledBorder.font");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class TitledSeparatorLayout
/*     */     implements LayoutManager
/*     */   {
/*     */     private final boolean centerSeparators;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private TitledSeparatorLayout(boolean centerSeparators) {
/* 470 */       this.centerSeparators = centerSeparators;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(String name, Component comp) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 506 */       return preferredLayoutSize(parent);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 519 */       Component label = getLabel(parent);
/* 520 */       Dimension labelSize = label.getPreferredSize();
/* 521 */       Insets insets = parent.getInsets();
/* 522 */       int width = labelSize.width + insets.left + insets.right;
/* 523 */       int height = labelSize.height + insets.top + insets.bottom;
/* 524 */       return new Dimension(width, height);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 533 */       synchronized (parent.getTreeLock()) {
/*     */         
/* 535 */         Dimension size = parent.getSize();
/* 536 */         Insets insets = parent.getInsets();
/* 537 */         int width = size.width - insets.left - insets.right;
/*     */ 
/*     */         
/* 540 */         JLabel label = getLabel(parent);
/* 541 */         Dimension labelSize = label.getPreferredSize();
/* 542 */         int labelWidth = labelSize.width;
/* 543 */         int labelHeight = labelSize.height;
/* 544 */         Component separator1 = parent.getComponent(1);
/* 545 */         int separatorHeight = (separator1.getPreferredSize()).height;
/*     */         
/* 547 */         FontMetrics metrics = label.getFontMetrics(label.getFont());
/* 548 */         int ascent = metrics.getMaxAscent();
/* 549 */         int hGapDlu = this.centerSeparators ? 3 : 1;
/* 550 */         int hGap = Sizes.dialogUnitXAsPixel(hGapDlu, label);
/* 551 */         int vOffset = this.centerSeparators ? (1 + (labelHeight - separatorHeight) / 2) : (ascent - separatorHeight / 2);
/*     */ 
/*     */ 
/*     */         
/* 555 */         int alignment = label.getHorizontalAlignment();
/* 556 */         int y = insets.top;
/* 557 */         if (alignment == 2) {
/* 558 */           int x = insets.left;
/* 559 */           label.setBounds(x, y, labelWidth, labelHeight);
/* 560 */           x += labelWidth;
/* 561 */           x += hGap;
/* 562 */           int separatorWidth = size.width - insets.right - x;
/* 563 */           separator1.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
/* 564 */         } else if (alignment == 4) {
/* 565 */           int x = insets.left + width - labelWidth;
/* 566 */           label.setBounds(x, y, labelWidth, labelHeight);
/* 567 */           x -= hGap;
/* 568 */           x--;
/* 569 */           int separatorWidth = x - insets.left;
/* 570 */           separator1.setBounds(insets.left, y + vOffset, separatorWidth, separatorHeight);
/*     */         } else {
/* 572 */           int xOffset = (width - labelWidth - 2 * hGap) / 2;
/* 573 */           int x = insets.left;
/* 574 */           separator1.setBounds(x, y + vOffset, xOffset - 1, separatorHeight);
/* 575 */           x += xOffset;
/* 576 */           x += hGap;
/* 577 */           label.setBounds(x, y, labelWidth, labelHeight);
/* 578 */           x += labelWidth;
/* 579 */           x += hGap;
/* 580 */           Component separator2 = parent.getComponent(2);
/* 581 */           int separatorWidth = size.width - insets.right - x;
/* 582 */           separator2.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private JLabel getLabel(Container parent) {
/* 588 */       return (JLabel)parent.getComponent(0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\factories\DefaultComponentFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */