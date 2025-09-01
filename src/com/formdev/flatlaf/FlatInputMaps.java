package com.formdev.flatlaf;

import com.formdev.flatlaf.util.SystemInfo;
import java.util.function.BooleanSupplier;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIDefaults.LazyInputMap;
import javax.swing.UIDefaults.LazyValue;
import javax.swing.plaf.InputMapUIResource;

class FlatInputMaps {
   static void initInputMaps(UIDefaults defaults) {
      initBasicInputMaps(defaults);
      initTextComponentInputMaps(defaults);
      if (SystemInfo.isMacOS) {
         initMacInputMaps(defaults);
      }

   }

   private static void initBasicInputMaps(UIDefaults defaults) {
      if (SystemInfo.isMacOS) {
         defaults.put("Button.focusInputMap", new LazyInputMap(new Object[]{"SPACE", "pressed", "released SPACE", "released"}));
      }

      modifyInputMap(defaults, "ComboBox.ancestorInputMap", "SPACE", "spacePopup", "UP", mac("selectPrevious2", "selectPrevious"), "DOWN", mac("selectNext2", "selectNext"), "KP_UP", mac("selectPrevious2", "selectPrevious"), "KP_DOWN", mac("selectNext2", "selectNext"), mac("alt UP", (Object)null), "togglePopup", mac("alt DOWN", (Object)null), "togglePopup", mac("alt KP_UP", (Object)null), "togglePopup", mac("alt KP_DOWN", (Object)null), "togglePopup");
      if (!SystemInfo.isMacOS) {
         modifyInputMap(defaults, "FileChooser.ancestorInputMap", "F2", "editFileName", "BACK_SPACE", "Go Up");
      }

      Object[] bindings = (Object[])defaults.get("PopupMenu.selectedWindowInputMapBindings");
      Object[] rtlBindings = (Object[])defaults.get("PopupMenu.selectedWindowInputMapBindings.RightToLeft");
      if (bindings != null && rtlBindings != null) {
         Object[] newBindings = new Object[bindings.length + rtlBindings.length];
         System.arraycopy(bindings, 0, newBindings, 0, bindings.length);
         System.arraycopy(rtlBindings, 0, newBindings, bindings.length, rtlBindings.length);
         defaults.put("PopupMenu.selectedWindowInputMapBindings.RightToLeft", newBindings);
      }

      modifyInputMap(defaults, "TabbedPane.ancestorInputMap", "ctrl TAB", "navigateNext", "shift ctrl TAB", "navigatePrevious");
      modifyInputMap(() -> {
         return UIManager.getBoolean("Table.consistentHomeEndKeyBehavior");
      }, defaults, "Table.ancestorInputMap", "HOME", "selectFirstRow", "END", "selectLastRow", "shift HOME", "selectFirstRowExtendSelection", "shift END", "selectLastRowExtendSelection", mac("ctrl HOME", (Object)null), "selectFirstColumn", mac("ctrl END", (Object)null), "selectLastColumn", mac("shift ctrl HOME", (Object)null), "selectFirstColumnExtendSelection", mac("shift ctrl END", (Object)null), "selectLastColumnExtendSelection");
      if (!SystemInfo.isMacOS) {
         modifyInputMap(defaults, "Tree.focusInputMap", "ADD", "expand", "SUBTRACT", "collapse");
      }

   }

