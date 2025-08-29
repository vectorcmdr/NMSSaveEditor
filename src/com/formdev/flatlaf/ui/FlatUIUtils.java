/*      */ package com.formdev.flatlaf.ui;
/*      */ 
/*      */ import com.formdev.flatlaf.FlatClientProperties;
/*      */ import com.formdev.flatlaf.FlatIntelliJLaf;
/*      */ import com.formdev.flatlaf.FlatLaf;
/*      */ import com.formdev.flatlaf.FlatLightLaf;
/*      */ import com.formdev.flatlaf.FlatSystemProperties;
/*      */ import com.formdev.flatlaf.util.DerivedColor;
/*      */ import com.formdev.flatlaf.util.Graphics2DProxy;
/*      */ import com.formdev.flatlaf.util.HiDPIUtils;
/*      */ import com.formdev.flatlaf.util.SystemInfo;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.Insets;
/*      */ import java.awt.KeyboardFocusManager;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.SystemColor;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Path2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.RoundRectangle2D;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIDefaults;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.CompoundBorder;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FlatUIUtils
/*      */ {
/*      */   private static boolean useSharedUIs = true;
/*   81 */   private static final WeakHashMap<LookAndFeel, IdentityHashMap<Object, ComponentUI>> sharedUIinstances = new WeakHashMap<>(); private static UIDefaults lightAWTPeerDefaults;
/*      */   
/*      */   public static Rectangle addInsets(Rectangle r, Insets insets) {
/*   84 */     return new Rectangle(r.x - insets.left, r.y - insets.top, r.width + insets.left + insets.right, r.height + insets.top + insets.bottom);
/*      */   }
/*      */   public static final double MOVE_TO = -1.000000000001E12D; public static final double QUAD_TO = -1.000000000002E12D;
/*      */   public static final double CURVE_TO = -1.000000000003E12D;
/*      */   public static final double ROUNDED = -1.000000000004E12D;
/*      */   public static final double CLOSE_PATH = -1.000000000005E12D;
/*      */   
/*      */   public static Rectangle subtractInsets(Rectangle r, Insets insets) {
/*   92 */     return new Rectangle(r.x + insets.left, r.y + insets.top, r.width - insets.left - insets.right, r.height - insets.top - insets.bottom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Dimension addInsets(Dimension dim, Insets insets) {
/*  100 */     return new Dimension(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Insets addInsets(Insets insets1, Insets insets2) {
/*  106 */     if (insets1 == null)
/*  107 */       return insets2; 
/*  108 */     if (insets2 == null) {
/*  109 */       return insets1;
/*      */     }
/*  111 */     return new Insets(insets1.top + insets2.top, insets1.left + insets2.left, insets1.bottom + insets2.bottom, insets1.right + insets2.right);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setInsets(Insets dest, Insets src) {
/*  119 */     dest.top = src.top;
/*  120 */     dest.left = src.left;
/*  121 */     dest.bottom = src.bottom;
/*  122 */     dest.right = src.right;
/*      */   }
/*      */   
/*      */   public static Color getUIColor(String key, int defaultColorRGB) {
/*  126 */     Color color = UIManager.getColor(key);
/*  127 */     return (color != null) ? color : new Color(defaultColorRGB);
/*      */   }
/*      */   
/*      */   public static Color getUIColor(String key, Color defaultColor) {
/*  131 */     Color color = UIManager.getColor(key);
/*  132 */     return (color != null) ? color : defaultColor;
/*      */   }
/*      */   
/*      */   public static Color getUIColor(String key, String defaultKey) {
/*  136 */     Color color = UIManager.getColor(key);
/*  137 */     return (color != null) ? color : UIManager.getColor(defaultKey);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean getUIBoolean(String key, boolean defaultValue) {
/*  142 */     Object value = UIManager.get(key);
/*  143 */     return (value instanceof Boolean) ? ((Boolean)value).booleanValue() : defaultValue;
/*      */   }
/*      */   
/*      */   public static int getUIInt(String key, int defaultValue) {
/*  147 */     Object value = UIManager.get(key);
/*  148 */     return (value instanceof Integer) ? ((Integer)value).intValue() : defaultValue;
/*      */   }
/*      */   
/*      */   public static float getUIFloat(String key, float defaultValue) {
/*  152 */     Object value = UIManager.get(key);
/*  153 */     return (value instanceof Number) ? ((Number)value).floatValue() : defaultValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends Enum<T>> T getUIEnum(String key, Class<T> enumType, T defaultValue) {
/*  158 */     Object value = UIManager.get(key);
/*  159 */     if (value instanceof String) {
/*      */       try {
/*  161 */         return Enum.valueOf(enumType, (String)value);
/*  162 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */     }
/*      */ 
/*      */     
/*  166 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getBoolean(JComponent c, String systemPropertyKey, String clientPropertyKey, String uiKey, boolean defaultValue) {
/*  174 */     Boolean value = FlatSystemProperties.getBooleanStrict(systemPropertyKey, null);
/*  175 */     if (value != null) {
/*  176 */       return value.booleanValue();
/*      */     }
/*      */     
/*  179 */     value = FlatClientProperties.clientPropertyBooleanStrict(c, clientPropertyKey, null);
/*  180 */     if (value != null) {
/*  181 */       return value.booleanValue();
/*      */     }
/*  183 */     return getUIBoolean(uiKey, defaultValue);
/*      */   }
/*      */   
/*      */   public static boolean isChevron(String arrowType) {
/*  187 */     return !"triangle".equals(arrowType);
/*      */   }
/*      */   
/*      */   public static Color nonUIResource(Color c) {
/*  191 */     return (c instanceof javax.swing.plaf.UIResource) ? new Color(c.getRGB(), true) : c;
/*      */   }
/*      */   
/*      */   public static Font nonUIResource(Font font) {
/*  195 */     return (font instanceof javax.swing.plaf.UIResource) ? font.deriveFont(font.getStyle()) : font;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Border nonUIResource(Border border) {
/*  200 */     return (border instanceof javax.swing.plaf.UIResource) ? new NonUIResourceBorder(border) : border;
/*      */   }
/*      */   
/*      */   static Border unwrapNonUIResourceBorder(Border border) {
/*  204 */     return (border instanceof NonUIResourceBorder) ? ((NonUIResourceBorder)border).delegate : border;
/*      */   }
/*      */   
/*      */   public static int minimumWidth(JComponent c, int minimumWidth) {
/*  208 */     return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumWidth", minimumWidth);
/*      */   }
/*      */   
/*      */   public static int minimumHeight(JComponent c, int minimumHeight) {
/*  212 */     return FlatClientProperties.clientPropertyInt(c, "JComponent.minimumHeight", minimumHeight);
/*      */   }
/*      */   
/*      */   public static boolean isCellEditor(Component c) {
/*  216 */     if (c == null) {
/*  217 */       return false;
/*      */     }
/*      */     
/*  220 */     Component c2 = c;
/*  221 */     for (int i = 0; i <= 2 && c2 != null; i++) {
/*  222 */       Container parent = c2.getParent();
/*  223 */       if (parent instanceof JTable && ((JTable)parent).getEditorComponent() == c2) {
/*  224 */         return true;
/*      */       }
/*  226 */       c2 = parent;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  232 */     String name = c.getName();
/*  233 */     if ("Table.editor".equals(name) || "Tree.cellEditor".equals(name)) {
/*  234 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  238 */     return (c instanceof JComponent && Boolean.TRUE.equals(((JComponent)c).getClientProperty("JComboBox.isTableCellEditor")));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPermanentFocusOwner(Component c) {
/*  248 */     KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/*      */     
/*  250 */     if (c instanceof JComponent) {
/*  251 */       Object value = ((JComponent)c).getClientProperty("JComponent.focusOwner");
/*  252 */       if (value instanceof Predicate) {
/*  253 */         return (((Predicate<JComponent>)value).test((JComponent)c) && 
/*  254 */           isInActiveWindow(c, keyboardFocusManager.getActiveWindow()));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  260 */     if (c.hasFocus()) {
/*  261 */       return true;
/*      */     }
/*  263 */     return (keyboardFocusManager.getPermanentFocusOwner() == c && 
/*  264 */       isInActiveWindow(c, keyboardFocusManager.getActiveWindow()));
/*      */   }
/*      */   
/*      */   static boolean isInActiveWindow(Component c, Window activeWindow) {
/*  268 */     Window window = SwingUtilities.windowForComponent(c);
/*  269 */     return (window == activeWindow || (window != null && window
/*  270 */       .getType() == Window.Type.POPUP && window.getOwner() == activeWindow));
/*      */   }
/*      */ 
/*      */   
/*      */   static boolean isAWTPeer(Component c) {
/*  275 */     if (SystemInfo.isMacOS)
/*  276 */       return c.getClass().getName().startsWith("sun.lwawt.LW"); 
/*  277 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean needsLightAWTPeer(JComponent c) {
/*  286 */     return (isAWTPeer(c) && FlatLaf.isLafDark());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void runWithLightAWTPeerUIDefaults(Runnable runnable) {
/*  292 */     if (lightAWTPeerDefaults == null) {
/*      */ 
/*      */       
/*  295 */       FlatLaf lightLaf = (UIManager.getInt("Component.focusWidth") >= 2) ? (FlatLaf)new FlatIntelliJLaf() : (FlatLaf)new FlatLightLaf();
/*  296 */       lightAWTPeerDefaults = lightLaf.getDefaults();
/*      */     } 
/*      */     
/*  299 */     FlatLaf.runWithUIDefaultsGetter(key -> { Object value = lightAWTPeerDefaults.get(key); return (value != null) ? value : FlatLaf.NULL_VALUE; }runnable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFullScreen(Component c) {
/*  309 */     GraphicsConfiguration gc = c.getGraphicsConfiguration();
/*  310 */     GraphicsDevice gd = (gc != null) ? gc.getDevice() : null;
/*  311 */     Window fullScreenWindow = (gd != null) ? gd.getFullScreenWindow() : null;
/*  312 */     return (fullScreenWindow != null && fullScreenWindow == SwingUtilities.windowForComponent(c));
/*      */   }
/*      */   
/*      */   public static Boolean isRoundRect(Component c) {
/*  316 */     return (c instanceof JComponent) ? 
/*  317 */       FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "JComponent.roundRect", null) : 
/*      */       
/*  319 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getBorderFocusWidth(JComponent c) {
/*  326 */     FlatBorder border = getOutsideFlatBorder(c);
/*  327 */     return (border != null) ? 
/*  328 */       UIScale.scale(border.getFocusWidth(c)) : 
/*  329 */       0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getBorderLineWidth(JComponent c) {
/*  338 */     FlatBorder border = getOutsideFlatBorder(c);
/*  339 */     return (border != null) ? 
/*  340 */       UIScale.scale(border.getLineWidth(c)) : 
/*  341 */       0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getBorderFocusAndLineWidth(JComponent c) {
/*  351 */     FlatBorder border = getOutsideFlatBorder(c);
/*  352 */     return (border != null) ? 
/*  353 */       Math.round(UIScale.scale(border.getFocusWidth(c)) + 
/*  354 */         UIScale.scale(border.getLineWidth(c))) : 
/*  355 */       0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getBorderArc(JComponent c) {
/*  362 */     FlatBorder border = getOutsideFlatBorder(c);
/*  363 */     return (border != null) ? 
/*  364 */       UIScale.scale(border.getArc(c)) : 
/*  365 */       0.0F;
/*      */   }
/*      */   
/*      */   public static boolean hasRoundBorder(JComponent c) {
/*  369 */     return (getBorderArc(c) >= c.getHeight());
/*      */   }
/*      */   
/*      */   public static FlatBorder getOutsideFlatBorder(JComponent c) {
/*  373 */     Border border = c.getBorder();
/*      */     while (true) {
/*  375 */       if (border instanceof FlatBorder)
/*  376 */         return (FlatBorder)border; 
/*  377 */       if (border instanceof CompoundBorder) {
/*  378 */         border = ((CompoundBorder)border).getOutsideBorder(); continue;
/*      */       }  break;
/*  380 */     }  return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object[] setRenderingHints(Graphics g) {
/*  388 */     Graphics2D g2 = (Graphics2D)g;
/*      */ 
/*      */     
/*  391 */     Object[] oldRenderingHints = { g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL) };
/*      */ 
/*      */     
/*  394 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  395 */     g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
/*      */     
/*  397 */     return oldRenderingHints;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetRenderingHints(Graphics g, Object[] oldRenderingHints) {
/*  404 */     Graphics2D g2 = (Graphics2D)g;
/*  405 */     if (oldRenderingHints[0] != null)
/*  406 */       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldRenderingHints[0]); 
/*  407 */     if (oldRenderingHints[1] != null) {
/*  408 */       g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldRenderingHints[1]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void runWithoutRenderingHints(Graphics g, Object[] oldRenderingHints, Runnable runnable) {
/*  425 */     if (oldRenderingHints == null) {
/*  426 */       runnable.run();
/*      */       
/*      */       return;
/*      */     } 
/*  430 */     Graphics2D g2 = (Graphics2D)g;
/*      */ 
/*      */     
/*  433 */     Object[] oldRenderingHints2 = { g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING), g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL) };
/*      */ 
/*      */     
/*  436 */     resetRenderingHints(g2, oldRenderingHints);
/*  437 */     runnable.run();
/*  438 */     resetRenderingHints(g2, oldRenderingHints2);
/*      */   }
/*      */   
/*      */   public static Color deriveColor(Color color, Color baseColor) {
/*  442 */     return (color instanceof DerivedColor) ? (
/*  443 */       (DerivedColor)color).derive(baseColor) : 
/*  444 */       color;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintComponentBackground(Graphics2D g, int x, int y, int width, int height, float focusWidth, float arc) {
/*  459 */     paintOutlinedComponent(g, x, y, width, height, focusWidth, 0.0F, 0.0F, 0.0F, arc, null, null, g.getPaint());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintOutlinedComponent(Graphics2D g, int x, int y, int width, int height, float focusWidth, float focusWidthFraction, float focusInnerWidth, float borderWidth, float arc, Paint focusColor, Paint borderColor, Paint background) {
/*  522 */     double systemScaleFactor = UIScale.getSystemScaleFactor(g);
/*  523 */     if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
/*      */       
/*  525 */       HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> paintOutlinedComponentImpl(g2d, x2, y2, width2, height2, (float)(focusWidth * scaleFactor), focusWidthFraction, (float)(focusInnerWidth * scaleFactor), (float)(borderWidth * scaleFactor), (float)(arc * scaleFactor), focusColor, borderColor, background));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  535 */     paintOutlinedComponentImpl(g, x, y, width, height, focusWidth, focusWidthFraction, focusInnerWidth, borderWidth, arc, focusColor, borderColor, background);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void paintOutlinedComponentImpl(Graphics2D g, int x, int y, int width, int height, float focusWidth, float focusWidthFraction, float focusInnerWidth, float borderWidth, float arc, Paint focusColor, Paint borderColor, Paint background) {
/*  544 */     float x1 = x + focusWidth;
/*  545 */     float y1 = y + focusWidth;
/*  546 */     float w1 = width - focusWidth * 2.0F;
/*  547 */     float h1 = height - focusWidth * 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  552 */     if (background != null) {
/*  553 */       g.setPaint(background);
/*  554 */       g.fill(createComponentRectangle(x1, y1, w1, h1, arc));
/*      */     } 
/*      */ 
/*      */     
/*  558 */     if (borderColor != null && borderColor.equals(focusColor)) {
/*  559 */       borderColor = null;
/*  560 */       focusInnerWidth = Math.max(focusInnerWidth, borderWidth);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  568 */     float paintedFocusWidth = focusWidth * focusWidthFraction + focusInnerWidth;
/*  569 */     if (focusColor != null && paintedFocusWidth != 0.0F) {
/*      */       
/*  571 */       float inset = focusWidth - focusWidth * focusWidthFraction;
/*  572 */       float x2 = x + inset;
/*  573 */       float y2 = y + inset;
/*  574 */       float w2 = width - inset * 2.0F;
/*  575 */       float h2 = height - inset * 2.0F;
/*      */       
/*  577 */       float outerArc = arc + focusWidth * 2.0F;
/*  578 */       float innerArc = arc - focusInnerWidth * 2.0F;
/*      */ 
/*      */       
/*  581 */       if (focusWidth > 0.0F && arc > 0.0F && arc < UIScale.scale(10)) {
/*  582 */         outerArc -= UIScale.scale(2.0F);
/*      */       }
/*      */       
/*  585 */       if (focusWidthFraction != 1.0F) {
/*  586 */         outerArc = arc + (outerArc - arc) * focusWidthFraction;
/*      */       }
/*  588 */       g.setPaint(focusColor);
/*  589 */       paintOutline(g, x2, y2, w2, h2, paintedFocusWidth, outerArc, innerArc);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  597 */     if (borderColor != null && borderWidth != 0.0F) {
/*  598 */       g.setPaint(borderColor);
/*  599 */       paintOutline(g, x1, y1, w1, h1, borderWidth, arc);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintOutline(Graphics2D g, float x, float y, float w, float h, float lineWidth, float arc) {
/*  620 */     paintOutline(g, x, y, w, h, lineWidth, arc, arc - lineWidth * 2.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintOutline(Graphics2D g, float x, float y, float w, float h, float lineWidth, float arc, float innerArc) {
/*  641 */     if (lineWidth == 0.0F || w <= 0.0F || h <= 0.0F) {
/*      */       return;
/*      */     }
/*  644 */     float t = lineWidth;
/*  645 */     float t2x = t * 2.0F;
/*      */     
/*  647 */     Path2D border = new Path2D.Float(0);
/*  648 */     border.append(createComponentRectangle(x, y, w, h, arc), false);
/*  649 */     border.append(createComponentRectangle(x + t, y + t, w - t2x, h - t2x, innerArc), false);
/*  650 */     g.fill(border);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Shape createComponentRectangle(float x, float y, float w, float h, float arc) {
/*  658 */     if (arc <= 0.0F) {
/*  659 */       return new Rectangle2D.Float(x, y, w, h);
/*      */     }
/*  661 */     if (w == h && arc >= w) {
/*  662 */       return new Ellipse2D.Float(x, y, w, h);
/*      */     }
/*  664 */     arc = Math.min(arc, Math.min(w, h));
/*  665 */     return new RoundRectangle2D.Float(x, y, w, h, arc, arc);
/*      */   }
/*      */   
/*      */   static void paintFilledRectangle(Graphics g, Color color, float x, float y, float w, float h) {
/*  669 */     Graphics2D g2 = (Graphics2D)g.create();
/*      */     try {
/*  671 */       setRenderingHints(g2);
/*  672 */       g2.setColor(color);
/*  673 */       g2.fill(new Rectangle2D.Float(x, y, w, h));
/*      */     } finally {
/*  675 */       g2.dispose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintSelection(Graphics2D g, int x, int y, int width, int height, Insets insets, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight, int flags) {
/*  691 */     if (insets != null) {
/*  692 */       x += insets.left;
/*  693 */       y += insets.top;
/*  694 */       width -= insets.left + insets.right;
/*  695 */       height -= insets.top + insets.bottom;
/*      */     } 
/*      */     
/*  698 */     if (arcTopLeft > 0.0F || arcTopRight > 0.0F || arcBottomLeft > 0.0F || arcBottomRight > 0.0F) {
/*  699 */       double systemScaleFactor = UIScale.getSystemScaleFactor(g);
/*  700 */       if (systemScaleFactor != 1.0D && systemScaleFactor != 2.0D) {
/*      */         
/*  702 */         HiDPIUtils.paintAtScale1x(g, x, y, width, height, (g2d, x2, y2, width2, height2, scaleFactor) -> paintRoundedSelectionImpl(g2d, x2, y2, width2, height2, (float)(arcTopLeft * scaleFactor), (float)(arcTopRight * scaleFactor), (float)(arcBottomLeft * scaleFactor), (float)(arcBottomRight * scaleFactor)));
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  709 */         paintRoundedSelectionImpl(g, x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight);
/*      */       } 
/*      */     } else {
/*  712 */       g.fillRect(x, y, width, height);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void paintRoundedSelectionImpl(Graphics2D g, int x, int y, int width, int height, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
/*  718 */     Object[] oldRenderingHints = setRenderingHints(g);
/*  719 */     g.fill(createRoundRectanglePath(x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight));
/*  720 */     resetRenderingHints(g, oldRenderingHints);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void paintGrip(Graphics g, int x, int y, int width, int height, boolean horizontal, int dotCount, int dotSize, int gap, boolean centerPrecise) {
/*      */     float gx, gy;
/*  726 */     dotSize = UIScale.scale(dotSize);
/*  727 */     gap = UIScale.scale(gap);
/*  728 */     int gripSize = dotSize * dotCount + gap * (dotCount - 1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  733 */     if (horizontal) {
/*  734 */       gx = (x + Math.round((width - gripSize) / 2.0F));
/*  735 */       gy = y + (height - dotSize) / 2.0F;
/*      */       
/*  737 */       if (!centerPrecise) {
/*  738 */         gy = Math.round(gy);
/*      */       }
/*      */     } else {
/*  741 */       gx = x + (width - dotSize) / 2.0F;
/*  742 */       gy = (y + Math.round((height - gripSize) / 2.0F));
/*      */       
/*  744 */       if (!centerPrecise) {
/*  745 */         gx = Math.round(gx);
/*      */       }
/*      */     } 
/*      */     
/*  749 */     for (int i = 0; i < dotCount; i++) {
/*  750 */       ((Graphics2D)g).fill(new Ellipse2D.Float(gx, gy, dotSize, dotSize));
/*  751 */       if (horizontal) {
/*  752 */         gx += (dotSize + gap);
/*      */       } else {
/*  754 */         gy += (dotSize + gap);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintParentBackground(Graphics g, JComponent c) {
/*  763 */     Color background = getParentBackground(c);
/*  764 */     if (background != null) {
/*  765 */       g.setColor(background);
/*  766 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Color getParentBackground(JComponent c) {
/*  774 */     Container parent = findOpaqueParent(c);
/*      */ 
/*      */     
/*  777 */     Color background = (parent != null) ? parent.getBackground() : null;
/*  778 */     if (background != null) {
/*  779 */       return background;
/*      */     }
/*  781 */     if (isAWTPeer(c))
/*      */     {
/*  783 */       return (c instanceof javax.swing.JTextField || c instanceof javax.swing.JScrollPane || c.getBackground() == null) ? 
/*  784 */         SystemColor.window : 
/*  785 */         c.getBackground();
/*      */     }
/*      */     
/*  788 */     return UIManager.getColor("Panel.background");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Container findOpaqueParent(Container c) {
/*  795 */     while ((c = c.getParent()) != null) {
/*  796 */       if (c.isOpaque())
/*  797 */         return c; 
/*      */     } 
/*  799 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Path2D createRectangle(float x, float y, float width, float height, float lineWidth) {
/*  806 */     Path2D path = new Path2D.Float(0);
/*  807 */     path.append(new Rectangle2D.Float(x, y, width, height), false);
/*  808 */     path.append(new Rectangle2D.Float(x + lineWidth, y + lineWidth, width - lineWidth * 2.0F, height - lineWidth * 2.0F), false);
/*      */     
/*  810 */     return path;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Path2D createRoundRectangle(float x, float y, float width, float height, float lineWidth, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
/*  819 */     Path2D path = new Path2D.Float(0);
/*  820 */     path.append(createRoundRectanglePath(x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight), false);
/*  821 */     path.append(createRoundRectanglePath(x + lineWidth, y + lineWidth, width - lineWidth * 2.0F, height - lineWidth * 2.0F, arcTopLeft - lineWidth, arcTopRight - lineWidth, arcBottomLeft - lineWidth, arcBottomRight - lineWidth), false);
/*      */     
/*  823 */     return path;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Shape createRoundRectanglePath(float x, float y, float width, float height, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
/*  832 */     if (arcTopLeft <= 0.0F && arcTopRight <= 0.0F && arcBottomLeft <= 0.0F && arcBottomRight <= 0.0F) {
/*  833 */       return new Rectangle2D.Float(x, y, width, height);
/*      */     }
/*      */     
/*  836 */     float maxArc = Math.min(width, height) / 2.0F;
/*  837 */     arcTopLeft = (arcTopLeft > 0.0F) ? Math.min(arcTopLeft, maxArc) : 0.0F;
/*  838 */     arcTopRight = (arcTopRight > 0.0F) ? Math.min(arcTopRight, maxArc) : 0.0F;
/*  839 */     arcBottomLeft = (arcBottomLeft > 0.0F) ? Math.min(arcBottomLeft, maxArc) : 0.0F;
/*  840 */     arcBottomRight = (arcBottomRight > 0.0F) ? Math.min(arcBottomRight, maxArc) : 0.0F;
/*      */     
/*  842 */     float x2 = x + width;
/*  843 */     float y2 = y + height;
/*      */ 
/*      */     
/*  846 */     double c = 0.5522847498307933D;
/*  847 */     double ci = 1.0D - c;
/*  848 */     double ciTopLeft = arcTopLeft * ci;
/*  849 */     double ciTopRight = arcTopRight * ci;
/*  850 */     double ciBottomLeft = arcBottomLeft * ci;
/*  851 */     double ciBottomRight = arcBottomRight * ci;
/*      */     
/*  853 */     Path2D rect = new Path2D.Float(1, 16);
/*  854 */     rect.moveTo((x2 - arcTopRight), y);
/*  855 */     rect.curveTo(x2 - ciTopRight, y, x2, y + ciTopRight, x2, (y + arcTopRight));
/*      */ 
/*      */     
/*  858 */     rect.lineTo(x2, (y2 - arcBottomRight));
/*  859 */     rect.curveTo(x2, y2 - ciBottomRight, x2 - ciBottomRight, y2, (x2 - arcBottomRight), y2);
/*      */ 
/*      */     
/*  862 */     rect.lineTo((x + arcBottomLeft), y2);
/*  863 */     rect.curveTo(x + ciBottomLeft, y2, x, y2 - ciBottomLeft, x, (y2 - arcBottomLeft));
/*      */ 
/*      */     
/*  866 */     rect.lineTo(x, (y + arcTopLeft));
/*  867 */     rect.curveTo(x, y + ciTopLeft, x + ciTopLeft, y, (x + arcTopLeft), y);
/*      */ 
/*      */     
/*  870 */     rect.closePath();
/*      */     
/*  872 */     return rect;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Shape createRoundTrianglePath(float x1, float y1, float x2, float y2, float x3, float y3, float arc) {
/*  883 */     double averageSideLength = (distance(x1, y1, x2, y2) + distance(x2, y2, x3, y3) + distance(x3, y3, x1, y1)) / 3.0D;
/*  884 */     double t1 = 1.0D / averageSideLength * arc;
/*  885 */     double t2 = 1.0D - t1;
/*      */     
/*  887 */     return createPath(new double[] { 
/*  888 */           lerp(x3, x1, t2), lerp(y3, y1, t2), -1.000000000002E12D, x1, y1, 
/*  889 */           lerp(x1, x2, t1), lerp(y1, y2, t1), 
/*  890 */           lerp(x1, x2, t2), lerp(y1, y2, t2), -1.000000000002E12D, x2, y2, 
/*  891 */           lerp(x2, x3, t1), lerp(y2, y3, t1), 
/*  892 */           lerp(x2, x3, t2), lerp(y2, y3, t2), -1.000000000002E12D, x3, y3, 
/*  893 */           lerp(x3, x1, t1), lerp(y3, y1, t1) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void paintArrow(Graphics2D g, int x, int y, int width, int height, int direction, boolean chevron, int arrowSize, float arrowThickness, float xOffset, float yOffset) {
/*  921 */     float aw = UIScale.scale(arrowSize + (chevron ? -1 : 0));
/*  922 */     float ah = chevron ? (aw / 2.0F) : UIScale.scale(arrowSize / 2 + 1);
/*      */ 
/*      */     
/*  925 */     boolean vert = (direction == 1 || direction == 5);
/*  926 */     if (!vert) {
/*  927 */       float temp = aw;
/*  928 */       aw = ah;
/*  929 */       ah = temp;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  934 */     int extra = chevron ? 1 : 0;
/*      */ 
/*      */     
/*  937 */     float ox = (width - aw + extra) / 2.0F + UIScale.scale(xOffset);
/*  938 */     float oy = (height - ah + extra) / 2.0F + UIScale.scale(yOffset);
/*  939 */     float ax = x + ((direction == 7) ? (-Math.round(-(ox + aw)) - aw) : Math.round(ox));
/*  940 */     float ay = y + ((direction == 1) ? (-Math.round(-(oy + ah)) - ah) : Math.round(oy));
/*      */ 
/*      */     
/*  943 */     g.translate(ax, ay);
/*      */ 
/*      */ 
/*      */     
/*  947 */     Shape arrowShape = createArrowShape(direction, chevron, aw, ah);
/*  948 */     if (chevron) {
/*  949 */       Stroke oldStroke = g.getStroke();
/*  950 */       g.setStroke(new BasicStroke(UIScale.scale(arrowThickness)));
/*  951 */       drawShapePure(g, arrowShape);
/*  952 */       g.setStroke(oldStroke);
/*      */     } else {
/*      */       
/*  955 */       g.fill(arrowShape);
/*      */     } 
/*  957 */     g.translate(-ax, -ay);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Shape createArrowShape(int direction, boolean chevron, float w, float h) {
/*  975 */     switch (direction) { case 1:
/*  976 */         return createPath(!chevron, new double[] { 0.0D, h, (w / 2.0F), 0.0D, w, h });
/*  977 */       case 5: return createPath(!chevron, new double[] { 0.0D, 0.0D, (w / 2.0F), h, w, 0.0D });
/*  978 */       case 7: return createPath(!chevron, new double[] { w, 0.0D, 0.0D, (h / 2.0F), w, h });
/*  979 */       case 3: return createPath(!chevron, new double[] { 0.0D, 0.0D, w, (h / 2.0F), 0.0D, h }); }
/*  980 */      return new Path2D.Float();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Path2D createPath(double... points) {
/* 1017 */     return createPath(true, points);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Path2D createPath(boolean close, double... points) {
/* 1024 */     Path2D path = new Path2D.Float(1, points.length / 2 + (close ? 1 : 0));
/* 1025 */     path.moveTo(points[0], points[1]);
/* 1026 */     for (int i = 2; i < points.length; ) {
/* 1027 */       double p = points[i];
/* 1028 */       if (p == -1.000000000001E12D) {
/*      */ 
/*      */         
/* 1031 */         path.moveTo(points[i + 1], points[i + 2]);
/* 1032 */         i += 3; continue;
/* 1033 */       }  if (p == -1.000000000002E12D) {
/*      */ 
/*      */         
/* 1036 */         path.quadTo(points[i + 1], points[i + 2], points[i + 3], points[i + 4]);
/* 1037 */         i += 5; continue;
/* 1038 */       }  if (p == -1.000000000003E12D) {
/*      */ 
/*      */         
/* 1041 */         path.curveTo(points[i + 1], points[i + 2], points[i + 3], points[i + 4], points[i + 5], points[i + 6]);
/* 1042 */         i += 7; continue;
/* 1043 */       }  if (p == -1.000000000004E12D) {
/*      */ 
/*      */         
/* 1046 */         double x = points[i + 1];
/* 1047 */         double y = points[i + 2];
/* 1048 */         double arc = points[i + 3];
/*      */ 
/*      */         
/* 1051 */         int ip2 = i + 4;
/* 1052 */         if (points[ip2] == -1.000000000002E12D || points[ip2] == -1.000000000004E12D) {
/* 1053 */           ip2++;
/*      */         }
/*      */         
/* 1056 */         Point2D p1 = path.getCurrentPoint();
/* 1057 */         double x1 = p1.getX();
/* 1058 */         double y1 = p1.getY();
/* 1059 */         double x2 = points[ip2];
/* 1060 */         double y2 = points[ip2 + 1];
/*      */         
/* 1062 */         double d1 = distance(x, y, x1, y1);
/* 1063 */         double d2 = distance(x, y, x2, y2);
/* 1064 */         double t1 = 1.0D - 1.0D / d1 * arc;
/* 1065 */         double t2 = 1.0D / d2 * arc;
/*      */         
/* 1067 */         path.lineTo(lerp(x1, x, t1), lerp(y1, y, t1));
/* 1068 */         path.quadTo(x, y, lerp(x, x2, t2), lerp(y, y2, t2));
/*      */         
/* 1070 */         i += 4; continue;
/* 1071 */       }  if (p == -1.000000000005E12D) {
/*      */ 
/*      */         
/* 1074 */         path.closePath();
/* 1075 */         i++;
/*      */         
/*      */         continue;
/*      */       } 
/* 1079 */       path.lineTo(p, points[i + 1]);
/* 1080 */       i += 2;
/*      */     } 
/*      */     
/* 1083 */     if (close)
/* 1084 */       path.closePath(); 
/* 1085 */     return path;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static double lerp(double v1, double v2, double t) {
/* 1094 */     return v1 * (1.0D - t) + v2 * t;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static double distance(double x1, double y1, double x2, double y2) {
/* 1101 */     double dx = x2 - x1;
/* 1102 */     double dy = y2 - y1;
/* 1103 */     return Math.sqrt(dx * dx + dy * dy);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawShapePure(Graphics2D g, Shape shape) {
/* 1113 */     Object oldStrokeControl = g.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
/* 1114 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/*      */     
/* 1116 */     g.translate(0.5D, 0.5D);
/* 1117 */     g.draw(shape);
/* 1118 */     g.translate(-0.5D, -0.5D);
/*      */     
/* 1120 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldStrokeControl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawString(JComponent c, Graphics g, String text, int x, int y) {
/* 1133 */     HiDPIUtils.drawStringWithYCorrection(c, (Graphics2D)g, text, x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
/*      */     Graphics2DProxy graphics2DProxy;
/* 1147 */     if (underlinedIndex >= 0 && UIScale.getUserScaleFactor() > 1.0F) {
/* 1148 */       graphics2DProxy = new Graphics2DProxy((Graphics2D)g)
/*      */         {
/*      */           public void fillRect(int x, int y, int width, int height) {
/* 1151 */             if (height == 1) {
/*      */ 
/*      */               
/* 1154 */               height = Math.round(UIScale.scale(0.9F));
/* 1155 */               y += height - 1;
/*      */             } 
/*      */             
/* 1158 */             super.fillRect(x, y, width, height);
/*      */           }
/*      */         };
/*      */     }
/*      */     
/* 1163 */     HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(c, (Graphics2D)graphics2DProxy, text, underlinedIndex, x, y);
/*      */   }
/*      */   
/*      */   public static boolean hasOpaqueBeenExplicitlySet(JComponent c) {
/* 1167 */     boolean oldOpaque = c.isOpaque();
/* 1168 */     LookAndFeel.installProperty(c, "opaque", Boolean.valueOf(!oldOpaque));
/* 1169 */     boolean explicitlySet = (c.isOpaque() == oldOpaque);
/* 1170 */     LookAndFeel.installProperty(c, "opaque", Boolean.valueOf(oldOpaque));
/* 1171 */     return explicitlySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isUseSharedUIs() {
/* 1180 */     return useSharedUIs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean setUseSharedUIs(boolean useSharedUIs) {
/* 1190 */     boolean old = FlatUIUtils.useSharedUIs;
/* 1191 */     FlatUIUtils.useSharedUIs = useSharedUIs;
/* 1192 */     return old;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ComponentUI createSharedUI(Object key, Supplier<ComponentUI> newInstanceSupplier) {
/* 1203 */     if (!useSharedUIs) {
/* 1204 */       return newInstanceSupplier.get();
/*      */     }
/* 1206 */     return ((IdentityHashMap<Object, ComponentUI>)sharedUIinstances
/* 1207 */       .computeIfAbsent(UIManager.getLookAndFeel(), k -> new IdentityHashMap<>()))
/* 1208 */       .computeIfAbsent(key, k -> (ComponentUI)newInstanceSupplier.get());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canUseSharedUI(JComponent c) {
/* 1216 */     return !FlatStylingSupport.hasStyleProperty(c);
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RepaintFocusListener
/*      */     implements FocusListener
/*      */   {
/*      */     private final Component repaintComponent;
/*      */     
/*      */     private final Predicate<Component> repaintCondition;
/*      */     
/*      */     public RepaintFocusListener(Component repaintComponent, Predicate<Component> repaintCondition) {
/* 1228 */       this.repaintComponent = repaintComponent;
/* 1229 */       this.repaintCondition = repaintCondition;
/*      */     }
/*      */ 
/*      */     
/*      */     public void focusGained(FocusEvent e) {
/* 1234 */       if (this.repaintCondition == null || this.repaintCondition.test(this.repaintComponent)) {
/* 1235 */         this.repaintComponent.repaint();
/*      */       }
/*      */     }
/*      */     
/*      */     public void focusLost(FocusEvent e) {
/* 1240 */       if (this.repaintCondition == null || this.repaintCondition.test(this.repaintComponent)) {
/* 1241 */         this.repaintComponent.repaint();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class NonUIResourceBorder
/*      */     implements Border
/*      */   {
/*      */     private final Border delegate;
/*      */     
/*      */     NonUIResourceBorder(Border delegate) {
/* 1253 */       this.delegate = delegate;
/*      */     }
/*      */ 
/*      */     
/*      */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 1258 */       this.delegate.paintBorder(c, g, x, y, width, height);
/*      */     }
/*      */ 
/*      */     
/*      */     public Insets getBorderInsets(Component c) {
/* 1263 */       return this.delegate.getBorderInsets(c);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isBorderOpaque() {
/* 1268 */       return this.delegate.isBorderOpaque();
/*      */     }
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatUIUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */