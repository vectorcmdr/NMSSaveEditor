package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.Window.Type;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D.Float;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;

public class FlatUIUtils {
   private static boolean useSharedUIs = true;
   private static final WeakHashMap<LookAndFeel, IdentityHashMap<Object, ComponentUI>> sharedUIinstances = new WeakHashMap();
   private static UIDefaults lightAWTPeerDefaults;
   public static final double MOVE_TO = -1.000000000001E12D;
   public static final double QUAD_TO = -1.000000000002E12D;
   public static final double CURVE_TO = -1.000000000003E12D;
   public static final double ROUNDED = -1.000000000004E12D;
   public static final double CLOSE_PATH = -1.000000000005E12D;

   public static Rectangle addInsets(Rectangle r, Insets insets) {
      return new Rectangle(r.x - insets.left, r.y - insets.top, r.width + insets.left + insets.right, r.height + insets.top + insets.bottom);
   }

   public static Rectangle subtractInsets(Rectangle r, Insets insets) {
      return new Rectangle(r.x + insets.left, r.y + insets.top, r.width - insets.left - insets.right, r.height - insets.top - insets.bottom);
   }

   public static Dimension addInsets(Dimension dim, Insets insets) {
      return new Dimension(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
   }

   public static Insets addInsets(Insets insets1, Insets insets2) {
      if (insets1 == null) {
         return insets2;
      } else {
         return insets2 == null ? insets1 : new Insets(insets1.top + insets2.top, insets1.left + insets2.left, insets1.bottom + insets2.bottom, insets1.right + insets2.right);
      }
   }

   public static void setInsets(Insets dest, Insets src) {
      dest.top = src.top;
      dest.left = src.left;
      dest.bottom = src.bottom;
      dest.right = src.right;
   }

   public static Color getUIColor(String key, int defaultColorRGB) {
      Color color = UIManager.getColor(key);
      return color != null ? color : new Color(defaultColorRGB);
   }

   public static Color getUIColor(String key, Color defaultColor) {
      Color color = UIManager.getColor(key);
      return color != null ? color : defaultColor;
   }

   public static Color getUIColor(String key, String defaultKey) {
      Color color = UIManager.getColor(key);
      return color != null ? color : UIManager.getColor(defaultKey);
   }

   public static boolean getUIBoolean(String key, boolean defaultValue) {
      Object value = UIManager.get(key);
      return value instanceof Boolean ? (Boolean)value : defaultValue;
   }

   public static int getUIInt(String key, int defaultValue) {
      Object value = UIManager.get(key);
      return value instanceof Integer ? (Integer)value : defaultValue;
   }

   public static float getUIFloat(String key, float defaultValue) {
      Object value = UIManager.get(key);
      return value instanceof Number ? ((Number)value).floatValue() : defaultValue;
   }

   public static <T extends Enum<T>> T getUIEnum(String key, Class<T> enumType, T defaultValue) {
      Object value = UIManager.get(key);
      if (value instanceof String) {
         try {
            return Enum.valueOf(enumType, (String)value);
         } catch (IllegalArgumentException var5) {
         }
      }

      return defaultValue;
   }

   public static boolean getBoolean(JComponent c, String systemPropertyKey, String clientPropertyKey, String uiKey, boolean defaultValue) {
      Boolean value = FlatSystemProperties.getBooleanStrict(systemPropertyKey, (Boolean)null);
      if (value != null) {
         return value;
      } else {
         value = FlatClientProperties.clientPropertyBooleanStrict(c, clientPropertyKey, (Boolean)null);
         return value != null ? value : getUIBoolean(uiKey, defaultValue);
      }
   }

   public static boolean isChevron(String arrowType) {
      return !"triangle".equals(arrowType);
   }

   public static Color nonUIResource(Color c) {
      return c instanceof UIResource ? new Color(c.getRGB(), true) : c;
   }

   public static Font nonUIResource(Font font) {
      return font instanceof UIResource ? font.deriveFont(font.getStyle()) : font;
   }

   public static Border nonUIResource(Border border) {
      return (Border)(border instanceof UIResource ? new FlatUIUtils.NonUIResourceBorder(border) : border);
   }

   static Border unwrapNonUIResourceBorder(Border border) {
      return border instanceof FlatUIUtils.NonUIResourceBorder ? ((FlatUIUtils.NonUIResourceBorder)border).delegate : border;
   }

   public static int minimumWidth(JComponent c, int minimumWidth) {
      return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumWidth", minimumWidth);
   }

   public static int minimumHeight(JComponent c, int minimumHeight) {
      return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumHeight", minimumHeight);
   }

   public static boolean isCellEditor(Component c) {
      if (c == null) {
         return false;
      } else {
         Component c2 = c;

         for(int i = 0; i <= 2 && c2 != null; ++i) {
            Container parent = ((Component)c2).getParent();
            if (parent instanceof JTable && ((JTable)parent).getEditorComponent() == c2) {
               return true;
            }

            c2 = parent;
         }

         String name = c.getName();
         if (!"Table.editor".equals(name) && !"Tree.cellEditor".equals(name)) {
            return c instanceof JComponent && Boolean.TRUE.equals(((JComponent)c).getClientProperty("JComboBox.isTableCellEditor"));
         } else {
            return true;
         }
      }
   }

   public static boolean isPermanentFocusOwner(Component c) {
      KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
      if (c instanceof JComponent) {
         Object value = ((JComponent)c).getClientProperty("JComponent.focusOwner");
         if (value instanceof Predicate) {
            return ((Predicate)value).test((JComponent)c) && isInActiveWindow(c, keyboardFocusManager.getActiveWindow());
         }
      }

      if (c.hasFocus()) {
         return true;
      } else {
         return keyboardFocusManager.getPermanentFocusOwner() == c && isInActiveWindow(c, keyboardFocusManager.getActiveWindow());
      }
   }

   static boolean isInActiveWindow(Component c, Window activeWindow) {
      Window window = SwingUtilities.windowForComponent(c);
      return window == activeWindow || window != null && window.getType() == Type.POPUP && window.getOwner() == activeWindow;
   }

   static boolean isAWTPeer(Component c) {
      return SystemInfo.isMacOS ? c.getClass().getName().startsWith("sun.lwawt.LW") : false;
   }

   static boolean needsLightAWTPeer(JComponent c) {
      return isAWTPeer(c) && FlatLaf.isLafDark();
   }

   static void runWithLightAWTPeerUIDefaults(Runnable runnable) {
      if (lightAWTPeerDefaults == null) {
         FlatLaf lightLaf = UIManager.getInt("Component.focusWidth") >= 2 ? new FlatIntelliJLaf() : new FlatLightLaf();
         lightAWTPeerDefaults = ((FlatLaf)lightLaf).getDefaults();
      }

      FlatLaf.runWithUIDefaultsGetter((key) -> {
         Object value = lightAWTPeerDefaults.get(key);
         return value != null ? value : FlatLaf.NULL_VALUE;
      }, runnable);
   }

   public static boolean isFullScreen(Component c) {
      GraphicsConfiguration gc = c.getGraphicsConfiguration();
      GraphicsDevice gd = gc != null ? gc.getDevice() : null;
      Window fullScreenWindow = gd != null ? gd.getFullScreenWindow() : null;
      return fullScreenWindow != null && fullScreenWindow == SwingUtilities.windowForComponent(c);
   }

   public static Boolean isRoundRect(Component c) {
      return c instanceof JComponent ? FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "JComponent.roundRect", (Boolean)null) : null;
   }

