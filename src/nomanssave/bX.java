package nomanssave;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

class bX extends MouseAdapter {
  bX(bS parambS, int paramInt1, int paramInt2) {}
  
  public void mouseReleased(MouseEvent paramMouseEvent) {
    if (!bO.a(bS.j(this.fk)).h(this.fl, this.fm) || bO.a(bS.j(this.fk)).l(this.fl, this.fm))
      return; 
    int i = UIManager.getInt("Inventory.gridSize");
    int j = this.fl + (int)Math.floor(paramMouseEvent.getX() / i);
    int k = this.fm + (int)Math.floor(paramMouseEvent.getY() / i);
    if (j < 0 || j >= bO.a(bS.j(this.fk)).getWidth())
      return; 
    if (k < 0 || k >= bO.a(bS.j(this.fk)).getHeight())
      return; 
    if (j == this.fl && k == this.fm)
      return; 
    bS bS1 = bO.a(bS.j(this.fk), j, k);
    if (bS1 == null || !bS.e(bS1) || bS.f(bS1))
      return; 
    if (paramMouseEvent.isControlDown()) {
      bO.a(bS.j(this.fk)).a(this.fl, this.fm, j, k);
    } else {
      bO.a(bS.j(this.fk)).b(this.fl, this.fm, j, k);
    } 
    bS.c(this.fk);
    bS.c(bS1);
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent) {
    if (paramMouseEvent.getClickCount() == 2) {
      gu gu = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (gu != null) {
        cg.a(bS.j(this.fk), gu);
        bS.c(this.fk);
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */