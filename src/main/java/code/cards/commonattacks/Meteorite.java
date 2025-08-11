package code.cards.commonattacks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.actions.PurgeHelperAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import code.vfx.MissileStrikeEffect;

import static code.KosmoMod.makeID;

public class Meteorite extends AbstractEasyCard {
    public final static String ID = makeID("Meteorite");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Meteorite() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 6;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new MissileStrikeEffect(m.hb.cX + 240.0F * Settings.scale, m.hb.cY + 250.0F * Settings.scale, MathUtils.random(-40.0F, -50.0F), Color.RED)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void triggerOnExhaust() {
        Meteorite copy = (Meteorite) this.makeStatEquivalentCopy();

        copy.baseDamage += this.magicNumber;
        copy.damage = copy.baseDamage;
        copy.applyPowers();
        addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction(copy, 1));

        isPurging = true;
        addToBot(new PurgeHelperAction(this));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}