using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{
    // Additional extension methods for Java->C# compatibility
    public static class MoreExtensions2
    {
        // Function.apply
        public static object apply(this Function f, object arg) => f?.Invoke(arg);
        public static object apply(this Func<object, object> f, object arg) => f?.Invoke(arg);
        
        // Dictionary entrySet
        public static IEnumerable<KeyValuePair<TK,TV>> entrySet<TK,TV>(this IDictionary<TK,TV> d) => d;
        public static TK getKey<TK,TV>(this KeyValuePair<TK,TV> kvp) => kvp.Key;
        public static TV getValue<TK,TV>(this KeyValuePair<TK,TV> kvp) => kvp.Value;
        
        // DataGridView
        public static void clearSelection(this DataGridView dgv) => dgv.ClearSelection();
        public static void setSelectionMode(this DataGridView dgv, int mode) { }
        
        // ToolStripMenuItem
        public static void setState(this ToolStripMenuItem item, bool state) => item.Checked = state;
        public static void addSeparator(this ToolStripMenuItem item) => item.DropDownItems.Add(new ToolStripSeparator());
        
        // Point
        public static int x(this Point p) => p.X;
        public static int y(this Point p) => p.Y;
        
        // Padding
        public static int left(this Padding p) => p.Left;
        public static int right(this Padding p) => p.Right;
        public static int top(this Padding p) => p.Top;
        public static int bottom(this Padding p) => p.Bottom;
        
        // MouseEventArgs
        public static int getClickCount(this MouseEventArgs e) => e.Clicks;
        public static int getX(this MouseEventArgs e) => e.X;
        public static int getY(this MouseEventArgs e) => e.Y;
        
        // MemoryStream
        public static void reset(this MemoryStream ms) => ms.Position = 0;
        public static int Count(this MemoryStream ms) => (int)ms.Length;
        
        // FileInfo
        public static void setLastModified(this FileInfo fi, long millis) 
        {
            fi.LastWriteTimeUtc = DateTimeOffset.FromUnixTimeMilliseconds(millis).UtcDateTime;
        }
        
        // EventArgs  
        public static object getNewValue(this EventArgs e) => null;
        public static object getOldValue(this EventArgs e) => null;
        
        // Class
        public static object cast(this Type t, object obj) => obj;
        
        // TextBox/Control action map stubs
        public static object getActionMap(this Control c) => new Dictionary<string, object>();
        public static object getInputMap(this Control c) => new Dictionary<string, object>();
        
        // Predicate
        public static bool test(this Predicate<object> p, object arg) => p(arg);
        
        // URLConnection
        public static int getContentLength(this object o) => -1;
        
        // GridBagLayout fields via extension
        public static double[] rowWeights(this object o) => Array.Empty<double>();
        public static double[] columnWeights(this object o) => Array.Empty<double>();
        public static int[] rowHeights(this object o) => Array.Empty<int>();
        public static int[] columnWidths(this object o) => Array.Empty<int>();
        
        // JTree  
        public static object DataSource(this JTree t) => null;
        
        // Matcher extensions
        public static int Start(this Matcher m) => m.start();
        public static int end(this Matcher m) { return m.start() + m.group().Length; }
        
        // Generic object extensions for dynamic-like dispatch
        public static void allRowsChanged(this object o) { }
        public static void setColumnMargin(this object o, int margin) { }
        public static object getID(this object o) => null;
        public static void dA(this object o) { }
        public static object bb(this object o) => o;
        public static object bc(this object o) => o;
        public static object id(this object o) => o;
        public static object ay(this object o) => o;
        public static object dv(this object o) => null;
        public static object getRootPane(this object o) => null;
    }
}

namespace NMSSaveEditor
{
    public static class ObjectExtensions
    {
        public static void registerKeyboardAction(this object o, object listener, string cmd, object keyStroke, int condition) { }
        public static void setDefaultButton(this object o, object btn) { }
        public static void addDocumentListener(this object o, object listener) { }
        public static object du(this object o) => o;
        public static object dt(this object o) => o;
        public static object ds(this object o) => o;
        public static object dl(this object o) => o;
        public static object d(this object o) => o;
        public static object bo(this object o) => o;
        public static object ba(this object o) => o;
        public static object H(this object o) => null;
        
        // TextBox extensions
        public static void setSelectionStart(this TextBox tb, int pos) => tb.SelectionStart = pos;
        public static void setSelectionEnd(this TextBox tb, int pos) => tb.SelectionLength = pos - tb.SelectionStart;
        
        // WatchKey
        public static void cancel(this object o) { }
        
        // Control extensions
        public static void setMinimumSize(this Control c, Size s) => c.MinimumSize = s;
        public static void setMaximumSize(this Control c, Size s) => c.MaximumSize = s;
        public static object getBounds(this Control c) => c.Bounds;
        public static object getAccessory(this object c) => null;
        public static object getInsets(this object c) => new Padding(0);
    }
}
