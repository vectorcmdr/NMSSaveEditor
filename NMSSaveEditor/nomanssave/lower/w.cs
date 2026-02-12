using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class w : Runnable {
   // $FF: synthetic field
   public bool ba;

   public w(bool var1) {
      this.ba = var1;
   }

   public void run() {
      try {
         Application.g(new Application(this.ba, (Application)null));
         Application.h(Application.H()).SetVisible(true);
      } catch (Exception var2) {
         var2.printStackTrace();
         Environment.Exit(1);
      }

   }
}

}
