/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import com.jgoodies.forms.layout.Size;
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
/*     */ public final class ButtonStackBuilder
/*     */   extends PanelBuilder
/*     */ {
/*  87 */   private static final ColumnSpec[] COL_SPECS = new ColumnSpec[] { FormFactory.BUTTON_COLSPEC };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private static final RowSpec[] ROW_SPECS = new RowSpec[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String NARROW_KEY = "jgoodies.isNarrow";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonStackBuilder() {
/* 115 */     this(new JPanel(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonStackBuilder(JPanel panel) {
/* 126 */     this(new FormLayout(COL_SPECS, ROW_SPECS), panel);
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
/*     */   public ButtonStackBuilder(FormLayout layout, JPanel panel) {
/* 140 */     super(layout, panel);
/* 141 */     setOpaque(false);
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
/*     */   public void addButtons(JButton[] buttons) {
/* 153 */     for (int i = 0; i < buttons.length; i++) {
/* 154 */       addGridded(buttons[i]);
/* 155 */       if (i < buttons.length - 1) {
/* 156 */         addRelatedGap();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFixed(JComponent component) {
/* 167 */     getLayout().appendRow(FormFactory.PREF_ROWSPEC);
/* 168 */     add(component);
/* 169 */     nextRow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGridded(JComponent component) {
/* 179 */     getLayout().appendRow(FormFactory.PREF_ROWSPEC);
/* 180 */     getLayout().addGroupedRow(getRow());
/* 181 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 182 */     add(component);
/* 183 */     nextRow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGlue() {
/* 192 */     appendGlueRow();
/* 193 */     nextRow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRelatedGap() {
/* 201 */     appendRelatedComponentsGapRow();
/* 202 */     nextRow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnrelatedGap() {
/* 210 */     appendUnrelatedComponentsGapRow();
/* 211 */     nextRow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStrut(ConstantSize size) {
/* 221 */     getLayout().appendRow(new RowSpec(RowSpec.TOP, (Size)size, 0.0D));
/*     */ 
/*     */     
/* 224 */     nextRow();
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
/*     */   public void addButton(JButton button) {
/* 240 */     addButton(new JButton[] { button });
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
/*     */   public void addButton(JButton button1, JButton button2) {
/* 255 */     addButton(new JButton[] { button1, button2 });
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
/*     */   public void addButton(JButton button1, JButton button2, JButton button3) {
/* 271 */     addButton(new JButton[] { button1, button2, button3 });
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
/*     */   public void addButton(JButton button1, JButton button2, JButton button3, JButton button4) {
/* 292 */     addButton(new JButton[] { button1, button2, button3, button4 });
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
/*     */   public void addButton(JButton[] buttons) {
/* 304 */     addButtons(buttons);
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
/* 319 */     addButton(new Action[] { action });
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
/*     */   public void addButton(Action action1, Action action2) {
/* 335 */     addButton(new Action[] { action1, action2 });
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
/*     */   public void addButton(Action action1, Action action2, Action action3) {
/* 352 */     addButton(new Action[] { action1, action2, action3 });
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
/*     */   public void addButton(Action action1, Action action2, Action action3, Action action4) {
/* 370 */     addButton(new Action[] { action1, action2, action3, action4 });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addButton(Action[] actions) {
/* 381 */     JButton[] buttons = new JButton[actions.length];
/* 382 */     for (int i = 0; i < actions.length; i++) {
/* 383 */       buttons[i] = new JButton(actions[i]);
/*     */     }
/* 385 */     addButtons(buttons);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\ButtonStackBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */