package code.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class CurlUpPower extends AbstractEasyPower {
   public static final String POWER_ID = "CurlUpPower";

   private boolean triggered = false;

   public CurlUpPower(AbstractCreature owner, int amount) {
      super(POWER_ID, "Curl Up", PowerType.BUFF, true, owner, amount);
      this.loadRegion("closeUp");
      this.updateDescription();
   }

   @Override
    public void updateDescription() {
        this.description = "First time receiving damage, gain" + this.amount + " Block.";
    }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (!this.triggered && damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageType.NORMAL) {
         this.flash();
         this.triggered = true;
         this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
         this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "CurlUpPower"));
      }

      return damageAmount;
   }
}
