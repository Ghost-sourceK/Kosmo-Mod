package code.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;

import code.actions.RetrieveFromExhaustAction;

public class KosmoHorizonFormPower extends LambdaPower {
    public static final String POWER_ID = "KosmoHorizonFormPower";

    public KosmoHorizonFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, "Kosmo Horizon Form", PowerType.BUFF, true, owner, amount);
        this.img = new Texture("KosmoModResources/images/powers/ExampleTwoAmountPower32.png");
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if(this.amount > 1){
            this.description = "At the start of your turn, put " + this.amount + " cards from your Exhaust pile into your hand.";
        } else {
            this.description = "At the start of your turn, put " + this.amount + " card from your Exhaust pile into your hand.";
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new RetrieveFromExhaustAction(this.amount));
    }
}