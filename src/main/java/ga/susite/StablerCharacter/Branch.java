package ga.susite.StablerCharacter;

public class Branch {
	/**
	 * The current dialog the branch is on.
	 */
	public int dialogIndex = 0;
	Dialog[] dialogs;
	
	public Branch() {}
	
	public Branch(Dialog[] nDialogs) {
		dialogs = nDialogs;
	}
	
	public void advanceDialogIndex() {
		dialogIndex++;
	}
	
	public Dialog getCurrentDialog() {
		return dialogs[dialogIndex];
	}
	
	public void printBranchTree() {
		for(Dialog i : dialogs) {
			System.out.println("Message: " + i.message);
			System.out.println("Event: " + i.event);
		}
	}

	public int getThisBranchLength() {
		return dialogs.length;
	}
}
