package code.cards.special;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.CheckKosmoTagAction;
import code.actions.PurgeHelperAction;
import code.cards.AbstractEasyCard;
import static code.KosmoMod.makeID;

public class VoidParticles extends AbstractEasyCard {
    public final static String ID = makeID("VoidParticles");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

    public VoidParticles() {
        super(ID, -2, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 4;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
        addToBot(new DrawCardAction(this.magicNumber, new CheckKosmoTagAction()));
        addToBot(new PurgeHelperAction(this));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}
  
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { 
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeBlock(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}