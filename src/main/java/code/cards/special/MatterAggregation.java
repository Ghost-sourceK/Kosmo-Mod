package code.cards.special;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.cards.AbstractEasyCard;
import static code.KosmoMod.makeID;

public class MatterAggregation extends AbstractEasyCard {
    public final static String ID = makeID("MatterAggregation");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

    private boolean endOfTurnExhaust = false;

    public MatterAggregation() {
        super(ID, -2, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 4;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.isEthereal) {
            endOfTurnExhaust = true;
        }
    }

    @Override
    public void triggerOnExhaust() {
        if (!endOfTurnExhaust) {
            addToBot(new GainEnergyAction(1));
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
        } else {
            addToBot(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.2F));
            addToBot(new code.actions.PurgeHelperAction(this));
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}
  
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { 
        return false;
    }

    public void upp() {
        upgradeBlock(2);
    }
}