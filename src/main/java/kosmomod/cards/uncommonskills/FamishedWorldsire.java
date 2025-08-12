package kosmomod.cards.uncommonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.ExcavateAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class FamishedWorldsire extends AbstractEasyCard {
    public final static String ID = makeID("FamishedWorldsire");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public FamishedWorldsire() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 4;
        magicNumber = baseMagicNumber;
        baseSecondMagic = 2;
        secondMagic = baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ExcavateAction(this.magicNumber, this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}