package nomanssave;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class a extends JDialog {
  private static a a = null;
  
  private a(Frame paramFrame) {
    super(paramFrame);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("About Save Editor");
    setModal(true);
    JTextPane jTextPane = new JTextPane();
    jTextPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    jTextPane.setOpaque(false);
    try {
      StyledDocument styledDocument = (StyledDocument)jTextPane.getDocument();
      SimpleAttributeSet simpleAttributeSet1 = new SimpleAttributeSet();
      StyleConstants.setBold(simpleAttributeSet1, true);
      SimpleAttributeSet simpleAttributeSet2 = new SimpleAttributeSet();
      styledDocument.insertString(styledDocument.getLength(), "No Man's Sky Save Editor\n\n", simpleAttributeSet1);
      styledDocument.insertString(styledDocument.getLength(), "Version: 1.18.1\n", simpleAttributeSet2);
      styledDocument.insertString(styledDocument.getLength(), "by GoatFungus\n\n", simpleAttributeSet2);
      styledDocument.insertString(styledDocument.getLength(), "For further information visit:\n", simpleAttributeSet2);
      styledDocument.insertString(styledDocument.getLength(), "https://github.com/goatfungus/NMSSaveEditor", simpleAttributeSet2);
    } catch (BadLocationException badLocationException) {
      jTextPane.setText("No Man's Sky Save Editor\r\n\r\nVersion: 1.18.1\r\nby GoatFungus\r\n\r\nFor further information visit:\r\nhttps://github.com/goatfungus/NMSSaveEditor");
    } 
    jTextPane.setEditable(false);
    getContentPane().add(jTextPane, "Center");
    getRootPane().registerKeyboardAction(new b(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  public static void a(Container paramContainer) {
    if (a == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      a = new a(frame);
    } 
    a.setLocationRelativeTo(a.getParent());
    a.setVisible(true);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */