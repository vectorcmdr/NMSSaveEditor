using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cI : TreeModel {
   public List<object> gh;
   public cy gg;

   public cI(cy var1) {
      this.gg = var1;
      this.gh = new List<object>();
   }

   public object getRoot() {
      return new cJ(this.gg, (cJ)null, 0, cy.a(this.gg), cy.b(this.gg));
   }

   public object getChild(object var1, int var2) {
      return ((cJ)var1).x(var2);
   }

   public int getChildCount(object var1) {
      return ((cJ)var1).getChildCount();
   }

   public bool isLeaf(object var1) {
      return ((cJ)var1).isLeaf();
   }

   public void valueForPathChanged(TreePath var1, object var2) {
   }

   public int getIndexOfChild(object var1, object var2) {
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

      TreeModelEvent var3 = new TreeModelEvent(this, var2.ToArray());
      IEnumerator var5 = this.gh.GetEnumerator();

      while(var5.MoveNext()) {
         TreeModelListener var4 = (TreeModelListener)var5.Current;
         var4.treeStructureChanged(var3);
      }

   }
   public cI(cy var1, cI var2) {
      this(var1);
   }
}


#else

public class cI
{
   public cI() { }
   public cI(params object[] args) { }
   public List<object> gh = default;
   public cy gg = default;
   public object getRoot() { return default; }
   public object getChild(object var1, int var2) { return default; }
   public int getChildCount(object var1) { return 0; }
   public bool isLeaf(object var1) { return false; }
   public void valueForPathChanged(TreePath var1, object var2) { }
   public int getIndexOfChild(object var1, object var2) { return 0; }
   public void addTreeModelListener(TreeModelListener var1) { }
   public void removeTreeModelListener(TreeModelListener var1) { }
   public void a(cJ var1) { }
}

#endif

}
