using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Xml;

namespace NMSSaveEditor
{
    /// <summary>
    /// Extension methods to bridge Java API calls to C# equivalents.
    /// These allow the mechanically-ported Java code to compile with minimal edits.
    /// </summary>
    public static class JavaExtensions
    {
        // String extensions (Java String API)
        public static int length(this string s) => s?.Length ?? 0;
        public static bool isEmpty(this string s) => string.IsNullOrEmpty(s);
        public static string trim(this string s) => s?.Trim() ?? "";
        public static string toLowerCase(this string s) => s?.ToLower() ?? "";
        public static string toUpperCase(this string s) => s?.ToUpper() ?? "";
        public static bool startsWith(this string s, string prefix) => s?.StartsWith(prefix) ?? false;
        public static bool endsWith(this string s, string suffix) => s?.EndsWith(suffix) ?? false;
        public static int indexOf(this string s, string str) => s?.IndexOf(str) ?? -1;
        public static int indexOf(this string s, char c) => s?.IndexOf(c) ?? -1;
        public static int indexOf(this string s, string str, int from) => s?.IndexOf(str, from) ?? -1;
        public static int indexOf(this string s, char c, int from) => s?.IndexOf(c, from) ?? -1;
        public static int lastIndexOf(this string s, string str) => s?.LastIndexOf(str) ?? -1;
        public static int lastIndexOf(this string s, char c) => s?.LastIndexOf(c) ?? -1;
        public static string substring(this string s, int start) => s?.Substring(start) ?? "";
        public static string substring(this string s, int start, int end) => s?.Substring(start, end - start) ?? "";
        public static char charAt(this string s, int i) => s[i];
        public static char[] toCharArray(this string s) => s?.ToCharArray() ?? Array.Empty<char>();
        public static bool contains(this string s, string str) => s?.Contains(str) ?? false;
        public static bool contains(this string s, char c) => s?.Contains(c) ?? false;
        public static string replace(this string s, string old, string @new) => s?.Replace(old, @new) ?? "";
        public static string replaceAll(this string s, string regex, string replacement) => System.Text.RegularExpressions.Regex.Replace(s ?? "", regex, replacement);
        public static string replaceFirst(this string s, string regex, string replacement) => System.Text.RegularExpressions.Regex.Replace(s ?? "", regex, replacement, default, TimeSpan.FromSeconds(1));
        public static bool matches(this string s, string regex) => System.Text.RegularExpressions.Regex.IsMatch(s ?? "", regex);
        public static byte[] getBytes(this string s) => Encoding.UTF8.GetBytes(s ?? "");
        public static byte[] getBytes(this string s, string charset) => Encoding.GetEncoding(charset).GetBytes(s ?? "");
        public static byte[] getBytes(this string s, Encoding enc) => enc.GetBytes(s ?? "");
        public static bool equalsIgnoreCase(this string s, string other) => string.Equals(s, other, StringComparison.OrdinalIgnoreCase);
        public static int compareTo(this string s, string other) => string.Compare(s, other, StringComparison.Ordinal);
        public static int compareToIgnoreCase(this string s, string other) => string.Compare(s, other, StringComparison.OrdinalIgnoreCase);
        public static string intern(this string s) => string.Intern(s ?? "");
        public static bool contentEquals(this string s, string other) => s == other;
        public static string concat(this string s, string other) => s + other;
        public static int codePointAt(this string s, int i) => char.ConvertToUtf32(s, i);
        public static string[] split(this string s, string regex, int limit) => s?.Split(new[] { regex }, limit, StringSplitOptions.None) ?? Array.Empty<string>();
        
        // StringBuilder extensions
        public static int length(this StringBuilder sb) => sb?.Length ?? 0;
        public static void setLength(this StringBuilder sb, int len) { if (sb != null) sb.Length = len; }
        public static void deleteCharAt(this StringBuilder sb, int index) => sb?.Remove(index, 1);
        public static char charAt(this StringBuilder sb, int i) => sb[i];
        public static void setCharAt(this StringBuilder sb, int i, char c) { if (sb != null) sb[i] = c; }
        public static StringBuilder delete(this StringBuilder sb, int start, int end) { sb?.Remove(start, end - start); return sb; }
        public static StringBuilder reverse(this StringBuilder sb) { if (sb == null) return sb; var arr = sb.ToString().ToCharArray(); Array.Reverse(arr); sb.Clear(); sb.Append(arr); return sb; }
        public static int indexOf(this StringBuilder sb, string str) => sb?.ToString().IndexOf(str) ?? -1;
        
        // Collection extensions
        public static int size<T>(this ICollection<T> list) => list?.Count ?? 0;
        public static int size(this ICollection list) => list?.Count ?? 0;
        public static bool isEmpty<T>(this ICollection<T> list) => list == null || list.Count == 0;
        public static T get<T>(this IList<T> list, int index) => list[index];
        public static object get(this IList list, int index) => list[index];
        public static void add<T>(this IList<T> list, int index, T item) => list.Insert(index, item);
        public static T set<T>(this IList<T> list, int index, T value) { var old = list[index]; list[index] = value; return old; }
        public static T remove<T>(this IList<T> list, int index) where T : class { var old = list[index]; list.RemoveAt(index); return old; }
        public static bool addAll<T>(this IList<T> list, IEnumerable<T> items) { foreach (var item in items) list.Add(item); return true; }
        public static List<T> subList<T>(this List<T> list, int from, int to) => list.GetRange(from, to - from);
        public static object[] toArray(this IList list) { var arr = new object[list.Count]; list.CopyTo(arr, 0); return arr; }
        public static T[] toArray<T>(this IList<T> list, T[] arr) => list.ToArray();
        public static void sort<T>(this List<T> list, IComparer<T> comp) => list.Sort(comp);
        public static IEnumerable<T> stream<T>(this IEnumerable<T> list) => list;  // stream() is identity in LINQ world
        
        // Map extensions
        public static V get<K, V>(this IDictionary<K, V> dict, K key) { dict.TryGetValue(key, out var val); return val; }
        public static void put<K, V>(this IDictionary<K, V> dict, K key, V value) => dict[key] = value;
        public static V getOrDefault<K, V>(this IDictionary<K, V> dict, K key, V def) => dict.TryGetValue(key, out var val) ? val : def;
        public static void putAll<K, V>(this IDictionary<K, V> dict, IDictionary<K, V> other) { foreach (var kv in other) dict[kv.Key] = kv.Value; }
        public static bool containsKey<K, V>(this IDictionary<K, V> dict, K key) => dict.ContainsKey(key);
        public static bool containsValue<K, V>(this IDictionary<K, V> dict, V value) => dict.Values.Contains(value);
        public static ICollection<K> keySet<K, V>(this IDictionary<K, V> dict) => dict.Keys;
        public static ICollection<V> values<K, V>(this IDictionary<K, V> dict) => dict.Values;
        public static int size<K, V>(this IDictionary<K, V> dict) => dict.Count;
        public static bool isEmpty<K, V>(this IDictionary<K, V> dict) => dict.Count == 0;
        public static V remove<K, V>(this IDictionary<K, V> dict, K key) { dict.TryGetValue(key, out var val); dict.Remove(key); return val; }
        public static V putIfAbsent<K, V>(this IDictionary<K, V> dict, K key, V value) { if (!dict.ContainsKey(key)) { dict[key] = value; return default; } return dict[key]; }
        
        // Properties (Dictionary<string,string>) extensions
        public static string getProperty(this Dictionary<string, string> props, string key) => props.TryGetValue(key, out var val) ? val : null;
        public static string getProperty(this Dictionary<string, string> props, string key, string def) => props.TryGetValue(key, out var val) ? val : def;
        public static void setProperty(this Dictionary<string, string> props, string key, string value) => props[key] = value;
        public static void load(this Dictionary<string, string> props, Stream s) { /* TODO: implement properties loading */ }
        public static void store(this Dictionary<string, string> props, Stream s, string comment) { /* TODO: implement properties storing */ }
        
        // Iterator/Enumerable extensions  
        public static List<T> collect<T>(this IEnumerable<T> source, object collector) => source.ToList();
        public static T[] toArray<T>(this IEnumerable<T> source, Func<int, T[]> gen) => source.ToArray();
        public static bool anyMatch<T>(this IEnumerable<T> source, Func<T, bool> pred) => source.Any(pred);
        public static bool allMatch<T>(this IEnumerable<T> source, Func<T, bool> pred) => source.All(pred);
        public static bool noneMatch<T>(this IEnumerable<T> source, Func<T, bool> pred) => !source.Any(pred);
        public static T findFirst<T>(this IEnumerable<T> source) => source.FirstOrDefault();
        public static long count<T>(this IEnumerable<T> source) => source.LongCount();
        public static void forEach<T>(this IEnumerable<T> source, Action<T> action) { foreach (var item in source) action(item); }
        public static IEnumerable<T> filter<T>(this IEnumerable<T> source, Func<T, bool> pred) => source.Where(pred);
        public static IEnumerable<R> map<T, R>(this IEnumerable<T> source, Func<T, R> mapper) => source.Select(mapper);
        public static IEnumerable<T> sorted<T>(this IEnumerable<T> source) => source.OrderBy(x => x);
        public static IEnumerable<T> sorted<T>(this IEnumerable<T> source, IComparer<T> comp) => source.OrderBy(x => x, comp);
        public static IEnumerable<T> distinct<T>(this IEnumerable<T> source) => source.Distinct();
        public static IEnumerable<T> limit<T>(this IEnumerable<T> source, int n) => source.Take(n);
        public static IEnumerable<T> skip<T>(this IEnumerable<T> source, int n) => source.Skip(n);
        public static T reduce<T>(this IEnumerable<T> source, T identity, Func<T, T, T> accumulator) => source.Aggregate(identity, accumulator);
        public static IEnumerable<R> flatMap<T, R>(this IEnumerable<T> source, Func<T, IEnumerable<R>> mapper) => source.SelectMany(mapper);
        
        // Stream collectors
        public static string joining(this IEnumerable<string> source, string sep) => string.Join(sep, source);
        public static string joining(this IEnumerable<string> source) => string.Join("", source);
        
        // Throwable extensions
        public static void addSuppressed(this Exception ex, Exception suppressed) { /* C# doesn't have suppressed exceptions */ }
        public static string getMessage(this Exception ex) => ex?.Message ?? "";
        public static void printStackTrace(this Exception ex) { Console.Error.WriteLine(ex?.ToString()); }
        
        // I/O extensions
        public static byte[] toByteArray(this MemoryStream ms) => ms?.ToArray() ?? Array.Empty<byte>();
        public static void print(this StreamWriter sw, object obj) => sw?.Write(obj?.ToString() ?? "");
        public static void print(this TextWriter sw, object obj) => sw?.Write(obj?.ToString() ?? "");
        public static void println(this StreamWriter sw, object obj) => sw?.WriteLine(obj?.ToString() ?? "");
        public static void println(this StreamWriter sw) => sw?.WriteLine();
        public static void println(this TextWriter sw, object obj) => sw?.WriteLine(obj?.ToString() ?? "");
        public static void println(this TextWriter sw) => sw?.WriteLine();
        public static int read(this Stream s) { return s.ReadByte(); }
        public static int read(this Stream s, byte[] buf) { return s.Read(buf, 0, buf.Length); }
        public static int read(this Stream s, byte[] buf, int off, int len) { return s.Read(buf, off, len); }
        public static void write(this Stream s, byte[] buf) { s.Write(buf, 0, buf.Length); }
        public static void write(this Stream s, byte[] buf, int off, int len) { s.Write(buf, off, len); }
        public static void write(this Stream s, int b) { s.WriteByte((byte)b); }
        public static long skip(this Stream s, long n) { return s.Seek(n, SeekOrigin.Current); }
        public static int available(this Stream s) { return (int)(s.Length - s.Position); }
        
        // FileInfo extensions  
        public static bool isDirectory(this FileInfo fi) => (fi.Attributes & FileAttributes.Directory) != 0;
        public static bool isHidden(this FileInfo fi) => (fi.Attributes & FileAttributes.Hidden) != 0;
        public static long lastModified(this FileInfo fi) => fi.LastWriteTimeUtc.Ticks;
        public static string getParent(this FileInfo fi) => fi.DirectoryName;
        public static FileInfo getParentFile(this FileInfo fi) => fi.Directory != null ? new FileInfo(fi.Directory.FullName) : null;
        public static bool canRead(this FileInfo fi) => fi.Exists;
        public static long length(this FileInfo fi) => fi.Exists ? fi.Length : 0;
        public static bool renameTo(this FileInfo fi, FileInfo dest) { try { fi.MoveTo(dest.FullName); return true; } catch { return false; } }
        public static bool mkdirs(this DirectoryInfo di) { try { di.Create(); return true; } catch { return false; } }
        
        // Size/Dimension extensions  
        public static int width(this Size s) => s.Width;
        public static int height(this Size s) => s.Height;
        
        // Rectangle extensions
        public static int x(this Rectangle r) => r.X;
        public static int y(this Rectangle r) => r.Y;
        
