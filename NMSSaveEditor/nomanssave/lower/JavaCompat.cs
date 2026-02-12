using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Drawing;

namespace NMSSaveEditor
{
    // Java ActionListener equivalent - base class for event handlers
    public class ActionListener
    {
        public virtual void actionPerformed(EventArgs e) { }
    }

    // Java AbstractAction
    public class AbstractAction : ActionListener { }

    // Java Runnable interface
    public interface Runnable
    {
        void run();
    }

    // Java Pattern (regex)
    public class Pattern
    {
        private Regex _regex;
        public static Pattern compile(string regex) { return new Pattern { _regex = new Regex(regex) }; }
        public static Pattern compile(string regex, int flags) { return new Pattern { _regex = new Regex(regex) }; }
        public Matcher matcher(string input) { return new Matcher(_regex, input); }
        public Matcher Match(string input) { return matcher(input); }
        public string pattern() { return _regex?.ToString() ?? ""; }
        public static int CASE_INSENSITIVE = 1;
        public static int DOTALL = 2;
        public static int MULTILINE = 4;
        public static implicit operator Pattern(Regex r) { return new Pattern { _regex = r }; }
    }

    public class Matcher
    {
        private Regex _regex;
        private string _input;
        private Match _match;
        public int _lastIndex;
        public string Input => _input;
        public string Pattern => _regex?.ToString() ?? "";
        public bool hitEnd => _match != null && (_match.Index + _match.Length >= _input.Length);
        public Matcher(Regex regex, string input) { _regex = regex; _input = input; }
        public bool find() { 
            if (_match != null && _match.Success) { _match = _match.NextMatch(); }
            else { _match = _regex.Match(_input); }
            _lastIndex = _match?.Index ?? 0; 
            return _match.Success; 
        }
        public bool Matches() { return matches(); }
        public bool matches() { _match = _regex.Match(_input); return _match.Success && _match.Value == _input; }
        public string group() { return _match?.Value; }
        public string group(int i) { return _match?.Groups[i]?.Value; }
        public string replaceAll(string replacement) { return _regex.Replace(_input, replacement); }
        public string replaceFirst(string replacement) { return _regex.Replace(_input, replacement, 1); }
        public int start() { return _match?.Index ?? 0; }
        public int Start() { return start(); }
        public int end() { return (_match?.Index ?? 0) + (_match?.Length ?? 0); }
        public System.Text.RegularExpressions.GroupCollection Groups => _match?.Groups;
        public bool Success => _match?.Success ?? false;
        public int groupCount() { return _match?.Groups?.Count - 1 ?? 0; }
    }

    // Java Function<T,R>
    public delegate R Function<T, R>(T arg);

    // Java Supplier<T>
    public delegate T Supplier<T>();

    // Java Class type
    public class Class
    {
        private Type _type;
        public Class() { }
        public Class(Type t) { _type = t; }
        public string getName() { return _type?.FullName ?? ""; }
        public string Name => getName();
        public string getSimpleName() { return _type?.Name ?? ""; }
        public bool isAssignableFrom(Class other) { return true; }
        public bool IsInstanceOfType(object obj) { return _type?.IsInstanceOfType(obj) ?? false; }
        public object[] getEnumConstants() { return _type != null ? Enum.GetValues(_type) as object[] : new object[0]; }
        public System.Reflection.FieldInfo getDeclaredField(string name) { return _type?.GetField(name, System.Reflection.BindingFlags.Public | System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.Static); }
        public System.Reflection.MethodInfo getDeclaredMethod(string name, params Type[] paramTypes) { return _type?.GetMethod(name, System.Reflection.BindingFlags.Public | System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.Static); }
        public object cast(object obj) { return obj; }
        public static implicit operator Class(Type t) { return new Class(t); }
    }

    // Java Integer wrapper
    public class Integer
    {
        public int _value;
        public Integer() { }
        public Integer(int v) { _value = v; }
        public Integer(string s) { _value = int.Parse(s); }
        public static implicit operator int(Integer i) => i._value;
        public static implicit operator Integer(int i) => new Integer(i);
        public static Type TYPE = typeof(int);
        public static int parseInt(string s) { return int.Parse(s); }
        public static int parseInt(string s, int radix) { return Convert.ToInt32(s, radix); }
        public static string toHexString(int i) { return i.ToString("X"); }
        public static string toBinaryString(int i) { return Convert.ToString(i, 2); }
        public static int numberOfLeadingZeros(int i) { if (i == 0) return 32; int n = 0; if ((i & 0xFFFF0000) == 0) { n += 16; i <<= 16; } if ((i & 0xFF000000) == 0) { n += 8; i <<= 8; } if ((i & 0xF0000000) == 0) { n += 4; i <<= 4; } if ((i & 0xC0000000) == 0) { n += 2; i <<= 2; } if ((i & 0x80000000) == 0) { n += 1; } return n; }
        public static int MAX_VALUE = int.MaxValue;
        public static int MIN_VALUE = int.MinValue;
        public override string ToString() => _value.ToString();
    }

    // Java Number
    public class Number
    {
        public virtual int intValue() { return 0; }
        public virtual long longValue() { return 0; }
        public virtual float floatValue() { return 0; }
        public virtual double doubleValue() { return 0; }
        public static implicit operator Number(int i) => new NumberWrapper(i, i, i, i);
        public static implicit operator Number(long l) => new NumberWrapper((int)l, l, l, l);
        public static implicit operator Number(double d) => new NumberWrapper((int)d, (long)d, (float)d, d);
        public static implicit operator Number(float f) => new NumberWrapper((int)f, (long)f, f, f);
    }

    // Java Long wrapper
    public class Long
    {
        public static Type TYPE = typeof(long);
        public static long parseLong(string s) { return long.Parse(s); }
        public long longValue() { return _value; }
        private long _value;
        public Long() { }
        public Long(long v) { _value = v; }
        public static implicit operator long(Long l) { return l._value; }
        public static implicit operator Long(long l) { return new Long(l); }
        public override bool Equals(object obj) { if (obj is Long l) return _value == l._value; if (obj is long lv) return _value == lv; return false; }
        public override int GetHashCode() { return _value.GetHashCode(); }
    }

    // Java CharSequence
    public interface CharSequence
    {
        int length();
        char charAt(int index);
        string subSequence(int start, int end);
    }

    // Java Component (AWT)
    public class Component : Control
    {
        public virtual void setEnabled(bool b) { this.Enabled = b; }
        public virtual void setVisible(bool b) { this.Visible = b; }
        public virtual void setForeground(Color c) { this.ForeColor = c; }
        public virtual void setBackground(Color c) { this.BackColor = c; }
        public virtual void setFont(Font f) { this.Font = f; }
        public virtual void addFocusListener(FocusListener l) { }
        public virtual void removeFocusListener(FocusListener l) { }
        public virtual void setPreferredSize(object d) { }
        public virtual void revalidate() { this.Invalidate(); }
    }

    // Java Container
    public class Container : Component
    {
        public virtual void setLayout(object layout) { }
        public virtual void add(object comp) { }
        public virtual void add(object comp, object constraints) { }
    }

    // Java Frame
    public class Frame : Form
    {
        public virtual void setTitle(string title) { this.Text = title; }
    }

    // Java Window
    public class Window : Form { }

    // Java Focus types
    public class FocusEvent : EventArgs
    {
        public object getSource() { return null; }
    }

    public interface FocusListener
    {
        void focusGained(FocusEvent e);
        void focusLost(FocusEvent e);
    }

    // Java ComponentEvent/ComponentAdapter
    public class ComponentEvent : EventArgs
    {
        public object getSource() { return null; }
        public object getComponent() { return null; }
    }

    public class ComponentAdapter
    {
        public virtual void componentResized(ComponentEvent e) { }
        public virtual void componentMoved(ComponentEvent e) { }
        public virtual void componentShown(ComponentEvent e) { }
        public virtual void componentHidden(ComponentEvent e) { }
    }

    // Java Element (XML)
    // Java DOM Node - wraps XmlNode
    public class Node
    {
        public static short ELEMENT_NODE = 1;
        public static short TEXT_NODE = 3;
        public static short CDATA_SECTION_NODE = 4;
        public static short COMMENT_NODE = 8;
        public System.Xml.XmlNode _xmlNode;
        public Node() { }
        public Node(System.Xml.XmlNode n) { _xmlNode = n; }
        public virtual string getNodeName() { return _xmlNode?.Name ?? ""; }
        public virtual string getNodeValue() { return _xmlNode?.Value ?? ""; }
        public virtual string getTextContent() { return _xmlNode?.InnerText ?? ""; }
        public virtual void setTextContent(string text) { if (_xmlNode != null) _xmlNode.InnerText = text; }
        public virtual int getNodeType() { return _xmlNode != null ? (int)_xmlNode.NodeType : 0; }
        public virtual NodeList getChildNodes() { return _xmlNode != null ? new NodeList(_xmlNode.ChildNodes) : new NodeList(); }
        public virtual Node getParentNode() { return _xmlNode?.ParentNode != null ? new Node(_xmlNode.ParentNode) : null; }
        public virtual void appendChild(object child) { }
        public virtual void removeChild(object child) { }
        public virtual bool hasChildNodes() { return _xmlNode?.HasChildNodes ?? false; }
        public static implicit operator Node(System.Xml.XmlNode n) => n != null ? new Node(n) : null;
        public static implicit operator System.Xml.XmlNode(Node n) => n?._xmlNode;
    }

    public class Element : Node
    {
        public Element() { }
        public Element(System.Xml.XmlNode n) : base(n) { }
        public virtual string getAttribute(string name) { return (_xmlNode as System.Xml.XmlElement)?.GetAttribute(name) ?? ""; }
        public virtual void setAttribute(string name, string value) { (_xmlNode as System.Xml.XmlElement)?.SetAttribute(name, value); }
        public virtual string getTagName() { return _xmlNode?.Name ?? ""; }
        public override string getTextContent() { return _xmlNode?.InnerText ?? ""; }
        public override void setTextContent(string text) { if (_xmlNode != null) _xmlNode.InnerText = text; }
        public virtual NodeList getElementsByTagName(string name) { return _xmlNode is System.Xml.XmlElement el ? new NodeList(el.GetElementsByTagName(name)) : new NodeList(); }
        public new virtual NodeList getChildNodes() { return _xmlNode != null ? new NodeList(_xmlNode.ChildNodes) : new NodeList(); }
        public new virtual Element getParentNode() { return _xmlNode?.ParentNode != null ? new Element(_xmlNode.ParentNode) : null; }
        public override void appendChild(object child) { }
        public override void removeChild(object child) { }
        public static implicit operator Element(System.Xml.XmlNode n) => n != null ? new Element(n) : null;
    }

    public class NodeList
    {
        private System.Xml.XmlNodeList _xmlNodeList;
        private List<Element> items = new List<Element>();
        public NodeList() { }
        public NodeList(System.Xml.XmlNodeList nl) { _xmlNodeList = nl; }
        public int getLength() { return _xmlNodeList?.Count ?? items.Count; }
        public int Length => getLength();
        public int Count => getLength();
        public Element item(int index) { return _xmlNodeList != null ? new Element(_xmlNodeList[index]) : items[index]; }
        public static implicit operator NodeList(System.Xml.XmlNodeList nl) => nl != null ? new NodeList(nl) : null;
        public static implicit operator System.Xml.XmlNodeList(NodeList nl) => nl?._xmlNodeList;
    }

    // Java File types
    public class FileFilter
    {
        public virtual bool accept(string f) { return true; }
        public virtual string getDescription() { return ""; }
    }

    public class FileView
    {
        public virtual string getName(string f) { return ""; }
        public virtual string getDescription(string f) { return ""; }
        public virtual string getTypeDescription(string f) { return ""; }
        public virtual object getIcon(string f) { return null; }
    }

    // Java JFileChooser base (OpenFileDialog is sealed, so we need wrapper)
    public class JFileChooser
    {
        public string FileName { get; set; }
        public string InitialDirectory { get; set; }
        public string Filter { get; set; }
        public string Title { get; set; }
        public virtual void setFileFilter(FileFilter filter) { }
        public virtual void setFileView(FileView view) { }
        public virtual void setCurrentDirectory(string dir) { }
        public virtual void setDialogTitle(string title) { Title = title; }
        public virtual int showOpenDialog(object parent) { return 0; }
        public virtual int showSaveDialog(object parent) { return 0; }
        public virtual string getSelectedFile() { return FileName; }
        public static int APPROVE_OPTION = 0;
    }

    // Java table/list types
    public interface TableCellRenderer
    {
        object getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column);
    }

    // Base class for DefaultTableCellRenderer (Java: extends JLabel implements TableCellRenderer)
    public class DefaultTableCellRenderer : Label, TableCellRenderer
    {
        public virtual object getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column) { return this; }
    }

    public class ListModel
    {
        public virtual int getSize() { return 0; }
        public virtual object getElementAt(int index) { return null; }
    }

    // Java Transferable/Clipboard types
    public interface Transferable
    {
        object getTransferData(object flavor);
    }

    public interface ClipboardOwner
    {
        void lostOwnership(object clipboard, Transferable contents);
    }

    // Java Thread base class (System.Threading.Thread is sealed)
    public class JavaThread
    {
        protected System.Threading.Thread _thread;
        public JavaThread()
        {
            _thread = new System.Threading.Thread(() => run());
        }
        public virtual void run() { }
        public virtual void start() { _thread.Start(); }
        public virtual void join() { _thread.Join(); }
        public virtual void interrupt() { }
        public static void sleep(long millis) { System.Threading.Thread.Sleep((int)millis); }
        public virtual void setDaemon(bool d) { _thread.IsBackground = d; }
    }

    // Java Iterable
    public interface Iterable<T> : IEnumerable<T> { }

    // Java Closeable
    public interface Closeable : IDisposable { }

    // Java text types
    public class JTextComponent : TextBox { }

    public class TextAction : ActionListener { }

    // Java Caret types
    public class CaretEvent : EventArgs
    {
        public virtual int getDot() { return 0; }
        public virtual int getMark() { return 0; }
    }

    public interface CaretListener
    {
        void caretUpdate(CaretEvent e);
    }

    // Java FontMetrics
    public class FontMetrics
    {
        private Font _font;
        public FontMetrics() { }
        public FontMetrics(Font f) { _font = f; }
        public virtual int stringWidth(string s) { return s.Length * 8; }
        public virtual int getHeight() { return 16; }
        public virtual int getAscent() { return 12; }
        public virtual int getDescent() { return 4; }
    }

    // Java Tree types
    public class TreePath
    {
        public object getLastPathComponent() { return null; }
        public object[] getPath() { return new object[0]; }
    }

    public class TreeSelectionEvent : EventArgs
    {
        public TreePath getPath() { return new TreePath(); }
        public TreePath getNewLeadSelectionPath() { return new TreePath(); }
    }

    public interface TreeSelectionListener
    {
        void valueChanged(TreeSelectionEvent e);
    }

    public interface TreeModel
    {
        object getRoot();
        object getChild(object parent, int index);
        int getChildCount(object parent);
        bool isLeaf(object node);
    }

    public interface TreeModelListener
    {
        void treeNodesChanged(object e);
        void treeNodesInserted(object e);
        void treeNodesRemoved(object e);
        void treeStructureChanged(object e);
    }

    public class DefaultTreeCellRenderer : Label
    {
        public virtual object getTreeCellRendererComponent(object tree, object value, bool sel, bool expanded, bool leaf, int row, bool hasFocus) { return this; }
    }

    public class JTree : TreeView { }

    // Java Logging
    public class LogRecord
    {
        public string getMessage() { return ""; }
        public object getLevel() { return null; }
    }

    public class Handler
    {
        public virtual void publish(LogRecord record) { }
        public virtual void flush() { }
        public virtual void close() { }
    }

    // Java LZ4 compression stubs
    public class LZ4Factory
    {
        public static LZ4Factory fastestInstance() { return new LZ4Factory(); }
        public LZ4Compressor fastCompressor() { return new LZ4Compressor(); }
        public LZ4Compressor highCompressor() { return new LZ4Compressor(); }
        public object fastDecompressor() { return null; }
        public object safeDecompressor() { return null; }
    }

    public class LZ4Compressor
    {
        public int maxCompressedLength(int length) { return length * 2; }
        public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) { return 0; }
    }

    // Java FilterInputStream / FilterOutputStream
    public class FilterInputStream : Stream
    {
        protected Stream @in;
        public FilterInputStream() { }
        public FilterInputStream(Stream s) { @in = s; }
        public override bool CanRead => @in?.CanRead ?? false;
        public override bool CanSeek => @in?.CanSeek ?? false;
        public override bool CanWrite => @in?.CanWrite ?? false;
        public override long Length => @in?.Length ?? 0;
        public override long Position { get => @in?.Position ?? 0; set { if (@in != null) @in.Position = value; } }
        public override void Flush() { @in?.Flush(); }
        public override int Read(byte[] buffer, int offset, int count) { return @in?.Read(buffer, offset, count) ?? 0; }
        public override long Seek(long offset, SeekOrigin origin) { return @in?.Seek(offset, origin) ?? 0; }
        public override void SetLength(long value) { @in?.SetLength(value); }
        public override void Write(byte[] buffer, int offset, int count) { @in?.Write(buffer, offset, count); }
    }

    public class FilterOutputStream : Stream
    {
        protected Stream @out;
        public FilterOutputStream() { }
        public FilterOutputStream(Stream s) { @out = s; }
        public override bool CanRead => @out?.CanRead ?? false;
        public override bool CanSeek => @out?.CanSeek ?? false;
        public override bool CanWrite => @out?.CanWrite ?? true;
        public override long Length => @out?.Length ?? 0;
        public override long Position { get => @out?.Position ?? 0; set { if (@out != null) @out.Position = value; } }
        public override void Flush() { @out?.Flush(); }
        public override int Read(byte[] buffer, int offset, int count) { return @out?.Read(buffer, offset, count) ?? 0; }
        public override long Seek(long offset, SeekOrigin origin) { return @out?.Seek(offset, origin) ?? 0; }
        public override void SetLength(long value) { @out?.SetLength(value); }
        public override void Write(byte[] buffer, int offset, int count) { @out?.Write(buffer, offset, count); }
    }

    // Java SecureRandom
    public class SecureRandom : Random { }

    // Java WatchService
    public class WatchService : IDisposable
    {
        public void Dispose() { }
    }

    // Java CharsetDecoder stub
    public class CharsetDecoder
    {
        public CharsetDecoder onMalformedInput(object action) { return this; }
        public CharsetDecoder onUnmappableCharacter(object action) { return this; }
    }

    // Java Throwable
    public class Throwable : Exception
    {
        public Throwable() : base() { }
        public Throwable(string message) : base(message) { }
        public Throwable(string message, Exception inner) : base(message, inner) { }
    }

    // Java Predicate (non-generic) - for compatibility
    public delegate bool Predicate(object obj);

    // Non-generic versions for raw type usage (Java compatibility)
    public delegate object Function(object arg);

    public delegate object Supplier();

    public interface Iterable : IEnumerable<object> { }

}

// === Additional Java types needed for compilation ===

public class ActionEvent : EventArgs {
    public object Source { get; set; }
    public string ActionCommand { get; set; }
    public ActionEvent() {}
    public ActionEvent(object source) { Source = source; }
}

public interface ListDataListener {
    void intervalAdded(object e);
    void intervalRemoved(object e);
    void contentsChanged(object e);
}

public interface ComboBoxModel {
    object getSelectedItem();
    void setSelectedItem(object item);
    int getSize();
    object getElementAt(int index);
    void addListDataListener(ListDataListener l);
    void removeListDataListener(ListDataListener l);
}

public interface TableModelListener {
    void tableChanged(object e);
}

public interface TableModel {
    int getRowCount();
    int getColumnCount();
    string getColumnName(int col);
    object getColumnClass(int col);
    bool isCellEditable(int row, int col);
    object getValueAt(int row, int col);
    void setValueAt(object val, int row, int col);
    void addTableModelListener(TableModelListener l);
    void removeTableModelListener(TableModelListener l);
}

public class TableRowSorter {
    public TableRowSorter() {}
    public TableRowSorter(TableModel model) {}
    public void setModel(TableModel model) {}
    public Comparator getComparator(int col) { return null; }
    public void setComparator(int col, Comparator c) {}
    public void setSortable(int col, bool sortable) {}
    public int convertRowIndexToModel(int viewRow) { return viewRow; }
    public void sort() {}
    public void setRowFilter(object filter) {}
    public void toggleSortOrder(int col) {}
}

public class StringBuffer {
    private System.Text.StringBuilder sb = new System.Text.StringBuilder();
    public StringBuffer() {}
    public StringBuffer(string s) { sb = new System.Text.StringBuilder(s); }
    public StringBuffer(int capacity) { sb = new System.Text.StringBuilder(capacity); }
    public StringBuffer append(object o) { sb.Append(o); return this; }
    public StringBuffer append(string s) { sb.Append(s); return this; }
    public StringBuffer append(char c) { sb.Append(c); return this; }
    public StringBuffer append(int i) { sb.Append(i); return this; }
    public StringBuffer append(long l) { sb.Append(l); return this; }
    public StringBuffer append(double d) { sb.Append(d); return this; }
    public StringBuffer append(bool b) { sb.Append(b); return this; }
    public StringBuffer Append(object o) { sb.Append(o); return this; }
    public StringBuffer Append(string s) { sb.Append(s); return this; }
    public StringBuffer Append(char c) { sb.Append(c); return this; }
    public StringBuffer Append(int i) { sb.Append(i); return this; }
    public StringBuffer Append(long l) { sb.Append(l); return this; }
    public StringBuffer Append(double d) { sb.Append(d); return this; }
    public StringBuffer Append(bool b) { sb.Append(b); return this; }
    public int length() { return sb.Length; }
    public int Length => sb.Length;
    public char charAt(int i) { return sb[i]; }
    public void setCharAt(int i, char c) { sb[i] = c; }
    public StringBuffer delete(int start, int end) { sb.Remove(start, end - start); return this; }
    public StringBuffer insert(int offset, string s) { sb.Insert(offset, s); return this; }
    public StringBuffer insert(int offset, char c) { sb.Insert(offset, c); return this; }
    public StringBuffer Insert(int offset, string s) { sb.Insert(offset, s); return this; }
    public StringBuffer replace(int start, int end, string s) { sb.Remove(start, end - start); sb.Insert(start, s); return this; }
    public int indexOf(string s) { return sb.ToString().IndexOf(s); }
    public int indexOf(string s, int start) { return sb.ToString().IndexOf(s, start); }
    public int lastIndexOf(string s) { return sb.ToString().LastIndexOf(s); }
    public override string ToString() { return sb.ToString(); }
    public string toString() { return sb.ToString(); }
    public string substring(int start) { return sb.ToString(start, sb.Length - start); }
    public string substring(int start, int end) { return sb.ToString(start, end - start); }
    public void deleteCharAt(int i) { sb.Remove(i, 1); }
    public void setLength(int len) { sb.Length = len; }
}

public interface DocumentEvent {
    int getOffset();
    int getLength();
}

public interface DocumentListener {
    void insertUpdate(DocumentEvent e);
    void removeUpdate(DocumentEvent e);
    void changedUpdate(DocumentEvent e);
}

public class XmlElement : XmlNode {
    private System.Xml.XmlElement elem;
    public XmlElement() {}
    public XmlElement(System.Xml.XmlElement e) : base(e) { elem = e; }
    public XmlElement(System.Xml.XmlNode n) : base(n) { elem = n as System.Xml.XmlElement; }
    public string TagName => elem?.Name ?? "";
    public string Name => TagName;
    public string GetAttribute(string name) { return elem?.GetAttribute(name) ?? ""; }
    public string getAttribute(string name) { return GetAttribute(name); }
    public bool HasAttribute(string name) { return elem?.HasAttribute(name) ?? false; }
    public bool hasAttribute(string name) { return HasAttribute(name); }
    public void setAttribute(string name, string value) { elem?.SetAttribute(name, value); }
    public XmlNodeList ChildNodes => new XmlNodeList(elem?.ChildNodes);
    public XmlNodeList getChildNodes() { return ChildNodes; }
    public XmlNodeList getElementsByTagName(string name) { return elem != null ? new XmlNodeList(elem.GetElementsByTagName(name)) : new XmlNodeList(); }
    public new string getTextContent() { return elem?.InnerText ?? ""; }
    public new void setTextContent(string text) { if (elem != null) elem.InnerText = text; }
    public XmlElement appendChild(XmlElement child) { if (elem != null && child?.Inner != null) elem.AppendChild(child.Inner); return child; }
}

public interface Comparator {
    int compare(object o1, object o2);
}

public class Comparator<T> : Comparator {
    private Func<T, T, int> _func;
    public Comparator() {}
    public Comparator(Func<T, T, int> func) { _func = func; }
    public int compare(object o1, object o2) { return _func != null ? _func((T)o1, (T)o2) : 0; }
    public static Comparator<T> comparing(Func<T, IComparable> keyExtractor) {
        return new Comparator<T>((a, b) => keyExtractor(a).CompareTo(keyExtractor(b)));
    }
}

public class JComponent : Panel {
    public JComponent() {}
    public virtual void setPreferredSize(System.Drawing.Size s) { this.Size = s; }
    public virtual void setBorder(Border b) {}
    public virtual void putClientProperty(object key, object value) {}
    public virtual object getClientProperty(object key) { return null; }
    public virtual void revalidate() { Invalidate(); }
    public void setAlignmentX(float alignment) {}
    public static float CENTER_ALIGNMENT = 0.5f;
    public static float LEFT_ALIGNMENT = 0.0f;
    public static float RIGHT_ALIGNMENT = 1.0f;
    public void registerKeyboardAction(object listener, string name, object key, int cond) {}
}

public class Border {
    public Border() {}
}

public class WindowEvent : EventArgs {
    public Form Window { get; set; }
    public WindowEvent() {}
    public WindowEvent(Form window) { Window = window; }
}

