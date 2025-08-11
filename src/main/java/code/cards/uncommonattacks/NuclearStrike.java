package code.cards.uncommonattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import code.actions.MakeTempCardInExhaustAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class NuclearStrike extends AbstractEasyCard {
    public final static String ID = makeID("NuclearStrike");
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public NuclearStrike() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 16;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new VFXAction(new WeightyImpactEffect(randomMonster.hb.cX, randomMonster.hb.cY), 0.75F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ExhaustAction(1, true, false, false));
        addToBot(new MakeTempCardInExhaustAction(new Dazed(), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(-1);
    }
}