package kosmomod.cards.commonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.actions.PurgeCardExhaustPileAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoNuclearFusion extends AbstractEasyCard {
    public final static String ID = makeID("KosmoNuclearFusion");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoNuclearFusion() {
        super(ID, 0, TYPE, RARITY, TARGET, COLOR);

        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PurgeCardExhaustPileAction(1));
        addToBot(new GainEnergyAction(1));
        if(upgraded){
            addToBot(new DrawCardAction(1));
        }
    }

    public void upp() {
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}