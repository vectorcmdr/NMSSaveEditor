/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.ScaledImageIcon;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.filechooser.FileSystemView;
/*     */ import javax.swing.filechooser.FileView;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicFileChooserUI;
/*     */ import javax.swing.plaf.metal.MetalFileChooserUI;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatFileChooserUI
/*     */   extends MetalFileChooserUI
/*     */ {
/* 163 */   private final FlatFileView fileView = new FlatFileView();
/*     */   private FlatShortcutsPanel shortcutsPanel;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 167 */     return new FlatFileChooserUI((JFileChooser)c);
/*     */   }
/*     */   
/*     */   public FlatFileChooserUI(JFileChooser filechooser) {
/* 171 */     super(filechooser);
/*     */   }
/*     */ 
/*     */   
/*     */   public void installComponents(JFileChooser fc) {
/* 176 */     super.installComponents(fc);
/*     */     
/* 178 */     patchUI(fc);
/*     */     
/* 180 */     if (!UIManager.getBoolean("FileChooser.noPlacesBar")) {
/* 181 */       FlatShortcutsPanel panel = createShortcutsPanel(fc);
/* 182 */       if (panel.getComponentCount() > 0) {
/* 183 */         this.shortcutsPanel = panel;
/* 184 */         fc.add(this.shortcutsPanel, "Before");
/* 185 */         fc.addPropertyChangeListener(this.shortcutsPanel);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallComponents(JFileChooser fc) {
/* 192 */     super.uninstallComponents(fc);
/*     */     
/* 194 */     if (this.shortcutsPanel != null) {
/* 195 */       fc.removePropertyChangeListener(this.shortcutsPanel);
/* 196 */       this.shortcutsPanel = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void patchUI(JFileChooser fc) {
/* 202 */     Component topPanel = fc.getComponent(0);
/* 203 */     if (topPanel instanceof JPanel && ((JPanel)topPanel)
/* 204 */       .getLayout() instanceof BorderLayout) {
/*     */       
/* 206 */       Component topButtonPanel = ((JPanel)topPanel).getComponent(0);
/* 207 */       if (topButtonPanel instanceof JPanel && ((JPanel)topButtonPanel)
/* 208 */         .getLayout() instanceof javax.swing.BoxLayout) {
/*     */         
/* 210 */         Insets margin = UIManager.getInsets("Button.margin");
/* 211 */         Component[] comps = ((JPanel)topButtonPanel).getComponents();
/* 212 */         for (int i = comps.length - 1; i >= 0; i--) {
/* 213 */           Component c = comps[i];
/* 214 */           if (c instanceof javax.swing.JButton || c instanceof JToggleButton) {
/* 215 */             AbstractButton b = (AbstractButton)c;
/* 216 */             b.putClientProperty("JButton.buttonType", "toolBarButton");
/*     */             
/* 218 */             b.setMargin(margin);
/* 219 */             b.setFocusable(false);
/* 220 */           } else if (c instanceof javax.swing.Box.Filler) {
/* 221 */             ((JPanel)topButtonPanel).remove(i);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 228 */       Component directoryComboBox = ((JPanel)topPanel).getComponent(2);
/* 229 */       if (directoryComboBox instanceof JComboBox) {
/* 230 */         int maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount");
/* 231 */         if (maximumRowCount > 0)
/* 232 */           ((JComboBox)directoryComboBox).setMaximumRowCount(maximumRowCount); 
/*     */       } 
/* 234 */     } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     LayoutManager layout = fc.getLayout();
/* 241 */     if (layout instanceof BorderLayout) {
/* 242 */       BorderLayout borderLayout = (BorderLayout)layout;
/* 243 */       borderLayout.setHgap(8);
/*     */       
/* 245 */       Component north = borderLayout.getLayoutComponent("North");
/* 246 */       Component lineEnd = borderLayout.getLayoutComponent("After");
/* 247 */       Component center = borderLayout.getLayoutComponent("Center");
/* 248 */       Component south = borderLayout.getLayoutComponent("South");
/* 249 */       if (north != null && lineEnd != null && center != null && south != null) {
/* 250 */         JPanel p = new JPanel(new BorderLayout(0, 11));
/* 251 */         p.add(north, "North");
/* 252 */         p.add(lineEnd, "After");
/* 253 */         p.add(center, "Center");
/* 254 */         p.add(south, "South");
/* 255 */         fc.add(p, "Center");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JPanel createDetailsView(JFileChooser fc) {
/* 262 */     JPanel p = super.createDetailsView(fc);
/*     */     
/* 264 */     if (!SystemInfo.isWindows) {
/* 265 */       return p;
/*     */     }
/*     */     
/* 268 */     JScrollPane scrollPane = null;
/* 269 */     for (Component c : p.getComponents()) {
/* 270 */       if (c instanceof JScrollPane) {
/* 271 */         scrollPane = (JScrollPane)c;
/*     */         break;
/*     */       } 
/*     */     } 
/* 275 */     if (scrollPane == null) {
/* 276 */       return p;
/*     */     }
/*     */     
/* 279 */     Component view = scrollPane.getViewport().getView();
/* 280 */     if (!(view instanceof JTable)) {
/* 281 */       return p;
/*     */     }
/* 283 */     JTable table = (JTable)view;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     final TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
/* 290 */     table.setDefaultRenderer(Object.class, new TableCellRenderer()
/*     */         {
/*     */ 
/*     */           
/*     */           public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */           {
/* 296 */             if (value instanceof String && ((String)value).startsWith("‎")) {
/* 297 */               String str = (String)value;
/* 298 */               char[] buf = new char[str.length()];
/* 299 */               int j = 0;
/* 300 */               for (int i = 0; i < buf.length; i++) {
/* 301 */                 char ch = str.charAt(i);
/* 302 */                 if (ch != '‎' && ch != '‏')
/* 303 */                   buf[j++] = ch; 
/*     */               } 
/* 305 */               value = new String(buf, 0, j);
/*     */             } 
/*     */             
/* 308 */             return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 313 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatShortcutsPanel createShortcutsPanel(JFileChooser fc) {
/* 318 */     return new FlatShortcutsPanel(fc);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 323 */     Dimension prefSize = super.getPreferredSize(c);
/* 324 */     Dimension minSize = getMinimumSize(c);
/* 325 */     int shortcutsPanelWidth = (this.shortcutsPanel != null) ? (this.shortcutsPanel.getPreferredSize()).width : 0;
/* 326 */     return new Dimension(
/* 327 */         Math.max(prefSize.width, minSize.width + shortcutsPanelWidth), 
/* 328 */         Math.max(prefSize.height, minSize.height));
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 333 */     return UIScale.scale(super.getMinimumSize(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public FileView getFileView(JFileChooser fc) {
/* 338 */     return doNotUseSystemIcons() ? super.getFileView(fc) : this.fileView;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearIconCache() {
/* 343 */     if (doNotUseSystemIcons()) {
/* 344 */       super.clearIconCache();
/*     */     } else {
/* 346 */       this.fileView.clearIconCache();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean doNotUseSystemIcons() {
/* 352 */     return (SystemInfo.isWindows && SystemInfo.isX86 && SystemInfo.isJava_17_orLater && !SystemInfo.isJava_18_orLater);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class FlatFileView
/*     */     extends BasicFileChooserUI.BasicFileView
/*     */   {
/*     */     private FlatFileView() {}
/*     */ 
/*     */     
/*     */     public Icon getIcon(File f) {
/*     */       ScaledImageIcon scaledImageIcon;
/* 365 */       Icon icon = getCachedIcon(f);
/* 366 */       if (icon != null) {
/* 367 */         return icon;
/*     */       }
/*     */       
/* 370 */       if (f != null) {
/* 371 */         icon = FlatFileChooserUI.this.getFileChooser().getFileSystemView().getSystemIcon(f);
/*     */         
/* 373 */         if (icon != null) {
/* 374 */           if (icon instanceof ImageIcon)
/* 375 */             scaledImageIcon = new ScaledImageIcon((ImageIcon)icon); 
/* 376 */           cacheIcon(f, (Icon)scaledImageIcon);
/* 377 */           return (Icon)scaledImageIcon;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 382 */       icon = super.getIcon(f);
/*     */       
/* 384 */       if (icon instanceof ImageIcon) {
/* 385 */         scaledImageIcon = new ScaledImageIcon((ImageIcon)icon);
/* 386 */         cacheIcon(f, (Icon)scaledImageIcon);
/*     */       } 
/*     */       
/* 389 */       return (Icon)scaledImageIcon;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class FlatShortcutsPanel
/*     */     extends JToolBar
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private final JFileChooser fc;
/*     */     
/*     */     private final Dimension buttonSize;
/*     */     
/*     */     private final Dimension iconSize;
/*     */     
/*     */     private final Function<File[], File[]> filesFunction;
/*     */     
/*     */     private final Function<File, String> displayNameFunction;
/*     */     
/*     */     private final Function<File, Icon> iconFunction;
/*     */     protected final File[] files;
/*     */     protected final JToggleButton[] buttons;
/*     */     protected final ButtonGroup buttonGroup;
/*     */     
/*     */     public FlatShortcutsPanel(JFileChooser fc) {
/* 414 */       super(1);
/* 415 */       this.fc = fc;
/* 416 */       setFloatable(false);
/*     */       
/* 418 */       this.buttonSize = UIScale.scale(getUIDimension("FileChooser.shortcuts.buttonSize", 84, 64));
/* 419 */       this.iconSize = getUIDimension("FileChooser.shortcuts.iconSize", 32, 32);
/*     */       
/* 421 */       this.filesFunction = (Function<File[], File[]>)UIManager.get("FileChooser.shortcuts.filesFunction");
/* 422 */       this.displayNameFunction = (Function<File, String>)UIManager.get("FileChooser.shortcuts.displayNameFunction");
/* 423 */       this.iconFunction = (Function<File, Icon>)UIManager.get("FileChooser.shortcuts.iconFunction");
/*     */       
/* 425 */       FileSystemView fsv = fc.getFileSystemView();
/* 426 */       File[] files = getChooserShortcutPanelFiles(fsv);
/* 427 */       if (this.filesFunction != null)
/* 428 */         files = this.filesFunction.apply(files); 
/* 429 */       this.files = files;
/*     */ 
/*     */       
/* 432 */       this.buttons = new JToggleButton[files.length];
/* 433 */       this.buttonGroup = new ButtonGroup();
/* 434 */       for (int i = 0; i < files.length; i++) {
/*     */         ScaledImageIcon scaledImageIcon; FlatFileChooserUI.ShortcutIcon shortcutIcon;
/* 436 */         if (fsv.isFileSystemRoot(files[i])) {
/* 437 */           files[i] = fsv.createFileObject(files[i].getAbsolutePath());
/*     */         }
/* 439 */         File file = files[i];
/* 440 */         String name = getDisplayName(fsv, file);
/* 441 */         Icon icon = getIcon(fsv, file);
/*     */ 
/*     */         
/* 444 */         int lastSepIndex = name.lastIndexOf(File.separatorChar);
/* 445 */         if (lastSepIndex >= 0 && lastSepIndex < name.length() - 1) {
/* 446 */           name = name.substring(lastSepIndex + 1);
/*     */         }
/*     */         
/* 449 */         if (icon instanceof ImageIcon) {
/* 450 */           scaledImageIcon = new ScaledImageIcon((ImageIcon)icon, this.iconSize.width, this.iconSize.height);
/* 451 */         } else if (scaledImageIcon != null) {
/* 452 */           shortcutIcon = new FlatFileChooserUI.ShortcutIcon((Icon)scaledImageIcon, this.iconSize.width, this.iconSize.height);
/*     */         } 
/*     */         
/* 455 */         JToggleButton button = createButton(name, shortcutIcon);
/* 456 */         button.addActionListener(e -> fc.setCurrentDirectory(file));
/*     */ 
/*     */ 
/*     */         
/* 460 */         add(button);
/* 461 */         this.buttonGroup.add(button);
/* 462 */         this.buttons[i] = button;
/*     */       } 
/*     */       
/* 465 */       directoryChanged(fc.getCurrentDirectory());
/*     */     }
/*     */     
/*     */     private Dimension getUIDimension(String key, int defaultWidth, int defaultHeight) {
/* 469 */       Dimension size = UIManager.getDimension(key);
/* 470 */       if (size == null)
/* 471 */         size = new Dimension(defaultWidth, defaultHeight); 
/* 472 */       return size;
/*     */     }
/*     */     
/*     */     protected JToggleButton createButton(String name, Icon icon) {
/* 476 */       JToggleButton button = new JToggleButton(name, icon);
/* 477 */       button.setVerticalTextPosition(3);
/* 478 */       button.setHorizontalTextPosition(0);
/* 479 */       button.setAlignmentX(0.5F);
/* 480 */       button.setIconTextGap(0);
/* 481 */       button.setPreferredSize(this.buttonSize);
/* 482 */       button.setMaximumSize(this.buttonSize);
/* 483 */       return button;
/*     */     }
/*     */     
/*     */     protected File[] getChooserShortcutPanelFiles(FileSystemView fsv) {
/*     */       try {
/* 488 */         if (SystemInfo.isJava_12_orLater) {
/* 489 */           Method m = fsv.getClass().getMethod("getChooserShortcutPanelFiles", new Class[0]);
/* 490 */           File[] files = (File[])m.invoke(fsv, new Object[0]);
/*     */ 
/*     */           
/* 493 */           if (files.length == 1 && files[0].equals(new File(System.getProperty("user.home")))) {
/* 494 */             files = new File[0];
/*     */           }
/* 496 */           return files;
/* 497 */         }  if (SystemInfo.isWindows) {
/* 498 */           Class<?> cls = Class.forName("sun.awt.shell.ShellFolder");
/* 499 */           Method m = cls.getMethod("get", new Class[] { String.class });
/* 500 */           return (File[])m.invoke((Object)null, new Object[] { "fileChooserShortcutPanelFolders" });
/*     */         } 
/* 502 */       } catch (IllegalAccessException illegalAccessException) {
/*     */       
/* 504 */       } catch (Exception ex) {
/* 505 */         LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */       } 
/*     */ 
/*     */       
/* 509 */       return new File[0];
/*     */     }
/*     */     
/*     */     protected String getDisplayName(FileSystemView fsv, File file) {
/* 513 */       if (this.displayNameFunction != null) {
/* 514 */         String name = this.displayNameFunction.apply(file);
/* 515 */         if (name != null) {
/* 516 */           return name;
/*     */         }
/*     */       } 
/* 519 */       return fsv.getSystemDisplayName(file);
/*     */     }
/*     */     
/*     */     protected Icon getIcon(FileSystemView fsv, File file) {
/* 523 */       if (this.iconFunction != null) {
/* 524 */         Icon icon = this.iconFunction.apply(file);
/* 525 */         if (icon != null) {
/* 526 */           return icon;
/*     */         }
/*     */       } 
/*     */       
/*     */       try {
/* 531 */         if (SystemInfo.isJava_17_orLater) {
/* 532 */           Method m = fsv.getClass().getMethod("getSystemIcon", new Class[] { File.class, int.class, int.class });
/* 533 */           return (Icon)m.invoke(fsv, new Object[] { file, Integer.valueOf(this.iconSize.width), Integer.valueOf(this.iconSize.height) });
/* 534 */         }  if (this.iconSize.width > 16 || this.iconSize.height > 16) {
/* 535 */           Class<?> cls = Class.forName("sun.awt.shell.ShellFolder");
/* 536 */           if (cls.isInstance(file)) {
/* 537 */             Method m = file.getClass().getMethod("getIcon", new Class[] { boolean.class });
/* 538 */             m.setAccessible(true);
/* 539 */             Image image = (Image)m.invoke(file, new Object[] { Boolean.valueOf(true) });
/* 540 */             if (image != null)
/* 541 */               return new ImageIcon(image); 
/*     */           } 
/*     */         } 
/* 544 */       } catch (IllegalAccessException illegalAccessException) {
/*     */       
/* 546 */       } catch (Exception ex) {
/* 547 */         LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */       } 
/*     */ 
/*     */       
/* 551 */       return fsv.getSystemIcon(file);
/*     */     }
/*     */     
/*     */     protected void directoryChanged(File file) {
/* 555 */       if (file != null) {
/* 556 */         String absolutePath = file.getAbsolutePath();
/* 557 */         for (int i = 0; i < this.files.length; i++) {
/*     */ 
/*     */           
/* 560 */           if (this.files[i].equals(file) || this.files[i].getAbsolutePath().equals(absolutePath)) {
/* 561 */             this.buttons[i].setSelected(true);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 567 */       this.buttonGroup.clearSelection();
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 572 */       switch (e.getPropertyName()) {
/*     */         case "directoryChanged":
/* 574 */           directoryChanged(this.fc.getCurrentDirectory());
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ShortcutIcon
/*     */     implements Icon
/*     */   {
/*     */     private final Icon icon;
/*     */     
/*     */     private final int iconWidth;
/*     */     private final int iconHeight;
/*     */     
/*     */     ShortcutIcon(Icon icon, int iconWidth, int iconHeight) {
/* 590 */       this.icon = icon;
/* 591 */       this.iconWidth = iconWidth;
/* 592 */       this.iconHeight = iconHeight;
/*     */     }
/*     */ 
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 597 */       Graphics2D g2 = (Graphics2D)g.create();
/*     */       
/*     */       try {
/* 600 */         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
/*     */         
/* 602 */         double scale = getIconWidth() / this.icon.getIconWidth();
/* 603 */         g2.translate(x, y);
/* 604 */         g2.scale(scale, scale);
/*     */         
/* 606 */         this.icon.paintIcon(c, g2, 0, 0);
/*     */       } finally {
/* 608 */         g2.dispose();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIconWidth() {
/* 614 */       return UIScale.scale(this.iconWidth);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIconHeight() {
/* 619 */       return UIScale.scale(this.iconHeight);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatFileChooserUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */