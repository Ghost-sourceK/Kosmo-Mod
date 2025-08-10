package code.cards.commonattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.MakeTempCardInExhaustAction;
import code.actions.PlayFromExhaustAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class SingularityMealtdown extends AbstractEasyCard {
    public final static String ID = makeID("SingularityMealtdown");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public SingularityMealtdown() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 4;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToTop(new PlayFromExhaustAction(1, true));
        addToBot(new MakeTempCardInExhaustAction(new Dazed(), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(-1);
    }
}