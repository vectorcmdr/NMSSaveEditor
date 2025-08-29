/*     */ package com.formdev.flatlaf;
/*     */ 
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.KeyEventPostProcessor;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.lang.ref.WeakReference;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.MenuElement;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ class MnemonicHandler
/*     */   implements KeyEventPostProcessor, ChangeListener
/*     */ {
/*     */   private static boolean showMnemonics;
/*     */   private static WeakReference<Window> lastShowMnemonicWindow;
/*     */   private static WindowListener windowListener;
/*     */   private static int altPressedEventCount;
/*     */   private static boolean selectMenuOnAltReleased;
/*     */   
/*     */   static boolean isShowMnemonics() {
/*  59 */     return (showMnemonics || !UIManager.getBoolean("Component.hideMnemonics"));
/*     */   }
/*     */   
/*     */   void install() {
/*  63 */     KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
/*  64 */     MenuSelectionManager.defaultManager().addChangeListener(this);
/*     */   }
/*     */   
/*     */   void uninstall() {
/*  68 */     KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(this);
/*  69 */     MenuSelectionManager.defaultManager().removeChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean postProcessKeyEvent(KeyEvent e) {
/*  74 */     int keyCode = e.getKeyCode();
/*  75 */     if (SystemInfo.isMacOS) {
/*     */       
/*  77 */       if (keyCode == 17 || keyCode == 18) {
/*  78 */         showMnemonics((shouldShowMnemonics(e) && e.isControlDown() && e.isAltDown()), e.getComponent());
/*     */       }
/*     */     } else {
/*  81 */       if (SystemInfo.isWindows) {
/*  82 */         return processKeyEventOnWindows(e);
/*     */       }
/*  84 */       if (keyCode == 18) {
/*  85 */         showMnemonics(shouldShowMnemonics(e), e.getComponent());
/*     */       }
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   private boolean shouldShowMnemonics(KeyEvent e) {
/*  92 */     return (e.getID() == 401 || (
/*  93 */       MenuSelectionManager.defaultManager().getSelectedPath()).length > 0);
/*     */   }
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
/*     */   private boolean processKeyEventOnWindows(KeyEvent e) {
/* 107 */     if (e.getKeyCode() != 18) {
/* 108 */       selectMenuOnAltReleased = false;
/* 109 */       return false;
/*     */     } 
/*     */     
/* 112 */     if (e.getID() == 401) {
/* 113 */       altPressedEventCount++;
/*     */       
/* 115 */       if (altPressedEventCount == 1 && !e.isConsumed()) {
/* 116 */         MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
/* 117 */         selectMenuOnAltReleased = ((menuSelectionManager.getSelectedPath()).length == 0);
/*     */ 
/*     */         
/* 120 */         if (!selectMenuOnAltReleased) {
/* 121 */           menuSelectionManager.clearSelectedPath();
/*     */         }
/*     */       } 
/*     */       
/* 125 */       showMnemonics(shouldShowMnemonics(e), e.getComponent());
/*     */ 
/*     */       
/* 128 */       e.consume();
/* 129 */       return true;
/*     */     } 
/* 131 */     if (e.getID() == 402) {
/* 132 */       altPressedEventCount = 0;
/*     */       
/* 134 */       boolean mnemonicsShown = false;
/* 135 */       if (selectMenuOnAltReleased && !e.isConsumed()) {
/* 136 */         MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
/* 137 */         if ((menuSelectionManager.getSelectedPath()).length == 0) {
/*     */           
/* 139 */           Component c = e.getComponent();
/* 140 */           JRootPane rootPane = SwingUtilities.getRootPane(c);
/* 141 */           JMenuBar menuBar = (rootPane != null) ? rootPane.getJMenuBar() : null;
/* 142 */           if (menuBar == null) {
/*     */ 
/*     */ 
/*     */             
/* 146 */             Window window = SwingUtilities.getWindowAncestor(c);
/* 147 */             if (window instanceof JFrame) {
/* 148 */               menuBar = ((JFrame)window).getJMenuBar();
/* 149 */             } else if (window instanceof JDialog) {
/* 150 */               menuBar = ((JDialog)window).getJMenuBar();
/*     */             } 
/* 152 */           }  JMenu firstMenu = (menuBar != null) ? menuBar.getMenu(0) : null;
/*     */ 
/*     */           
/* 155 */           if (firstMenu != null) {
/* 156 */             menuSelectionManager.setSelectedPath(new MenuElement[] { menuBar, firstMenu });
/* 157 */             showMnemonics(true, c);
/* 158 */             mnemonicsShown = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 162 */       selectMenuOnAltReleased = false;
/*     */ 
/*     */       
/* 165 */       if (!mnemonicsShown)
/* 166 */         showMnemonics(shouldShowMnemonics(e), e.getComponent()); 
/*     */     } 
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stateChanged(ChangeEvent e) {
/* 173 */     MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
/* 174 */     if (selectedPath.length == 0 && altPressedEventCount == 0)
/*     */     {
/* 176 */       showMnemonics(false, null);
/*     */     }
/*     */   }
/*     */   
/*     */   static void showMnemonics(boolean show, Component c) {
/* 181 */     if (show == showMnemonics) {
/*     */       return;
/*     */     }
/* 184 */     showMnemonics = show;
/*     */ 
/*     */     
/* 187 */     if (!UIManager.getBoolean("Component.hideMnemonics")) {
/*     */       return;
/*     */     }
/* 190 */     if (show) {
/*     */       
/* 192 */       JRootPane rootPane = SwingUtilities.getRootPane(c);
/* 193 */       if (rootPane == null) {
/*     */         return;
/*     */       }
/*     */       
/* 197 */       Window window = SwingUtilities.getWindowAncestor(rootPane);
/* 198 */       if (window == null) {
/*     */         return;
/*     */       }
/*     */       
/* 202 */       repaintMnemonics(window);
/*     */ 
/*     */       
/* 205 */       windowListener = new WindowAdapter()
/*     */         {
/*     */           public void windowDeactivated(WindowEvent e) {
/* 208 */             MnemonicHandler.altPressedEventCount = 0;
/* 209 */             MnemonicHandler.selectMenuOnAltReleased = false;
/*     */ 
/*     */ 
/*     */             
/* 213 */             EventQueue.invokeLater(() -> MnemonicHandler.showMnemonics(false, null));
/*     */           }
/*     */         };
/*     */ 
/*     */       
/* 218 */       window.addWindowListener(windowListener);
/*     */       
/* 220 */       lastShowMnemonicWindow = new WeakReference<>(window);
/* 221 */     } else if (lastShowMnemonicWindow != null) {
/* 222 */       Window window = lastShowMnemonicWindow.get();
/* 223 */       if (window != null) {
/* 224 */         repaintMnemonics(window);
/*     */         
/* 226 */         if (windowListener != null) {
/* 227 */           window.removeWindowListener(windowListener);
/* 228 */           windowListener = null;
/*     */         } 
/*     */       } 
/*     */       
/* 232 */       lastShowMnemonicWindow = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void repaintMnemonics(Container container) {
/* 237 */     for (Component c : container.getComponents()) {
/* 238 */       if (c.isVisible()) {
/*     */ 
/*     */         
/* 241 */         if (hasMnemonic(c)) {
/* 242 */           c.repaint();
/*     */         }
/* 244 */         if (c instanceof Container)
/* 245 */           repaintMnemonics((Container)c); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private static boolean hasMnemonic(Component c) {
/* 250 */     if (c instanceof JLabel && ((JLabel)c).getDisplayedMnemonicIndex() >= 0) {
/* 251 */       return true;
/*     */     }
/* 253 */     if (c instanceof AbstractButton && ((AbstractButton)c).getDisplayedMnemonicIndex() >= 0) {
/* 254 */       return true;
/*     */     }
/* 256 */     if (c instanceof JTabbedPane) {
/* 257 */       JTabbedPane tabPane = (JTabbedPane)c;
/* 258 */       int tabCount = tabPane.getTabCount();
/* 259 */       for (int i = 0; i < tabCount; i++) {
/* 260 */         if (tabPane.getDisplayedMnemonicIndexAt(i) >= 0) {
/* 261 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 265 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\MnemonicHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */