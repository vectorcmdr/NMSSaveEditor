using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cI : TreeModel {
   private List<object> gh;
   cy gg;

   private cI(cy var1) {
      this.gg = var1;
      this.gh = new List<object>();
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

   public bool isLeaf(Object var1) {
      return ((cJ)var1).isLeaf();
   }

   public void valueForPathChanged(TreePath var1, Object var2) {
   }

   public int getIndexOfChild(Object var1, Object var2) {
      return ((cJ)var1).IndexOf(var2);
   }

   public void addTreeModelListener(TreeModelListener var1) {
      this.gh.Add(var1);
   }

   public void removeTreeModelListener(TreeModelListener var1) {
      this.gh.Remove(var1);
   }

   public void a(cJ var1) {
      List<object> var2 = new List<object>();
      var2.Add(var1);

      while((var1 = var1.gi) != null) {
         var2.Add(0, var1);
      }

      TreeModelEvent var3 = new TreeModelEvent(this, var2.toArray());
      IEnumerator var5 = this.gh.iterator();

      while(var5.hasNext()) {
         TreeModelListener var4 = (TreeModelListener)var5.next();
         var4.treeStructureChanged(var3);
      }

   }
   cI(cy var1, cI var2) {
      this(var1);
   }
}

}
