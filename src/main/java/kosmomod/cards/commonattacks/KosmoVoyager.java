package kosmomod.cards.commonattacks;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.actions.CheckKosmoTagAction;
import kosmomod.actions.PurgeAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoVoyager extends AbstractEasyCard {
    public final static String ID = makeID("KosmoVoyager");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoVoyager() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 15;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new DrawCardAction(2, new CheckKosmoTagAction()));
        addToBot(new PurgeAction(1));
    }

    public void upp() {
        upgradeDamage(5);
    }
}