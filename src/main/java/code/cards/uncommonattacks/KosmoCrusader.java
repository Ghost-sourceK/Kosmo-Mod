package code.cards.uncommonattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;

import code.KosmoModTags;
import code.actions.CheckKosmoTagAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class KosmoCrusader extends AbstractEasyCard {
    public final static String ID = makeID("KosmoCrusader");
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoCrusader() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 14;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY), 0.4F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new DrawCardAction(this.magicNumber, new CheckKosmoTagAction()));
        addToTop(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}