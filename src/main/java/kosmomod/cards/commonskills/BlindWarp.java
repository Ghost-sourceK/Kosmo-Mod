package kosmomod.cards.commonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.MakeTempCardInExhaustAction;
import kosmomod.actions.MultipleExhumeAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class BlindWarp extends AbstractEasyCard {
    public final static String ID = makeID("BlindWarp");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public BlindWarp() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
        this.cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MultipleExhumeAction(this.magicNumber, true));
        addToBot(new MakeTempCardInExhaustAction(new Dazed(), 2));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}