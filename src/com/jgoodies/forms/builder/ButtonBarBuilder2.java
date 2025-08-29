/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.Borders;
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import com.jgoodies.forms.util.LayoutStyle;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
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
/*     */ public class ButtonBarBuilder2
/*     */   extends AbstractButtonPanelBuilder
/*     */ {
/* 167 */   private static final ColumnSpec[] COL_SPECS = new ColumnSpec[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   private static final RowSpec[] ROW_SPECS = new RowSpec[] { RowSpec.decode("center:pref") };
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
/*     */   private boolean leftToRight;
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
/*     */   public ButtonBarBuilder2() {
/* 198 */     this(new JPanel(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonBarBuilder2(JPanel panel) {
/* 208 */     super(new FormLayout(COL_SPECS, ROW_SPECS), panel);
/* 209 */     this.leftToRight = LayoutStyle.getCurrent().isLeftToRightButtonOrder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ButtonBarBuilder2 createLeftToRightBuilder() {
/* 220 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 221 */     builder.setLeftToRightButtonOrder(true);
/* 222 */     return builder;
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
/*     */   public boolean isLeftToRightButtonOrder() {
/* 237 */     return this.leftToRight;
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
/*     */   public void setLeftToRightButtonOrder(boolean newButtonOrder) {
/* 251 */     this.leftToRight = newButtonOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultButtonBarGapBorder() {
/* 261 */     setBorder(Borders.BUTTON_BAR_GAP_BORDER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGlue() {
/* 272 */     appendGlueColumn();
/* 273 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRelatedGap() {
/* 283 */     appendRelatedComponentsGapColumn();
/* 284 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnrelatedGap() {
/* 294 */     appendUnrelatedComponentsGapColumn();
/* 295 */     nextColumn();
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
/*     */   public void addStrut(ConstantSize width) {
/* 309 */     getLayout().appendColumn(ColumnSpec.createGap(width));
/* 310 */     nextColumn();
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
/*     */   public void addButton(JComponent button) {
/* 328 */     button.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 329 */     getLayout().appendColumn(FormFactory.BUTTON_COLSPEC);
/* 330 */     add(button);
/* 331 */     nextColumn();
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
/*     */   public void addButton(JComponent button1, JComponent button2) {
/* 354 */     addButton(new JComponent[] { button1, button2 });
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
/*     */   public void addButton(JComponent button1, JComponent button2, JComponent button3) {
/* 379 */     addButton(new JComponent[] { button1, button2, button3 });
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
/*     */   public void addButton(JComponent button1, JComponent button2, JComponent button3, JComponent button4) {
/* 406 */     addButton(new JComponent[] { button1, button2, button3, button4 });
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
/*     */   public void addButton(JComponent button1, JComponent button2, JComponent button3, JComponent button4, JComponent button5) {
/* 435 */     addButton(new JComponent[] { button1, button2, button3, button4, button5 });
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
/*     */   public void addButton(JComponent[] buttons) {
/* 459 */     if (buttons == null) {
/* 460 */       throw new NullPointerException("The button array must not be null.");
/*     */     }
/* 462 */     int length = buttons.length;
/* 463 */     if (length == 0) {
/* 464 */       throw new IllegalArgumentException("The button array must not be empty.");
/*     */     }
/* 466 */     for (int i = 0; i < length; i++) {
/* 467 */       int index = this.leftToRight ? i : (length - 1 - i);
/* 468 */       addButton(buttons[index]);
/* 469 */       if (i < buttons.length - 1) {
/* 470 */         addRelatedGap();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addButton(Action action) {
/* 487 */     if (action == null) {
/* 488 */       throw new NullPointerException("The button Action must not be null.");
/*     */     }
/* 490 */     addButton(new JButton(action));
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
/*     */   public void addButton(Action action1, Action action2) {
/* 514 */     addButton(new Action[] { action1, action2 });
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
/*     */   public void addButton(Action action1, Action action2, Action action3) {
/* 540 */     addButton(new Action[] { action1, action2, action3 });
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
/*     */   public void addButton(Action action1, Action action2, Action action3, Action action4) {
/* 568 */     addButton(new Action[] { action1, action2, action3, action4 });
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
/*     */   public void addButton(Action action1, Action action2, Action action3, Action action4, Action action5) {
/* 598 */     addButton(new Action[] { action1, action2, action3, action4, action5 });
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
/*     */   public void addButton(Action[] actions) {
/* 618 */     if (actions == null) {
/* 619 */       throw new NullPointerException("The Action array must not be null.");
/*     */     }
/* 621 */     int length = actions.length;
/* 622 */     if (length == 0) {
/* 623 */       throw new IllegalArgumentException("The Action array must not be empty.");
/*     */     }
/* 625 */     JButton[] buttons = new JButton[length];
/* 626 */     for (int i = 0; i < length; i++) {
/* 627 */       buttons[i] = new JButton(actions[i]);
/*     */     }
/* 629 */     addButton((JComponent[])buttons);
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
/*     */   public void addGrowing(JComponent component) {
/* 643 */     getLayout().appendColumn(FormFactory.GROWING_BUTTON_COLSPEC);
/* 644 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 645 */     add(component);
/* 646 */     nextColumn();
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
/*     */   public void addGrowing(JComponent[] buttons) {
/* 662 */     int length = buttons.length;
/* 663 */     for (int i = 0; i < length; i++) {
/* 664 */       int index = this.leftToRight ? i : (length - 1 - i);
/* 665 */       addGrowing(buttons[index]);
/* 666 */       if (i < buttons.length - 1) {
/* 667 */         addRelatedGap();
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
/*     */   
/*     */   public void addFixed(JComponent component) {
/* 680 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 681 */     getLayout().appendColumn(FormFactory.PREF_COLSPEC);
/* 682 */     add(component);
/* 683 */     nextColumn();
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\ButtonBarBuilder2.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */