using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class a : Form {
   public static a _a_inst = null;

public a(Frame var1) : base(var1) {
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      this.Text = ("About Save Editor");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      JTextPane var2 = new JTextPane();
      var2.Padding = new Padding(0); /* setBorder */ //(null /* EmptyBorder */);
      // PORT_TODO: var2.setOpaque(false);

      try {
         StyledDocument var3 = (StyledDocument)var2.getDocument();
         SimpleAttributeSet var4 = new SimpleAttributeSet();
         /* StyleConstants */;
         SimpleAttributeSet var5 = new SimpleAttributeSet();
         var3.insertString(var3.getLength(), "No Man's Sky Save Editor\n\n", var4);
         var3.insertString(var3.getLength(), "Version: 1.19.14", var5);
         var3.insertString(var3.getLength(), "by GoatFungus\n\n", var5);
         var3.insertString(var3.getLength(), "For further information visit:\n", var5);
         var3.insertString(var3.getLength(), "https://github.com/goatfungus/NMSSaveEditor", var5);
      } catch (Exception var6) {
         var2.Text = ("No Man's Sky Save EditorVersion: 1.19.14by GoatFungusFor further information visit:https://github.com/goatfungus/NMSSaveEditor");
      }

      var2.setEditable(false);
      this.Controls.Add(var2);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new b(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.PerformLayout();
   }

   public static void a_init(Container var0) {
      if (true) { // PORT_TODO: original condition had errors
         Frame var1 = null;
         // PORT_TODO: a = new a(var1);
      }

      // PORT_TODO: a.StartPosition = FormStartPosition.CenterParent; //(a.DirectoryName);
      // PORT_TODO: a.Show();
   }
}



}