public class WindowAdapter {
    public virtual void windowClosing(WindowEvent e) {}
    public virtual void windowOpened(WindowEvent e) {}
    public virtual void windowClosed(WindowEvent e) {}
}

public class MouseEvent : EventArgs {
    public int X { get; set; }
    public int Y { get; set; }
    public int Button { get; set; }
    public int ClickCount { get; set; }
    public object Source { get; set; }
    public bool isPopupTrigger() { return Button == 2; }
    public int getX() { return X; }
    public int getY() { return Y; }
    public int getButton() { return Button; }
    public int getClickCount() { return ClickCount; }
    public object getSource() { return Source; }
    public System.Drawing.Point getPoint() { return new System.Drawing.Point(X, Y); }
}

public class MouseAdapter {
    public virtual void mouseClicked(MouseEvent e) {}
    public virtual void mousePressed(MouseEvent e) {}
    public virtual void mouseReleased(MouseEvent e) {}
}

public class DefaultListCellRenderer : Label {
    public DefaultListCellRenderer() {}
    public virtual Control getListCellRendererComponent(object list, object value, int index, bool isSelected, bool cellHasFocus) { return this; }
}

public interface PropertyChangeListener {
    void propertyChange(PropertyChangeEvent e);
}

public class PropertyChangeEvent : EventArgs {
    public object Source { get; set; }
    public string PropertyName { get; set; }
    public object OldValue { get; set; }
    public object NewValue { get; set; }
    public PropertyChangeEvent() {}
    public PropertyChangeEvent(object source, string propertyName, object oldValue, object newValue) {
        Source = source; PropertyName = propertyName; OldValue = oldValue; NewValue = newValue;
    }
    public object getNewValue() { return NewValue; }
    public object getOldValue() { return OldValue; }
    public string getPropertyName() { return PropertyName; }
}

public interface ListSelectionListener {
    void valueChanged(ListSelectionEvent e);
}

public class ListSelectionEvent : EventArgs {
    public object Source { get; set; }
    public int FirstIndex { get; set; }
    public int LastIndex { get; set; }
    public bool ValueIsAdjusting { get; set; }
    public ListSelectionEvent() {}
    public bool getValueIsAdjusting() { return ValueIsAdjusting; }
}

public class Charset {
    public static Charset forName(string name) { return new Charset(); }
    public string name() { return "UTF-8"; }
}

public class Transferable {
}


// === Additional types for full port ===

public class XmlNode {
    public System.Xml.XmlNode Inner { get; set; }
    public XmlNode() {}
    public XmlNode(System.Xml.XmlNode inner) { Inner = inner; }
    public string getNodeName() { return Inner?.Name ?? ""; }
    public string Name => getNodeName();
    public string getTextContent() { return Inner?.InnerText ?? ""; }
    public void setTextContent(string text) { if (Inner != null) Inner.InnerText = text; }
    public XmlNodeList getChildNodes() { return new XmlNodeList(Inner?.ChildNodes); }
    public short getNodeType() { return (short)(Inner?.NodeType ?? 0); }
    public static short ELEMENT_NODE = 1;
    public static short TEXT_NODE = 3;
}

public class XmlNodeList {
    private System.Xml.XmlNodeList inner;
    public XmlNodeList() {}
    public XmlNodeList(System.Xml.XmlNodeList list) { inner = list; }
    public int getLength() { return inner?.Count ?? 0; }
    public int Length => getLength();
    public int Count => getLength();
    public XmlNode item(int index) { return inner != null ? new XmlNode(inner[index]) : null; }
    public XmlNode this[int index] { get { return item(index); } }
}

public class XmlDocument {
    private System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
    public XmlDocument() {}
    public XmlDocument(System.Xml.XmlDocument d) { doc = d; }
    public void parse(string xml) { doc.LoadXml(xml); }
    public void parse(System.IO.Stream stream) { doc.Load(stream); }
    public XmlElement getDocumentElement() { return new XmlElement(doc.DocumentElement); }
    public XmlElement DocumentElement => getDocumentElement();
    public XmlElement createElement(string name) { return new XmlElement(doc.CreateElement(name)); }
    public System.Xml.XmlDocument InnerDocument => doc;
    public static implicit operator XmlDocument(System.Xml.XmlDocument d) { return new XmlDocument(d); }
}

