package code.cards.uncommonskills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class Hyperleap extends AbstractEasyCard {
    public final static String ID = makeID("Hyperleap");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Hyperleap() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        baseSecondMagic = 1;
        secondMagic = baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(this.magicNumber));
        addToBot(new ExhaustAction(this.secondMagic, false));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}