package code.cards.uncommonattacks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import code.vfx.LaserEffect;

import static code.KosmoMod.makeID;

public class KosmoTroopers extends AbstractEasyCard {
    public final static String ID = makeID("KosmoTroopers");
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoTroopers() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 2;
        baseMagicNumber = 4;
        magicNumber = baseMagicNumber;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            addToBot(new VFXAction(new LaserEffect(randomMonster.hb.cX + 240.0F * Settings.scale, randomMonster.hb.cY + 250.0F * Settings.scale, MathUtils.random(10.0F, -10.0F), Color.GRAY)));
            addToBot(new DamageAction(randomMonster, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
        
    public void upp() {
        upgradeMagicNumber(2);
    }
}