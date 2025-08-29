/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class I15dPanelBuilder
/*     */   extends AbstractI15dPanelBuilder
/*     */ {
/*     */   private final ResourceBundle bundle;
/*     */   
/*     */   public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle) {
/*  80 */     this(layout, bundle, new JPanel(null));
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
/*     */   public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle, JPanel panel) {
/*  93 */     super(layout, panel);
/*  94 */     this.bundle = bundle;
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
/*     */   protected String getI15dString(String resourceKey) {
/* 111 */     if (this.bundle == null) {
/* 112 */       throw new IllegalStateException("You must specify a ResourceBundle before using the internationalization support.");
/*     */     }
/*     */     try {
/* 115 */       return this.bundle.getString(resourceKey);
/* 116 */     } catch (MissingResourceException mre) {
/* 117 */       return resourceKey;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\I15dPanelBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */