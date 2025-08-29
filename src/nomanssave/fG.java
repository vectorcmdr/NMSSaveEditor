package nomanssave;

import java.util.Comparator;

class fG implements Comparator {
  fG(fE paramfE) {}
  
  public int a(fs paramfs1, fs paramfs2) {
    long l = paramfs2.lastModified() - paramfs1.lastModified();
    return (l < -2147483648L) ? Integer.MIN_VALUE : ((l > 2147483647L) ? Integer.MAX_VALUE : (int)l);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */