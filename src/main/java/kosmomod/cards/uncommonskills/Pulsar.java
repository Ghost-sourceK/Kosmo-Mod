package kosmomod.cards.uncommonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.PurgeAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class Pulsar extends AbstractEasyCard {
    public final static String ID = makeID("Pulsar");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Pulsar() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 5;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PurgeAction(3, false,true, this.magicNumber, true));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}