   private static void initTextComponentInputMaps(UIDefaults defaults) {
      Object[] commonTextComponentBindings = new Object[]{"LEFT", "caret-backward", "RIGHT", "caret-forward", "KP_LEFT", "caret-backward", "KP_RIGHT", "caret-forward", "shift LEFT", "selection-backward", "shift RIGHT", "selection-forward", "shift KP_LEFT", "selection-backward", "shift KP_RIGHT", "selection-forward", mac("ctrl LEFT", "alt LEFT"), "caret-previous-word", mac("ctrl RIGHT", "alt RIGHT"), "caret-next-word", mac("ctrl KP_LEFT", "alt KP_LEFT"), "caret-previous-word", mac("ctrl KP_RIGHT", "alt KP_RIGHT"), "caret-next-word", mac("ctrl shift LEFT", "shift alt LEFT"), "selection-previous-word", mac("ctrl shift RIGHT", "shift alt RIGHT"), "selection-next-word", mac("ctrl shift KP_LEFT", "shift alt KP_LEFT"), "selection-previous-word", mac("ctrl shift KP_RIGHT", "shift alt KP_RIGHT"), "selection-next-word", mac("HOME", "meta LEFT"), "caret-begin-line", mac("END", "meta RIGHT"), "caret-end-line", mac("shift HOME", "shift meta LEFT"), "selection-begin-line", mac("shift END", "shift meta RIGHT"), "selection-end-line", mac("ctrl A", "meta A"), "select-all", mac("ctrl BACK_SLASH", "meta BACK_SLASH"), "unselect", "BACK_SPACE", "delete-previous", "shift BACK_SPACE", "delete-previous", "ctrl H", "delete-previous", "DELETE", "delete-next", mac("ctrl BACK_SPACE", "alt BACK_SPACE"), "delete-previous-word", mac("ctrl DELETE", "alt DELETE"), "delete-next-word", mac("ctrl X", "meta X"), "cut-to-clipboard", mac("ctrl C", "meta C"), "copy-to-clipboard", mac("ctrl V", "meta V"), "paste-from-clipboard", "CUT", "cut-to-clipboard", "COPY", "copy-to-clipboard", "PASTE", "paste-from-clipboard", mac("shift DELETE", (Object)null), "cut-to-clipboard", mac("control INSERT", (Object)null), "copy-to-clipboard", mac("shift INSERT", (Object)null), "paste-from-clipboard", "control shift O", "toggle-componentOrientation"};
      Object[] macCommonTextComponentBindings = SystemInfo.isMacOS ? new Object[]{"ctrl B", "caret-backward", "ctrl F", "caret-forward", "HOME", "caret-begin", "END", "caret-end", "meta UP", "caret-begin", "meta DOWN", "caret-end", "meta KP_UP", "caret-begin", "meta KP_DOWN", "caret-end", "ctrl P", "caret-begin", "ctrl N", "caret-end", "ctrl V", "caret-end", "meta KP_LEFT", "caret-begin-line", "meta KP_RIGHT", "caret-end-line", "ctrl A", "caret-begin-line", "ctrl E", "caret-end-line", "shift meta UP", "selection-begin", "shift meta DOWN", "selection-end", "shift meta KP_UP", "selection-begin", "shift meta KP_DOWN", "selection-end", "shift HOME", "selection-begin", "shift END", "selection-end", "shift meta KP_LEFT", "selection-begin-line", "shift meta KP_RIGHT", "selection-end-line", "shift UP", "selection-begin-line", "shift DOWN", "selection-end-line", "shift KP_UP", "selection-begin-line", "shift KP_DOWN", "selection-end-line", "ctrl W", "delete-previous-word", "ctrl D", "delete-next"} : null;
      Object[] singleLineTextComponentBindings = new Object[]{"ENTER", "notify-field-accept"};
      Object[] macSingleLineTextComponentBindings = SystemInfo.isMacOS ? new Object[]{"UP", "caret-begin-line", "DOWN", "caret-end-line", "KP_UP", "caret-begin-line", "KP_DOWN", "caret-end-line"} : null;
      Object[] formattedTextComponentBindings = new Object[]{"ESCAPE", "reset-field-edit", "UP", "increment", "DOWN", "decrement", "KP_UP", "increment", "KP_DOWN", "decrement"};
      Object[] passwordTextComponentBindings = new Object[]{mac("ctrl LEFT", "alt LEFT"), "caret-begin-line", mac("ctrl RIGHT", "alt RIGHT"), "caret-end-line", mac("ctrl KP_LEFT", "alt KP_LEFT"), "caret-begin-line", mac("ctrl KP_RIGHT", "alt KP_RIGHT"), "caret-end-line", mac("ctrl shift LEFT", "shift alt LEFT"), "selection-begin-line", mac("ctrl shift RIGHT", "shift alt RIGHT"), "selection-end-line", mac("ctrl shift KP_LEFT", "shift alt KP_LEFT"), "selection-begin-line", mac("ctrl shift KP_RIGHT", "shift alt KP_RIGHT"), "selection-end-line", mac("ctrl BACK_SPACE", "alt BACK_SPACE"), null, mac("ctrl DELETE", "alt DELETE"), null};
      Object[] multiLineTextComponentBindings = new Object[]{"UP", "caret-up", "DOWN", "caret-down", "KP_UP", "caret-up", "KP_DOWN", "caret-down", "shift UP", "selection-up", "shift DOWN", "selection-down", "shift KP_UP", "selection-up", "shift KP_DOWN", "selection-down", "PAGE_UP", "page-up", "PAGE_DOWN", "page-down", "shift PAGE_UP", "selection-page-up", "shift PAGE_DOWN", "selection-page-down", mac("ctrl shift PAGE_UP", "shift meta PAGE_UP"), "selection-page-left", mac("ctrl shift PAGE_DOWN", "shift meta PAGE_DOWN"), "selection-page-right", mac("ctrl HOME", "meta UP"), "caret-begin", mac("ctrl END", "meta DOWN"), "caret-end", mac("ctrl shift HOME", "shift meta UP"), "selection-begin", mac("ctrl shift END", "shift meta DOWN"), "selection-end", "ENTER", "insert-break", "TAB", "insert-tab", mac("ctrl T", "meta T"), "next-link-action", mac("ctrl shift T", "shift meta T"), "previous-link-action", mac("ctrl SPACE", "meta SPACE"), "activate-link-action"};
      Object[] macMultiLineTextComponentBindings = SystemInfo.isMacOS ? new Object[]{"ctrl N", "caret-down", "ctrl P", "caret-up", "shift alt UP", "selection-begin-paragraph", "shift alt DOWN", "selection-end-paragraph", "shift alt KP_UP", "selection-begin-paragraph", "shift alt KP_DOWN", "selection-end-paragraph", "ctrl V", "page-down"} : null;
      defaults.put("TextField.focusInputMap", new FlatInputMaps.LazyInputMapEx(new Object[][]{commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings}));
      defaults.put("FormattedTextField.focusInputMap", new FlatInputMaps.LazyInputMapEx(new Object[][]{commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings, formattedTextComponentBindings}));
      defaults.put("PasswordField.focusInputMap", new FlatInputMaps.LazyInputMapEx(new Object[][]{commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings, passwordTextComponentBindings}));
      Object multiLineInputMap = new FlatInputMaps.LazyInputMapEx(new Object[][]{commonTextComponentBindings, macCommonTextComponentBindings, multiLineTextComponentBindings, macMultiLineTextComponentBindings});
      defaults.put("TextArea.focusInputMap", multiLineInputMap);
      defaults.put("TextPane.focusInputMap", multiLineInputMap);
      defaults.put("EditorPane.focusInputMap", multiLineInputMap);
   }

   private static void initMacInputMaps(UIDefaults defaults) {
      modifyInputMap(defaults, "List.focusInputMap", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl KP_UP", null, "ctrl LEFT", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl RIGHT", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl KP_UP", null, "shift ctrl LEFT", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl RIGHT", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift SPACE", null);
      modifyInputMap(defaults, "List.focusInputMap.RightToLeft", "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl LEFT", null, "ctrl RIGHT", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl LEFT", null, "shift ctrl RIGHT", null);
      modifyInputMap(defaults, "ScrollPane.ancestorInputMap", "END", "scrollEnd", "HOME", "scrollHome", "ctrl END", null, "ctrl HOME", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null);
      modifyInputMap(defaults, "ScrollPane.ancestorInputMap.RightToLeft", "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null);
      modifyInputMap(defaults, "TabbedPane.ancestorInputMap", "ctrl UP", null, "ctrl KP_UP", null);
      modifyInputMap(defaults, "TabbedPane.focusInputMap", "ctrl DOWN", null, "ctrl KP_DOWN", null);
      modifyInputMap(defaults, "Table.ancestorInputMap", "alt TAB", "focusHeader", "shift alt TAB", "focusHeader", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl KP_UP", null, "ctrl LEFT", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl RIGHT", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "F2", null, "F8", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl KP_UP", null, "shift ctrl LEFT", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl RIGHT", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift SPACE", null);
      modifyInputMap(defaults, "Table.ancestorInputMap.RightToLeft", "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl LEFT", null, "ctrl RIGHT", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl LEFT", null, "shift ctrl RIGHT", null);
      modifyInputMap(defaults, "Tree.focusInputMap", "LEFT", "selectParent", "RIGHT", "selectChild", "KP_LEFT", "selectParent", "KP_RIGHT", "selectChild", "shift LEFT", "selectParent", "shift RIGHT", "selectChild", "shift KP_LEFT", "selectParent", "shift KP_RIGHT", "selectChild", "alt LEFT", "selectParent", "alt RIGHT", "selectChild", "alt KP_LEFT", "selectParent", "alt KP_RIGHT", "selectChild", "shift HOME", "selectFirstExtendSelection", "shift END", "selectLastExtendSelection", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl LEFT", null, "ctrl RIGHT", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_UP", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "F2", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_UP", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift PAGE_DOWN", null, "shift PAGE_UP", null, "shift SPACE", null);
      defaults.put("Tree.focusInputMap.RightToLeft", new LazyInputMap(new Object[]{"LEFT", "selectChild", "RIGHT", "selectParent", "KP_LEFT", "selectChild", "KP_RIGHT", "selectParent", "shift LEFT", "selectChild", "shift RIGHT", "selectParent", "shift KP_LEFT", "selectChild", "shift KP_RIGHT", "selectParent", "alt LEFT", "selectChild", "alt RIGHT", "selectParent", "alt KP_LEFT", "selectChild", "alt KP_RIGHT", "selectParent"}));
   }

   private static void modifyInputMap(UIDefaults defaults, String key, Object... bindings) {
      modifyInputMap((BooleanSupplier)null, defaults, key, bindings);
   }

   private static void modifyInputMap(BooleanSupplier condition, UIDefaults defaults, String key, Object... bindings) {
      defaults.put(key, new FlatInputMaps.LazyModifyInputMap(condition, defaults.remove(key), bindings));
   }

   private static <T> T mac(T value, T macValue) {
      return SystemInfo.isMacOS ? macValue : value;
   }

   private static class LazyModifyInputMap implements LazyValue {
      private final BooleanSupplier condition;
      private final Object baseInputMap;
      private final Object[] bindings;

      LazyModifyInputMap(BooleanSupplier condition, Object baseInputMap, Object[] bindings) {
         this.condition = condition;
         this.baseInputMap = baseInputMap;
         this.bindings = bindings;
      }

      public Object createValue(UIDefaults table) {
         InputMap inputMap = this.baseInputMap instanceof LazyValue ? (InputMap)((LazyValue)this.baseInputMap).createValue(table) : (InputMap)this.baseInputMap;
         if (this.condition != null && !this.condition.getAsBoolean()) {
            return inputMap;
         } else {
            for(int i = 0; i < this.bindings.length; i += 2) {
               KeyStroke keyStroke = KeyStroke.getKeyStroke((String)this.bindings[i]);
               if (this.bindings[i + 1] != null) {
                  inputMap.put(keyStroke, this.bindings[i + 1]);
               } else {
                  inputMap.remove(keyStroke);
               }
            }

            return inputMap;
         }
      }
   }

   private static class LazyInputMapEx implements LazyValue {
      private final Object[][] bindingsArray;

      LazyInputMapEx(Object[]... bindingsArray) {
         this.bindingsArray = bindingsArray;
      }

      public Object createValue(UIDefaults table) {
         InputMap inputMap = new InputMapUIResource();
         Object[][] var3 = this.bindingsArray;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Object[] bindings = var3[var5];
            LookAndFeel.loadKeyBindings(inputMap, bindings);
         }

         return inputMap;
      }
   }
}
