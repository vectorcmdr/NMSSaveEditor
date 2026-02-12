using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class a : Form {
   public static a a_inst = null;

   public a(Form var1) {
      base(var1);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("About Save Editor");
      this.SetModal(true);
      RichTextBox var2 = new RichTextBox();
      var2.SetBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      var2.setOpaque(false);

      try {
         StyledDocument var3 = (StyledDocument)var2.getDocument();
         SimpleAttributeSet var4 = new SimpleAttributeSet();
         StyleConstants.setBold(var4, true);
         SimpleAttributeSet var5 = new SimpleAttributeSet();
         var3.insertString(var3.Count, "No Man's Sky Save Editor\n\n", var4);
         var3.insertString(var3.Count, "Version: 1.19.14", var5);
         var3.insertString(var3.Count, "by GoatFungus\n\n", var5);
         var3.insertString(var3.Count, "For further information visit:\n", var5);
         var3.insertString(var3.Count, "https://github.com/goatfungus/NMSSaveEditor", var5);
      } catch (BadLocationException var6) {
         var2.SetText("No Man's Sky Save EditorVersion: 1.19.14by GoatFungusFor further information visit:https://github.com/goatfungus/NMSSaveEditor");
      }

      var2.setEditable(false);
      this.GetContentPane().Add(var2, "Center");
      this.GetRootPane().registerKeyboardAction(new b(this), Keys.getKeyStroke(27, 0), 2);
      this.Pack();
   }

   public static void showDialog(Container var0) {
      if (a == null) {
         Form var1 = JOptionPane.getFrameForComponent(var0);
         a = new a(var1);
      }

      a.SetLocationRelativeTo(a.Parent);
      a.SetVisible(true);
   }
}

}
