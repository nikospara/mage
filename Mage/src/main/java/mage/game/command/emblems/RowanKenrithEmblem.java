package mage.game.command.emblems;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.common.CopyStackObjectEffect;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.command.Emblem;
import mage.game.events.GameEvent;
import mage.game.stack.StackAbility;

/**
 * @author TheElk801
 */
public final class RowanKenrithEmblem extends Emblem {
    // Target player gets an emblem with "Whenever you activate an ability that isn't a mana ability, copy it. You may choose new targets for the copy."

    public RowanKenrithEmblem() {
        super("Emblem Rowan Kenrith");
        this.getAbilities().add(new RowanKenrithEmblemTriggeredAbility());
    }

    private RowanKenrithEmblem(final RowanKenrithEmblem card) {
        super(card);
    }

    @Override
    public RowanKenrithEmblem copy() {
        return new RowanKenrithEmblem(this);
    }
}

class RowanKenrithEmblemTriggeredAbility extends TriggeredAbilityImpl {

    RowanKenrithEmblemTriggeredAbility() {
        super(Zone.COMMAND, new CopyStackObjectEffect(), false);
    }

    private RowanKenrithEmblemTriggeredAbility(final RowanKenrithEmblemTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public RowanKenrithEmblemTriggeredAbility copy() {
        return new RowanKenrithEmblemTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ACTIVATED_ABILITY;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (!event.getPlayerId().equals(getControllerId())) {
            return false;
        }
        StackAbility stackAbility = (StackAbility) game.getStack().getStackObject(event.getSourceId());
        if (stackAbility == null || stackAbility.getStackAbility().isManaActivatedAbility()) {
            return false;
        }
        this.getEffects().setValue("stackObject", stackAbility);
        return true;
    }

    @Override
    public String getRule() {
        return "Whenever you activate an ability that isn't a mana ability, copy it. You may choose new targets for the copy.";
    }
}
