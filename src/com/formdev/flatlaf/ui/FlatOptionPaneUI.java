/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.SwingUtils;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicHTML;
/*     */ import javax.swing.plaf.basic.BasicOptionPaneUI;
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
/*     */ public class FlatOptionPaneUI
/*     */   extends BasicOptionPaneUI
/*     */ {
/*     */   protected boolean showIcon;
/*     */   protected int iconMessageGap;
/*     */   protected int messagePadding;
/*     */   protected int maxCharactersPerLine;
/*     */   private int focusWidth;
/*     */   private boolean sameSizeButtons;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 103 */     return new FlatOptionPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 108 */     super.installDefaults();
/*     */     
/* 110 */     this.showIcon = UIManager.getBoolean("OptionPane.showIcon");
/* 111 */     this.iconMessageGap = UIManager.getInt("OptionPane.iconMessageGap");
/* 112 */     this.messagePadding = UIManager.getInt("OptionPane.messagePadding");
/* 113 */     this.maxCharactersPerLine = UIManager.getInt("OptionPane.maxCharactersPerLine");
/* 114 */     this.focusWidth = UIManager.getInt("Component.focusWidth");
/* 115 */     this.sameSizeButtons = FlatUIUtils.getUIBoolean("OptionPane.sameSizeButtons", true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installComponents() {
/* 120 */     super.installComponents();
/*     */     
/* 122 */     updateChildPanels(this.optionPane);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/* 127 */     PropertyChangeListener superListener = super.createPropertyChangeListener();
/* 128 */     return e -> {
/*     */         superListener.propertyChange(e);
/*     */         if (!this.showIcon && "ancestor".equals(e.getPropertyName()) && e.getNewValue() != null) {
/*     */           JRootPane rootPane = SwingUtilities.getRootPane(this.optionPane);
/*     */           if (rootPane != null && rootPane.getContentPane().getComponentCount() > 0 && rootPane.getContentPane().getComponent(0) == this.optionPane) {
/*     */             rootPane.putClientProperty("JRootPane.titleBarShowIcon", Boolean.valueOf(false));
/*     */           }
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumOptionPaneSize() {
/* 145 */     return UIScale.scale(super.getMinimumOptionPaneSize());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMaxCharactersPerLineCount() {
/* 150 */     int max = super.getMaxCharactersPerLineCount();
/* 151 */     return (this.maxCharactersPerLine > 0 && max == Integer.MAX_VALUE) ? this.maxCharactersPerLine : max;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createMessageArea() {
/* 156 */     Container messageArea = super.createMessageArea();
/*     */ 
/*     */     
/* 159 */     if (this.iconMessageGap > 0) {
/* 160 */       Component iconMessageSeparator = SwingUtils.getComponentByName(messageArea, "OptionPane.separator");
/* 161 */       if (iconMessageSeparator != null) {
/* 162 */         iconMessageSeparator.setPreferredSize(new Dimension(UIScale.scale(this.iconMessageGap), 1));
/*     */       }
/*     */     } 
/* 165 */     return messageArea;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createButtonArea() {
/* 170 */     Container buttonArea = super.createButtonArea();
/*     */ 
/*     */     
/* 173 */     if (buttonArea.getLayout() instanceof BasicOptionPaneUI.ButtonAreaLayout) {
/* 174 */       BasicOptionPaneUI.ButtonAreaLayout layout = (BasicOptionPaneUI.ButtonAreaLayout)buttonArea.getLayout();
/* 175 */       layout.setPadding(UIScale.scale(layout.getPadding() - this.focusWidth * 2));
/*     */     } 
/*     */     
/* 178 */     return buttonArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addMessageComponents(Container container, GridBagConstraints cons, Object msg, int maxll, boolean internallyCreated) {
/* 186 */     if (this.messagePadding > 0) {
/* 187 */       cons.insets.bottom = UIScale.scale(this.messagePadding);
/*     */     }
/*     */     
/* 190 */     if (msg != null && !(msg instanceof Component) && !(msg instanceof Object[]) && !(msg instanceof javax.swing.Icon)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 195 */       msg = msg.toString();
/* 196 */       if (BasicHTML.isHTMLString((String)msg)) {
/* 197 */         maxll = Integer.MAX_VALUE;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 202 */     if (msg instanceof Box) {
/* 203 */       Box box = (Box)msg;
/* 204 */       if ("OptionPane.verticalBox".equals(box.getName()) && box
/* 205 */         .getLayout() instanceof BoxLayout && ((BoxLayout)box
/* 206 */         .getLayout()).getAxis() == 1)
/*     */       {
/* 208 */         box.addPropertyChangeListener("componentOrientation", e -> {
/*     */               float alignX = box.getComponentOrientation().isLeftToRight() ? 0.0F : 1.0F;
/*     */               for (Component c : box.getComponents()) {
/*     */                 if (c instanceof JLabel && "OptionPane.label".equals(c.getName())) {
/*     */                   ((JLabel)c).setAlignmentX(alignX);
/*     */                 }
/*     */               } 
/*     */             });
/*     */       }
/*     */     } 
/* 218 */     super.addMessageComponents(container, cons, msg, maxll, internallyCreated);
/*     */   }
/*     */   
/*     */   private void updateChildPanels(Container c) {
/* 222 */     for (Component child : c.getComponents()) {
/* 223 */       if (child.getClass() == JPanel.class) {
/* 224 */         JPanel panel = (JPanel)child;
/*     */ 
/*     */         
/* 227 */         panel.setOpaque(false);
/*     */ 
/*     */         
/* 230 */         Border border = panel.getBorder();
/* 231 */         if (border instanceof javax.swing.plaf.UIResource) {
/* 232 */           panel.setBorder(FlatUIUtils.nonUIResource(border));
/*     */         }
/*     */       } 
/* 235 */       if (child instanceof Container) {
/* 236 */         updateChildPanels((Container)child);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean getSizeButtonsToSameWidth() {
/* 242 */     return this.sameSizeButtons;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatOptionPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */