package kosmomod.cards.special;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.actions.CheckKosmoTagAction;
import kosmomod.cards.AbstractEasyCard;

public class DormantStar extends AbstractEasyCard {
    public final static String ID = makeID("DormantStar");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

    public DormantStar() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 3;
        baseDamage = 9;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        tags.add(KosmoModTags.EXIMPOSSIBLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (!randomMonster.isDeadOrEscaped()) {
            addToBot(new DamageAction(randomMonster, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.FIRE));
        }
        addToBot(new DrawCardAction(this.magicNumber, new CheckKosmoTagAction()));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeDamage(3);
        upgradeMagicNumber(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
