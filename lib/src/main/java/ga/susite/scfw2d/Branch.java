package ga.susite.scfw2d;

/**
 * The story branch. Stores dialogs.
 */
public class Branch {
	/**
	 * The current dialog the branch is on.
	 */
	public int dialogIndex = 0;
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
			System.out.println("Message: " + i.message);
			System.out.println("Event(s): " + i.events.toString());
		}
	}

	/**
	 * Returns the length of the branch. (In other words, return the number of dialogs existed.)
	 * @return The number of dialogs existed in the branch that existed.
	 */
	public int getThisBranchLength() {
		return dialogs.length;
	}
}
