package mage.cards.p;

import mage.abilities.Ability;
import mage.abilities.condition.common.GiftWasPromisedCondition;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.keyword.GiftAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.GiftType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.targetadjustment.TargetAdjuster;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class PeerlessRecycling extends CardImpl {

    public PeerlessRecycling(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{G}");

        // Gift a card
        this.addAbility(new GiftAbility(this, GiftType.CARD));

        // Return target permanent from your graveyard to your hand. If the gift was promised, instead return two target permanent cards from your graveyard to your hand.
        this.getSpellAbility().addEffect(new ReturnFromGraveyardToHandTargetEffect()
                .setText("return target permanent from your graveyard to your hand. If the gift was promised, " +
                        "instead return two target permanent cards from your graveyard to your hand"));
        this.getSpellAbility().addTarget(new TargetCardInYourGraveyard(StaticFilters.FILTER_CARD_PERMANENT));
        this.getSpellAbility().setTargetAdjuster(PeerlessRecyclingAdjuster.instance);
    }

    private PeerlessRecycling(final PeerlessRecycling card) {
        super(card);
    }

    @Override
    public PeerlessRecycling copy() {
        return new PeerlessRecycling(this);
    }
}

enum PeerlessRecyclingAdjuster implements TargetAdjuster {
    instance;

    @Override
    public void adjustTargets(Ability ability, Game game) {
        if (GiftWasPromisedCondition.TRUE.apply(game, ability)) {
            ability.getTargets().clear();
            ability.addTarget(new TargetCardInYourGraveyard(2, StaticFilters.FILTER_CARD_PERMANENTS));
        }
    }
}