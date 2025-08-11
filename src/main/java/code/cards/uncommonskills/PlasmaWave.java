package code.cards.uncommonskills;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.MakeTempCardInExhaustAction;
import code.actions.PlayFromExhaustAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class PlasmaWave extends AbstractEasyCard {
    public final static String ID = makeID("PlasmaWave");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public PlasmaWave() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
        baseSecondMagic = 4;
        secondMagic = baseSecondMagic;
        this.cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new PlayFromExhaustAction(this.magicNumber, true));
        addToBot(new MakeTempCardInExhaustAction(new Dazed(), this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(-1);
    }
}