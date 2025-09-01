package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Map;
import java.util.Objects;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

@FlatStylingSupport.StyleableFields({@FlatStylingSupport.StyleableField(
   cls = BasicScrollBarUI.class,
   key = "track",
   fieldName = "trackColor"
), @FlatStylingSupport.StyleableField(
   cls = BasicScrollBarUI.class,
   key = "thumb",
   fieldName = "thumbColor"
), @FlatStylingSupport.StyleableField(
   cls = BasicScrollBarUI.class,
   key = "width",
   fieldName = "scrollBarWidth"
), @FlatStylingSupport.StyleableField(
   cls = BasicScrollBarUI.class,
   key = "minimumThumbSize"
), @FlatStylingSupport.StyleableField(
   cls = BasicScrollBarUI.class,
   key = "maximumThumbSize"
)})
public class FlatScrollBarUI extends BasicScrollBarUI implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider {
   @FlatStylingSupport.Styleable
   protected boolean allowsAbsolutePositioning;
   @FlatStylingSupport.Styleable
   protected Dimension minimumButtonSize;
   @FlatStylingSupport.Styleable
   protected Insets trackInsets;
   @FlatStylingSupport.Styleable
   protected Insets thumbInsets;
   @FlatStylingSupport.Styleable
   protected int trackArc;
   @FlatStylingSupport.Styleable
   protected int thumbArc;
   @FlatStylingSupport.Styleable
   protected Color hoverTrackColor;
   @FlatStylingSupport.Styleable
   protected Color hoverThumbColor;
   @FlatStylingSupport.Styleable
   protected boolean hoverThumbWithTrack;
   @FlatStylingSupport.Styleable
   protected Color pressedTrackColor;
   @FlatStylingSupport.Styleable
   protected Color pressedThumbColor;
   @FlatStylingSupport.Styleable
   protected boolean pressedThumbWithTrack;
   @FlatStylingSupport.Styleable
   protected boolean showButtons;
   @FlatStylingSupport.Styleable
   protected String arrowType;
   @FlatStylingSupport.Styleable
   protected Color buttonArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonDisabledArrowColor;
   @FlatStylingSupport.Styleable
   protected Color hoverButtonBackground;
   @FlatStylingSupport.Styleable
   protected Color pressedButtonBackground;
   private MouseAdapter hoverListener;
   protected boolean hoverTrack;
   protected boolean hoverThumb;
   private Map<String, Object> oldStyleValues;
   private boolean isAWTPeer;
   private static boolean isPressed;

   public static ComponentUI createUI(JComponent c) {
      return new FlatScrollBarUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installListeners() {
      super.installListeners();
      this.hoverListener = new FlatScrollBarUI.ScrollBarHoverListener();
      this.scrollbar.addMouseListener(this.hoverListener);
      this.scrollbar.addMouseMotionListener(this.hoverListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.scrollbar.removeMouseListener(this.hoverListener);
      this.scrollbar.removeMouseMotionListener(this.hoverListener);
      this.hoverListener = null;
   }

   protected void installDefaults() {
      super.installDefaults();
      this.allowsAbsolutePositioning = super.getSupportsAbsolutePositioning();
      this.minimumButtonSize = UIManager.getDimension("ScrollBar.minimumButtonSize");
      this.trackInsets = UIManager.getInsets("ScrollBar.trackInsets");
      this.thumbInsets = UIManager.getInsets("ScrollBar.thumbInsets");
      this.trackArc = UIManager.getInt("ScrollBar.trackArc");
      this.thumbArc = UIManager.getInt("ScrollBar.thumbArc");
      this.hoverTrackColor = UIManager.getColor("ScrollBar.hoverTrackColor");
      this.hoverThumbColor = UIManager.getColor("ScrollBar.hoverThumbColor");
      this.hoverThumbWithTrack = UIManager.getBoolean("ScrollBar.hoverThumbWithTrack");
      this.pressedTrackColor = UIManager.getColor("ScrollBar.pressedTrackColor");
      this.pressedThumbColor = UIManager.getColor("ScrollBar.pressedThumbColor");
      this.pressedThumbWithTrack = UIManager.getBoolean("ScrollBar.pressedThumbWithTrack");
      this.showButtons = UIManager.getBoolean("ScrollBar.showButtons");
      this.arrowType = UIManager.getString("Component.arrowType");
      this.buttonArrowColor = UIManager.getColor("ScrollBar.buttonArrowColor");
      this.buttonDisabledArrowColor = UIManager.getColor("ScrollBar.buttonDisabledArrowColor");
      this.hoverButtonBackground = UIManager.getColor("ScrollBar.hoverButtonBackground");
      this.pressedButtonBackground = UIManager.getColor("ScrollBar.pressedButtonBackground");
      if (this.trackInsets == null) {
         this.trackInsets = new Insets(0, 0, 0, 0);
      }

      if (this.thumbInsets == null) {
         this.thumbInsets = new Insets(0, 0, 0, 0);
      }

   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.minimumButtonSize = null;
      this.trackInsets = null;
      this.thumbInsets = null;
      this.hoverTrackColor = null;
      this.hoverThumbColor = null;
      this.pressedTrackColor = null;
      this.pressedThumbColor = null;
      this.buttonArrowColor = null;
      this.buttonDisabledArrowColor = null;
      this.hoverButtonBackground = null;
      this.pressedButtonBackground = null;
      this.oldStyleValues = null;
   }

   protected PropertyChangeListener createPropertyChangeListener() {
      PropertyChangeListener superListener = super.createPropertyChangeListener();
      return (e) -> {
         superListener.propertyChange(e);
         String var3 = e.getPropertyName();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case -973829677:
            if (var3.equals("ancestor")) {
               var4 = 4;
            }
            break;
         case 193872818:
            if (var3.equals("JScrollBar.showButtons")) {
               var4 = 0;
            }
            break;
         case 1030195901:
            if (var3.equals("FlatLaf.styleClass")) {
               var4 = 2;
            }
            break;
         case 1247047827:
            if (var3.equals("componentOrientation")) {
               var4 = 3;
            }
            break;
         case 1545413499:
            if (var3.equals("FlatLaf.style")) {
               var4 = 1;
            }
         }

         switch(var4) {
         case 0:
            this.scrollbar.revalidate();
            this.scrollbar.repaint();
            break;
         case 1:
         case 2:
            this.installStyle();
            this.scrollbar.revalidate();
            this.scrollbar.repaint();
            break;
         case 3:
            InputMap inputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap");
            if (!this.scrollbar.getComponentOrientation().isLeftToRight()) {
               InputMap rtlInputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap.RightToLeft");
               if (rtlInputMap != null) {
                  rtlInputMap.setParent(inputMap);
                  inputMap = rtlInputMap;
               }
            }

            SwingUtilities.replaceUIInputMap(this.scrollbar, 1, inputMap);
            break;
         case 4:
            if (SystemInfo.isMacOS) {
               Container p = this.scrollbar.getParent();

               for(int i = 0; i < 2 && p != null; p = p.getParent()) {
                  if (FlatUIUtils.isAWTPeer(p)) {
                     this.isAWTPeer = true;
                     if (FlatLaf.isLafDark()) {
                        FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> {
                           JScrollBar scrollbar = this.scrollbar;
                           this.uninstallUI(scrollbar);
                           this.installUI(scrollbar);
                        });
                     }
                     break;
                  }

                  ++i;
               }
            }
         }

      };
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.scrollbar, "ScrollBar"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      if (this.incrButton instanceof FlatScrollBarUI.FlatScrollBarButton) {
         ((FlatScrollBarUI.FlatScrollBarButton)this.incrButton).updateStyle();
      }

      if (this.decrButton instanceof FlatScrollBarUI.FlatScrollBarButton) {
         ((FlatScrollBarUI.FlatScrollBarButton)this.decrButton).updateStyle();
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.scrollbar, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   public Lookup getLookupForStyling() {
      return MethodHandles.lookup();
   }

   public Dimension getPreferredSize(JComponent c) {
      return UIScale.scale(super.getPreferredSize(c));
   }

   protected JButton createDecreaseButton(int orientation) {
      return new FlatScrollBarUI.FlatScrollBarButton(orientation);
   }

   protected JButton createIncreaseButton(int orientation) {
      return new FlatScrollBarUI.FlatScrollBarButton(orientation);
   }

   protected boolean isShowButtons() {
      Object showButtons = this.scrollbar.getClientProperty("JScrollBar.showButtons");
      if (showButtons == null && this.scrollbar.getParent() instanceof JScrollPane) {
         JScrollPane scrollPane = (JScrollPane)this.scrollbar.getParent();
         showButtons = scrollPane.getClientProperty("JScrollBar.showButtons");
         if (showButtons == null && scrollPane.getUI() instanceof FlatScrollPaneUI) {
            showButtons = ((FlatScrollPaneUI)scrollPane.getUI()).showButtons;
         }
      }

      return showButtons != null ? Objects.equals(showButtons, true) : this.showButtons;
   }

   public void paint(Graphics g, JComponent c) {
      Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
      super.paint(g, c);
      FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
   }

   protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
      if (!trackBounds.isEmpty() && this.scrollbar.isEnabled()) {
         g.setColor(this.getTrackColor(c, this.hoverTrack, isPressed && this.hoverTrack && !this.hoverThumb));
         this.paintTrackOrThumb(g, c, trackBounds, this.trackInsets, this.trackArc);
      }
   }

   protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
      if (!thumbBounds.isEmpty() && this.scrollbar.isEnabled()) {
         g.setColor(this.getThumbColor(c, this.hoverThumb || this.hoverThumbWithTrack && this.hoverTrack, isPressed && (this.hoverThumb || this.pressedThumbWithTrack && this.hoverTrack)));
         this.paintTrackOrThumb(g, c, thumbBounds, this.thumbInsets, this.thumbArc);
      }
   }