   public static float getBorderFocusWidth(JComponent c) {
      FlatBorder border = getOutsideFlatBorder(c);
      return border != null ? UIScale.scale((float)border.getFocusWidth(c)) : 0.0F;
   }

   public static float getBorderLineWidth(JComponent c) {
      FlatBorder border = getOutsideFlatBorder(c);
      return border != null ? UIScale.scale((float)border.getLineWidth(c)) : 0.0F;
   }

   public static int getBorderFocusAndLineWidth(JComponent c) {
      FlatBorder border = getOutsideFlatBorder(c);
      return border != null ? Math.round(UIScale.scale((float)border.getFocusWidth(c)) + UIScale.scale((float)border.getLineWidth(c))) : 0;
   }

   public static float getBorderArc(JComponent c) {
      FlatBorder border = getOutsideFlatBorder(c);
      return border != null ? UIScale.scale((float)border.getArc(c)) : 0.0F;
   }

   public static boolean hasRoundBorder(JComponent c) {
      return getBorderArc(c) >= (float)c.getHeight();
   }

   public static FlatBorder getOutsideFlatBorder(JComponent c) {
      Border border;
      for(border = c.getBorder(); !(border instanceof FlatBorder); border = ((CompoundBorder)border).getOutsideBorder()) {
         if (!(border instanceof CompoundBorder)) {
            return null;
         }
      }

      return (FlatBorder)border;
   }

