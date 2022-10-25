package org.centrale.objet.WoE;

/**
 * The class that implements this interface has the combat property.
 */
public interface Combattant {
    /**
     * Attack a creature in the world, leaving it to the subclasses to concretely implement.
     * @param c The attacked creature
     */
    void combattre(Creature c);
}
