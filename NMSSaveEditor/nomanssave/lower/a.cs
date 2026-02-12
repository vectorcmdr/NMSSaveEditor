using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class a : Form {
   private static a a = null;

   private a(Frame var1) {
      base(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("About Save Editor");
      this.setModal(true);
      JTextPane var2 = new JTextPane();
      var2.setBorder(object.createEmptyBorder(10, 10, 10, 10));
      var2.setOpaque(false);

      try {
         StyledDocument var3 = (StyledDocument)var2.getDocument();
         SimpleAttributeSet var4 = new SimpleAttributeSet();
         StyleConstants.setBold(var4, true);
         SimpleAttributeSet var5 = new SimpleAttributeSet();
         var3.insertString(var3.getLength(), "No Man's Sky Save Editor\n\n", var4);
         var3.insertString(var3.getLength(), "Version: 1.19.14", var5);
         var3.insertString(var3.getLength(), "by GoatFungus\n\n", var5);
         var3.insertString(var3.getLength(), "For further information visit:\n", var5);
         var3.insertString(var3.getLength(), "https://github.com/goatfungus/NMSSaveEditor", var5);
      } catch (Exception var6) {
         var2.setText("No Man's Sky Save EditorVersion: 1.19.14by GoatFungusFor further information visit:https://github.com/goatfungus/NMSSaveEditor");
      }

      var2.setEditable(false);
      this.getContentPane().Add(var2, "Center");
      this.getRootPane().registerKeyboardAction(new b(this), Keys.getKeyStroke(27, 0), 2);
      this.pack();
   }

   public static void a(Container var0) {
      if (a == null) {
         Frame var1 = MessageBox.getFrameForComponent(var0);
         a = new a(var1);
      }

      a.setLocationRelativeTo(a.getParent());
      a.setVisible(true);
   }
}

}
