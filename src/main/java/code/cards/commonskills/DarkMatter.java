package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.PurgeHelperAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class DarkMatter extends AbstractEasyCard {
    public final static String ID = makeID("DarkMatter");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public DarkMatter() {
        super(ID, -2, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 5;
        baseMagicNumber = 7;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new PurgeHelperAction(this));
        
        applyPowers();
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (!randomMonster.isDeadOrEscaped()) {
            addToTop(new DamageAction(randomMonster, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS),AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}
  
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { 
        return false;
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(4);
    }
}