   public static Object[] setRenderingHints(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      Object[] oldRenderingHints = new Object[]{g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL)};
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
      return oldRenderingHints;
   }

   public static void resetRenderingHints(Graphics g, Object[] oldRenderingHints) {
      Graphics2D g2 = (Graphics2D)g;
      if (oldRenderingHints[0] != null) {
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldRenderingHints[0]);
      }

      if (oldRenderingHints[1] != null) {
         g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldRenderingHints[1]);
      }

   }

   public static void runWithoutRenderingHints(Graphics g, Object[] oldRenderingHints, Runnable runnable) {
      if (oldRenderingHints == null) {
         runnable.run();
      } else {
         Graphics2D g2 = (Graphics2D)g;
         Object[] oldRenderingHints2 = new Object[]{g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL)};
         resetRenderingHints(g2, oldRenderingHints);
         runnable.run();
         resetRenderingHints(g2, oldRenderingHints2);
      }
   }

   public static Color deriveColor(Color color, Color baseColor) {
      return color instanceof DerivedColor ? ((DerivedColor)color).derive(baseColor) : color;
   }

   public static void paintComponentBackground(Graphics2D g, int x, int y, int width, int height, float focusWidth, float arc) {
      paintOutlinedComponent(g, x, y, width, height, focusWidth, 0.0F, 0.0F, 0.0F, arc, (Paint)null, (Paint)null, g.getPaint());
   }

   public static void paintOutlinedComponent(Graphics2D g, int x, int y, int width, int height, float focusWidth, float focusWidthFraction, float focusInnerWidth, float borderWidth, float arc, Paint focusColor, Paint borderColor, Paint background) {
      double systemScaleFactor = UIScale.getSystemScaleFactor(g);
      if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
         HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> {
            paintOutlinedComponentImpl(g2d, x2, y2, width2, height2, (float)((double)focusWidth * scaleFactor), focusWidthFraction, (float)((double)focusInnerWidth * scaleFactor), (float)((double)borderWidth * scaleFactor), (float)((double)arc * scaleFactor), focusColor, borderColor, background);
         });
      } else {
         paintOutlinedComponentImpl(g, x, y, width, height, focusWidth, focusWidthFraction, focusInnerWidth, borderWidth, arc, focusColor, borderColor, background);
      }
   }

   private static void paintOutlinedComponentImpl(Graphics2D g, int x, int y, int width, int height, float focusWidth, float focusWidthFraction, float focusInnerWidth, float borderWidth, float arc, Paint focusColor, Paint borderColor, Paint background) {
      float x1 = (float)x + focusWidth;
      float y1 = (float)y + focusWidth;
      float w1 = (float)width - focusWidth * 2.0F;
      float h1 = (float)height - focusWidth * 2.0F;
      if (background != null) {
         g.setPaint(background);
         g.fill(createComponentRectangle(x1, y1, w1, h1, arc));
      }

      if (borderColor != null && borderColor.equals(focusColor)) {
         borderColor = null;
         focusInnerWidth = Math.max(focusInnerWidth, borderWidth);
      }

      float paintedFocusWidth = focusWidth * focusWidthFraction + focusInnerWidth;
      if (focusColor != null && paintedFocusWidth != 0.0F) {
         float inset = focusWidth - focusWidth * focusWidthFraction;
         float x2 = (float)x + inset;
         float y2 = (float)y + inset;
         float w2 = (float)width - inset * 2.0F;
         float h2 = (float)height - inset * 2.0F;
         float outerArc = arc + focusWidth * 2.0F;
         float innerArc = arc - focusInnerWidth * 2.0F;
         if (focusWidth > 0.0F && arc > 0.0F && arc < (float)UIScale.scale(10)) {
            outerArc -= UIScale.scale(2.0F);
         }

         if (focusWidthFraction != 1.0F) {
            outerArc = arc + (outerArc - arc) * focusWidthFraction;
         }

         g.setPaint(focusColor);
         paintOutline(g, x2, y2, w2, h2, paintedFocusWidth, outerArc, innerArc);
      }

      if (borderColor != null && borderWidth != 0.0F) {
         g.setPaint(borderColor);
         paintOutline(g, x1, y1, w1, h1, borderWidth, arc);
      }

   }

   public static void paintOutline(Graphics2D g, float x, float y, float w, float h, float lineWidth, float arc) {
      paintOutline(g, x, y, w, h, lineWidth, arc, arc - lineWidth * 2.0F);
   }

   public static void paintOutline(Graphics2D g, float x, float y, float w, float h, float lineWidth, float arc, float innerArc) {
      if (lineWidth != 0.0F && !(w <= 0.0F) && !(h <= 0.0F)) {
         float t2x = lineWidth * 2.0F;
         Path2D border = new Float(0);
         border.append(createComponentRectangle(x, y, w, h, arc), false);
         border.append(createComponentRectangle(x + lineWidth, y + lineWidth, w - t2x, h - t2x, innerArc), false);
         g.fill(border);
      }
   }

   public static Shape createComponentRectangle(float x, float y, float w, float h, float arc) {
      if (arc <= 0.0F) {
         return new java.awt.geom.Rectangle2D.Float(x, y, w, h);
      } else if (w == h && arc >= w) {
         return new java.awt.geom.Ellipse2D.Float(x, y, w, h);
      } else {
         arc = Math.min(arc, Math.min(w, h));
         return new java.awt.geom.RoundRectangle2D.Float(x, y, w, h, arc, arc);
      }
   }

   static void paintFilledRectangle(Graphics g, Color color, float x, float y, float w, float h) {
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         setRenderingHints(g2);
         g2.setColor(color);
         g2.fill(new java.awt.geom.Rectangle2D.Float(x, y, w, h));
      } finally {
         g2.dispose();
      }

   }

   public static void paintSelection(Graphics2D g, int x, int y, int width, int height, Insets insets, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight, int flags) {
      if (insets != null) {
         x += insets.left;
         y += insets.top;
         width -= insets.left + insets.right;
         height -= insets.top + insets.bottom;
      }

      if (!(arcTopLeft > 0.0F) && !(arcTopRight > 0.0F) && !(arcBottomLeft > 0.0F) && !(arcBottomRight > 0.0F)) {
         g.fillRect(x, y, width, height);
      } else {
         double systemScaleFactor = UIScale.getSystemScaleFactor(g);
         if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
            HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> {
               paintRoundedSelectionImpl(g2d, x2, y2, width2, height2, (float)((double)arcTopLeft * scaleFactor), (float)((double)arcTopRight * scaleFactor), (float)((double)arcBottomLeft * scaleFactor), (float)((double)arcBottomRight * scaleFactor));
            });
         } else {
            paintRoundedSelectionImpl(g, x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight);
         }
      }

   }

   private static void paintRoundedSelectionImpl(Graphics2D g, int x, int y, int width, int height, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
      Object[] oldRenderingHints = setRenderingHints(g);
      g.fill(createRoundRectanglePath((float)x, (float)y, (float)width, (float)height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight));
      resetRenderingHints(g, oldRenderingHints);
   }

   public static void paintGrip(Graphics g, int x, int y, int width, int height, boolean horizontal, int dotCount, int dotSize, int gap, boolean centerPrecise) {
      dotSize = UIScale.scale(dotSize);
      gap = UIScale.scale(gap);
      int gripSize = dotSize * dotCount + gap * (dotCount - 1);
      float gx;
      float gy;
      if (horizontal) {
         gx = (float)(x + Math.round((float)(width - gripSize) / 2.0F));
         gy = (float)y + (float)(height - dotSize) / 2.0F;
         if (!centerPrecise) {
            gy = (float)Math.round(gy);
         }
      } else {
         gx = (float)x + (float)(width - dotSize) / 2.0F;
         gy = (float)(y + Math.round((float)(height - gripSize) / 2.0F));
         if (!centerPrecise) {
            gx = (float)Math.round(gx);
         }
      }

      for(int i = 0; i < dotCount; ++i) {
         ((Graphics2D)g).fill(new java.awt.geom.Ellipse2D.Float(gx, gy, (float)dotSize, (float)dotSize));
         if (horizontal) {
            gx += (float)(dotSize + gap);
         } else {
            gy += (float)(dotSize + gap);
         }
      }

   }

   public static void paintParentBackground(Graphics g, JComponent c) {
      Color background = getParentBackground(c);
      if (background != null) {
         g.setColor(background);
         g.fillRect(0, 0, c.getWidth(), c.getHeight());
      }

   }

   public static Color getParentBackground(JComponent c) {
      Container parent = findOpaqueParent(c);
      Color background = parent != null ? parent.getBackground() : null;
      if (background != null) {
         return background;
      } else if (!isAWTPeer(c)) {
         return UIManager.getColor("Panel.background");
      } else {
         return (Color)(!(c instanceof JTextField) && !(c instanceof JScrollPane) && c.getBackground() != null ? c.getBackground() : SystemColor.window);
      }
   }

   private static Container findOpaqueParent(Container c) {
      while(true) {
         if ((c = c.getParent()) != null) {
            if (!c.isOpaque()) {
               continue;
            }

            return c;
         }

         return null;
      }
   }

   public static Path2D createRectangle(float x, float y, float width, float height, float lineWidth) {
      Path2D path = new Float(0);
      path.append(new java.awt.geom.Rectangle2D.Float(x, y, width, height), false);
      path.append(new java.awt.geom.Rectangle2D.Float(x + lineWidth, y + lineWidth, width - lineWidth * 2.0F, height - lineWidth * 2.0F), false);
      return path;
   }

   public static Path2D createRoundRectangle(float x, float y, float width, float height, float lineWidth, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
      Path2D path = new Float(0);
      path.append(createRoundRectanglePath(x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight), false);
      path.append(createRoundRectanglePath(x + lineWidth, y + lineWidth, width - lineWidth * 2.0F, height - lineWidth * 2.0F, arcTopLeft - lineWidth, arcTopRight - lineWidth, arcBottomLeft - lineWidth, arcBottomRight - lineWidth), false);
      return path;
   }

   public static Shape createRoundRectanglePath(float x, float y, float width, float height, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
      if (arcTopLeft <= 0.0F && arcTopRight <= 0.0F && arcBottomLeft <= 0.0F && arcBottomRight <= 0.0F) {
         return new java.awt.geom.Rectangle2D.Float(x, y, width, height);
      } else {
         float maxArc = Math.min(width, height) / 2.0F;
         arcTopLeft = arcTopLeft > 0.0F ? Math.min(arcTopLeft, maxArc) : 0.0F;
         arcTopRight = arcTopRight > 0.0F ? Math.min(arcTopRight, maxArc) : 0.0F;
         arcBottomLeft = arcBottomLeft > 0.0F ? Math.min(arcBottomLeft, maxArc) : 0.0F;
         arcBottomRight = arcBottomRight > 0.0F ? Math.min(arcBottomRight, maxArc) : 0.0F;
         float x2 = x + width;
         float y2 = y + height;
         double c = 0.5522847498307933D;
         double ci = 1.0D - c;
         double ciTopLeft = (double)arcTopLeft * ci;
         double ciTopRight = (double)arcTopRight * ci;
         double ciBottomLeft = (double)arcBottomLeft * ci;
         double ciBottomRight = (double)arcBottomRight * ci;
         Path2D rect = new Float(1, 16);
         rect.moveTo((double)(x2 - arcTopRight), (double)y);
         rect.curveTo((double)x2 - ciTopRight, (double)y, (double)x2, (double)y + ciTopRight, (double)x2, (double)(y + arcTopRight));
         rect.lineTo((double)x2, (double)(y2 - arcBottomRight));
         rect.curveTo((double)x2, (double)y2 - ciBottomRight, (double)x2 - ciBottomRight, (double)y2, (double)(x2 - arcBottomRight), (double)y2);
         rect.lineTo((double)(x + arcBottomLeft), (double)y2);
         rect.curveTo((double)x + ciBottomLeft, (double)y2, (double)x, (double)y2 - ciBottomLeft, (double)x, (double)(y2 - arcBottomLeft));
         rect.lineTo((double)x, (double)(y + arcTopLeft));
         rect.curveTo((double)x, (double)y + ciTopLeft, (double)x + ciTopLeft, (double)y, (double)(x + arcTopLeft), (double)y);
         rect.closePath();
         return rect;
      }
   }

   public static Shape createRoundTrianglePath(float x1, float y1, float x2, float y2, float x3, float y3, float arc) {
      double averageSideLength = (distance((double)x1, (double)y1, (double)x2, (double)y2) + distance((double)x2, (double)y2, (double)x3, (double)y3) + distance((double)x3, (double)y3, (double)x1, (double)y1)) / 3.0D;
      double t1 = 1.0D / averageSideLength * (double)arc;
      double t2 = 1.0D - t1;
      return createPath(lerp((double)x3, (double)x1, t2), lerp((double)y3, (double)y1, t2), -1.000000000002E12D, (double)x1, (double)y1, lerp((double)x1, (double)x2, t1), lerp((double)y1, (double)y2, t1), lerp((double)x1, (double)x2, t2), lerp((double)y1, (double)y2, t2), -1.000000000002E12D, (double)x2, (double)y2, lerp((double)x2, (double)x3, t1), lerp((double)y2, (double)y3, t1), lerp((double)x2, (double)x3, t2), lerp((double)y2, (double)y3, t2), -1.000000000002E12D, (double)x3, (double)y3, lerp((double)x3, (double)x1, t1), lerp((double)y3, (double)y1, t1));
   }

   public static void paintArrow(Graphics2D g, int x, int y, int width, int height, int direction, boolean chevron, int arrowSize, float arrowThickness, float xOffset, float yOffset) {
      float aw = (float)UIScale.scale(arrowSize + (chevron ? -1 : 0));
      float ah = chevron ? aw / 2.0F : (float)UIScale.scale(arrowSize / 2 + 1);
      boolean vert = direction == 1 || direction == 5;
      if (!vert) {
         float temp = aw;
         aw = ah;
         ah = temp;
      }

      int extra = chevron ? 1 : 0;
      float ox = ((float)width - (aw + (float)extra)) / 2.0F + UIScale.scale(xOffset);
      float oy = ((float)height - (ah + (float)extra)) / 2.0F + UIScale.scale(yOffset);
      float ax = (float)x + (direction == 7 ? (float)(-Math.round(-(ox + aw))) - aw : (float)Math.round(ox));
      float ay = (float)y + (direction == 1 ? (float)(-Math.round(-(oy + ah))) - ah : (float)Math.round(oy));
      g.translate((double)ax, (double)ay);
      Shape arrowShape = createArrowShape(direction, chevron, aw, ah);
      if (chevron) {
         Stroke oldStroke = g.getStroke();
         g.setStroke(new BasicStroke(UIScale.scale(arrowThickness)));
         drawShapePure(g, arrowShape);
         g.setStroke(oldStroke);
      } else {
         g.fill(arrowShape);
      }

      g.translate((double)(-ax), (double)(-ay));
   }

   public static Shape createArrowShape(int direction, boolean chevron, float w, float h) {
      switch(direction) {
      case 1:
         return createPath(!chevron, 0.0D, (double)h, (double)(w / 2.0F), 0.0D, (double)w, (double)h);
      case 2:
      case 4:
      case 6:
      default:
         return new Float();
      case 3:
         return createPath(!chevron, 0.0D, 0.0D, (double)w, (double)(h / 2.0F), 0.0D, (double)h);
      case 5:
         return createPath(!chevron, 0.0D, 0.0D, (double)(w / 2.0F), (double)h, (double)w, 0.0D);
      case 7:
         return createPath(!chevron, (double)w, 0.0D, 0.0D, (double)(h / 2.0F), (double)w, (double)h);
      }
   }

   public static Path2D createPath(double... points) {
      return createPath(true, points);
   }

   public static Path2D createPath(boolean close, double... points) {
      Path2D path = new Float(1, points.length / 2 + (close ? 1 : 0));
      path.moveTo(points[0], points[1]);
      int i = 2;

      while(true) {
         while(i < points.length) {
            double p = points[i];
            if (p == -1.000000000001E12D) {
               path.moveTo(points[i + 1], points[i + 2]);
               i += 3;
            } else if (p == -1.000000000002E12D) {
               path.quadTo(points[i + 1], points[i + 2], points[i + 3], points[i + 4]);
               i += 5;
            } else if (p == -1.000000000003E12D) {
               path.curveTo(points[i + 1], points[i + 2], points[i + 3], points[i + 4], points[i + 5], points[i + 6]);
               i += 7;
            } else if (p != -1.000000000004E12D) {
               if (p == -1.000000000005E12D) {
                  path.closePath();
                  ++i;
               } else {
                  path.lineTo(p, points[i + 1]);
                  i += 2;
               }
            } else {
               double x = points[i + 1];
               double y = points[i + 2];
               double arc = points[i + 3];
               int ip2 = i + 4;
               if (points[ip2] == -1.000000000002E12D || points[ip2] == -1.000000000004E12D) {
                  ++ip2;
               }

               Point2D p1 = path.getCurrentPoint();
               double x1 = p1.getX();
               double y1 = p1.getY();
               double x2 = points[ip2];
               double y2 = points[ip2 + 1];
               double d1 = distance(x, y, x1, y1);
               double d2 = distance(x, y, x2, y2);
               double t1 = 1.0D - 1.0D / d1 * arc;
               double t2 = 1.0D / d2 * arc;
               path.lineTo(lerp(x1, x, t1), lerp(y1, y, t1));
               path.quadTo(x, y, lerp(x, x2, t2), lerp(y, y2, t2));
               i += 4;
            }
         }

         if (close) {
            path.closePath();
         }

         return path;
      }
   }

   private static double lerp(double v1, double v2, double t) {
      return v1 * (1.0D - t) + v2 * t;
   }

   private static double distance(double x1, double y1, double x2, double y2) {
      double dx = x2 - x1;
      double dy = y2 - y1;
      return Math.sqrt(dx * dx + dy * dy);
   }

   public static void drawShapePure(Graphics2D g, Shape shape) {
      Object oldStrokeControl = g.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.translate(0.5D, 0.5D);
      g.draw(shape);
      g.translate(-0.5D, -0.5D);
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldStrokeControl);
   }

   public static void drawString(JComponent c, Graphics g, String text, int x, int y) {
      HiDPIUtils.drawStringWithYCorrection(c, (Graphics2D)g, text, x, y);
   }

   public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
      if (underlinedIndex >= 0 && UIScale.getUserScaleFactor() > 1.0F) {
         g = new Graphics2DProxy((Graphics2D)g) {
            public void fillRect(int x, int y, int width, int height) {
               if (height == 1) {
                  height = Math.round(UIScale.scale(0.9F));
                  y += height - 1;
               }

               super.fillRect(x, y, width, height);
            }
         };
      }

      HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(c, (Graphics2D)g, text, underlinedIndex, x, y);
   }

   public static boolean hasOpaqueBeenExplicitlySet(JComponent c) {
      boolean oldOpaque = c.isOpaque();
      LookAndFeel.installProperty(c, "opaque", !oldOpaque);
      boolean explicitlySet = c.isOpaque() == oldOpaque;
      LookAndFeel.installProperty(c, "opaque", oldOpaque);
      return explicitlySet;
   }

   public static boolean isUseSharedUIs() {
      return useSharedUIs;
   }

   public static boolean setUseSharedUIs(boolean useSharedUIs) {
      boolean old = FlatUIUtils.useSharedUIs;
      FlatUIUtils.useSharedUIs = useSharedUIs;
      return old;
   }

   public static ComponentUI createSharedUI(Object key, Supplier<ComponentUI> newInstanceSupplier) {
      return !useSharedUIs ? (ComponentUI)newInstanceSupplier.get() : (ComponentUI)((IdentityHashMap)sharedUIinstances.computeIfAbsent(UIManager.getLookAndFeel(), (k) -> {
         return new IdentityHashMap();
      })).computeIfAbsent(key, (k) -> {
         return (ComponentUI)newInstanceSupplier.get();
      });
   }

   public static boolean canUseSharedUI(JComponent c) {
      return !FlatStylingSupport.hasStyleProperty(c);
   }

   private static class NonUIResourceBorder implements Border {
      private final Border delegate;

      NonUIResourceBorder(Border delegate) {
         this.delegate = delegate;
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         this.delegate.paintBorder(c, g, x, y, width, height);
      }

      public Insets getBorderInsets(Component c) {
         return this.delegate.getBorderInsets(c);
      }

      public boolean isBorderOpaque() {
         return this.delegate.isBorderOpaque();
      }
   }

   public static class RepaintFocusListener implements FocusListener {
      private final Component repaintComponent;
      private final Predicate<Component> repaintCondition;

      public RepaintFocusListener(Component repaintComponent, Predicate<Component> repaintCondition) {
         this.repaintComponent = repaintComponent;
         this.repaintCondition = repaintCondition;
      }

      public void focusGained(FocusEvent e) {
         if (this.repaintCondition == null || this.repaintCondition.test(this.repaintComponent)) {
            this.repaintComponent.repaint();
         }

      }

      public void focusLost(FocusEvent e) {
         if (this.repaintCondition == null || this.repaintCondition.test(this.repaintComponent)) {
            this.repaintComponent.repaint();
         }

      }
   }
}
