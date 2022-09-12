package ga.susite.scfw2d;

import tech.fastj.engine.FastJEngine;

import lombok.Getter;
import lombok.Setter;

/**
 * The story branch. Stores dialogs.
 */
public class Branch {
	/**
	 * The current dialog the branch is on.
	 */
	@Getter @Setter private int dialogIndex = 0;
	Dialog[] dialogs;
	
	/**
	 * Initialize an empty branch.
	 */
	public Branch() {}
	
	/**
	 * Initialize a branch with the specified dialogs.
	 */
	public Branch(Dialog[] nDialogs) {
		dialogs = nDialogs;
	}

	/**
	 * Advance to the next dialog.
	 */
	public void advanceDialogIndex() {
		dialogIndex++;
	}
	
	/**
	 * Get the current dialog.
	 * @return The current dialog.
	 */
	public Dialog getCurrentDialog() {
		return dialogs[dialogIndex];
	}
	
	/**
	 * Print a tree representation of the branch.
	 */
	public void printBranchTree() {
		for(Dialog i : dialogs) {
			FastJEngine.log("Message: " + i.message);
			FastJEngine.log("Event(s): " + i.events.toString());
		}
	}

	/**
	 * Returns the length of the branch. (In other words, return the number of dialogs existed.)
	 * @return The number of dialogs existed in the branch that existed.
	 */
	public int getThisBranchLength() {
		return dialogs.length;
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || getClass() != other.getClass()) return false;
		Branch branch = (Branch)other;
		if(dialogIndex != branch.dialogIndex) return false;
		if(dialogs.length != branch.dialogs.length) return false;
		for(int i = 0; i < dialogs.length; i++) {
			if(!dialogs[i].equals(branch.dialogs[i])) return false;
		}
		return true;
	}
}
