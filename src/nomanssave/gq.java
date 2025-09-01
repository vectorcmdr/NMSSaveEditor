package nomanssave;

public enum gq {
   oS("Combat"),
   oT("Exploration"),
   oU("Industry"),
   oV("Trading"),
   oW("Cost Per Warp"),
   oX("Expedition Fuel Cost", -1),
   oY("Expedition Duration", -1),
   oZ("Loot"),
   pa("Repair"),
   pb("Damage Reduction"),
   pc("Stealth");

   private String name;
   private int pd;

   private gq(String var3) {
      this(var3, 1);
   }

   private gq(String var3, int var4) {
      this.name = var3;
      this.pd = var4;
   }

   public int di() {
      return this.pd;
   }

   public String toString() {
      return this.name;
   }
}
