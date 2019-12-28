package mage.cards.i;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.DevotionCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.PreventAllDamageToAllEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.effects.common.continuous.LoseCreatureTypeSourceEffect;
import mage.abilities.hint.ValueHint;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.AttackingPredicate;
import mage.filter.predicate.permanent.ControllerPredicate;

import java.util.UUID;

/**
 * @author LevelX2
 */
public final class IroasGodOfVictory extends CardImpl {

    private static final FilterPermanent filter
            = new FilterCreaturePermanent("Creatures you control");
    private static final FilterPermanent filterAttacking
            = new FilterControlledCreaturePermanent("attacking creatures you control");

    static {
        filter.add(new ControllerPredicate(TargetController.YOU));
        filterAttacking.add(AttackingPredicate.instance);
    }

    public IroasGodOfVictory(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT, CardType.CREATURE}, "{2}{R}{W}");
        addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.GOD);

        this.power = new MageInt(7);
        this.toughness = new MageInt(4);

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // As long as your devotion to red and white is less than seven, Iroas isn't a creature.
        Effect effect = new LoseCreatureTypeSourceEffect(DevotionCount.RW, 7);
        effect.setText("As long as your devotion to red and white is less than seven, {this} isn't a creature");
        this.addAbility(new SimpleStaticAbility(effect).addHint(new ValueHint("Devotion to red and white", DevotionCount.RW)));

        // Creatures you control have menace. (They can't be blocked except by two or more creatures.)
        this.addAbility(new SimpleStaticAbility(
                new GainAbilityAllEffect(new MenaceAbility(), Duration.WhileOnBattlefield, filter)
        ));

        // Prevent all damage that would be dealt to attacking creatures you control.
        this.addAbility(new SimpleStaticAbility(
                new PreventAllDamageToAllEffect(Duration.WhileOnBattlefield, filterAttacking)
        ));
    }

    private IroasGodOfVictory(final IroasGodOfVictory card) {
        super(card);
    }

    @Override
    public IroasGodOfVictory copy() {
        return new IroasGodOfVictory(this);
    }
}
