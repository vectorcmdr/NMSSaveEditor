package com.formdev.flatlaf;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.ref.WeakReference;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MnemonicHandler implements KeyEventPostProcessor, ChangeListener {
   private static boolean showMnemonics;
   private static WeakReference<Window> lastShowMnemonicWindow;
   private static WindowListener windowListener;
   private static int altPressedEventCount;
   private static boolean selectMenuOnAltReleased;

   static boolean isShowMnemonics() {
      return showMnemonics || !UIManager.getBoolean("Component.hideMnemonics");
   }

   void install() {
      KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
      MenuSelectionManager.defaultManager().addChangeListener(this);
   }

   void uninstall() {
      KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(this);
      MenuSelectionManager.defaultManager().removeChangeListener(this);
   }

   public boolean postProcessKeyEvent(KeyEvent e) {
      int keyCode = e.getKeyCode();
      if (SystemInfo.isMacOS) {
         if (keyCode == 17 || keyCode == 18) {
            showMnemonics(this.shouldShowMnemonics(e) && e.isControlDown() && e.isAltDown(), e.getComponent());
         }
      } else {
         if (SystemInfo.isWindows) {
            return this.processKeyEventOnWindows(e);
         }

         if (keyCode == 18) {
            showMnemonics(this.shouldShowMnemonics(e), e.getComponent());
         }
      }

      return false;
   }

   private boolean shouldShowMnemonics(KeyEvent e) {
      return e.getID() == 401 || MenuSelectionManager.defaultManager().getSelectedPath().length > 0;
   }

   private boolean processKeyEventOnWindows(KeyEvent e) {
      if (e.getKeyCode() != 18) {
         selectMenuOnAltReleased = false;
         return false;
      } else if (e.getID() == 401) {
         ++altPressedEventCount;
         if (altPressedEventCount == 1 && !e.isConsumed()) {
            MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
            selectMenuOnAltReleased = menuSelectionManager.getSelectedPath().length == 0;
            if (!selectMenuOnAltReleased) {
               menuSelectionManager.clearSelectedPath();
            }
         }

         showMnemonics(this.shouldShowMnemonics(e), e.getComponent());
         e.consume();
         return true;
      } else {
         if (e.getID() == 402) {
            altPressedEventCount = 0;
            boolean mnemonicsShown = false;
            if (selectMenuOnAltReleased && !e.isConsumed()) {
               MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
               if (menuSelectionManager.getSelectedPath().length == 0) {
                  Component c = e.getComponent();
                  JRootPane rootPane = SwingUtilities.getRootPane(c);
                  JMenuBar menuBar = rootPane != null ? rootPane.getJMenuBar() : null;
                  if (menuBar == null) {
                     Window window = SwingUtilities.getWindowAncestor(c);
                     if (window instanceof JFrame) {
                        menuBar = ((JFrame)window).getJMenuBar();
                     } else if (window instanceof JDialog) {
                        menuBar = ((JDialog)window).getJMenuBar();
                     }
                  }

                  JMenu firstMenu = menuBar != null ? menuBar.getMenu(0) : null;
                  if (firstMenu != null) {
                     menuSelectionManager.setSelectedPath(new MenuElement[]{menuBar, firstMenu});
                     showMnemonics(true, c);
                     mnemonicsShown = true;
                  }
               }
            }

            selectMenuOnAltReleased = false;
            if (!mnemonicsShown) {
               showMnemonics(this.shouldShowMnemonics(e), e.getComponent());
            }
         }

         return false;
      }
   }

   public void stateChanged(ChangeEvent e) {
      MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
      if (selectedPath.length == 0 && altPressedEventCount == 0) {
         showMnemonics(false, (Component)null);
      }

   }

   static void showMnemonics(boolean show, Component c) {
      if (show != showMnemonics) {
         showMnemonics = show;
         if (UIManager.getBoolean("Component.hideMnemonics")) {
            if (show) {
               JRootPane rootPane = SwingUtilities.getRootPane(c);
               if (rootPane == null) {
                  return;
               }

               Window window = SwingUtilities.getWindowAncestor(rootPane);
               if (window == null) {
                  return;
               }

               repaintMnemonics(window);
               windowListener = new WindowAdapter() {
                  public void windowDeactivated(WindowEvent e) {
                     MnemonicHandler.altPressedEventCount = 0;
                     MnemonicHandler.selectMenuOnAltReleased = false;
                     EventQueue.invokeLater(() -> {
                        MnemonicHandler.showMnemonics(false, (Component)null);
                     });
                  }
               };
               window.addWindowListener(windowListener);
               lastShowMnemonicWindow = new WeakReference(window);
            } else if (lastShowMnemonicWindow != null) {
               Window window = (Window)lastShowMnemonicWindow.get();
               if (window != null) {
                  repaintMnemonics(window);
                  if (windowListener != null) {
                     window.removeWindowListener(windowListener);
                     windowListener = null;
                  }
               }

               lastShowMnemonicWindow = null;
            }

         }
      }
   }

   private static void repaintMnemonics(Container container) {
      Component[] var1 = container.getComponents();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Component c = var1[var3];
         if (c.isVisible()) {
            if (hasMnemonic(c)) {
               c.repaint();
            }

            if (c instanceof Container) {
               repaintMnemonics((Container)c);
            }
         }
      }

   }

   private static boolean hasMnemonic(Component c) {
      if (c instanceof JLabel && ((JLabel)c).getDisplayedMnemonicIndex() >= 0) {
         return true;
      } else if (c instanceof AbstractButton && ((AbstractButton)c).getDisplayedMnemonicIndex() >= 0) {
         return true;
      } else {
         if (c instanceof JTabbedPane) {
            JTabbedPane tabPane = (JTabbedPane)c;
            int tabCount = tabPane.getTabCount();

            for(int i = 0; i < tabCount; ++i) {
               if (tabPane.getDisplayedMnemonicIndexAt(i) >= 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
