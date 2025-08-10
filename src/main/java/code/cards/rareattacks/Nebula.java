package code.cards.rareattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.vfx.GreenEnergyEffect;
import code.KosmoModTags;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class Nebula extends AbstractEasyCard {
    public final static String ID = makeID("Nebula");
    
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Nebula() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 7;
        this.exhaust = true;
        tags.add(KosmoModTags.GRAVE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GreenEnergyEffect(m));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    public void upp() {
        upgradeDamage(3);
    }
}