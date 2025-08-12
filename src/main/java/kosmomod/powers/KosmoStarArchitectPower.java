package kosmomod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;

import kosmomod.actions.MakeTempCardInExhaustAction;
import kosmomod.cards.special.DormantStar;

public class KosmoStarArchitectPower extends LambdaPower {
    public static final String POWER_ID = "KosmoStarArchitectPower";

    private int upgradedStacks = 0;
    private int normalStacks = 0;

    public KosmoStarArchitectPower(AbstractCreature owner, int amount, boolean upgrade) {
        super(POWER_ID, "Kosmo Star Architect", PowerType.BUFF, true, owner, amount);
        this.img = new Texture("KosmoModResources/images/powers/ExampleTwoAmountPower32.png");
        if (upgrade) this.upgradedStacks = amount;
        else this.normalStacks = amount;

        this.amount = this.upgradedStacks + this.normalStacks;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (upgradedStacks > 0 && normalStacks > 0) {
            this.description = "At the end of your turn, add " + upgradedStacks + " Dormant Star+ and " + normalStacks + " Dormant Star into the Exhaust pile.";
        } else if (upgradedStacks > 0) {
            this.description = "At the end of your turn, add " + upgradedStacks + " Dormant Star+ into the Exhaust pile.";
        } else {
            this.description = "At the end of your turn, add " + normalStacks + " Dormant Star into the Exhaust pile.";
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.normalStacks += stackAmount;
        this.amount = this.upgradedStacks + this.normalStacks;

        this.updateDescription();
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (upgradedStacks > 0) {
            DormantStar c = new DormantStar();
            c.upgrade();
            addToBot(new MakeTempCardInExhaustAction(c, upgradedStacks, 0.6F));
        }
        if (normalStacks > 0) {
            addToBot(new MakeTempCardInExhaustAction(new DormantStar(), normalStacks, 0.6F));
        }
    }

    public void addStacks(int amt, boolean upgrade) {
        if (upgrade) this.upgradedStacks += amt;
        else this.normalStacks += amt;

        this.amount = this.upgradedStacks + this.normalStacks;
        this.updateDescription();
    }
}
