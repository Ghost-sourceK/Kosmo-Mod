package code.cards.rareskills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.cards.AbstractEasyCard;
import code.character.KosmoCharacter;
import code.powers.KosmoSanctuaryPower;

import static code.KosmoMod.makeID;

public class KosmoSanctuary extends AbstractEasyCard {
    public final static String ID = makeID("KosmoSanctuary");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public KosmoSanctuary() {
        super(ID, 1, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
        tags.add(KosmoModTags.KOSMO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new KosmoSanctuaryPower(p, this.magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}