package kosmomod.cards.uncommonskills;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class SBSP extends AbstractEasyCard {
    public final static String ID = makeID("SBSP");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

    public SBSP() {
        super(ID, -2, TYPE, RARITY, TARGET, COLOR);

        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.magicNumber)));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.magicNumber)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}
  
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { 
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}