        // WinForms Control extensions (Java Swing API)
        public static void setEditable(this TextBox tb, bool editable) { tb.ReadOnly = !editable; }
        public static void setHorizontalAlignment(this Control c, int align) { /* TODO */ }
        public static void setIcon(this Label lbl, Image img) { lbl.Image = img; }
        public static Color getBackground(this Control c) => c.BackColor;
        public static Color getForeground(this Control c) => c.ForeColor;
        public static Font getFont(this Control c) => c.Font;
        public static void setForeground(this Control c, Color color) { c.ForeColor = color; }
        public static void setBackground(this Control c, Color color) { c.BackColor = color; }
        public static void setFont(this Control c, Font font) { if (font != null) c.Font = font; }
        public static string getText(this Control c) => c.Text;
        public static void setVisible(this Control c, bool visible) { c.Visible = visible; }
        public static void setEnabled(this Control c, bool enabled) { c.Enabled = enabled; }
        public static bool isEnabled(this Control c) => c.Enabled;
        public static bool isVisible(this Control c) => c.Visible;
        public static void setToolTipText(this Control c, string tip) { /* TODO: ToolTip */ }
        public static void requestFocus(this Control c) { c.Focus(); }
        public static void requestFocusInWindow(this Control c) { c.Focus(); }
        public static void repaint(this Control c) { c.Invalidate(); }
        public static void revalidate(this Control c) { c.PerformLayout(); }
        public static Size getPreferredSize(this Control c) => c.PreferredSize;
        public static Size getSize(this Control c) => c.Size;
        public static int getWidth(this Control c) => c.Width;
        public static int getHeight(this Control c) => c.Height;
        public static void setBounds(this Control c, int x, int y, int w, int h) { c.Bounds = new Rectangle(x, y, w, h); }
        public static void setSize(this Control c, int w, int h) { c.Size = new Size(w, h); }
        public static void setLocation(this Control c, int x, int y) { c.Location = new Point(x, y); }
        public static void setPreferredSize(this Control c, Size s) { c.Size = s; }
        public static void setViewportView(this Panel p, Control c) { p.Controls.Clear(); p.Controls.Add(c); }
        public static void addFocusListener(this Control c, object listener) { /* TODO */ }
        public static void removeFocusListener(this Control c, object listener) { /* TODO */ }
        
        // Panel/Form extensions
        public static void add(this Panel p, Control c) { p.Controls.Add(c); }
        public static void add(this Panel p, Control c, object constraints) { p.Controls.Add(c); }
        public static void Add(this Panel p, Control c) { p.Controls.Add(c); }
        public static void Add(this Panel p, Control c, object constraints) { p.Controls.Add(c); }
        public static void Add(this Panel p, string text) { /* ignore string-only add */ }
        public static void setLayout(this Control c, object layout) { /* TODO: layout manager */ }
        public static void setContentPane(this Form f, Control c) { /* TODO */ }
        public static Control getContentPane(this Form f) => f;
        public static void pack(this Form f) { /* TODO */ }
        public static void setDefaultCloseOperation(this Form f, int op) { /* TODO */ }
        public static void setLocationRelativeTo(this Form f, Control c) { f.StartPosition = FormStartPosition.CenterParent; }
        public static void setResizable(this Form f, bool r) { if (r == null) f.FormBorderStyle = FormBorderStyle.FixedDialog; }
        public static void setModal(this Form f, bool m) { /* TODO */ }
        public static void setIconImage(this Form f, Image img) { /* TODO */ }
        public static void setTitle(this Form f, string title) { f.Text = title; }
        public static string getTitle(this Form f) => f.Text;
        
        // ToolStripMenuItem extensions
        public static void add(this ToolStripMenuItem menu, ToolStripItem item) { menu.DropDownItems.Add(item); }
        public static void add(this ToolStripMenuItem menu, object item) { if (item is ToolStripItem tsi) menu.DropDownItems.Add(tsi); }
        public static void Add(this ToolStripMenuItem menu, ToolStripItem item) { menu.DropDownItems.Add(item); }
        public static void Add(this ToolStripMenuItem menu, object item) { if (item is ToolStripItem tsi) menu.DropDownItems.Add(tsi); }
        public static void Hide(this ToolStripMenuItem menu) { menu.Visible = false; }
        public static void Show(this ToolStripMenuItem menu) { menu.Visible = true; }
        public static void setVisible(this ToolStripItem item, bool visible) { item.Visible = visible; }
        public static void setEnabled(this ToolStripItem item, bool enabled) { item.Enabled = enabled; }
        
        // TabControl extensions (Java JTabbedPane)
        public static void setEnabledAt(this TabControl tc, int index, bool enabled) { if (index >= 0 && index < tc.TabPages.Count) tc.TabPages[index].Enabled = enabled; }
        public static void addTab(this TabControl tc, string title, Control comp) { var page = new TabPage(title); page.Controls.Add(comp); tc.TabPages.Add(page); }
        public static void addTab(this TabControl tc, string title, object icon, Control comp) { var page = new TabPage(title); page.Controls.Add(comp); tc.TabPages.Add(page); }
        public static void addTab(this TabControl tc, string title, object icon, Control comp, string tip) { var page = new TabPage(title); page.Controls.Add(comp); tc.TabPages.Add(page); }
        public static int getTabCount(this TabControl tc) => tc.TabPages.Count;
        public static void setSelectedIndex(this TabControl tc, int index) { if (index >= 0 && index < tc.TabPages.Count) tc.SelectedIndex = index; }
        public static int getSelectedIndex(this TabControl tc) => tc.SelectedIndex;
        public static void setTitleAt(this TabControl tc, int index, string title) { if (index >= 0 && index < tc.TabPages.Count) tc.TabPages[index].Text = title; }
        public static Control getComponentAt(this TabControl tc, int index) => tc.TabPages[index];
        public static void removeAll(this TabControl tc) { tc.TabPages.Clear(); }
        
        // DataGridView extensions (Java JTable)
        public static object getColumnModel(this DataGridView dgv) => dgv.Columns;
        public static object getTableHeader(this DataGridView dgv) => dgv.ColumnHeadersDefaultCellStyle;
        public static object getSelectionModel(this DataGridView dgv) => dgv.SelectedRows;
        public static void setAutoCreateRowSorter(this DataGridView dgv, bool auto) { /* TODO */ }
        public static int getRowCount(this DataGridView dgv) => dgv.RowCount;
        public static int getColumnCount(this DataGridView dgv) => dgv.ColumnCount;
        public static object getValueAt(this DataGridView dgv, int row, int col) => dgv.Rows[row].Cells[col].Value;
        public static void setValueAt(this DataGridView dgv, object val, int row, int col) { dgv.Rows[row].Cells[col].Value = val; }
        public static int getSelectedRow(this DataGridView dgv) => dgv.CurrentRow?.Index ?? -1;
        public static int[] getSelectedRows(this DataGridView dgv) => dgv.SelectedRows.Cast<DataGridViewRow>().Select(r => r.Index).ToArray();
        public static int convertRowIndexToModel(this DataGridView dgv, int viewIndex) => viewIndex;
        public static int convertRowIndexToView(this DataGridView dgv, int modelIndex) => modelIndex;
        public static void setRowSelectionInterval(this DataGridView dgv, int from, int to) { dgv.ClearSelection(); for (int i = from; i <= to && i < dgv.RowCount; i++) dgv.Rows[i].Selected = true; }
        public static void addListSelectionListener(this DataGridView dgv, object listener) { /* TODO */ }
        public static object getRowSorter(this DataGridView dgv) => null;
        public static void setRowSorter(this DataGridView dgv, object sorter) { /* TODO */ }
        public static void setDefaultRenderer(this DataGridView dgv, Type type, object renderer) { /* TODO */ }
        public static void setDefaultEditor(this DataGridView dgv, Type type, object editor) { /* TODO */ }
        
