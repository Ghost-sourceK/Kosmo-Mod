package code.cards.commonattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.actions.CheckKosmoTagAction;
import code.actions.PurgeAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class KosmoDelta extends AbstractEasyCard {
    public final static String ID = makeID("KosmoDelta");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoDelta() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 9;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new DrawCardAction(1, new CheckKosmoTagAction()));
        addToBot(new PurgeAction(1));
    }

    public void upp() {
        upgradeDamage(3);
    }
}