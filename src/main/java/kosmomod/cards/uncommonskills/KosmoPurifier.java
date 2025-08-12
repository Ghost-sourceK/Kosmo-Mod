package kosmomod.cards.uncommonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.actions.StatusPurifierAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class KosmoPurifier extends AbstractEasyCard {
    public final static String ID = makeID("KosmoPurifier");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoPurifier() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseBlock = 9;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new StatusPurifierAction(this.magicNumber));

    }

    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }
}