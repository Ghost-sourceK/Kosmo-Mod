package kosmomod.cards.power;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;
import kosmomod.powers.EchoOfTheVoidPower;

public class EchoOfTheVoid extends AbstractEasyCard {
  public static final String ID = makeID("EchoOfTheVoid");

  private static final CardRarity RARITY = CardRarity.UNCOMMON;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

  public EchoOfTheVoid() {
    super(ID, 2, TYPE, RARITY, TARGET, COLOR);

    baseMagicNumber = 1;
    magicNumber = baseMagicNumber;
    this.isInnate = false;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m) {
    addToBot(new ApplyPowerAction(p, p, new EchoOfTheVoidPower(p, this.magicNumber)));
  }

  public void upp() {
    this.isInnate = true;
    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    initializeDescription();
  }
}
