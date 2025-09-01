package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.function.Function;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.Box.Filler;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI.BasicFileView;
import javax.swing.plaf.metal.MetalFileChooserUI;
import javax.swing.table.TableCellRenderer;

public class FlatFileChooserUI extends MetalFileChooserUI {
   private final FlatFileChooserUI.FlatFileView fileView = new FlatFileChooserUI.FlatFileView();
   private FlatFileChooserUI.FlatShortcutsPanel shortcutsPanel;

   public static ComponentUI createUI(JComponent c) {
      return new FlatFileChooserUI((JFileChooser)c);
   }

   public FlatFileChooserUI(JFileChooser filechooser) {
      super(filechooser);
   }

   public void installComponents(JFileChooser fc) {
      super.installComponents(fc);
      this.patchUI(fc);
      if (!UIManager.getBoolean("FileChooser.noPlacesBar")) {
         FlatFileChooserUI.FlatShortcutsPanel panel = this.createShortcutsPanel(fc);
         if (panel.getComponentCount() > 0) {
            this.shortcutsPanel = panel;
            fc.add(this.shortcutsPanel, "Before");
            fc.addPropertyChangeListener(this.shortcutsPanel);
         }
      }

   }

   public void uninstallComponents(JFileChooser fc) {
      super.uninstallComponents(fc);
      if (this.shortcutsPanel != null) {
         fc.removePropertyChangeListener(this.shortcutsPanel);
         this.shortcutsPanel = null;
      }

   }

   private void patchUI(JFileChooser fc) {
      Component topPanel = fc.getComponent(0);
      Component topButtonPanel;
      Component c;
      if (topPanel instanceof JPanel && ((JPanel)topPanel).getLayout() instanceof BorderLayout) {
         topButtonPanel = ((JPanel)topPanel).getComponent(0);
         if (topButtonPanel instanceof JPanel && ((JPanel)topButtonPanel).getLayout() instanceof BoxLayout) {
            Insets margin = UIManager.getInsets("Button.margin");
            Component[] comps = ((JPanel)topButtonPanel).getComponents();

            for(int i = comps.length - 1; i >= 0; --i) {
               c = comps[i];
               if (!(c instanceof JButton) && !(c instanceof JToggleButton)) {
                  if (c instanceof Filler) {
                     ((JPanel)topButtonPanel).remove(i);
                  }
               } else {
                  AbstractButton b = (AbstractButton)c;
                  b.putClientProperty("JButton.buttonType", "toolBarButton");
                  b.setMargin(margin);
                  b.setFocusable(false);
               }
            }
         }
      }

      try {
         topButtonPanel = ((JPanel)topPanel).getComponent(2);
         if (topButtonPanel instanceof JComboBox) {
            int maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount");
            if (maximumRowCount > 0) {
               ((JComboBox)topButtonPanel).setMaximumRowCount(maximumRowCount);
            }
         }
      } catch (ArrayIndexOutOfBoundsException var10) {
      }

      LayoutManager layout = fc.getLayout();
      if (layout instanceof BorderLayout) {
         BorderLayout borderLayout = (BorderLayout)layout;
         borderLayout.setHgap(8);
         Component north = borderLayout.getLayoutComponent("North");
         Component lineEnd = borderLayout.getLayoutComponent("After");
         c = borderLayout.getLayoutComponent("Center");
         Component south = borderLayout.getLayoutComponent("South");
         if (north != null && lineEnd != null && c != null && south != null) {
            JPanel p = new JPanel(new BorderLayout(0, 11));
            p.add(north, "North");
            p.add(lineEnd, "After");
            p.add(c, "Center");
            p.add(south, "South");
            fc.add(p, "Center");
         }
      }

   }

