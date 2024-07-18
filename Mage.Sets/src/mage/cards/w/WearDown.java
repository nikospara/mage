package mage.cards.w;

import mage.abilities.Ability;
import mage.abilities.condition.common.GiftWasPromisedCondition;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.keyword.GiftAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.GiftType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.target.TargetPermanent;
import mage.target.targetadjustment.TargetAdjuster;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class WearDown extends CardImpl {

    public WearDown(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{G}");

        // Gift a card
        this.addAbility(new GiftAbility(this, GiftType.CARD));

        // Destroy target artifact or enchantment. If the gift was promised, instead destroy two target artifacts and/or enchantments.
        this.getSpellAbility().addEffect(new DestroyTargetEffect().setText("destroy target artifact or enchantment. " +
                "If the gift was promised, instead destroy two target artifacts and/or enchantments"));
        this.getSpellAbility().addTarget(new TargetPermanent(StaticFilters.FILTER_PERMANENT_ARTIFACT_OR_ENCHANTMENT));
        this.getSpellAbility().setTargetAdjuster(WearDownAdjuster.instance);
    }

    private WearDown(final WearDown card) {
        super(card);
    }

    @Override
    public WearDown copy() {
        return new WearDown(this);
    }
}

enum WearDownAdjuster implements TargetAdjuster {
    instance;

    @Override
    public void adjustTargets(Ability ability, Game game) {
        if (GiftWasPromisedCondition.TRUE.apply(game, ability)) {
            ability.getTargets().clear();
            ability.addTarget(new TargetPermanent(2, StaticFilters.FILTER_PERMANENT_ARTIFACT_OR_ENCHANTMENT));
        }
    }
}