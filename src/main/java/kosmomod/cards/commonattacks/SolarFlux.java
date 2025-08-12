package kosmomod.cards.commonattacks;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.CheckKosmoTagAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class SolarFlux extends AbstractEasyCard {
    public final static String ID = makeID("SolarFlux");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public SolarFlux() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 7;
        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(1, new CheckKosmoTagAction(m, this.magicNumber, true)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}