        // DataGridViewColumn extensions
        public static void setCellRenderer(this DataGridViewColumn col, object renderer) { /* TODO */ }
        public static void setCellEditor(this DataGridViewColumn col, object editor) { /* TODO */ }
        public static void setPreferredWidth(this DataGridViewColumn col, int w) { col.Width = w; }
        public static void setMaxWidth(this DataGridViewColumn col, int w) { /* TODO */ }
        public static void setMinWidth(this DataGridViewColumn col, int w) { col.MinimumWidth = w; }
        
        // TextBox extensions
        public static object getDocument(this TextBox tb) => tb;
        public static void setEditable(this Control c, bool editable) { if (c is TextBox tb) tb.ReadOnly = !editable; }
        public static void setText(this Control c, string text) { c.Text = text; }
        
        // ComboBox extensions (Java JComboBox)
        public static void addItem(this ComboBox cb, object item) { cb.Items.Add(item); }
        public static void removeAllItems(this ComboBox cb) { cb.Items.Clear(); }
        public static object getSelectedItem(this ComboBox cb) => cb.SelectedItem;
        public static void setSelectedItem(this ComboBox cb, object item) { cb.SelectedItem = item; }
        public static int getItemCount(this ComboBox cb) => cb.Items.Count;
        public static object getItemAt(this ComboBox cb, int index) => cb.Items[index];
        public static void setSelectedIndex(this ComboBox cb, int index) { cb.SelectedIndex = index; }
        public static int getSelectedIndex(this ComboBox cb) => cb.SelectedIndex;
        public static void addActionListener(this ComboBox cb, object listener) { /* TODO */ }
        public static void removeActionListener(this ComboBox cb, object listener) { /* TODO */ }
        
        // Element (XML) extensions
        public static bool hasAttribute(this Element e, string name) => e.getAttribute(name) != "";
        public static string getNodeName(this Element e) => e.getTagName();
        
        // XmlNodeList extensions
        public static int getLength(this XmlNodeList nl) => nl?.Count ?? 0;
        public static XmlNode item(this XmlNodeList nl, int i) => nl?[i];
        
        // Type extensions (Java Class)
        public static Stream GetManifestResourceStream(this Type t, string name)
        {
            var asm = t.Assembly;
            // Try various resource name patterns
            var fullName = asm.GetName().Name + ".Resources." + name.Replace('/', '.').Replace('\\', '.');
            return asm.GetManifestResourceStream(fullName);
        }
        public static string getSimpleName(this Type t) => t?.Name ?? "";
        public static string getName(this Type t) => t?.FullName ?? "";
        public static bool isAssignableFrom(this Type t, Type other) => t?.IsAssignableFrom(other) ?? false;
        public static bool isAssignableFrom(this Type t, Class other) => true;
        public static object[] getEnumConstants(this Type t) => t.IsEnum ? Enum.GetValues(t) as object[] : null;
        
        // Enum ordinal (for sealed classes acting as enums)
        public static int ordinal(this object obj) => 0; // Default ordinal for enum-like sealed classes
        
        // Boolean helpers
        public static bool booleanValue(this bool b) => b;
        
        // Object helpers
        public static string toString(this object o) => o?.ToString() ?? "null";
        public static int hashCode(this object o) => o?.GetHashCode() ?? 0;
        public static bool equals(this object o, object other) => object.Equals(o, other);
        public static Class getClass(this object o) => new Class(o?.GetType());
        
        // Array helpers
        public static void arraycopy(object src, int srcPos, object dest, int destPos, int length)
        {
            Array.Copy((Array)src, srcPos, (Array)dest, destPos, length);
        }
        
        // Random extensions
        public static int nextInt(this Random rng) => rng.Next();
        public static int nextInt(this Random rng, int bound) => rng.Next(bound);
        public static long nextLong(this Random rng) => ((long)rng.Next() << 32) | (long)rng.Next();
        public static double nextDouble(this Random rng) => rng.NextDouble();
        public static float nextFloat(this Random rng) => (float)rng.NextDouble();
        public static bool nextBoolean(this Random rng) => rng.Next(2) == 1;
        public static void nextBytes(this Random rng, byte[] bytes) => rng.NextBytes(bytes);
        
        // DataGridView.DataSource (TableModel) extensions
        public static bool isCellEditable(this object model, int row, int col) => true;
        public static object getValueAt(this object model, int row, int col) => null;
        public static int getRowCount(this object model) => 0;
        public static int getColumnCount(this object model) => 0;
    }

    /// <summary>
    /// Static helper classes for Java API compatibility
    /// </summary>
    public static class Arrays
    {
        public static List<T> asList<T>(params T[] items) => new List<T>(items);
        public static void sort<T>(T[] arr) => Array.Sort(arr);
        public static void sort<T>(T[] arr, IComparer<T> comp) => Array.Sort(arr, comp);
        public static void sort<T>(T[] arr, int from, int to) => Array.Sort(arr, from, to - from);
        public static T[] copyOf<T>(T[] arr, int newLen) { var result = new T[newLen]; Array.Copy(arr, result, Math.Min(arr.Length, newLen)); return result; }
        public static T[] copyOfRange<T>(T[] arr, int from, int to) { var len = to - from; var result = new T[len]; Array.Copy(arr, from, result, 0, len); return result; }
        public static void fill<T>(T[] arr, T val) => Array.Fill(arr, val);
        public static void fill<T>(T[] arr, int from, int to, T val) => Array.Fill(arr, val, from, to - from);
        public static bool equals<T>(T[] a, T[] b) => a.SequenceEqual(b);
        public static string toString<T>(T[] a) => "[" + string.Join(", ", a) + "]";
    }

