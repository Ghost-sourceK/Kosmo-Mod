package code.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EchoOfTheVoidPower extends LambdaPower {
    public static final String POWER_ID = "EchoOfTheVoidPower";

    private boolean triggeredThisTurn = false;

    public EchoOfTheVoidPower(AbstractCreature owner, int amount) {
        super(POWER_ID, "Echo Of The Void", PowerType.BUFF, true, owner, amount);
        this.img = new Texture("KosmoModResources/images/powers/ExampleTwoAmountPower32.png");
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "First time a card is Exhausted or Purged, gain" + this.amount + " Strength.";
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (!triggeredThisTurn) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount)));
            triggeredThisTurn = true;
        }
    }

    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = false;
    }
}