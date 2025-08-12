package kosmomod.cards.commonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.MultipleExhumeAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class Exoplanet extends AbstractEasyCard {
    public final static String ID = makeID("Exoplanet");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Exoplanet() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new MultipleExhumeAction(1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}