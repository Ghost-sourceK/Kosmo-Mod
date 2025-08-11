package code.cards.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.actions.CheckKosmoTagAction;
import code.cards.AbstractEasyCard;
import static code.KosmoMod.makeID;

public class DormantStar extends AbstractEasyCard {
    public final static String ID = makeID("DormantStar");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

    public DormantStar() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);

        baseDamage = 11;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        tags.add(KosmoModTags.EXIMPOSSIBLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (!randomMonster.isDeadOrEscaped()) {
            addToTop(new DamageAction(randomMonster, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.FIRE));
        }
        addToBot(new DrawCardAction(this.magicNumber, new CheckKosmoTagAction()));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
