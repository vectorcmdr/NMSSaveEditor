package nomanssave;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class cI implements TreeModel {
   private ArrayList gh;
   // $FF: synthetic field
   final cy gg;

   private cI(cy var1) {
      this.gg = var1;
      this.gh = new ArrayList();
   }

   public Object getRoot() {
      return new cJ(this.gg, (cJ)null, 0, cy.a(this.gg), cy.b(this.gg));
   }

   public Object getChild(Object var1, int var2) {
      return ((cJ)var1).x(var2);
   }

   public int getChildCount(Object var1) {
      return ((cJ)var1).getChildCount();
   }

   public boolean isLeaf(Object var1) {
      return ((cJ)var1).isLeaf();
   }

   public void valueForPathChanged(TreePath var1, Object var2) {
   }

   public int getIndexOfChild(Object var1, Object var2) {
      return ((cJ)var1).indexOf(var2);
   }

   public void addTreeModelListener(TreeModelListener var1) {
      this.gh.add(var1);
   }

   public void removeTreeModelListener(TreeModelListener var1) {
      this.gh.remove(var1);
   }

   public void a(cJ var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1);

      while((var1 = var1.gi) != null) {
         var2.add(0, var1);
      }

      TreeModelEvent var3 = new TreeModelEvent(this, var2.toArray());
      Iterator var5 = this.gh.iterator();

      while(var5.hasNext()) {
         TreeModelListener var4 = (TreeModelListener)var5.next();
         var4.treeStructureChanged(var3);
      }

   }

   // $FF: synthetic method
   cI(cy var1, cI var2) {
      this(var1);
   }
}
