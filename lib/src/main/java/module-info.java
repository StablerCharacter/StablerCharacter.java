/** 
 * The StablerCharacter.java (2D) module. (Or, "scfw2d" for the shorter name)
 * StablerCharacter.java contains several packages:
 * <ol>
 * 	<li>{@link ga.susite.scfw2d} -- The main engine itself.</li>
 * 	<li>{@link ga.susite.scfw2d.events} -- It stores all of the events in StablerCharacter. Probably will be your most used package.</li>
 * 	<li>{@link ga.susite.scfw2d.utils} -- Utility classes!</li>
 * </ol>
*/
module ga.susite.scfw2d {
    requires transitive fastj.library;
    requires transitive java.desktop;
    requires lombok;
    exports ga.susite.scfw2d;
    exports ga.susite.scfw2d.events;
    exports ga.susite.scfw2d.utils;
}