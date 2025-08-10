package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class HyperdriveMode extends AbstractEasyCard {
    public final static String ID = makeID("HyperdriveMode");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public HyperdriveMode() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new GainEnergyAction(1));
        if(this.upgraded){
            addToTop(new ApplyPowerAction(m, p, new EnergizedPower(p, 1)));
        }
        addToTop(new MakeTempCardInDiscardAction(new Dazed(), 2));
    }

    public void upp() {
        upgradeBlock(2);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}