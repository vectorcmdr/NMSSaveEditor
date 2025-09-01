package com.jgoodies.forms.builder;

import com.jgoodies.forms.layout.FormLayout;
import java.awt.LayoutManager;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JPanel;

public class I15dPanelBuilder extends AbstractI15dPanelBuilder {
   private final ResourceBundle bundle;

   public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle) {
      this(layout, bundle, new JPanel((LayoutManager)null));
   }

   public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle, JPanel panel) {
      super(layout, panel);
      this.bundle = bundle;
   }

   protected String getI15dString(String resourceKey) {
      if (this.bundle == null) {
         throw new IllegalStateException("You must specify a ResourceBundle before using the internationalization support.");
      } else {
         try {
            return this.bundle.getString(resourceKey);
         } catch (MissingResourceException var3) {
            return resourceKey;
         }
      }
   }
}
