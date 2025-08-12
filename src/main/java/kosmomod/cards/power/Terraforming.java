package kosmomod.cards.power;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.actions.TerraformingAction;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.character.KosmoCharacter;

public class Terraforming extends AbstractEasyCard {
  public static final String ID = makeID("Terraforming");

  private static final CardRarity RARITY = CardRarity.RARE;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

  public Terraforming() {
    super(ID, -1, TYPE, RARITY, TARGET, COLOR);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m) {
    addToBot(new TerraformingAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
  }

  public void upp() {
    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    initializeDescription();
  }
}
