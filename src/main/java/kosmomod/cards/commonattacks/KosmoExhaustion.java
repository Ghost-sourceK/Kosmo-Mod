package kosmomod.cards.commonattacks;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import kosmomod.KosmoModTags;
import kosmomod.actions.CheckKosmoTagAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoExhaustion extends AbstractEasyCard {
    public final static String ID = makeID("KosmoExhaustion");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoExhaustion() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 5;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new DrawCardAction(1, new CheckKosmoTagAction()));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -1)));
    }

    public void upp() {
        upgradeDamage(3);
    }
}