    public static class Collections
    {
        public static List<T> emptyList<T>() => new List<T>();
        public static Dictionary<K, V> emptyMap<K, V>() => new Dictionary<K, V>();
        public static HashSet<T> emptySet<T>() => new HashSet<T>();
        public static List<T> singletonList<T>(T item) => new List<T> { item };
        public static Dictionary<K, V> singletonMap<K, V>(K key, V value) => new Dictionary<K, V> { { key, value } };
        public static List<T> unmodifiableList<T>(List<T> list) => list; // TODO: make read-only
        public static Dictionary<K, V> unmodifiableMap<K, V>(Dictionary<K, V> map) => map;
        public static void sort<T>(List<T> list) => list.Sort();
        public static void sort<T>(List<T> list, IComparer<T> comp) => list.Sort(comp);
        public static void reverse<T>(List<T> list) => list.Reverse();
        public static T min<T>(IEnumerable<T> col) where T : IComparable<T> => col.Min();
        public static T max<T>(IEnumerable<T> col) where T : IComparable<T> => col.Max();
        public static void swap<T>(IList<T> list, int i, int j) { var tmp = list[i]; list[i] = list[j]; list[j] = tmp; }
        public static int frequency<T>(IEnumerable<T> col, T obj) => col.Count(x => object.Equals(x, obj));
        public static void addAll<T>(ICollection<T> col, params T[] items) { foreach (var i in items) col.Add(i); }
    }

    public static class Collectors
    {
        public static object toList() => null; // Dummy for .collect(Collectors.toList())
        public static object toSet() => null;
        public static Func<IEnumerable<string>, string> joining(string sep) => (source) => string.Join(sep, source);
        public static Func<IEnumerable<string>, string> joining() => (source) => string.Join("", source);
    }
    
    // Java Optional<T>
    public class Optional<T>
    {
        private T _value;
        private bool _hasValue;
        private Optional() { }
        private Optional(T value) { _value = value; _hasValue = true; }
        public static Optional<T> empty() => new Optional<T>();
        public static Optional<T> of(T value) => new Optional<T>(value);
        public static Optional<T> ofNullable(T value) => value != null ? of(value) : empty();
        public bool isPresent() => _hasValue;
        public T get() => _value;
        public T orElse(T other) => _hasValue ? _value : other;
        public T orElseGet(Func<T> supplier) => _hasValue ? _value : supplier();
        public void ifPresent(Action<T> action) { if (_hasValue) action(_value); }
        public Optional<R> map<R>(Func<T, R> mapper) => _hasValue ? Optional<R>.ofNullable(mapper(_value)) : Optional<R>.empty();
        public Optional<R> flatMap<R>(Func<T, Optional<R>> mapper) => _hasValue ? mapper(_value) : Optional<R>.empty();
        public Optional<T> filter(Func<T, bool> predicate) => _hasValue && predicate(_value) ? this : empty();
    }

    // Additional Java compatibility stubs
    public class Document : Element
    {
        public Element getDocumentElement() => this;
        public Element createElement(string name) => new Element();
        public Element createTextNode(string data) => new Element();
    }

    public class DocumentBuilder
    {
        public Document parse(Stream s) => new Document();
        public Document parse(FileInfo f) => new Document();
        public Document parse(string uri) => new Document();
        public Document newDocument() => new Document();
    }

    public class DocumentBuilderFactory
    {
        public static DocumentBuilderFactory newInstance() => new DocumentBuilderFactory();
        public DocumentBuilder newDocumentBuilder() => new DocumentBuilder();
    }

    public class TransformerFactory
    {
        public static TransformerFactory newInstance() => new TransformerFactory();
        public Transformer newTransformer() => new Transformer();
    }

    public class Transformer
    {
        public void setOutputProperty(string name, string value) { }
        public void transform(object source, object result) { }
    }

    public class DOMSource
    {
        public DOMSource(Document doc) { }
    }

    public class StreamResult
    {
        public StreamResult(Stream s) { }
        public StreamResult(StreamWriter w) { }
    }

    public class OutputKeys
    {
        public static string INDENT = "indent";
        public static string ENCODING = "encoding";
    }