   protected JPanel createDetailsView(JFileChooser fc) {
      JPanel p = super.createDetailsView(fc);
      if (!SystemInfo.isWindows) {
         return p;
      } else {
         JScrollPane scrollPane = null;
         Component[] var4 = p.getComponents();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            Component c = var4[var6];
            if (c instanceof JScrollPane) {
               scrollPane = (JScrollPane)c;
               break;
            }
         }

         if (scrollPane == null) {
            return p;
         } else {
            Component view = scrollPane.getViewport().getView();
            if (!(view instanceof JTable)) {
               return p;
            } else {
               JTable table = (JTable)view;
               final TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
               table.setDefaultRenderer(Object.class, new TableCellRenderer() {
                  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                     if (value instanceof String && ((String)value).startsWith("\u200e")) {
                        String str = (String)value;
                        char[] buf = new char[str.length()];
                        int j = 0;

                        for(int i = 0; i < buf.length; ++i) {
                           char ch = str.charAt(i);
                           if (ch != 8206 && ch != 8207) {
                              buf[j++] = ch;
                           }
                        }

                        value = new String(buf, 0, j);
                     }

                     return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                  }
               });
               return p;
            }
         }
      }
   }

   protected FlatFileChooserUI.FlatShortcutsPanel createShortcutsPanel(JFileChooser fc) {
      return new FlatFileChooserUI.FlatShortcutsPanel(fc);
   }

   public Dimension getPreferredSize(JComponent c) {
      Dimension prefSize = super.getPreferredSize(c);
      Dimension minSize = this.getMinimumSize(c);
      int shortcutsPanelWidth = this.shortcutsPanel != null ? this.shortcutsPanel.getPreferredSize().width : 0;
      return new Dimension(Math.max(prefSize.width, minSize.width + shortcutsPanelWidth), Math.max(prefSize.height, minSize.height));
   }

   public Dimension getMinimumSize(JComponent c) {
      return UIScale.scale(super.getMinimumSize(c));
   }

   public FileView getFileView(JFileChooser fc) {
      return (FileView)(this.doNotUseSystemIcons() ? super.getFileView(fc) : this.fileView);
   }

   public void clearIconCache() {
      if (this.doNotUseSystemIcons()) {
         super.clearIconCache();
      } else {
         this.fileView.clearIconCache();
      }

   }

   private boolean doNotUseSystemIcons() {
      return SystemInfo.isWindows && SystemInfo.isX86 && SystemInfo.isJava_17_orLater && !SystemInfo.isJava_18_orLater;
   }

   private static class ShortcutIcon implements Icon {
      private final Icon icon;
      private final int iconWidth;
      private final int iconHeight;

      ShortcutIcon(Icon icon, int iconWidth, int iconHeight) {
         this.icon = icon;
         this.iconWidth = iconWidth;
         this.iconHeight = iconHeight;
      }

      public void paintIcon(Component c, Graphics g, int x, int y) {
         Graphics2D g2 = (Graphics2D)g.create();

         try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            double scale = (double)this.getIconWidth() / (double)this.icon.getIconWidth();
            g2.translate(x, y);
            g2.scale(scale, scale);
            this.icon.paintIcon(c, g2, 0, 0);
         } finally {
            g2.dispose();
         }

      }

      public int getIconWidth() {
         return UIScale.scale(this.iconWidth);
      }

      public int getIconHeight() {
         return UIScale.scale(this.iconHeight);
      }
   }

   public static class FlatShortcutsPanel extends JToolBar implements PropertyChangeListener {
      private final JFileChooser fc;
      private final Dimension buttonSize;
      private final Dimension iconSize;
      private final Function<File[], File[]> filesFunction;
      private final Function<File, String> displayNameFunction;
      private final Function<File, Icon> iconFunction;
      protected final File[] files;
      protected final JToggleButton[] buttons;
      protected final ButtonGroup buttonGroup;

      public FlatShortcutsPanel(JFileChooser fc) {
         super(1);
         this.fc = fc;
         this.setFloatable(false);
         this.buttonSize = UIScale.scale(this.getUIDimension("FileChooser.shortcuts.buttonSize", 84, 64));
         this.iconSize = this.getUIDimension("FileChooser.shortcuts.iconSize", 32, 32);
         this.filesFunction = (Function)UIManager.get("FileChooser.shortcuts.filesFunction");
         this.displayNameFunction = (Function)UIManager.get("FileChooser.shortcuts.displayNameFunction");
         this.iconFunction = (Function)UIManager.get("FileChooser.shortcuts.iconFunction");
         FileSystemView fsv = fc.getFileSystemView();
         File[] files = this.getChooserShortcutPanelFiles(fsv);
         if (this.filesFunction != null) {
            files = (File[])this.filesFunction.apply(files);
         }

         this.files = files;
         this.buttons = new JToggleButton[files.length];
         this.buttonGroup = new ButtonGroup();

         for(int i = 0; i < files.length; ++i) {
            if (fsv.isFileSystemRoot(files[i])) {
               files[i] = fsv.createFileObject(files[i].getAbsolutePath());
            }

            File file = files[i];
            String name = this.getDisplayName(fsv, file);
            Icon icon = this.getIcon(fsv, file);
            int lastSepIndex = name.lastIndexOf(File.separatorChar);
            if (lastSepIndex >= 0 && lastSepIndex < name.length() - 1) {
               name = name.substring(lastSepIndex + 1);
            }

            if (icon instanceof ImageIcon) {
               icon = new ScaledImageIcon((ImageIcon)icon, this.iconSize.width, this.iconSize.height);
            } else if (icon != null) {
               icon = new FlatFileChooserUI.ShortcutIcon((Icon)icon, this.iconSize.width, this.iconSize.height);
            }

            JToggleButton button = this.createButton(name, (Icon)icon);
            button.addActionListener((e) -> {
               fc.setCurrentDirectory(file);
            });
            this.add(button);
            this.buttonGroup.add(button);
            this.buttons[i] = button;
         }

         this.directoryChanged(fc.getCurrentDirectory());
      }

      private Dimension getUIDimension(String key, int defaultWidth, int defaultHeight) {
         Dimension size = UIManager.getDimension(key);
         if (size == null) {
            size = new Dimension(defaultWidth, defaultHeight);
         }

         return size;
      }

      protected JToggleButton createButton(String name, Icon icon) {
         JToggleButton button = new JToggleButton(name, icon);
         button.setVerticalTextPosition(3);
         button.setHorizontalTextPosition(0);
         button.setAlignmentX(0.5F);
         button.setIconTextGap(0);
         button.setPreferredSize(this.buttonSize);
         button.setMaximumSize(this.buttonSize);
         return button;
      }

      protected File[] getChooserShortcutPanelFiles(FileSystemView fsv) {
         try {
            if (SystemInfo.isJava_12_orLater) {
               Method m = fsv.getClass().getMethod("getChooserShortcutPanelFiles");
               File[] files = (File[])m.invoke(fsv);
               if (files.length == 1 && files[0].equals(new File(System.getProperty("user.home")))) {
                  files = new File[0];
               }

               return files;
            }

            if (SystemInfo.isWindows) {
               Class<?> cls = Class.forName("sun.awt.shell.ShellFolder");
               Method m = cls.getMethod("get", String.class);
               return (File[])m.invoke((Object)null, "fileChooserShortcutPanelFolders");
            }
         } catch (IllegalAccessException var4) {
         } catch (Exception var5) {
            LoggingFacade.INSTANCE.logSevere((String)null, var5);
         }

         return new File[0];
      }

      protected String getDisplayName(FileSystemView fsv, File file) {
         if (this.displayNameFunction != null) {
            String name = (String)this.displayNameFunction.apply(file);
            if (name != null) {
               return name;
            }
         }

         return fsv.getSystemDisplayName(file);
      }

      protected Icon getIcon(FileSystemView fsv, File file) {
         if (this.iconFunction != null) {
            Icon icon = (Icon)this.iconFunction.apply(file);
            if (icon != null) {
               return icon;
            }
         }

         try {
            if (SystemInfo.isJava_17_orLater) {
               Method m = fsv.getClass().getMethod("getSystemIcon", File.class, Integer.TYPE, Integer.TYPE);
               return (Icon)m.invoke(fsv, file, this.iconSize.width, this.iconSize.height);
            }

            if (this.iconSize.width > 16 || this.iconSize.height > 16) {
               Class<?> cls = Class.forName("sun.awt.shell.ShellFolder");
               if (cls.isInstance(file)) {
                  Method m = file.getClass().getMethod("getIcon", Boolean.TYPE);
                  m.setAccessible(true);
                  Image image = (Image)m.invoke(file, true);
                  if (image != null) {
                     return new ImageIcon(image);
                  }
               }
            }
         } catch (IllegalAccessException var6) {
         } catch (Exception var7) {
            LoggingFacade.INSTANCE.logSevere((String)null, var7);
         }

         return fsv.getSystemIcon(file);
      }

      protected void directoryChanged(File file) {
         if (file != null) {
            String absolutePath = file.getAbsolutePath();

            for(int i = 0; i < this.files.length; ++i) {
               if (this.files[i].equals(file) || this.files[i].getAbsolutePath().equals(absolutePath)) {
                  this.buttons[i].setSelected(true);
                  return;
               }
            }
         }

         this.buttonGroup.clearSelection();
      }

      public void propertyChange(PropertyChangeEvent e) {
         String var2 = e.getPropertyName();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1295632505:
            if (var2.equals("directoryChanged")) {
               var3 = 0;
            }
         default:
            switch(var3) {
            case 0:
               this.directoryChanged(this.fc.getCurrentDirectory());
            default:
            }
         }
      }
   }

   private class FlatFileView extends BasicFileView {
      private FlatFileView() {
         super(FlatFileChooserUI.this);
      }

      public Icon getIcon(File f) {
         Icon iconx = this.getCachedIcon(f);
         if (iconx != null) {
            return iconx;
         } else {
            Object icon;
            if (f != null) {
               icon = FlatFileChooserUI.this.getFileChooser().getFileSystemView().getSystemIcon(f);
               if (icon != null) {
                  if (icon instanceof ImageIcon) {
                     icon = new ScaledImageIcon((ImageIcon)icon);
                  }

                  this.cacheIcon(f, (Icon)icon);
                  return (Icon)icon;
               }
            }

            icon = super.getIcon(f);
            if (icon instanceof ImageIcon) {
               icon = new ScaledImageIcon((ImageIcon)icon);
               this.cacheIcon(f, (Icon)icon);
            }

            return (Icon)icon;
         }
      }

      // $FF: synthetic method
      FlatFileView(Object x1) {
         this();
      }
   }
}
