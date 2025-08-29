package nomanssave;

import java.util.ArrayList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class cI implements TreeModel {
  private ArrayList gh = new ArrayList();
  
  private cI(cy paramcy) {}
  
  public Object getRoot() {
    return new cJ(this.gg, null, 0, cy.a(this.gg), cy.b(this.gg));
  }
  
  public Object getChild(Object paramObject, int paramInt) {
    return ((cJ)paramObject).x(paramInt);
  }
  
  public int getChildCount(Object paramObject) {
    return ((cJ)paramObject).getChildCount();
  }
  
  public boolean isLeaf(Object paramObject) {
    return ((cJ)paramObject).isLeaf();
  }
  
  public void valueForPathChanged(TreePath paramTreePath, Object paramObject) {}
  
  public int getIndexOfChild(Object paramObject1, Object paramObject2) {
    return ((cJ)paramObject1).indexOf(paramObject2);
  }
  
  public void addTreeModelListener(TreeModelListener paramTreeModelListener) {
    this.gh.add(paramTreeModelListener);
  }
  
  public void removeTreeModelListener(TreeModelListener paramTreeModelListener) {
    this.gh.remove(paramTreeModelListener);
  }
  
  public void a(cJ paramcJ) {
    ArrayList<cJ> arrayList = new ArrayList();
    arrayList.add(paramcJ);
    while ((paramcJ = paramcJ.gi) != null)
      arrayList.add(0, paramcJ); 
    TreeModelEvent treeModelEvent = new TreeModelEvent(this, arrayList.toArray());
    for (TreeModelListener treeModelListener : this.gh)
      treeModelListener.treeStructureChanged(treeModelEvent); 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */