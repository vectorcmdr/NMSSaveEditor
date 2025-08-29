package nomanssave;

import java.util.Collections;

public class aJ extends em {
  private static final int cV = 200;
  
  private static final int cW = 100;
  
  private static final int cX = 100;
  
  private static final long cY = 4294967295L;
  
  private static final long cZ = 4294967295L;
  
  private static final long da = 4294967295L;
  
  private G db;
  
  private G dc;
  
  private G dd;
  
  private G de;
  
  private G df;
  
  private G dg;
  
  private bO dh;
  
  private gz di;
  
  aJ(Application paramApplication) {
    k("Main Stats");
    this.de = new aK(this);
    a("Health", this.de);
    this.df = new aL(this);
    a("Shield", this.df);
    this.dg = new aM(this);
    a("Energy", this.dg);
    this.db = new aN(this, paramApplication);
    a("Units", this.db);
    this.dc = new aO(this);
    a("Nanites", this.dc);
    this.dd = new aP(this);
    a("Quicksilver", this.dd);
    this.dh = new bO(paramApplication);
    b(this.dh);
  }
  
  void w() {
    this.dh.w();
  }
  
  void x() {
    this.dh.x();
  }
  
  void y() {
    this.dh.y();
  }
  
  void A() {
    this.dh.A();
  }
  
  void a(gt paramgt) {
    this.dh.a(paramgt);
  }
  
  gz X() {
    return this.di;
  }
  
  void a(gz paramgz) {
    if (paramgz == null) {
      this.di = null;
      this.db.setText("");
      this.dc.setText("");
      this.dd.setText("");
      this.de.setText("");
      this.df.setText("");
      this.dg.setText("");
      this.dh.a(Collections.emptyList());
    } else {
      this.di = paramgz;
      this.db.setText(Long.toString(paramgz.dJ()));
      this.dc.setText(Long.toString(paramgz.dK()));
      this.dd.setText(Long.toString(paramgz.dL()));
      this.de.setText(Integer.toString(paramgz.dM()));
      this.df.setText(Integer.toString(paramgz.dN()));
      this.dg.setText(Integer.toString(paramgz.dO()));
      this.dh.a(paramgz.cC());
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */