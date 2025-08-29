/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.Borders;
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import com.jgoodies.forms.util.LayoutStyle;
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
/*     */ public final class ButtonBarBuilder
/*     */   extends PanelBuilder
/*     */ {
/* 147 */   private static final ColumnSpec[] COL_SPECS = new ColumnSpec[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   private static final RowSpec[] ROW_SPECS = new RowSpec[] { RowSpec.decode("center:pref") };
/*     */ 
/*     */ 
/*     */ 
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
/*     */   
/*     */   public ButtonBarBuilder() {
/* 192 */     this(new JPanel(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonBarBuilder(JPanel panel) {
/* 203 */     super(new FormLayout(COL_SPECS, ROW_SPECS), panel);
/* 204 */     this.leftToRight = LayoutStyle.getCurrent().isLeftToRightButtonOrder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ButtonBarBuilder createLeftToRightBuilder() {
/* 215 */     ButtonBarBuilder builder = new ButtonBarBuilder();
/* 216 */     builder.setLeftToRightButtonOrder(true);
/* 217 */     return builder;
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
/*     */   public boolean isLeftToRightButtonOrder() {
/* 234 */     return this.leftToRight;
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
/*     */   public void setLeftToRightButtonOrder(boolean newButtonOrder) {
/* 250 */     this.leftToRight = newButtonOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultButtonBarGapBorder() {
/* 260 */     setBorder(Borders.BUTTON_BAR_GAP_BORDER);
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
/*     */   public void addGriddedButtons(JButton[] buttons) {
/* 276 */     int length = buttons.length;
/* 277 */     for (int i = 0; i < length; i++) {
/* 278 */       int index = this.leftToRight ? i : (length - 1 - i);
/* 279 */       addGridded(buttons[index]);
/* 280 */       if (i < buttons.length - 1) {
/* 281 */         addRelatedGap();
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
/*     */   public void addGriddedGrowingButtons(JButton[] buttons) {
/* 298 */     int length = buttons.length;
/* 299 */     for (int i = 0; i < length; i++) {
/* 300 */       int index = this.leftToRight ? i : (length - 1 - i);
/* 301 */       addGriddedGrowing(buttons[index]);
/* 302 */       if (i < buttons.length - 1) {
/* 303 */         addRelatedGap();
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
/*     */   public void addFixed(JComponent component) {
/* 315 */     getLayout().appendColumn(FormFactory.PREF_COLSPEC);
/* 316 */     add(component);
/* 317 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFixedNarrow(JComponent component) {
/* 328 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 329 */     addFixed(component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGridded(JComponent component) {
/* 340 */     getLayout().appendColumn(FormFactory.BUTTON_COLSPEC);
/* 341 */     getLayout().addGroupedColumn(getColumn());
/* 342 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 343 */     add(component);
/* 344 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGriddedGrowing(JComponent component) {
/* 355 */     getLayout().appendColumn(FormFactory.GROWING_BUTTON_COLSPEC);
/* 356 */     getLayout().addGroupedColumn(getColumn());
/* 357 */     component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
/* 358 */     add(component);
/* 359 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGlue() {
/* 368 */     appendGlueColumn();
/* 369 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRelatedGap() {
/* 379 */     appendRelatedComponentsGapColumn();
/* 380 */     nextColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnrelatedGap() {
/* 390 */     appendUnrelatedComponentsGapColumn();
/* 391 */     nextColumn();
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
/* 405 */     getLayout().appendColumn(ColumnSpec.createGap(width));
/* 406 */     nextColumn();
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\ButtonBarBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */