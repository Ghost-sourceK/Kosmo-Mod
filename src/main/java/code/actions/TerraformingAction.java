package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import code.powers.TerraformingPower;

public class TerraformingAction extends AbstractGameAction {
  private boolean freeToPlayOnce = false;
  private boolean upgraded = false;
  private AbstractPlayer p;
  private int energyOnUse = -1;
  
  public TerraformingAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
    this.p = p;
    this.freeToPlayOnce = freeToPlayOnce;
    this.upgraded = upgraded;
    this.duration = Settings.ACTION_DUR_XFAST;
    this.actionType = AbstractGameAction.ActionType.SPECIAL;
    this.energyOnUse = energyOnUse;
  }
  
  public void update() {
    int effect = EnergyPanel.totalCount;
    if (this.energyOnUse != -1)
      effect = this.energyOnUse; 
    if (this.p.hasRelic("Chemical X")) {
      effect += 2;
      this.p.getRelic("Chemical X").flash();
    }
    if (this.upgraded)
      effect += 1;
    if (effect > 0) {
      addToBot(new ApplyPowerAction(p, p, new TerraformingPower(p, effect)));
      if (!this.freeToPlayOnce)
        this.p.energy.use(EnergyPanel.totalCount);
    } 
    this.isDone = true;
  }
}