package nomanssave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class cy extends JDialog implements TreeSelectionListener {
   private String fS;
   private eY fT = null;
   private JButton fU;
   private JTree fV;
   private JScrollPane fW;
   private JTextArea fX;
   private JScrollPane fY;
   private cJ fZ;
   private boolean ga;
   private boolean gb;
   private static final String gc = "0123456789ABCDEFabcdef";
   private static cy gd = null;
   private boolean ge = true;
   private String gf = "";

   private cy(Application var1) {
      super(var1.g());
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
      this.fW = new JScrollPane();
      this.fW.setViewportView(this.fV);
      this.fX = new JTextArea();
      this.fX.putClientProperty("FlatLaf.styleClass", "monospaced");
      this.fX.setEditable(false);
      this.fX.setTabSize(4);
      this.fX.getActionMap().put("copy-to-clipboard", new cG(this));
      this.fX.getActionMap().put("paste-from-clipboard", new cH(this));
      this.fX.getDocument().addDocumentListener(new cB(this));
      this.fY = new JScrollPane();
      this.fY.setRowHeaderView(new cW(this.fX));
      this.fY.setViewportView(this.fX);
      JPanel var4 = new JPanel();
      var4.setLayout(new BorderLayout());
      this.fU = new JButton("Validate");
      this.fU.addActionListener(new cC(this));
      var4.add(this.fU, "North");
      var4.add(this.fW, "Center");
      JSplitPane var5 = new JSplitPane(1, var4, this.fY);
      var5.setDividerLocation(aH.a("JSONEditor.Divider", 280));
      var5.addPropertyChangeListener("dividerLocation", new cD(this));
      this.setContentPane(var5);
      this.addWindowListener(new cE(this));
      cF var6 = new cF(this);
      this.fV.getInputMap().put(KeyStroke.getKeyStroke(70, 2), "find");
      this.fV.getActionMap().put("find", var6);
      this.fX.getInputMap().put(KeyStroke.getKeyStroke(70, 2), "find");
      this.fX.getActionMap().put("find", var6);
   }

   private boolean a(String var1, eY var2) {
      this.setTitle("JSON Editor (Advanced Users Only)");
      this.fS = var1;
      this.fT = var2;
      this.fX.setText("");
      this.ga = false;
      this.fZ = null;
      this.fV.updateUI();
      int var3 = 0;
      int var4 = 0;
      Iterator var6 = var2.names().iterator();

      while(var6.hasNext()) {
         String var5 = (String)var6.next();
         ++var4;
         if (var2.get(var5) instanceof eY) {
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

   private static String ay() {
      String var0;
      try {
         var0 = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
      } catch (IOException | UnsupportedFlavorException var4) {
         hc.error("Could not retrieve clipboard contents", var4);
         return "";
      }

      StringBuffer var1 = new StringBuffer();
      char[] var2 = var0.toCharArray();

      for(int var3 = 0; var3 < var2.length; ++var3) {
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

      return var1.toString();
   }

   private static int a(char var0) {
      int var1 = "0123456789ABCDEFabcdef".indexOf(var0);
      if (var1 < 0) {
         throw new RuntimeException("Error decoding hex");
      } else {
         if (var1 >= 16) {
            var1 -= 6;
         }

         return var1;
      }
   }

   private static void a(String var0, ClipboardOwner var1) {
      StringBuffer var2 = new StringBuffer();
      char[] var3 = var0.toCharArray();

      for(int var5 = 0; var5 < var3.length; ++var5) {
         if (var3[var5] == '\\' && var5 + 5 < var3.length && var3[var5 + 1] == 'u') {
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
            } catch (RuntimeException var7) {
            }
         } else {
            var2.append(var3[var5]);
         }
      }

      StringSelection var8 = new StringSelection(var2.toString());
      Clipboard var6 = Toolkit.getDefaultToolkit().getSystemClipboard();
      var6.setContents(var8, var1);
   }

   public static boolean a(Application var0, String var1, eY var2) {
      if (gd == null) {
         gd = new cy(var0);
      }

      return gd.a(var1, var2);
   }

   public void valueChanged(TreeSelectionEvent var1) {
      if (this.ge) {
         if (this.ga && this.fZ != null) {
            try {
               String var2 = this.fX.getText().trim();
               if (var2.length() == 0 && JOptionPane.showConfirmDialog(this, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.getTitle(), 0) == 0) {
                  this.fZ.remove();
                  ((cI)this.fV.getModel()).a(this.fZ.gi);
               } else if (JOptionPane.showConfirmDialog(this, "The JSON data has changed, do you wish to apply these changes to the save file?", this.getTitle(), 0) == 0) {
                  this.fZ.setText(var2);
                  ((cI)this.fV.getModel()).a(this.fZ);
               }
            } catch (eX var3) {
               JOptionPane.showOptionDialog(this, "Error on line #" + var3.getLineNumber() + ": " + var3.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
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

   void a(String var1, boolean var2, boolean var3, boolean var4) {
      String var5 = this.fX.getText();
      if (!this.gf.equals(var1)) {
         Highlighter var6 = this.fX.getHighlighter();
         var6.removeAllHighlights();
         Color var7 = UIManager.getColor("JSONEditor.hiliteColor");
         DefaultHighlightPainter var8 = new DefaultHighlightPainter(var7);
         int var9 = -1;

         while((var9 = var5.indexOf(var1, var9 + 1)) >= 0) {
            try {
               var6.addHighlight(var9, var9 + var1.length(), var8);
            } catch (BadLocationException var11) {
            }
         }
      }

      if (!var3) {
         var5 = var5.toUpperCase();
         var1 = var1.toUpperCase();
      }

      int var12 = this.fX.getCaretPosition();
      int var13 = -1;
      if (var2) {
         if (var12 > var1.length()) {
            var13 = var5.lastIndexOf(var1, var12 - var1.length() - 1);
         }

         if (var13 < 0 && var4) {
            var13 = var5.lastIndexOf(var1);
         }
      } else {
         if (var12 < var5.length() - 1) {
            var13 = var5.indexOf(var1, var12 + 1);
         }

         if (var13 < 0 && var4) {
            var13 = var5.indexOf(var1);
         }
      }

      if (var13 < 0) {
         JOptionPane.showMessageDialog(this, "Text not found!");
      } else {
         this.fX.setCaretPosition(var13);
         this.fX.setSelectionStart(var13);
         this.fX.setSelectionEnd(var13 + var1.length());
      }

   }

   // $FF: synthetic method
   static String az() {
      return ay();
   }

   // $FF: synthetic method
   static void b(String var0, ClipboardOwner var1) {
      a(var0, var1);
   }

   // $FF: synthetic method
   static String a(cy var0) {
      return var0.fS;
   }

   // $FF: synthetic method
   static eY b(cy var0) {
      return var0.fT;
   }

   // $FF: synthetic method
   static void a(cy var0, boolean var1) {
      var0.ga = var1;
   }

   // $FF: synthetic method
   static void b(cy var0, boolean var1) {
      var0.gb = var1;
   }

   // $FF: synthetic method
   static JTextArea c(cy var0) {
      return var0.fX;
   }

   // $FF: synthetic method
   static cJ d(cy var0) {
      return var0.fZ;
   }

   // $FF: synthetic method
   static JTree e(cy var0) {
      return var0.fV;
   }

   // $FF: synthetic method
   static JButton f(cy var0) {
      return var0.fU;
   }

   // $FF: synthetic method
   static boolean g(cy var0) {
      return var0.ga;
   }
}
