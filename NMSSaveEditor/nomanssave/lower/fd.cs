using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class fd : Exception {
   public fd() {
   }
   public fd(fd var1) {
      this();
   }
}


#else

public class fd
{
   public fd() { }
   public fd(params object[] args) { }
}

#endif

}
