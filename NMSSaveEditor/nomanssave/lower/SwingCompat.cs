using System;
using System.Drawing;
using System.Windows.Forms;

namespace NMSSaveEditor
{
    // Java AWT layout stubs
    public class BorderLayout { public BorderLayout(int h, int v) {} }
    public class FlowLayout { public FlowLayout(int align) {} }
    public class FormLayout
    {
        public FormLayout(object[] cols, object[] rows) {}
        public void appendColumn(object col) {}
        public void appendRow(object row) {}
        public void insertRow(int i, object row) {}
    }
    public class ColumnSpec
    {
        public static ColumnSpec decode(string s) => new ColumnSpec();
    }
    public class RowSpec
    {
        public static RowSpec decode(string s) => new RowSpec();
    }
    public static class FormFactory
    {
        public static ColumnSpec LABEL_COMPONENT_GAP_COLSPEC = new ColumnSpec();
        public static RowSpec LINE_GAP_ROWSPEC = new RowSpec();
        public static RowSpec DEFAULT_ROWSPEC = new RowSpec();
    }
    public class Insets
    {
        public int top, left, bottom, right;
        public Insets(int t, int l, int b, int r) { top = t; left = l; bottom = b; right = r; }
    }
    public class LineBorder
    {
        public Color color;
        public int thickness;
        public LineBorder(Color c) { color = c; thickness = 1; }
        public LineBorder(Color c, int t) { color = c; thickness = t; }
    }
    public class CompoundBorder
    {
        public CompoundBorder(object outer, object inner) {}
    }
    public class ImageIcon
    {
        public ImageIcon(Image img) {}
        public ImageIcon(string path) {}
    }

    // Java Swing stubs
    public enum ModalExclusionType { APPLICATION_EXCLUDE }
    public static class JOptionPane
    {
        public static int YES_OPTION = 0;
        public static int NO_OPTION = 1;
        public static int CANCEL_OPTION = 2;
        public static int YES_NO_OPTION = 0;
        public static int YES_NO_CANCEL_OPTION = 1;
        public static Form getFrameForComponent(object c) => null;
        public static int showConfirmDialog(object parent, string message) => 0;
        public static int showConfirmDialog(object parent, string message, string title, int optionType) => 0;
        public static void showMessageDialog(object parent, string message) {}
        public static void showMessageDialog(object parent, string message, string title, int messageType) {}
        public static string showInputDialog(object parent, string message) => "";
        public static string showInputDialog(object parent, string message, string title, int messageType) => "";
        public static int ERROR_MESSAGE = 0;
        public static int INFORMATION_MESSAGE = 1;
        public static int WARNING_MESSAGE = 2;
        public static int QUESTION_MESSAGE = 3;
        public static int PLAIN_MESSAGE = -1;
    }
    public static class UIManager
    {
        public static int getInt(string key) => 0;
        public static Font getFont(string key) => null;
        public static Color getColor(string key) => Color.Black;
        public static object get(string key) => null;
        public static void put(string key, object value) {}
        public static void setLookAndFeel(string className) {}
    }
    public static class BorderFactory
    {
        public static object createEmptyBorder(int t, int l, int b, int r) => null;
        public static object createTitledBorder(string title) => null;
        public static object createCompoundBorder(object outer, object inner) => null;
        public static object createLineBorder(Color c) => null;
        public static object createLineBorder(Color c, int thickness) => null;
    }

    // Additional extension methods for Swing compatibility
    public static class SwingCompatExtensions
    {
        public static void setBorder(this Control c, object border) { /* no-op */ }
        public static object getBorder(this Control c) => null;
        public static void addMouseListener(this Control c, object listener) { /* no-op */ }
        public static void addActionListener(this Control c, object listener) { /* no-op */ }
        public static void addActionListener(this ToolStripItem item, object listener) { /* no-op */ }
        public static void setSelected(this ToolStripMenuItem item, bool selected) { item.Checked = selected; }
        public static void setSelected(this CheckBox cb, bool selected) { cb.Checked = selected; }
        public static void setModel(this ComboBox cb, object model) { /* no-op */ }
        public static void setText(this ToolStripItem item, string text) { item.Text = text; }
        public static void setModalExclusionType(this Form f, ModalExclusionType type) { /* no-op */ }
    }
}
