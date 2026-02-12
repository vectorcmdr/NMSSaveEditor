using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cy : Form, TreeSelectionListener {
   private string fS;
   private eY fT = null;
   private Button fU;
   private JTree fV;
   private Panel fW;
   private TextBox fX;
   private Panel fY;
   private cJ fZ;
   private bool ga;
   private bool gb;
   private static string gc = "0123456789ABCDEFabcdef";
   private static cy gd = null;
   private bool ge = true;
   private string gf = "";

   private cy(Application var1) {
      base(var1.g());
      Rectangle var2 = new Rectangle(100, 100, 1000, 700);
      Point var3 = var1.g().getLocation();
      var2.x = aH.a("JSONEditor.X", var3.x + 10);
      var2.y = aH.a("JSONEditor.Y", var3.y + 10);
      var2.width = aH.a("JSONEditor.Width", 1000);
      var2.height = aH.a("JSONEditor.Height", 700);
      this.setBounds(var2);
      this.setDefaultCloseOperation(0);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("JSON Editor (Advanced Users Only)");
      this.setModal(true);
      this.addComponentListener(new cz(this));
      this.fV = new JTree();
      this.fV.setModel(new cI(this, (cI)null));
      this.fV.setCellRenderer(new cA(this));
      this.fV.addTreeSelectionListener(this);
      this.fW = new Panel();
      this.fW.setViewportView(this.fV);
      this.fX = new TextBox();
      this.fX.putClientProperty("FlatLaf.styleClass", "monospaced");
      this.fX.setEditable(false);
      this.fX.setTabSize(4);
      this.fX.getActionMap().put("copy-to-clipboard", new cG(this));
      this.fX.getActionMap().put("paste-from-clipboard", new cH(this));
      this.fX.getDocument().addDocumentListener(new cB(this));
      this.fY = new Panel();
      this.fY.setRowHeaderView(new cW(this.fX));
      this.fY.setViewportView(this.fX);
      Panel var4 = new Panel();
      var4.setLayout(new TableLayoutPanel());
      this.fU = new Button("Validate");
      this.fU.addActionListener(new cC(this));
      var4.Add(this.fU, "North");
      var4.Add(this.fW, "Center");
      JSplitPane var5 = new JSplitPane(1, var4, this.fY);
      var5.setDividerLocation(aH.a("JSONEditor.Divider", 280));
      var5.addPropertyChangeListener("dividerLocation", new cD(this));
      this.setContentPane(var5);
      this.addWindowListener(new cE(this));
      cF var6 = new cF(this);
      this.fV.getInputMap().put(Keys.getKeyStroke(70, 2), "find");
      this.fV.getActionMap().put("find", var6);
      this.fX.getInputMap().put(Keys.getKeyStroke(70, 2), "find");
      this.fX.getActionMap().put("find", var6);
   }

   private bool a(string var1, eY var2) {
      this.setTitle("JSON Editor (Advanced Users Only)");
      this.fS = var1;
      this.fT = var2;
      this.fX.setText("");
      this.ga = false;
      this.fZ = null;
      this.fV.updateUI();
      int var3 = 0;
      int var4 = 0;
      IEnumerator var6 = var2.names().iterator();

      while(var6.hasNext()) {
         string var5 = (string)var6.next();
         ++var4;
         if (var2.get(var5) is eY) {
            var3 = var4;
            break;
         }
      }

      this.fV.setSelectionRow(var3);
      this.gb = false;
      this.fV.setVisible(true);
      this.fU.setVisible(false);
      this.setVisible(true);
      return this.gb;
   }

   private static string ay() {
      string var0;
      try {
         var0 = (string)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
      } catch (IOException | UnsupportedFlavorException var4) {
         hc.error("Could not retrieve clipboard contents", var4);
         return "";
      }

      StringBuilder var1 = new StringBuilder();
      char[] var2 = var0.ToCharArray();

      for(int var3 = 0; var3 < var2.Length; ++var3) {
         if (var2[var3] != '\r' && var2[var3] != '\n' && var2[var3] != '\t') {
            if (var2[var3] == '\f') {
               var1.append("\\f");
            } else if (var2[var3] == '\b') {
               var1.append("\\b");
            } else if (var2[var3] == 11) {
               var1.append("\\v");
            } else if (var2[var3] == 0) {
               var1.append("\\0");
            } else if (var2[var3] >= ' ' && var2[var3] < 128) {
               var1.append(var2[var3]);
            } else {
               var1.append("\\u");
               var1.append("0123456789ABCDEFabcdef".charAt(var2[var3] >> 12 & 15));
               var1.append("0123456789ABCDEFabcdef".charAt(var2[var3] >> 8 & 15));
               var1.append("0123456789ABCDEFabcdef".charAt(var2[var3] >> 4 & 15));
               var1.append("0123456789ABCDEFabcdef".charAt(var2[var3] & 15));
            }
         } else {
            var1.append(var2[var3]);
         }
      }

      return var1.ToString();
   }

   private static int a(char var0) {
      int var1 = "0123456789ABCDEFabcdef".IndexOf(var0);
      if (var1 < 0) {
         throw new Exception("Error decoding hex");
      } else {
         if (var1 >= 16) {
            var1 -= 6;
         }

         return var1;
      }
   }

   private static void a(string var0, ClipboardOwner var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var3 = var0.ToCharArray();

      for(int var5 = 0; var5 < var3.Length; ++var5) {
         if (var3[var5] == '\\' && var5 + 5 < var3.Length && var3[var5 + 1] == 'u') {
            try {
               int var4 = a(var3[var5 + 2]) << 12 | a(var3[var5 + 3]) << 8 | a(var3[var5 + 4]) << 4 | a(var3[var5 + 5]);
               if (var4 < 32) {
                  var2.append(var3[var5]);
                  var2.append(var3[var5 + 1]);
                  var2.append(var3[var5 + 2]);
                  var2.append(var3[var5 + 3]);
                  var2.append(var3[var5 + 4]);
                  var2.append(var3[var5 + 5]);
               } else {
                  var2.append((char)var4);
               }

               var5 += 5;
            } catch (Exception var7) {
            }
         } else {
            var2.append(var3[var5]);
         }
      }

      StringSelection var8 = new StringSelection(var2.ToString());
      Clipboard var6 = Toolkit.getDefaultToolkit().getSystemClipboard();
      var6.setContents(var8, var1);
   }

   public static bool a(Application var0, string var1, eY var2) {
      if (gd == null) {
         gd = new cy(var0);
      }

      return gd.a(var1, var2);
   }

   public void valueChanged(TreeSelectionEvent var1) {
      if (this.ge) {
         if (this.ga && this.fZ != null) {
            try {
               string var2 = this.fX.getText().Trim();
               if (var2.length() == 0 && MessageBox.showConfirmDialog(this, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.getTitle(), 0) == 0) {
                  this.fZ.Remove();
                  ((cI)this.fV.getModel()).a(this.fZ.gi);
               } else if (MessageBox.showConfirmDialog(this, "The JSON data has changed, do you wish to apply these changes to the save file?", this.getTitle(), 0) == 0) {
                  this.fZ.setText(var2);
                  ((cI)this.fV.getModel()).a(this.fZ);
               }
            } catch (eX var3) {
               MessageBox.showOptionDialog(this, "Error on line #" + var3.getLineNumber() + ": " + var3.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
               this.fX.setCaretPosition(var3.bD());
               this.fX.requestFocus();
               return;
            }
         }

         this.fZ = (cJ)this.fV.getLastSelectedPathComponent();
         if (this.fZ == null) {
            this.fX.setText("");
            this.fX.setEditable(false);
         } else {
            this.fX.setText(this.fZ.getText());
            this.fX.setEditable(true);
         }

         this.ga = false;
         this.fX.setCaretPosition(0);
         this.fX.requestFocus();
      }
   }

   void a(string var1, bool var2, bool var3, bool var4) {
      string var5 = this.fX.getText();
      if (!this.gf.equals(var1)) {
         Highlighter var6 = this.fX.getHighlighter();
         var6.removeAllHighlights();
         Color var7 = SystemInformation.getColor("JSONEditor.hiliteColor");
         DefaultHighlightPainter var8 = new DefaultHighlightPainter(var7);
         int var9 = -1;

         while((var9 = var5.IndexOf(var1, var9 + 1)) >= 0) {
            try {
               var6.addHighlight(var9, var9 + var1.length(), var8);
            } catch (Exception var11) {
            }
         }
      }

      if (!var3) {
         var5 = var5.ToUpper();
         var1 = var1.ToUpper();
      }

      int var12 = this.fX.getCaretPosition();
      int var13 = -1;
      if (var2) {
         if (var12 > var1.length()) {
            var13 = var5.LastIndexOf(var1, var12 - var1.length() - 1);
         }

         if (var13 < 0 && var4) {
            var13 = var5.LastIndexOf(var1);
         }
      } else {
         if (var12 < var5.length() - 1) {
            var13 = var5.IndexOf(var1, var12 + 1);
         }

         if (var13 < 0 && var4) {
            var13 = var5.IndexOf(var1);
         }
      }

      if (var13 < 0) {
         MessageBox.showMessageDialog(this, "Text not found!");
      } else {
         this.fX.setCaretPosition(var13);
         this.fX.setSelectionStart(var13);
         this.fX.setSelectionEnd(var13 + var1.length());
      }

   }
   static string az() {
      return ay();
   }
   static void b(string var0, ClipboardOwner var1) {
      a(var0, var1);
   }
   static string a(cy var0) {
      return var0.fS;
   }
   static eY b(cy var0) {
      return var0.fT;
   }
   static void a(cy var0, bool var1) {
      var0.ga = var1;
   }
   static void b(cy var0, bool var1) {
      var0.gb = var1;
   }
   static TextBox c(cy var0) {
      return var0.fX;
   }
   static cJ d(cy var0) {
      return var0.fZ;
   }
   static JTree e(cy var0) {
      return var0.fV;
   }
   static Button f(cy var0) {
      return var0.fU;
   }
   static bool g(cy var0) {
      return var0.ga;
   }
}

}
