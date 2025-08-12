package kosmomod.cards.commonattacks;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;

import kosmomod.KosmoModTags;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoEcplisper extends AbstractEasyCard {
    public final static String ID = makeID("KosmoEcplisper");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoEcplisper() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 8;
        this.isMultiDamage = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_DEFECT_BEAM"));
        addToBot(new VFXAction(p, new SweepingBeamEffect(p.hb.cX, p.hb.cY, p.flipHorizontal), 0.4F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ExhaustAction(1, false));
    }

    public void upp() {
        upgradeDamage(3);
    }
}