    // Java Desktop class
    public class Desktop
    {
        public static Desktop getDesktop() => new Desktop();
        public void browse(Uri uri) { System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(uri.ToString()) { UseShellExecute = true }); }
        public void open(FileInfo file) { System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(file.FullName) { UseShellExecute = true }); }
    }

    // Java CodingErrorAction
    public class CodingErrorAction
    {
        public static CodingErrorAction REPLACE = new CodingErrorAction();
        public static CodingErrorAction IGNORE = new CodingErrorAction();
        public static CodingErrorAction REPORT = new CodingErrorAction();
    }

    // Java StandardCharsets
    public static class StandardCharsets
    {
        public static Encoding UTF_8 = Encoding.UTF8;
        public static Encoding US_ASCII = Encoding.ASCII;
        public static Encoding ISO_8859_1 = Encoding.GetEncoding("ISO-8859-1");
    }

    // Java InputStreamReader/OutputStreamWriter
    public class InputStreamReader : StreamReader
    {
        public InputStreamReader(Stream s) : base(s) { }
        public InputStreamReader(Stream s, Encoding enc) : base(s, enc) { }
        public InputStreamReader(Stream s, string charset) : base(s, Encoding.GetEncoding(charset)) { }
    }

    public class OutputStreamWriter : StreamWriter
    {
        public OutputStreamWriter(Stream s) : base(s) { }
        public OutputStreamWriter(Stream s, Encoding enc) : base(s, enc) { }
        public OutputStreamWriter(Stream s, string charset) : base(s, Encoding.GetEncoding(charset)) { }
    }

    public class BufferedReader : StreamReader
    {
        public BufferedReader(StreamReader r) : base(r.BaseStream) { }
        public BufferedReader(InputStreamReader r) : base(r.BaseStream) { }
        public string readLine() => base.ReadLine();
        public void close() => base.Close();
        public bool ready() => base.BaseStream.Position < base.BaseStream.Length;
    }

    public class PrintWriter : StreamWriter
    {
        public PrintWriter(Stream s) : base(s) { }
        public PrintWriter(StreamWriter w) : base(w.BaseStream) { }
        public PrintWriter(string path) : base(path) { }
        public void print(object o) => Write(o?.ToString() ?? "");
        public void println(object o) => WriteLine(o?.ToString() ?? "");
        public void println() => WriteLine();
        public void printf(string fmt, params object[] args) => Write(string.Format(fmt, args));
        public void close() => Close();
    }

    public class ByteArrayInputStream : MemoryStream
    {
        public ByteArrayInputStream(byte[] buf) : base(buf) { }
        public ByteArrayInputStream(byte[] buf, int off, int len) : base(buf, off, len) { }
    }

    public class ByteArrayOutputStream : MemoryStream
    {
        public byte[] toByteArray() => ToArray();
        public int size() => (int)Length;
    }

    public class DataInputStream : BinaryReader
    {
        public DataInputStream(Stream s) : base(s) { }
        public int readInt() => ReadInt32();
        public long readLong() => ReadInt64();
        public short readShort() => ReadInt16();
        public byte readByte() => ReadByte();
        public float readFloat() => ReadSingle();
        public double readDouble() => ReadDouble();
        public bool readBoolean() => ReadBoolean();
        public int readUnsignedByte() => ReadByte();
        public int readUnsignedShort() => ReadUInt16();
        public string readUTF() => ReadString();
        public int read(byte[] buf) => Read(buf, 0, buf.Length);
        public int read(byte[] buf, int off, int len) => Read(buf, off, len);
        public void readFully(byte[] buf) => Read(buf, 0, buf.Length);
        public void readFully(byte[] buf, int off, int len) => Read(buf, off, len);
        public long skipBytes(int n) => BaseStream.Seek(n, SeekOrigin.Current);
        public void close() => Close();
    }

    public class DataOutputStream : BinaryWriter
    {
        public DataOutputStream(Stream s) : base(s) { }
        public void writeInt(int v) => Write(v);
        public void writeLong(long v) => Write(v);
        public void writeShort(short v) => Write(v);
        public void writeByte(int v) => Write((byte)v);
        public void writeFloat(float v) => Write(v);
        public void writeDouble(double v) => Write(v);
        public void writeBoolean(bool v) => Write(v);
        public void writeUTF(string s) => Write(s);
        public void write(byte[] buf) => Write(buf);
        public void write(byte[] buf, int off, int len) => Write(buf, off, len);
        public void write(int b) => Write((byte)b);
        public void flush() => Flush();
        public void close() => Close();
        public int size() => (int)BaseStream.Length;
    }

    // Java MessageDigest
    public class MessageDigest
    {
        private System.Security.Cryptography.HashAlgorithm _alg;
        public static MessageDigest getInstance(string algorithm)
        {
            var md = new MessageDigest();
            md._alg = algorithm.ToUpper() switch
            {
                "SHA-256" => System.Security.Cryptography.SHA256.Create(),
                "SHA-1" => System.Security.Cryptography.SHA1.Create(),
                "MD5" => System.Security.Cryptography.MD5.Create(),
                _ => System.Security.Cryptography.SHA256.Create()
            };
            return md;
        }
        public byte[] digest(byte[] input) => _alg.ComputeHash(input);
        public byte[] digest() => _alg.Hash;
        public void update(byte[] input) => _alg.TransformBlock(input, 0, input.Length, input, 0);
        public void update(byte[] input, int off, int len) => _alg.TransformBlock(input, off, len, input, off);
        public void reset() => _alg.Initialize();
    }

    // Java FileNameExtensionFilter
    public class FileNameExtensionFilter : FileFilter
    {
        private string _desc;
        private string[] _exts;
        public FileNameExtensionFilter(string desc, params string[] exts) { _desc = desc; _exts = exts; }
        public override bool accept(string f) => _exts.Any(e => f.EndsWith("." + e, StringComparison.OrdinalIgnoreCase));
        public override string getDescription() => _desc;
    }
}

// File: Additional Java compatibility types (appended)
namespace NMSSaveEditor
{
    // Missing exception types
    public class NoSuchFieldError : Exception { public NoSuchFieldError() { } public NoSuchFieldError(string msg) : base(msg) { } }
    public class EOFException : IOException { public EOFException() { } public EOFException(string msg) : base(msg) { } }
    public class IndexOutOfBoundsException : Exception { public IndexOutOfBoundsException() { } public IndexOutOfBoundsException(string msg) : base(msg) { } }
    public class InterruptedException : Exception { public InterruptedException() { } public InterruptedException(string msg) : base(msg) { } }
    public class NoSuchAlgorithmException : Exception { public NoSuchAlgorithmException() { } public NoSuchAlgorithmException(string msg) : base(msg) { } }
    public class UnsupportedEncodingException : Exception { public UnsupportedEncodingException() { } public UnsupportedEncodingException(string msg) : base(msg) { } }

    // Java BigDecimal
    public class BigDecimal
    {
        private decimal _value;
        public BigDecimal(string s) { _value = decimal.Parse(s); }
        public BigDecimal(double d) { _value = (decimal)d; }
        public BigDecimal(long l) { _value = l; }
        public static BigDecimal ZERO = new BigDecimal(0);
        public static BigDecimal ONE = new BigDecimal(1);
        public static BigDecimal TEN = new BigDecimal(10);
        public BigDecimal add(BigDecimal other) => new BigDecimal((double)(_value + other._value));
        public BigDecimal subtract(BigDecimal other) => new BigDecimal((double)(_value - other._value));
        public BigDecimal multiply(BigDecimal other) => new BigDecimal((double)(_value * other._value));
        public BigDecimal divide(BigDecimal other) => new BigDecimal((double)(_value / other._value));
        public BigDecimal divide(BigDecimal other, int scale, int roundingMode) => new BigDecimal((double)Math.Round(_value / other._value, scale));
        public int compareTo(BigDecimal other) => _value.CompareTo(other._value);
        public BigDecimal setScale(int scale, int roundingMode) => new BigDecimal((double)Math.Round(_value, scale));
        public string toPlainString() => _value.ToString();
        public double doubleValue() => (double)_value;
        public long longValue() => (long)_value;
        public int intValue() => (int)_value;
        public override string ToString() => _value.ToString();
        public static int ROUND_HALF_UP = 4;
        public static int ROUND_DOWN = 1;
        public static implicit operator Number(BigDecimal bd) => new NumberWrapper(bd.intValue(), bd.longValue(), (float)bd.doubleValue(), bd.doubleValue());
    }

    // Java Zip support
    public class ZipEntry
    {
        public string Name { get; set; }
        public long Size { get; set; }
        public long CompressedSize { get; set; }
        public ZipEntry(string name) { Name = name; }
        public string getName() => Name;
        public long getSize() => Size;
        public long getCompressedSize() => CompressedSize;
        public bool isDirectory() => Name.EndsWith("/");
    }

    public class ZipFile : IDisposable
    {
        private string _path;
        public ZipFile(string path) { _path = path; }
        public ZipFile(FileInfo file) { _path = file.FullName; }
        public IEnumerable<ZipEntry> entries() => new List<ZipEntry>();
        public Stream getInputStream(ZipEntry entry) => Stream.Null;
        public void close() { }
        public void Dispose() { }
    }

