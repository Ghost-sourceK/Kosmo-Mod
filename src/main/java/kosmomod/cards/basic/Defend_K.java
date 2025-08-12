package kosmomod.cards.basic;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class Defend_K extends AbstractEasyCard {
    public final static String ID = makeID("Defend_K");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Defend_K() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void upp() {
        upgradeBlock(3);
    }
}