package kosmomod.cards.power;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.cards.AbstractEasyCard;
import kosmomod.cards.special.MatterAggregation;
import kosmomod.character.KosmoCharacter;
import kosmomod.powers.WormholePower;

public class Wormhole extends AbstractEasyCard {
  public static final String ID = makeID("Wormhole");

  private static final CardRarity RARITY = CardRarity.UNCOMMON;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

  public Wormhole() {
    super(ID, 2, TYPE, RARITY, TARGET, COLOR);

    baseMagicNumber = 1;
    magicNumber = baseMagicNumber;
    this.cardsToPreview = new MatterAggregation();
  }
  
  public void use(AbstractPlayer p, AbstractMonster m) {
    addToBot(new ApplyPowerAction(p, p, new WormholePower(p, this.magicNumber)));
  }

  public void upp() {
    upgradeBaseCost(1);
  }
}