    public class ZipOutputStream : Stream
    {
        private Stream _inner;
        public ZipOutputStream(Stream s) { _inner = s; }
        public void putNextEntry(ZipEntry entry) { }
        public void closeEntry() { }
        public override bool CanRead => false;
        public override bool CanSeek => false;
        public override bool CanWrite => true;
        public override long Length => _inner.Length;
        public override long Position { get => _inner.Position; set => _inner.Position = value; }
        public override void Flush() => _inner.Flush();
        public override int Read(byte[] buffer, int offset, int count) => 0;
        public override long Seek(long offset, SeekOrigin origin) => 0;
        public override void SetLength(long value) { }
        public override void Write(byte[] buffer, int offset, int count) => _inner.Write(buffer, offset, count);
    }

    // Java URL
    public class URL
    {
        private string _url;
        public URL(string url) { _url = url; }
        public URL(string protocol, string host, int port, string file) { _url = $"{protocol}://{host}:{port}{file}"; }
        public string toString() => _url;
        public string getProtocol() => new Uri(_url).Scheme;
        public string getHost() => new Uri(_url).Host;
        public int getPort() => new Uri(_url).Port;
        public string getFile() => new Uri(_url).AbsolutePath;
        public URLConnection openConnection() => new URLConnection(_url);
        public Stream openStream() => Stream.Null;
        public Uri toURI() => new Uri(_url);
    }

    public class URLConnection
    {
        private string _url;
        public URLConnection(string url) { _url = url; }
        public void setRequestProperty(string key, string value) { }
        public Stream getInputStream() => Stream.Null;
        public void connect() { }
    }

    // Java GridBag layout stubs
    public class GridBagLayout { }
    public class GridBagConstraints
    {
        public int gridx, gridy, gridwidth, gridheight;
        public double weightx, weighty;
        public int anchor, fill;
        public object insets;
        public static int NONE = 0, BOTH = 1, HORIZONTAL = 2, VERTICAL = 3;
        public static int CENTER = 10, NORTH = 11, SOUTH = 15, EAST = 13, WEST = 17;
        public static int NORTHWEST = 18, NORTHEAST = 12, SOUTHWEST = 16, SOUTHEAST = 14;
        public static int RELATIVE = -1, REMAINDER = 0;
    }

    // Java Swing stubs
    public class JPopupMenu : ContextMenuStrip
    {
        public void add(object item) { if (item is ToolStripItem tsi) Items.Add(tsi); }
    }

    public class JSplitPane : SplitContainer
    {
        public static int HORIZONTAL_SPLIT = 1;
        public static int VERTICAL_SPLIT = 0;
        public JSplitPane() { }
        public JSplitPane(int orientation) { Orientation = orientation == VERTICAL_SPLIT ? Orientation.Vertical : Orientation.Horizontal; }
        public JSplitPane(int orientation, Control left, Control right) : this(orientation) { Panel1.Controls.Add(left); Panel2.Controls.Add(right); }
        public void setDividerLocation(int loc) { SplitterDistance = loc; }
        public void setDividerSize(int size) { SplitterWidth = size; }
        public void setResizeWeight(double weight) { /* TODO */ }
    }

    public class JTextPane : RichTextBox
    {
        public void setEditable(bool editable) { ReadOnly = !editable; }
        public object getDocument() => this;
        public object getStyledDocument() => this;
    }

    public class SimpleAttributeSet
    {
        public static void addAttribute(object key, object value) { }
    }

    public class StyledDocument
    {
        public void insertString(int offset, string str, object attrs) { }
        public int getLength() => 0;
    }

    public class StringSelection : IDataObject
    {
        private string _text;
        public StringSelection(string text) { _text = text; }
        public object GetData(string format) => _text;
        public object GetData(Type format) => _text;
        public object GetData(string format, bool autoConvert) => _text;
        public bool GetDataPresent(string format) => true;
        public bool GetDataPresent(Type format) => true;
        public bool GetDataPresent(string format, bool autoConvert) => true;
        public string[] GetFormats() => new[] { DataFormats.Text };
        public string[] GetFormats(bool autoConvert) => new[] { DataFormats.Text };
        public void SetData(object data) { }
        public void SetData(string format, object data) { }
        public void SetData(Type format, object data) { }
        public void SetData(string format, bool autoConvert, object data) { }
    }

    public class DefaultCellEditor
    {
        public DefaultCellEditor(ComboBox cb) { }
        public DefaultCellEditor(TextBox tb) { }
        public DefaultCellEditor(CheckBox cb) { }
    }

    public class EmptyBorder
    {
        public EmptyBorder(int top, int left, int bottom, int right) { }
    }

    // Node class is now in JavaCompat.cs with XmlNode wrapping

    // Java Logging Level
    public class Level
    {
        public static Level INFO = new Level("INFO");
        public static Level WARNING = new Level("WARNING");
        public static Level SEVERE = new Level("SEVERE");
        public static Level FINE = new Level("FINE");
        public static Level FINER = new Level("FINER");
        public static Level FINEST = new Level("FINEST");
        public static Level ALL = new Level("ALL");
        public static Level OFF = new Level("OFF");
        private string _name;
        public Level(string name) { _name = name; }
        public override string ToString() => _name;
    }

    // Java Reflection stubs
    public class Field
    {
        public string getName() => "";
        public object get(object obj) => null;
        public void set(object obj, object value) { }
        public Type getType() => typeof(object);
        public void setAccessible(bool flag) { }
    }

    public class Method
    {
        public string getName() => "";
        public object invoke(object obj, params object[] args) => null;
        public void setAccessible(bool flag) { }
    }

    // Java WatchService stubs
    public class WatchKey
    {
        public List<WatchEvent> pollEvents() => new List<WatchEvent>();
        public bool reset() => true;
    }

    public class WatchEvent
    {
        public object context() => null;
        public object kind() => null;
    }

    public class StandardWatchEventKinds
    {
        public static object ENTRY_CREATE = "CREATE";
        public static object ENTRY_DELETE = "DELETE";
        public static object ENTRY_MODIFY = "MODIFY";
    }

    // Java TreeModelEvent
    public class TreeModelEvent : EventArgs
    {
        public TreeModelEvent(object source, object path) { }
        public TreePath getTreePath() => new TreePath();
    }

    // Java Crypto stubs
    public class SecretKeySpec
    {
        private byte[] _key;
        private string _alg;
        public SecretKeySpec(byte[] key, string algorithm) { _key = key; _alg = algorithm; }
    }

    public class IvParameterSpec
    {
        private byte[] _iv;
        public IvParameterSpec(byte[] iv) { _iv = iv; }
    }

    public class Cipher
    {
        public static int ENCRYPT_MODE = 1;
        public static int DECRYPT_MODE = 2;
        public static Cipher getInstance(string transformation) => new Cipher();
        public void init(int mode, object key) { }
        public void init(int mode, object key, object spec) { }
        public byte[] doFinal(byte[] input) => input;
        public byte[] doFinal(byte[] input, int off, int len) => input;
    }

