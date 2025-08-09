package code.cards.uncommonskills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.cards.AbstractEasyCard;
import code.cards.special.VoidParticles;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class KosmoDuplication extends AbstractEasyCard {
    public final static String ID = makeID("KosmoDuplication");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoDuplication() {
        super(ID, -2, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new VoidParticles();
        tags.add(KosmoModTags.KOSMO);
    }

    public void triggerWhenDrawn() {
        addToTop(new MakeTempCardInHandAction(new VoidParticles(), this.magicNumber));
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (AbstractDungeon.player.hand.contains(this)) {
            addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.hand.moveToDeck(KosmoDuplication.this, false);
                    this.isDone = true;
                }
            });
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MakeTempCardInHandAction(new VoidParticles(), this.magicNumber));
    }
  
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { 
        if (AbstractDungeon.player.exhaustPile.contains(this)) {
            return true;
        }
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}