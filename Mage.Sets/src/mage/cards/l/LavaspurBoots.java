package mage.cards.l;

import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AttachmentType;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class LavaspurBoots extends CardImpl {

    public LavaspurBoots(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");

        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature gets +1/+0 and has haste and ward {1}.
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(1, 0));
        ability.addEffect(new GainAbilityAttachedEffect(
                HasteAbility.getInstance(), AttachmentType.EQUIPMENT, Duration.WhileOnBattlefield
        ).setText("and has haste"));
        ability.addEffect(new GainAbilityAttachedEffect(
                new WardAbility(new GenericManaCost(1)), AttachmentType.EQUIPMENT, Duration.WhileOnBattlefield
        ).setText("and ward {1}"));
        this.addAbility(ability);

        // Equip {1}
        this.addAbility(new EquipAbility(1, false));
    }

    private LavaspurBoots(final LavaspurBoots card) {
        super(card);
    }

    @Override
    public LavaspurBoots copy() {
        return new LavaspurBoots(this);
    }
}