    // Java Entry<K,V> for map iteration
    public class Entry<K, V>
    {
        public K Key { get; set; }
        public V Value { get; set; }
        public K getKey() => Key;
        public V getValue() => Value;
    }

    // Static helper extensions for common Java patterns
    public static class JavaStaticHelpers
    {
        // Boolean
        public static bool valueOf(bool b) => b;
        public static bool parseBoolean(string s) => bool.TryParse(s, out var v) && v;

        // Long
        public static string toHexString(long l) => l.ToString("X");

        // Double
        public static bool isNaN(double d) => double.IsNaN(d);
        public static bool isInfinite(double d) => double.IsInfinity(d);

        // XmlDocument factory
        public static DocumentBuilderFactory newInstance() => new DocumentBuilderFactory();

        // JTree
        public static void setCellRenderer(this TreeView tree, object renderer) { /* TODO */ }
        public static void setModel(this TreeView tree, object model) { /* TODO */ }
        public static object getModel(this TreeView tree) => null;
        public static void expandRow(this TreeView tree, int row) { /* TODO */ }
        public static void collapseRow(this TreeView tree, int row) { /* TODO */ }
        public static void setRootVisible(this TreeView tree, bool visible) { /* TODO */ }
        public static void setShowsRootHandles(this TreeView tree, bool shows) { /* TODO */ }
        public static TreePath getSelectionPath(this TreeView tree) => new TreePath();
        public static void setSelectionPath(this TreeView tree, TreePath path) { /* TODO */ }
        public static void addTreeSelectionListener(this TreeView tree, object listener) { /* TODO */ }
    }
}

// Additional extension methods for remaining missing method patterns
namespace NMSSaveEditor
{
    public static class MoreJavaExtensions
    {
        // Fix capitalized method names from mechanical conversion
        public static void Put<K, V>(this IDictionary<K, V> dict, K key, V value) => dict[key] = value;
        public static int Count<T>(this ICollection<T> list) => list.Count;
        public static int Length<T>(this T[] arr) where T : class => arr.Length;
        public static void Add<T>(this IList<T> list, T item) => list.Add(item);
        public static void Add(this MenuStrip ms, ToolStripItem item) { ms.Items.Add(item); }
        public static void Add(this JPopupMenu pm, ToolStripItem item) { pm.Items.Add(item); }
        
        // ReadByte/Close fixes for custom streams
        public static int ReadByte(this Stream s) => s.ReadByte();
        public static void Close(this IDisposable d) => d.Dispose();
        
        // File/FileInfo
        public static FileInfo[] listFiles(this FileInfo fi) 
        { 
            if (fi.Exists && (fi.Attributes & FileAttributes.Directory) != 0)
                return new DirectoryInfo(fi.FullName).GetFiles().Select(f => new FileInfo(f.FullName)).ToArray();
            return Array.Empty<FileInfo>();
        }
        public static FileInfo[] listFiles(this DirectoryInfo di) => di.GetFiles().Select(f => new FileInfo(f.FullName)).ToArray();
        public static long lastModified(this DirectoryInfo di) => di.LastWriteTimeUtc.Ticks;
        
        // Matcher fix
        public static bool System.Text.RegularExpressions.Regex.IsMatch(this Matcher m) => m.matches();
        
        // Level
        public static int intValue(this Level l) => 0;
        
        // Function<T, R> delegate
        public static R apply<T, R>(this Func<T, R> func, T arg) => func(arg);
        
        // Rectangle
        public static int height(this Rectangle r) => r.Height;
        public static int width(this Rectangle r) => r.Width;
        
        // TextBox
        public static void setCaretPosition(this TextBox tb, int pos) { tb.SelectionStart = pos; tb.SelectionLength = 0; }
        public static int getCaretPosition(this TextBox tb) => tb.SelectionStart;
        
        // ComboBox
        public static void setRenderer(this ComboBox cb, object renderer) { /* TODO */ }
        public static void setMaximumRowCount(this ComboBox cb, int count) { cb.MaxDropDownItems = count; }
        public static void setPrototypeDisplayValue(this ComboBox cb, object value) { /* TODO */ }
        
        // ZipFile extra
        public static ZipEntry getEntry(this ZipFile zf, string name) => new ZipEntry(name);
        
        // DataGridView column model operations
        public static object getColumn(this object columnModel, int index) => null;
        public static void setSortable(this object column, bool sortable) { /* TODO */ }
        
        // Document/Element extras
        public static object getDefaultRootElement(this object doc) => null;
        public static int getElementIndex(this Element elem, int offset) => 0;
        public static Element getElement(this Element elem, int index) => new Element();
        public static int getStartOffset(this Element elem) => 0;
        public static int getEndOffset(this Element elem) => 0;
        
        // Control tree lock
        public static object getTreeLock(this Control c) => c;
        
        // Boolean.TRUE/FALSE fix - these are in the code as bool.TRUE/bool.FALSE
        // We need to handle this differently - via static helpers
        
        // BigDecimal missing methods
        public static BigDecimal scaleByPowerOfTen(this BigDecimal bd, int n) => bd.multiply(new BigDecimal(Math.Pow(10, n)));
        public static BigDecimal negate(this BigDecimal bd) => new BigDecimal(0).subtract(bd);
        public static int intValueExact(this BigDecimal bd) => bd.intValue();
        public static long longValueExact(this BigDecimal bd) => bd.longValue();
        public static BigDecimal Add(this BigDecimal a, BigDecimal b) => a.add(b);
        
        // Image/Icon
        public static Image getScaledInstance(this Image img, int w, int h, int hints) 
        { 
            return new Bitmap(img, new Size(w, h)); 
        }
        public static int getIconWidth(this Image img) => img?.Width ?? 0;
        public static int getIconHeight(this Image img) => img?.Height ?? 0;
        
        // JTree
        public static void setEditable(this TreeView tv, bool editable) { tv.LabelEdit = editable; }
        
        // Number conversions
        // Conversion helper moved to BigDecimal class itself
        public static Number AsNumber(this BigDecimal bd) => new NumberWrapper(bd.intValue(), bd.longValue(), (float)bd.doubleValue(), bd.doubleValue());
        
        // XmlDocument factory pattern
        public static DocumentBuilderFactory newInstance(this Type t) => new DocumentBuilderFactory();
    }

    public class NumberWrapper : Number
    {
        private int _int;
        private long _long;
        private float _float;
        private double _double;
        public NumberWrapper(int i, long l, float f, double d) { _int = i; _long = l; _float = f; _double = d; }
        public override int intValue() => _int;
        public override long longValue() => _long;
        public override float floatValue() => _float;
        public override double doubleValue() => _double;
    }
}
