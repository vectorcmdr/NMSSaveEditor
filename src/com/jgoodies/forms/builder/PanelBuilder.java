/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.Borders;
/*     */ import com.jgoodies.forms.factories.ComponentFactory;
/*     */ import com.jgoodies.forms.factories.ComponentFactory2;
/*     */ import com.jgoodies.forms.factories.DefaultComponentFactory;
/*     */ import com.jgoodies.forms.layout.CellConstraints;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.lang.ref.WeakReference;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.Border;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PanelBuilder
/*     */   extends AbstractFormBuilder
/*     */ {
/*     */   private static final String LABELED_BY_PROPERTY = "labeledBy";
/*     */   private static boolean labelForFeatureEnabledDefault = false;
/*     */   private ComponentFactory componentFactory;
/*     */   private boolean labelForFeatureEnabled;
/* 147 */   private WeakReference mostRecentlyAddedLabelReference = null;
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
/*     */   public PanelBuilder(FormLayout layout) {
/* 160 */     this(layout, new JPanel(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PanelBuilder(FormLayout layout, JPanel panel) {
/* 171 */     super(layout, panel);
/* 172 */     this.labelForFeatureEnabled = labelForFeatureEnabledDefault;
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
/*     */   
/*     */   public static boolean getLabelForFeatureEnabledDefault() {
/* 187 */     return labelForFeatureEnabledDefault;
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
/*     */   
/*     */   public static void setLabelForFeatureEnabledDefault(boolean b) {
/* 202 */     labelForFeatureEnabledDefault = b;
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
/*     */   
/*     */   public boolean isLabelForFeatureEnabled() {
/* 217 */     return this.labelForFeatureEnabled;
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
/*     */   public void setLabelForFeatureEnabled(boolean b) {
/* 230 */     this.labelForFeatureEnabled = b;
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
/*     */   public final JPanel getPanel() {
/* 242 */     return (JPanel)getContainer();
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
/*     */ 
/*     */   
/*     */   public final void setBackground(Color background) {
/* 258 */     getPanel().setBackground(background);
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
/*     */   public final void setBorder(Border border) {
/* 270 */     getPanel().setBorder(border);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setDefaultDialogBorder() {
/* 280 */     setBorder(Borders.DIALOG_BORDER);
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
/*     */   public final void setOpaque(boolean b) {
/* 294 */     getPanel().setOpaque(b);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addLabel(String textWithMnemonic) {
/* 317 */     return addLabel(textWithMnemonic, cellConstraints());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addLabel(String textWithMnemonic, CellConstraints constraints) {
/* 339 */     JLabel label = getComponentFactory().createLabel(textWithMnemonic);
/* 340 */     add(label, constraints);
/* 341 */     return label;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addLabel(String textWithMnemonic, String encodedConstraints) {
/* 363 */     return addLabel(textWithMnemonic, new CellConstraints(encodedConstraints));
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
/*     */   public final JLabel addLabel(String textWithMnemonic, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
/* 431 */     if (labelConstraints == componentConstraints) {
/* 432 */       throw new IllegalArgumentException("You must provide two CellConstraints instances, one for the label and one for the component.\nConsider using #clone(). See the JavaDocs for details.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 437 */     JLabel label = addLabel(textWithMnemonic, labelConstraints);
/* 438 */     add(component, componentConstraints);
/* 439 */     label.setLabelFor(component);
/* 440 */     return label;
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
/*     */   public final JLabel addROLabel(String textWithMnemonic) {
/* 466 */     return addROLabel(textWithMnemonic, cellConstraints());
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
/*     */   public final JLabel addROLabel(String textWithMnemonic, CellConstraints constraints) {
/*     */     DefaultComponentFactory defaultComponentFactory;
/* 491 */     ComponentFactory factory = getComponentFactory();
/*     */     
/* 493 */     if (factory instanceof ComponentFactory2) {
/* 494 */       ComponentFactory2 factory2 = (ComponentFactory2)factory;
/*     */     } else {
/* 496 */       defaultComponentFactory = DefaultComponentFactory.getInstance();
/*     */     } 
/* 498 */     JLabel label = defaultComponentFactory.createReadOnlyLabel(textWithMnemonic);
/* 499 */     add(label, constraints);
/* 500 */     return label;
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
/*     */   public final JLabel addROLabel(String textWithMnemonic, String encodedConstraints) {
/* 525 */     return addROLabel(textWithMnemonic, new CellConstraints(encodedConstraints));
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
/*     */   public final JLabel addROLabel(String textWithMnemonic, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
/* 595 */     if (labelConstraints == componentConstraints) {
/* 596 */       throw new IllegalArgumentException("You must provide two CellConstraints instances, one for the label and one for the component.\nConsider using #clone(). See the JavaDocs for details.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 601 */     JLabel label = addROLabel(textWithMnemonic, labelConstraints);
/* 602 */     add(component, componentConstraints);
/* 603 */     label.setLabelFor(component);
/* 604 */     return label;
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
/*     */   public final JLabel addTitle(String textWithMnemonic) {
/* 628 */     return addTitle(textWithMnemonic, cellConstraints());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addTitle(String textWithMnemonic, CellConstraints constraints) {
/* 650 */     JLabel titleLabel = getComponentFactory().createTitle(textWithMnemonic);
/* 651 */     add(titleLabel, constraints);
/* 652 */     return titleLabel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addTitle(String textWithMnemonic, String encodedConstraints) {
/* 674 */     return addTitle(textWithMnemonic, new CellConstraints(encodedConstraints));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JComponent addSeparator(String textWithMnemonic) {
/* 695 */     return addSeparator(textWithMnemonic, getLayout().getColumnCount());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JComponent addSeparator(String textWithMnemonic, CellConstraints constraints) {
/* 715 */     int titleAlignment = isLeftToRight() ? 2 : 4;
/*     */ 
/*     */     
/* 718 */     JComponent titledSeparator = getComponentFactory().createSeparator(textWithMnemonic, titleAlignment);
/*     */     
/* 720 */     add(titledSeparator, constraints);
/* 721 */     return titledSeparator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JComponent addSeparator(String textWithMnemonic, String encodedConstraints) {
/* 741 */     return addSeparator(textWithMnemonic, new CellConstraints(encodedConstraints));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JComponent addSeparator(String textWithMnemonic, int columnSpan) {
/* 761 */     return addSeparator(textWithMnemonic, createLeftAdjustedConstraints(columnSpan));
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
/*     */   public final JLabel add(JLabel label, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
/* 826 */     if (labelConstraints == componentConstraints) {
/* 827 */       throw new IllegalArgumentException("You must provide two CellConstraints instances, one for the label and one for the component.\nConsider using #clone(). See the JavaDocs for details.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 832 */     add(label, labelConstraints);
/* 833 */     add(component, componentConstraints);
/* 834 */     label.setLabelFor(component);
/* 835 */     return label;
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
/*     */ 
/*     */   
/*     */   public final ComponentFactory getComponentFactory() {
/* 851 */     if (this.componentFactory == null) {
/* 852 */       this.componentFactory = (ComponentFactory)DefaultComponentFactory.getInstance();
/*     */     }
/* 854 */     return this.componentFactory;
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
/*     */   public final void setComponentFactory(ComponentFactory newFactory) {
/* 866 */     this.componentFactory = newFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component add(Component component, CellConstraints cellConstraints) {
/* 887 */     Component result = super.add(component, cellConstraints);
/* 888 */     if (!isLabelForFeatureEnabled()) {
/* 889 */       return result;
/*     */     }
/* 891 */     JLabel mostRecentlyAddedLabel = getMostRecentlyAddedLabel();
/* 892 */     if (mostRecentlyAddedLabel != null && isLabelForApplicable(mostRecentlyAddedLabel, component)) {
/*     */       
/* 894 */       setLabelFor(mostRecentlyAddedLabel, component);
/* 895 */       clearMostRecentlyAddedLabel();
/*     */     } 
/* 897 */     if (component instanceof JLabel) {
/* 898 */       setMostRecentlyAddedLabel((JLabel)component);
/*     */     }
/* 900 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isLabelForApplicable(JLabel label, Component component) {
/* 921 */     if (label.getLabelFor() != null) {
/* 922 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 926 */     if (!component.isFocusable()) {
/* 927 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 931 */     if (!(component instanceof JComponent)) {
/* 932 */       return true;
/*     */     }
/* 934 */     JComponent c = (JComponent)component;
/* 935 */     return (c.getClientProperty("labeledBy") == null);
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
/*     */   
/*     */   protected void setLabelFor(JLabel label, Component component) {
/*     */     Component labeledComponent;
/* 951 */     if (component instanceof JScrollPane) {
/* 952 */       JScrollPane scrollPane = (JScrollPane)component;
/* 953 */       labeledComponent = scrollPane.getViewport().getView();
/*     */     } else {
/* 955 */       labeledComponent = component;
/*     */     } 
/* 957 */     label.setLabelFor(labeledComponent);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private JLabel getMostRecentlyAddedLabel() {
/* 974 */     if (this.mostRecentlyAddedLabelReference == null) {
/* 975 */       return null;
/*     */     }
/* 977 */     JLabel label = this.mostRecentlyAddedLabelReference.get();
/* 978 */     if (label == null) {
/* 979 */       return null;
/*     */     }
/* 981 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMostRecentlyAddedLabel(JLabel label) {
/* 991 */     this.mostRecentlyAddedLabelReference = new WeakReference(label);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearMostRecentlyAddedLabel() {
/* 999 */     this.mostRecentlyAddedLabelReference = null;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\PanelBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */