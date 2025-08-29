/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.MultiResolutionImageSupport;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyVetoException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JToolTip;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicDesktopIconUI;
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
/*     */ public class FlatDesktopIconUI
/*     */   extends BasicDesktopIconUI
/*     */ {
/*     */   private Dimension iconSize;
/*     */   private Dimension closeSize;
/*     */   private JLabel dockIcon;
/*     */   private JButton closeButton;
/*     */   private JToolTip titleTip;
/*     */   private ActionListener closeListener;
/*     */   private MouseInputListener mouseInputListener;
/*     */   private PropertyChangeListener ancestorListener;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  86 */     return new FlatDesktopIconUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  91 */     super.installUI(c);
/*     */ 
/*     */     
/*  94 */     if (c.isDisplayable()) {
/*  95 */       updateDockIconPreviewLater();
/*     */     }
/*     */   }
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 100 */     super.uninstallUI(c);
/*     */     
/* 102 */     this.dockIcon = null;
/* 103 */     this.closeButton = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installComponents() {
/* 108 */     this.dockIcon = new JLabel();
/* 109 */     this.dockIcon.setHorizontalAlignment(0);
/*     */     
/* 111 */     this.closeButton = new JButton();
/* 112 */     this.closeButton.setIcon(UIManager.getIcon("DesktopIcon.closeIcon"));
/* 113 */     this.closeButton.setFocusable(false);
/* 114 */     this.closeButton.setBorder(BorderFactory.createEmptyBorder());
/* 115 */     this.closeButton.setOpaque(true);
/* 116 */     this.closeButton.setBackground(FlatUIUtils.nonUIResource(this.desktopIcon.getBackground()));
/* 117 */     this.closeButton.setForeground(FlatUIUtils.nonUIResource(this.desktopIcon.getForeground()));
/* 118 */     this.closeButton.setVisible(false);
/*     */     
/* 120 */     this.desktopIcon.setLayout(new FlatDesktopIconLayout());
/* 121 */     this.desktopIcon.add(this.closeButton);
/* 122 */     this.desktopIcon.add(this.dockIcon);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallComponents() {
/* 127 */     hideTitleTip();
/*     */     
/* 129 */     this.desktopIcon.remove(this.dockIcon);
/* 130 */     this.desktopIcon.remove(this.closeButton);
/* 131 */     this.desktopIcon.setLayout((LayoutManager)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 136 */     super.installDefaults();
/*     */     
/* 138 */     LookAndFeel.installColors(this.desktopIcon, "DesktopIcon.background", "DesktopIcon.foreground");
/*     */     
/* 140 */     this.iconSize = UIManager.getDimension("DesktopIcon.iconSize");
/* 141 */     this.closeSize = UIManager.getDimension("DesktopIcon.closeSize");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 146 */     super.installListeners();
/*     */     
/* 148 */     this.closeListener = (e -> {
/*     */         if (this.frame.isClosable())
/*     */           this.frame.doDefaultCloseAction(); 
/*     */       });
/* 152 */     this.closeButton.addActionListener(this.closeListener);
/* 153 */     this.closeButton.addMouseListener(this.mouseInputListener);
/*     */     
/* 155 */     this.ancestorListener = (e -> {
/*     */         if (e.getNewValue() != null) {
/*     */           updateDockIconPreviewLater();
/*     */         } else {
/*     */           this.dockIcon.setIcon((Icon)null);
/*     */         } 
/*     */       });
/*     */ 
/*     */     
/* 164 */     this.desktopIcon.addPropertyChangeListener("ancestor", this.ancestorListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 169 */     super.uninstallListeners();
/*     */     
/* 171 */     this.closeButton.removeActionListener(this.closeListener);
/* 172 */     this.closeButton.removeMouseListener(this.mouseInputListener);
/* 173 */     this.closeListener = null;
/* 174 */     this.mouseInputListener = null;
/*     */     
/* 176 */     this.desktopIcon.removePropertyChangeListener("ancestor", this.ancestorListener);
/* 177 */     this.ancestorListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseInputListener createMouseInputListener() {
/* 182 */     this.mouseInputListener = new MouseInputAdapter()
/*     */       {
/*     */         public void mouseReleased(MouseEvent e) {
/* 185 */           if (FlatDesktopIconUI.this.frame.isIcon() && FlatDesktopIconUI.this.desktopIcon.contains(e.getX(), e.getY())) {
/* 186 */             FlatDesktopIconUI.this.hideTitleTip();
/* 187 */             FlatDesktopIconUI.this.closeButton.setVisible(false);
/*     */             
/*     */             try {
/* 190 */               FlatDesktopIconUI.this.frame.setIcon(false);
/* 191 */             } catch (PropertyVetoException propertyVetoException) {}
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void mouseEntered(MouseEvent e) {
/* 199 */           FlatDesktopIconUI.this.showTitleTip();
/* 200 */           if (FlatDesktopIconUI.this.frame.isClosable()) {
/* 201 */             FlatDesktopIconUI.this.closeButton.setVisible(true);
/*     */           }
/*     */         }
/*     */         
/*     */         public void mouseExited(MouseEvent e) {
/* 206 */           FlatDesktopIconUI.this.hideTitleTip();
/* 207 */           FlatDesktopIconUI.this.closeButton.setVisible(false);
/*     */         }
/*     */       };
/* 210 */     return this.mouseInputListener;
/*     */   }
/*     */   
/*     */   private void showTitleTip() {
/* 214 */     JRootPane rootPane = SwingUtilities.getRootPane(this.desktopIcon);
/* 215 */     if (rootPane == null) {
/*     */       return;
/*     */     }
/* 218 */     if (this.titleTip == null) {
/* 219 */       this.titleTip = new JToolTip();
/* 220 */       rootPane.getLayeredPane().add(this.titleTip, JLayeredPane.POPUP_LAYER);
/*     */     } 
/* 222 */     this.titleTip.setTipText(this.frame.getTitle());
/* 223 */     this.titleTip.setSize(this.titleTip.getPreferredSize());
/*     */     
/* 225 */     int tx = (this.desktopIcon.getWidth() - this.titleTip.getWidth()) / 2;
/* 226 */     int ty = -(this.titleTip.getHeight() + UIScale.scale(4));
/* 227 */     Point pt = SwingUtilities.convertPoint(this.desktopIcon, tx, ty, this.titleTip.getParent());
/* 228 */     if (pt.x + this.titleTip.getWidth() > rootPane.getWidth())
/* 229 */       pt.x = rootPane.getWidth() - this.titleTip.getWidth(); 
/* 230 */     if (pt.x < 0)
/* 231 */       pt.x = 0; 
/* 232 */     this.titleTip.setLocation(pt);
/* 233 */     this.titleTip.repaint();
/*     */   }
/*     */   
/*     */   private void hideTitleTip() {
/* 237 */     if (this.titleTip == null) {
/*     */       return;
/*     */     }
/* 240 */     this.titleTip.setVisible(false);
/* 241 */     this.titleTip.getParent().remove(this.titleTip);
/* 242 */     this.titleTip = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 247 */     return UIScale.scale(this.iconSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 252 */     return getPreferredSize(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize(JComponent c) {
/* 257 */     return getPreferredSize(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 262 */     if (c.isOpaque()) {
/*     */       
/* 264 */       Color background = c.getBackground();
/* 265 */       JDesktopPane desktopPane = this.desktopIcon.getDesktopPane();
/* 266 */       g.setColor((desktopPane != null) ? 
/* 267 */           FlatUIUtils.deriveColor(background, desktopPane.getBackground()) : 
/* 268 */           background);
/* 269 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */     } 
/*     */     
/* 272 */     paint(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateDockIconPreviewLater() {
/* 277 */     EventQueue.invokeLater(() -> {
/*     */           if (this.dockIcon != null) {
/*     */             updateDockIconPreview();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected void updateDockIconPreview() {
/* 285 */     if (this.frame.isSelected()) {
/*     */       try {
/* 287 */         this.frame.setSelected(false);
/* 288 */       } catch (PropertyVetoException propertyVetoException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (!this.frame.isValid())
/* 297 */       this.frame.doLayout(); 
/* 298 */     for (Component c : this.frame.getComponents()) {
/* 299 */       if (!c.isValid()) {
/* 300 */         c.doLayout();
/*     */       }
/*     */     } 
/*     */     
/* 304 */     int frameWidth = Math.max(this.frame.getWidth(), 1);
/* 305 */     int frameHeight = Math.max(this.frame.getHeight(), 1);
/* 306 */     BufferedImage frameImage = new BufferedImage(frameWidth, frameHeight, 2);
/* 307 */     Graphics2D g = frameImage.createGraphics();
/*     */     try {
/* 309 */       this.frame.paint(g);
/*     */     } finally {
/* 311 */       g.dispose();
/*     */     } 
/*     */ 
/*     */     
/* 315 */     Insets insets = this.desktopIcon.getInsets();
/* 316 */     int previewWidth = UIScale.scale(this.iconSize.width) - insets.left - insets.right;
/* 317 */     int previewHeight = UIScale.scale(this.iconSize.height) - insets.top - insets.bottom;
/* 318 */     float frameRatio = frameHeight / frameWidth;
/* 319 */     if (previewWidth / frameWidth > previewHeight / frameHeight) {
/* 320 */       previewWidth = Math.round(previewHeight / frameRatio);
/*     */     } else {
/* 322 */       previewHeight = Math.round(previewWidth * frameRatio);
/*     */     } 
/*     */     
/* 325 */     Image previewImage = frameImage.getScaledInstance(previewWidth, previewHeight, 4);
/* 326 */     if (MultiResolutionImageSupport.isAvailable()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 332 */       Image previewImage2x = frameImage.getScaledInstance(previewWidth * 2, previewHeight * 2, 4);
/* 333 */       double scaleFactor = UIScale.getSystemScaleFactor(this.desktopIcon.getGraphicsConfiguration());
/* 334 */       if (scaleFactor != 1.0D && scaleFactor != 2.0D) {
/* 335 */         Image previewImageCurrent = frameImage.getScaledInstance(
/* 336 */             (int)Math.round(previewWidth * scaleFactor), 
/* 337 */             (int)Math.round(previewHeight * scaleFactor), 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 343 */         previewImage = (scaleFactor < 2.0D) ? MultiResolutionImageSupport.create(0, new Image[] { previewImage, previewImageCurrent, previewImage2x }) : MultiResolutionImageSupport.create(0, new Image[] { previewImage, previewImage2x, previewImageCurrent });
/*     */       } else {
/* 345 */         previewImage = MultiResolutionImageSupport.create(0, new Image[] { previewImage, previewImage2x });
/*     */       } 
/* 347 */     }  this.dockIcon.setIcon(new ImageIcon(previewImage));
/*     */   }
/*     */   
/*     */   private class FlatDesktopIconLayout
/*     */     implements LayoutManager
/*     */   {
/*     */     private FlatDesktopIconLayout() {}
/*     */     
/*     */     public void addLayoutComponent(String name, Component comp) {}
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {}
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 360 */       return FlatDesktopIconUI.this.dockIcon.getPreferredSize();
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 365 */       return FlatDesktopIconUI.this.dockIcon.getMinimumSize();
/*     */     }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 370 */       Insets insets = parent.getInsets();
/*     */ 
/*     */       
/* 373 */       FlatDesktopIconUI.this.dockIcon.setBounds(insets.left, insets.top, parent
/* 374 */           .getWidth() - insets.left - insets.right, parent
/* 375 */           .getHeight() - insets.top - insets.bottom);
/*     */ 
/*     */       
/* 378 */       Dimension cSize = UIScale.scale(FlatDesktopIconUI.this.closeSize);
/* 379 */       FlatDesktopIconUI.this.closeButton.setBounds(parent.getWidth() - cSize.width, 0, cSize.width, cSize.height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatDesktopIconUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */