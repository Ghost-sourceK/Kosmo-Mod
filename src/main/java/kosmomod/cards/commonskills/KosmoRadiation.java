package kosmomod.cards.commonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import kosmomod.KosmoModTags;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoRadiation extends AbstractEasyCard {
    public final static String ID = makeID("KosmoRadiation");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoRadiation() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 6;
        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
        addToBot(new ExhaustAction(2, true, false, false));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}