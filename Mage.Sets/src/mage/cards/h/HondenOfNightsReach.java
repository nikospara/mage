package mage.cards.h;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.constants.TargetController;
import mage.filter.common.FilterControlledPermanent;
import mage.target.common.TargetOpponent;

/**
 * @author Loki
 */
public final class HondenOfNightsReach extends CardImpl {

    static final FilterControlledPermanent filter = new FilterControlledPermanent("Shrine you control");

    static {
        filter.add(SubType.SHRINE.getPredicate());
    }

    public HondenOfNightsReach(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{3}{B}");
        addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.SHRINE);

        // At the beginning of your upkeep, target opponent discards a card for each Shrine you control.
        Ability ability = new BeginningOfUpkeepTriggeredAbility(new DiscardTargetEffect(new PermanentsOnBattlefieldCount(filter)), TargetController.YOU, false);
        ability.addTarget(new TargetOpponent());
        this.addAbility(ability);
    }

    public HondenOfNightsReach(final HondenOfNightsReach card) {
        super(card);
    }

    @Override
    public HondenOfNightsReach copy() {
        return new HondenOfNightsReach(this);
    }

}
