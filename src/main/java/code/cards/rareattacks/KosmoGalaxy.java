package code.cards.rareattacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.actions.PlayFromExhaustAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

public class KosmoGalaxy extends AbstractEasyCard {
    public final static String ID = makeID("KosmoGalaxy");
    
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoGalaxy() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);
        
        baseDamage = 10;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new PlayFromExhaustAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}