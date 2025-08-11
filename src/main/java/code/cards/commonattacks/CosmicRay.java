package code.cards.commonattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;

import code.cards.AbstractEasyCard;
import code.cards.special.MatterAggregation;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class CosmicRay extends AbstractEasyCard {
    public final static String ID = makeID("CosmicRay");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public CosmicRay() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 8;
        this.cardsToPreview = new MatterAggregation();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BloodShotEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 1), 0.7F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new MakeTempCardInDiscardAction(new MatterAggregation(), 1));
    }

    public void upp() {
        upgradeDamage(4);
    }
}