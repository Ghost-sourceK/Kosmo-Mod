package kosmomod.cards.uncommonattacks;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.actions.CheckKosmoTagAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.cards.special.VoidParticles;
import kosmomod.character.KosmoCharacter;

public class Kosmology extends AbstractEasyCard {
    public final static String ID = makeID("Kosmology");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public Kosmology() {
        super(ID, 2, TYPE, RARITY, TARGET, COLOR);

        baseDamage = 15;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        this.cardsToPreview = new VoidParticles();
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new DrawCardAction(1, new CheckKosmoTagAction()));
        addToTop(new MakeTempCardInDrawPileAction(new VoidParticles(), this.magicNumber, true, true, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}