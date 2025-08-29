/*     */ package com.jgoodies.forms.factories;
/*     */ 
/*     */ import com.jgoodies.forms.builder.ButtonBarBuilder2;
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
/*     */ public final class ButtonBarFactory
/*     */ {
/*     */   public static JPanel buildLeftAlignedBar(JButton button1) {
/*  68 */     return buildLeftAlignedBar(new JButton[] { button1 });
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
/*     */   public static JPanel buildLeftAlignedBar(JButton button1, JButton button2) {
/*  83 */     return buildLeftAlignedBar(new JButton[] { button1, button2 }, true);
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
/*     */   public static JPanel buildLeftAlignedBar(JButton button1, JButton button2, JButton button3) {
/* 100 */     return buildLeftAlignedBar(new JButton[] { button1, button2, button3 }, true);
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
/*     */   public static JPanel buildLeftAlignedBar(JButton button1, JButton button2, JButton button3, JButton button4) {
/* 118 */     return buildLeftAlignedBar(new JButton[] { button1, button2, button3, button4 }, true);
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
/*     */   public static JPanel buildLeftAlignedBar(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
/* 138 */     return buildLeftAlignedBar(new JButton[] { button1, button2, button3, button4, button5 }, true);
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
/*     */   public static JPanel buildLeftAlignedBar(JButton[] buttons) {
/* 152 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 153 */     builder.addButton((JComponent[])buttons);
/* 154 */     builder.addGlue();
/* 155 */     return builder.getPanel();
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
/*     */   public static JPanel buildLeftAlignedBar(JButton[] buttons, boolean leftToRightButtonOrder) {
/* 169 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 170 */     builder.setLeftToRightButtonOrder(leftToRightButtonOrder);
/* 171 */     builder.addButton((JComponent[])buttons);
/* 172 */     builder.addGlue();
/* 173 */     return builder.getPanel();
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
/*     */   public static JPanel buildCenteredBar(JButton button1) {
/* 186 */     return buildCenteredBar(new JButton[] { button1 });
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
/*     */   public static JPanel buildCenteredBar(JButton button1, JButton button2) {
/* 201 */     return buildCenteredBar(new JButton[] { button1, button2 });
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
/*     */   public static JPanel buildCenteredBar(JButton button1, JButton button2, JButton button3) {
/* 217 */     return buildCenteredBar(new JButton[] { button1, button2, button3 });
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
/*     */   public static JPanel buildCenteredBar(JButton button1, JButton button2, JButton button3, JButton button4) {
/* 234 */     return buildCenteredBar(new JButton[] { button1, button2, button3, button4 });
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
/*     */   public static JPanel buildCenteredBar(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
/* 253 */     return buildCenteredBar(new JButton[] { button1, button2, button3, button4, button5 });
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
/*     */   public static JPanel buildCenteredBar(JButton[] buttons) {
/* 266 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 267 */     builder.addGlue();
/* 268 */     builder.addButton((JComponent[])buttons);
/* 269 */     builder.addGlue();
/* 270 */     return builder.getPanel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JPanel buildGrowingBar(JButton button1) {
/* 281 */     return buildGrowingBar(new JButton[] { button1 });
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
/*     */   public static JPanel buildGrowingBar(JButton button1, JButton button2) {
/* 296 */     return buildGrowingBar(new JButton[] { button1, button2 });
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
/*     */   public static JPanel buildGrowingBar(JButton button1, JButton button2, JButton button3) {
/* 312 */     return buildGrowingBar(new JButton[] { button1, button2, button3 });
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
/*     */   public static JPanel buildGrowingBar(JButton button1, JButton button2, JButton button3, JButton button4) {
/* 329 */     return buildGrowingBar(new JButton[] { button1, button2, button3, button4 });
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
/*     */   public static JPanel buildGrowingBar(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
/* 348 */     return buildGrowingBar(new JButton[] { button1, button2, button3, button4, button5 });
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
/*     */   public static JPanel buildGrowingBar(JButton[] buttons) {
/* 362 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 363 */     builder.addGrowing((JComponent[])buttons);
/* 364 */     return builder.getPanel();
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
/*     */   public static JPanel buildRightAlignedBar(JButton button1) {
/* 377 */     return buildRightAlignedBar(new JButton[] { button1 });
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
/*     */   public static JPanel buildRightAlignedBar(JButton button1, JButton button2) {
/* 392 */     return buildRightAlignedBar(new JButton[] { button1, button2 }, true);
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
/*     */   public static JPanel buildRightAlignedBar(JButton button1, JButton button2, JButton button3) {
/* 409 */     return buildRightAlignedBar(new JButton[] { button1, button2, button3 }, true);
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
/*     */   public static JPanel buildRightAlignedBar(JButton button1, JButton button2, JButton button3, JButton button4) {
/* 427 */     return buildRightAlignedBar(new JButton[] { button1, button2, button3, button4 }, true);
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
/*     */   public static JPanel buildRightAlignedBar(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
/* 447 */     return buildRightAlignedBar(new JButton[] { button1, button2, button3, button4, button5 }, true);
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
/*     */   public static JPanel buildRightAlignedBar(JButton[] buttons) {
/* 461 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 462 */     builder.addGlue();
/* 463 */     builder.addButton((JComponent[])buttons);
/* 464 */     return builder.getPanel();
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
/*     */   public static JPanel buildRightAlignedBar(JButton[] buttons, boolean leftToRightButtonOrder) {
/* 478 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 479 */     builder.setLeftToRightButtonOrder(leftToRightButtonOrder);
/* 480 */     builder.addGlue();
/* 481 */     builder.addButton((JComponent[])buttons);
/* 482 */     return builder.getPanel();
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
/*     */   public static JPanel buildHelpBar(JButton help, JButton button1) {
/* 497 */     return buildHelpBar(help, new JButton[] { button1 });
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
/*     */   public static JPanel buildHelpBar(JButton help, JButton button1, JButton button2) {
/* 513 */     return buildHelpBar(help, new JButton[] { button1, button2 });
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
/*     */   public static JPanel buildHelpBar(JButton help, JButton button1, JButton button2, JButton button3) {
/* 530 */     return buildHelpBar(help, new JButton[] { button1, button2, button3 });
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
/*     */   public static JPanel buildHelpBar(JButton help, JButton button1, JButton button2, JButton button3, JButton button4) {
/* 548 */     return buildHelpBar(help, new JButton[] { button1, button2, button3, button4 });
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
/*     */   public static JPanel buildHelpBar(JButton help, JButton[] buttons) {
/* 562 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 563 */     builder.addButton(help);
/* 564 */     builder.addUnrelatedGap();
/* 565 */     builder.addGlue();
/* 566 */     builder.addButton((JComponent[])buttons);
/* 567 */     return builder.getPanel();
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
/*     */   public static JPanel buildCloseBar(JButton close) {
/* 580 */     return buildRightAlignedBar(close);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JPanel buildOKBar(JButton ok) {
/* 591 */     return buildRightAlignedBar(ok);
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
/*     */   public static JPanel buildOKCancelBar(JButton ok, JButton cancel) {
/* 604 */     return buildRightAlignedBar(new JButton[] { ok, cancel });
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
/*     */   public static JPanel buildOKCancelApplyBar(JButton ok, JButton cancel, JButton apply) {
/* 618 */     return buildRightAlignedBar(new JButton[] { ok, cancel, apply });
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
/*     */   public static JPanel buildHelpCloseBar(JButton help, JButton close) {
/* 634 */     return buildHelpBar(help, close);
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
/*     */   public static JPanel buildHelpOKBar(JButton help, JButton ok) {
/* 648 */     return buildHelpBar(help, ok);
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
/*     */   public static JPanel buildHelpOKCancelBar(JButton help, JButton ok, JButton cancel) {
/* 663 */     return buildHelpBar(help, ok, cancel);
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
/*     */   public static JPanel buildHelpOKCancelApplyBar(JButton help, JButton ok, JButton cancel, JButton apply) {
/* 679 */     return buildHelpBar(help, ok, cancel, apply);
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
/*     */   public static JPanel buildCloseHelpBar(JButton close, JButton help) {
/* 695 */     return buildRightAlignedBar(new JButton[] { close, help });
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
/*     */   public static JPanel buildOKHelpBar(JButton ok, JButton help) {
/* 709 */     return buildRightAlignedBar(new JButton[] { ok, help });
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
/*     */   public static JPanel buildOKCancelHelpBar(JButton ok, JButton cancel, JButton help) {
/* 724 */     return buildRightAlignedBar(new JButton[] { ok, cancel, help });
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
/*     */   public static JPanel buildOKCancelApplyHelpBar(JButton ok, JButton cancel, JButton apply, JButton help) {
/* 740 */     return buildRightAlignedBar(new JButton[] { ok, cancel, apply, help });
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
/*     */   public static JPanel buildAddRemoveLeftBar(JButton add, JButton remove) {
/* 756 */     return buildLeftAlignedBar(add, remove);
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
/*     */   public static JPanel buildAddRemoveBar(JButton add, JButton remove) {
/* 769 */     return buildGrowingBar(add, remove);
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
/*     */   public static JPanel buildAddRemoveRightBar(JButton add, JButton remove) {
/* 783 */     return buildRightAlignedBar(add, remove);
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
/*     */   public static JPanel buildAddRemovePropertiesLeftBar(JButton add, JButton remove, JButton properties) {
/* 800 */     return buildLeftAlignedBar(add, remove, properties);
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
/*     */   public static JPanel buildAddRemovePropertiesBar(JButton add, JButton remove, JButton properties) {
/* 815 */     ButtonBarBuilder2 builder = new ButtonBarBuilder2();
/* 816 */     builder.addButton(add);
/* 817 */     builder.addRelatedGap();
/* 818 */     builder.addButton(remove);
/* 819 */     builder.addRelatedGap();
/* 820 */     builder.addButton(properties);
/* 821 */     return builder.getPanel();
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
/*     */   public static JPanel buildAddRemovePropertiesRightBar(JButton add, JButton remove, JButton properties) {
/* 836 */     return buildRightAlignedBar(add, remove, properties);
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
/*     */   public static JPanel buildWizardBar(JButton back, JButton next, JButton finish, JButton cancel) {
/* 854 */     return buildWizardBar(back, next, new JButton[] { finish, cancel });
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
/*     */   public static JPanel buildWizardBar(JButton help, JButton back, JButton next, JButton finish, JButton cancel) {
/* 871 */     return buildWizardBar(new JButton[] { help }, back, next, new JButton[] { finish, cancel });
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
/*     */   public static JPanel buildWizardBar(JButton back, JButton next, JButton[] rightAlignedButtons) {
/* 889 */     return buildWizardBar((JButton[])null, back, next, rightAlignedButtons);
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
/*     */   public static JPanel buildWizardBar(JButton[] leftAlignedButtons, JButton back, JButton next, JButton[] rightAlignedButtons) {
/* 909 */     return buildWizardBar(leftAlignedButtons, back, next, (JButton)null, rightAlignedButtons);
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
/*     */   public static JPanel buildWizardBar(JButton[] leftAlignedButtons, JButton back, JButton next, JButton overlaidFinish, JButton[] rightAlignedButtons) {
/* 936 */     MyButtonBarBuilder2 builder = new MyButtonBarBuilder2();
/* 937 */     if (leftAlignedButtons != null) {
/* 938 */       builder.addButton((JComponent[])leftAlignedButtons);
/* 939 */       builder.addRelatedGap();
/*     */     } 
/* 941 */     builder.addGlue();
/* 942 */     builder.addButton(back);
/* 943 */     builder.addButton(next);
/*     */ 
/*     */     
/* 946 */     if (overlaidFinish != null) {
/* 947 */       builder.getPanel().add(overlaidFinish, CC.xy(builder.getColumn(), 1));
/*     */     }
/*     */     
/* 950 */     if (rightAlignedButtons != null) {
/* 951 */       builder.addRelatedGap();
/* 952 */       builder.addButton((JComponent[])rightAlignedButtons);
/*     */     } 
/* 954 */     return builder.getPanel();
/*     */   }
/*     */   
/*     */   private static final class MyButtonBarBuilder2 extends ButtonBarBuilder2 {
/*     */     public int getColumn() {
/* 959 */       return super.getColumn();
/*     */     }
/*     */     
/*     */     private MyButtonBarBuilder2() {}
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\factories\ButtonBarFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */