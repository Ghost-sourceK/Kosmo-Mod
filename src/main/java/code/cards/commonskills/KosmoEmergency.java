package code.cards.commonskills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.actions.DrawTaggedCardAction;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;

import static code.KosmoMod.makeID;

public class KosmoEmergency extends AbstractEasyCard {
    public final static String ID = makeID("KosmoEmergency");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoEmergency() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 6;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new DrawTaggedCardAction(KosmoModTags.KOSMO));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;

        boolean hasKosmoCard = false;
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(KosmoModTags.KOSMO)) {
                hasKosmoCard = true;
                break;
            }
        }

        if (!hasKosmoCard) {
            this.cantUseMessage = "No Kosmo cards in the draw pile.";
            canUse = false;
        }
        
        this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        return canUse;
    }

    public void upp() {
        upgradeBlock(3);
    }
}