   protected void paintTrackOrThumb(Graphics g, JComponent c, Rectangle bounds, Insets insets, int arc) {
      if (this.scrollbar.getOrientation() == 0) {
         insets = new Insets(insets.right, insets.top, insets.left, insets.bottom);
      }

      bounds = FlatUIUtils.subtractInsets(bounds, UIScale.scale(insets));
      if (arc <= 0) {
         g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
      } else {
         arc = Math.min(UIScale.scale(arc), Math.min(bounds.width, bounds.height));
         g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, arc, arc);
      }

   }

   protected void paintDecreaseHighlight(Graphics g) {
   }

   protected void paintIncreaseHighlight(Graphics g) {
   }

   protected Color getTrackColor(JComponent c, boolean hover, boolean pressed) {
      Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
      return pressed && this.pressedTrackColor != null ? FlatUIUtils.deriveColor(this.pressedTrackColor, trackColor) : (hover && this.hoverTrackColor != null && !this.isAWTPeer ? FlatUIUtils.deriveColor(this.hoverTrackColor, trackColor) : trackColor);
   }

   protected Color getThumbColor(JComponent c, boolean hover, boolean pressed) {
      Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
      Color thumbColor = FlatUIUtils.deriveColor(this.thumbColor, trackColor);
      return pressed && this.pressedThumbColor != null ? FlatUIUtils.deriveColor(this.pressedThumbColor, thumbColor) : (hover && this.hoverThumbColor != null && !this.isAWTPeer ? FlatUIUtils.deriveColor(this.hoverThumbColor, thumbColor) : thumbColor);
   }

   protected Dimension getMinimumThumbSize() {
      return UIScale.scale(FlatUIUtils.addInsets(super.getMinimumThumbSize(), this.thumbInsets));
   }

   protected Dimension getMaximumThumbSize() {
      return UIScale.scale(FlatUIUtils.addInsets(super.getMaximumThumbSize(), this.thumbInsets));
   }

   public boolean getSupportsAbsolutePositioning() {
      return this.allowsAbsolutePositioning;
   }

   protected class FlatScrollBarButton extends FlatArrowButton {
      protected FlatScrollBarButton(int direction) {
         this(direction, FlatScrollBarUI.this.arrowType, FlatScrollBarUI.this.buttonArrowColor, FlatScrollBarUI.this.buttonDisabledArrowColor, (Color)null, FlatScrollBarUI.this.hoverButtonBackground, (Color)null, FlatScrollBarUI.this.pressedButtonBackground);
      }

      protected FlatScrollBarButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
         super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
         this.setFocusable(false);
         this.setRequestFocusEnabled(false);
      }

      protected void updateStyle() {
         this.updateStyle(FlatScrollBarUI.this.arrowType, FlatScrollBarUI.this.buttonArrowColor, FlatScrollBarUI.this.buttonDisabledArrowColor, (Color)null, FlatScrollBarUI.this.hoverButtonBackground, (Color)null, FlatScrollBarUI.this.pressedButtonBackground);
      }

      public int getArrowWidth() {
         int arrowWidth = Math.round(6.0F * ((float)FlatScrollBarUI.this.scrollBarWidth / 10.0F));
         arrowWidth = FlatScrollBarUI.this.scrollBarWidth - (FlatScrollBarUI.this.scrollBarWidth - arrowWidth) / 2 * 2;
         return arrowWidth;
      }

      protected Color deriveBackground(Color background) {
         return FlatUIUtils.deriveColor(background, FlatScrollBarUI.this.scrollbar.getBackground());
      }

      public Dimension getPreferredSize() {
         if (FlatScrollBarUI.this.isShowButtons()) {
            int w = UIScale.scale(Math.max(FlatScrollBarUI.this.scrollBarWidth, FlatScrollBarUI.this.minimumButtonSize != null ? FlatScrollBarUI.this.minimumButtonSize.width : 0));
            int h = UIScale.scale(Math.max(FlatScrollBarUI.this.scrollBarWidth, FlatScrollBarUI.this.minimumButtonSize != null ? FlatScrollBarUI.this.minimumButtonSize.height : 0));
            return new Dimension(w, h);
         } else {
            return new Dimension();
         }
      }

      public Dimension getMinimumSize() {
         return FlatScrollBarUI.this.isShowButtons() ? super.getMinimumSize() : new Dimension();
      }

      public Dimension getMaximumSize() {
         return FlatScrollBarUI.this.isShowButtons() ? super.getMaximumSize() : new Dimension();
      }
   }

   private class ScrollBarHoverListener extends MouseAdapter {
      private ScrollBarHoverListener() {
      }

      public void mouseExited(MouseEvent e) {
         if (!FlatScrollBarUI.isPressed) {
            FlatScrollBarUI.this.hoverTrack = FlatScrollBarUI.this.hoverThumb = false;
            this.repaint();
         }

      }

      public void mouseMoved(MouseEvent e) {
         if (!FlatScrollBarUI.isPressed) {
            this.update(e.getX(), e.getY());
         }

      }

      public void mousePressed(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e) || this.isAbsolutePositioning(e)) {
            FlatScrollBarUI.isPressed = true;
            this.repaint();
            if (this.isAbsolutePositioning(e)) {
               this.update(e.getX(), e.getY());
            }
         }

      }

      public void mouseReleased(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e) || this.isAbsolutePositioning(e)) {
            FlatScrollBarUI.isPressed = false;
            this.repaint();
         }

         this.update(e.getX(), e.getY());
      }

      private boolean isAbsolutePositioning(MouseEvent e) {
         return FlatScrollBarUI.this.getSupportsAbsolutePositioning() && SwingUtilities.isMiddleMouseButton(e);
      }

      private void update(int x, int y) {
         boolean inTrack = FlatScrollBarUI.this.getTrackBounds().contains(x, y);
         boolean inThumb = FlatScrollBarUI.this.getThumbBounds().contains(x, y);
         if (inTrack != FlatScrollBarUI.this.hoverTrack || inThumb != FlatScrollBarUI.this.hoverThumb) {
            FlatScrollBarUI.this.hoverTrack = inTrack;
            FlatScrollBarUI.this.hoverThumb = inThumb;
            this.repaint();
         }

      }

      private void repaint() {
         if (FlatScrollBarUI.this.scrollbar.isEnabled()) {
            FlatScrollBarUI.this.scrollbar.repaint();
         }

      }

      // $FF: synthetic method
      ScrollBarHoverListener(Object x1) {
         this();
      }
   }
}
