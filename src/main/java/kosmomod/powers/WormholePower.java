package kosmomod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import kosmomod.cards.special.MatterAggregation;

public class WormholePower extends LambdaPower {
    public static final String POWER_ID = "WormholePower";

    public WormholePower(AbstractCreature owner, int amount) {
        super(POWER_ID, "Wormhole", PowerType.BUFF, true, owner, amount);
        this.img = new Texture("KosmoModResources/images/powers/ExampleTwoAmountPower32.png");
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "At the start of your turn, add " + this.amount + " Matter Aggregation into your hand.";
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new MakeTempCardInHandAction(new MatterAggregation(), this.amount));
    }


}
