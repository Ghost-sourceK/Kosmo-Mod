package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.CheckKosmoTagAction;
import code.actions.PurgeHelperAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class Supernova extends AbstractEasyCard {
    public final static String ID = makeID("Supernova");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Supernova() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 7;
        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DrawCardAction(this.magicNumber, new CheckKosmoTagAction()));
        addToBot(new PurgeHelperAction(this));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}