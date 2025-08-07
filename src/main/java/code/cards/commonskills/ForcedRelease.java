package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.CheckKosmoTagAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class ForcedRelease extends AbstractEasyCard {
    public final static String ID = makeID("ForcedRelease");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public ForcedRelease() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 4;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(2, new CheckKosmoTagAction()));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void upp() {
        upgradeBlock(2);
    }
}