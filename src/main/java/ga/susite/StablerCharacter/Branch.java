package ga.susite.StablerCharacter;

public class Branch {
	Dialog[] dialogs;
	int dialogIndex = 0;
	
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
	
	public void setDialogIndex(int index) {
		dialogIndex = index;
	}
	
	public void printBranchTree() {
		for(Dialog i : dialogs) {
			System.out.println("Message: " + i.message);
			System.out.println("Event: " + i.event);
		}
	}
}
