package ga.susite.StablerCharacter;

import java.util.HashMap;
import java.util.Map.Entry;

public class Chapter {
	public String name;
	public String description;
	HashMap<String, Branch> branches;
	String currentBranchName = "main";
	
	Chapter() {}
	
	Chapter(HashMap<String, Branch> nBranches, String nName, String nDescription) {
		branches = nBranches;
		name = nName;
		description = nDescription;
	}

	public Dialog getCurrentDialog() {
		// TODO Auto-generated method stub
		return branches.get(currentBranchName).getCurrentDialog();
	}

	public void advanceDialog() {
		// TODO Auto-generated method stub
		branches.get(currentBranchName).advanceDialogIndex();
	}
	
	public void changeBranch(String nBranch) {
		currentBranchName = nBranch;
	}
	
	/*
	 * Print the chapter story tree.
	 * Mainly used in Debugging feature. */
	public void printChapterTree() {
		for(Entry<String, Branch> set : branches.entrySet()) {
			System.out.println("-- Branch " + set.getKey());
			set.getValue().printBranchTree();
		}
	}
}
