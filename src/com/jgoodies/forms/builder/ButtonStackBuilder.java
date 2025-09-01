package com.jgoodies.forms.builder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.LayoutManager;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public final class ButtonStackBuilder extends PanelBuilder {
   private static final ColumnSpec[] COL_SPECS;
   private static final RowSpec[] ROW_SPECS;
   private static final String NARROW_KEY = "jgoodies.isNarrow";

   public ButtonStackBuilder() {
      this(new JPanel((LayoutManager)null));
   }

   public ButtonStackBuilder(JPanel panel) {
      this(new FormLayout(COL_SPECS, ROW_SPECS), panel);
   }

   public ButtonStackBuilder(FormLayout layout, JPanel panel) {
      super(layout, panel);
      this.setOpaque(false);
   }

   public void addButtons(JButton[] buttons) {
      for(int i = 0; i < buttons.length; ++i) {
         this.addGridded(buttons[i]);
         if (i < buttons.length - 1) {
            this.addRelatedGap();
         }
      }

   }

   public void addFixed(JComponent component) {
      this.getLayout().appendRow(FormFactory.PREF_ROWSPEC);
      this.add(component);
      this.nextRow();
   }

   public void addGridded(JComponent component) {
      this.getLayout().appendRow(FormFactory.PREF_ROWSPEC);
      this.getLayout().addGroupedRow(this.getRow());
      component.putClientProperty("jgoodies.isNarrow", Boolean.TRUE);
      this.add(component);
      this.nextRow();
   }

   public void addGlue() {
      this.appendGlueRow();
      this.nextRow();
   }

   public void addRelatedGap() {
      this.appendRelatedComponentsGapRow();
      this.nextRow();
   }

   public void addUnrelatedGap() {
      this.appendUnrelatedComponentsGapRow();
      this.nextRow();
   }

   public void addStrut(ConstantSize size) {
      this.getLayout().appendRow(new RowSpec(RowSpec.TOP, size, 0.0D));
      this.nextRow();
   }

   public void addButton(JButton button) {
      this.addButton(new JButton[]{button});
   }

   public void addButton(JButton button1, JButton button2) {
      this.addButton(new JButton[]{button1, button2});
   }

   public void addButton(JButton button1, JButton button2, JButton button3) {
      this.addButton(new JButton[]{button1, button2, button3});
   }

   public void addButton(JButton button1, JButton button2, JButton button3, JButton button4) {
      this.addButton(new JButton[]{button1, button2, button3, button4});
   }

   public void addButton(JButton[] buttons) {
      this.addButtons(buttons);
   }

   public void addButton(Action action) {
      this.addButton(new Action[]{action});
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

   public void addButton(Action[] actions) {
      JButton[] buttons = new JButton[actions.length];

      for(int i = 0; i < actions.length; ++i) {
         buttons[i] = new JButton(actions[i]);
      }

      this.addButtons(buttons);
   }

   static {
      COL_SPECS = new ColumnSpec[]{FormFactory.BUTTON_COLSPEC};
      ROW_SPECS = new RowSpec[0];
   }
}
