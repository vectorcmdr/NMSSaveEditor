package com.jgoodies.forms.builder;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.util.LayoutStyle;
import java.awt.LayoutManager;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ButtonBarBuilder2 extends AbstractButtonPanelBuilder {
   private static final ColumnSpec[] COL_SPECS = new ColumnSpec[0];
   private static final RowSpec[] ROW_SPECS = new RowSpec[]{RowSpec.decode("center:pref")};
   private boolean leftToRight;

   public ButtonBarBuilder2() {
      this(new JPanel((LayoutManager)null));
   }

   public ButtonBarBuilder2(JPanel panel) {
      super(new FormLayout(COL_SPECS, ROW_SPECS), panel);
      this.leftToRight = LayoutStyle.getCurrent().isLeftToRightButtonOrder();
   }

   public static ButtonBarBuilder2 createLeftToRightBuilder() {
      ButtonBarBuilder2 builder = new ButtonBarBuilder2();
      builder.setLeftToRightButtonOrder(true);
      return builder;
   }

   public boolean isLeftToRightButtonOrder() {
      return this.leftToRight;
   }

   public void setLeftToRightButtonOrder(boolean newButtonOrder) {
      this.leftToRight = newButtonOrder;
   }

   public void setDefaultButtonBarGapBorder() {
      this.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
   }

   public void addGlue() {
      this.appendGlueColumn();
      this.nextColumn();
   }

   public void addRelatedGap() {
      this.appendRelatedComponentsGapColumn();
      this.nextColumn();
   }

   public void addUnrelatedGap() {
      this.appendUnrelatedComponentsGapColumn();
      this.nextColumn();
   }

   public void addStrut(ConstantSize width) {
      this.getLayout().appendColumn(ColumnSpec.createGap(width));
      this.nextColumn();
   }

   public void addButton(JComponent button) {
      button.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
      this.getLayout().appendColumn(FormFactory.BUTTON_COLSPEC);
      this.add(button);
      this.nextColumn();
   }

   public void addButton(JComponent button1, JComponent button2) {
      this.addButton(new JComponent[]{button1, button2});
   }

   public void addButton(JComponent button1, JComponent button2, JComponent button3) {
      this.addButton(new JComponent[]{button1, button2, button3});
   }

   public void addButton(JComponent button1, JComponent button2, JComponent button3, JComponent button4) {
      this.addButton(new JComponent[]{button1, button2, button3, button4});
   }

   public void addButton(JComponent button1, JComponent button2, JComponent button3, JComponent button4, JComponent button5) {
      this.addButton(new JComponent[]{button1, button2, button3, button4, button5});
   }

   public void addButton(JComponent[] buttons) {
      if (buttons == null) {
         throw new NullPointerException("The button array must not be null.");
      } else {
         int length = buttons.length;
         if (length == 0) {
            throw new IllegalArgumentException("The button array must not be empty.");
         } else {
            for(int i = 0; i < length; ++i) {
               int index = this.leftToRight ? i : length - 1 - i;
               this.addButton(buttons[index]);
               if (i < buttons.length - 1) {
                  this.addRelatedGap();
               }
            }

         }
      }
   }

   public void addButton(Action action) {
      if (action == null) {
         throw new NullPointerException("The button Action must not be null.");
      } else {
         this.addButton((JComponent)(new JButton(action)));
      }
   }

   public void addButton(Action action1, Action action2) {
      this.addButton(new Action[]{action1, action2});
   }

   public void addButton(Action action1, Action action2, Action action3) {
      this.addButton(new Action[]{action1, action2, action3});
   }

   public void addButton(Action action1, Action action2, Action action3, Action action4) {
      this.addButton(new Action[]{action1, action2, action3, action4});
   }

   public void addButton(Action action1, Action action2, Action action3, Action action4, Action action5) {
      this.addButton(new Action[]{action1, action2, action3, action4, action5});
   }

   public void addButton(Action[] actions) {
      if (actions == null) {
         throw new NullPointerException("The Action array must not be null.");
      } else {
         int length = actions.length;
         if (length == 0) {
            throw new IllegalArgumentException("The Action array must not be empty.");
         } else {
            JButton[] buttons = new JButton[length];

            for(int i = 0; i < length; ++i) {
               buttons[i] = new JButton(actions[i]);
            }

            this.addButton((JComponent[])buttons);
         }
      }
   }

   public void addGrowing(JComponent component) {
      this.getLayout().appendColumn(FormFactory.GROWING_BUTTON_COLSPEC);
      component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
      this.add(component);
      this.nextColumn();
   }

   public void addGrowing(JComponent[] buttons) {
      int length = buttons.length;

      for(int i = 0; i < length; ++i) {
         int index = this.leftToRight ? i : length - 1 - i;
         this.addGrowing(buttons[index]);
         if (i < buttons.length - 1) {
            this.addRelatedGap();
         }
      }

   }

   public void addFixed(JComponent component) {
      component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
      this.getLayout().appendColumn(FormFactory.PREF_COLSPEC);
      this.add(component);
      this.nextColumn();
   }
}