public class Properties : Dictionary<string, string> {
    public Properties() {}
    public string getProperty(string key) { return ContainsKey(key) ? this[key] : null; }
    public string getProperty(string key, string defaultValue) { return ContainsKey(key) ? this[key] : defaultValue; }
    public void setProperty(string key, string value) { this[key] = value; }
    public void load(System.IO.Stream stream) {
        using var reader = new System.IO.StreamReader(stream);
        string line;
        while ((line = reader.ReadLine()) != null) {
            line = line.Trim();
            if (line.Length > 0 && !line.StartsWith("#")) {
                int eq = line.IndexOf('=');
                if (eq > 0) {
                    this[line.Substring(0, eq).Trim()] = line.Substring(eq + 1).Trim();
                }
            }
        }
    }
    public void store(System.IO.Stream stream, string comments) {
        using var writer = new System.IO.StreamWriter(stream);
        if (comments != null) writer.WriteLine("# " + comments);
        foreach (var kv in this) writer.WriteLine(kv.Key + "=" + kv.Value);
    }
    public System.Collections.Generic.IEnumerable<string> stringPropertyNames() { return Keys; }
}

public class GridLayout : System.Windows.Forms.TableLayoutPanel {
    public GridLayout() {}
    public GridLayout(int rows, int cols) { RowCount = rows; ColumnCount = cols; }
    public GridLayout(int rows, int cols, int hgap, int vgap) : this(rows, cols) {}
}

public class SAXException : Exception {
    public SAXException() {}
    public SAXException(string msg) : base(msg) {}
}

public class ParserConfigurationException : Exception {
    public ParserConfigurationException() {}
    public ParserConfigurationException(string msg) : base(msg) {}
}

public class Date {
    private DateTime dt;
    public Date() { dt = DateTime.Now; }
    public Date(long millis) { dt = new DateTime(1970, 1, 1).AddMilliseconds(millis); }
    public long getTime() { return (long)(dt - new DateTime(1970, 1, 1)).TotalMilliseconds; }
    public override string ToString() { return dt.ToString(); }
}

public class SimpleDateFormat {
    private string pattern;
    public SimpleDateFormat(string pattern) { this.pattern = pattern; }
    public string format(Date date) { return date.ToString(); }
    public string format(object date) { return date?.ToString() ?? ""; }
}

public class Character {
    public static bool isDigit(char c) { return char.IsDigit(c); }
    public static bool isLetter(char c) { return char.IsLetter(c); }
    public static bool isLetterOrDigit(char c) { return char.IsLetterOrDigit(c); }
    public static bool isWhitespace(char c) { return char.IsWhiteSpace(c); }
    public static char toUpperCase(char c) { return char.ToUpper(c); }
    public static char toLowerCase(char c) { return char.ToLower(c); }
    public static int digit(char c, int radix) { return Convert.ToInt32(c.ToString(), radix); }
    public static char forDigit(int digit, int radix) { return Convert.ToString(digit, radix)[0]; }
}

public class Runtime {
    private static Runtime instance = new Runtime();
    public static Runtime getRuntime() { return instance; }
    public int availableProcessors() { return Environment.ProcessorCount; }
    public long maxMemory() { return GC.GetGCMemoryInfo().TotalAvailableMemoryBytes; }
    public long totalMemory() { return GC.GetTotalMemory(false); }
    public long freeMemory() { return maxMemory() - totalMemory(); }
    public void gc() { GC.Collect(); }
}

public class Toolkit {
    private static Toolkit instance = new Toolkit();
    public static Toolkit getDefaultToolkit() { return instance; }
    public System.Drawing.Size getScreenSize() { return new System.Drawing.Size(1920, 1080); }
    public object getSystemClipboard() { return System.Windows.Forms.Clipboard.GetDataObject(); }
}

public static class StyleConstants {
    public static void setForeground(object attrs, System.Drawing.Color c) {}
    public static void setBold(object attrs, bool bold) {}
    public static void setItalic(object attrs, bool italic) {}
}

public class TimeUnit {
    public static TimeUnit MILLISECONDS = new TimeUnit(1);
    public static TimeUnit SECONDS = new TimeUnit(1000);
    public static TimeUnit MINUTES = new TimeUnit(60000);
    private long multiplier;
    private TimeUnit(long mult) { multiplier = mult; }
    public long toMillis(long duration) { return duration * multiplier; }
    public void sleep(long duration) { System.Threading.Thread.Sleep((int)(duration * multiplier)); }
}

public class RoundingMode {
    public static RoundingMode HALF_UP = new RoundingMode();
}

public class Utilities {
    public static int getRowStart(object textComponent, int offset) { return 0; }
    public static int getRowEnd(object textComponent, int offset) { return 0; }
}

