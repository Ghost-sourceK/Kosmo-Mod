package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.actions.CheckKosmoTagAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class KosmoVoid extends AbstractEasyCard {
    public final static String ID = makeID("KosmoVoid");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoVoid() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(1, new CheckKosmoTagAction()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}