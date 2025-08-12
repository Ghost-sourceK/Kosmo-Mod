package kosmomod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.SadisticPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class TerraformingPower extends LambdaPower {
    public static final String POWER_ID = "TerraformingPower";

    public TerraformingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, "Terraforming", PowerType.BUFF, true, owner, amount);
        this.img = new Texture("KosmoModResources/images/powers/ExampleTwoAmountPower32.png");
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1){
            this.description = "At the start of your turn, gain a random Buff.";
        } else {
            this.description = "At the start of your turn, gain " + this.amount + " amount of a random Buffs.";
        }
    }

    @Override
    public void atStartOfTurn() {
        int choice = AbstractDungeon.cardRandomRng.random(13);

        switch (choice) {
            case 0:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount)));
                break;
            case 1:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount)));
                break;
            case 2:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, this.amount)));
                break;
            case 3:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new MetallicizePower(this.owner, this.amount)));
                break;
            case 4:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new EnergizedPower(this.owner, this.amount)));
                break;
            case 5:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, this.amount)));
                break;
            case 6:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, this.amount)));
                break;
            case 7:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new CurlUpPower(this.owner, this.amount)));
                break;
            case 8:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new VigorPower(this.owner, this.amount)));
                break;
            case 9:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, this.amount)));
                break;
            case 10:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, this.amount)));
                break;
            case 11:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new NoxiousFumesPower(this.owner, this.amount)));
                break;
            case 12:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new SadisticPower(this.owner, this.amount)));
                break;
            case 13:
                addToBot(new ApplyPowerAction(this.owner, this.owner, new BlurPower(this.owner, this.amount)));
                break;
        }
    }

}
