package ga.susite.StablerCharacter;

import java.util.HashMap;
import java.util.Map.Entry;

public class Chapter {
	/**
	 * The chapter name.
	 */
	public String name;
	/**
	 * The chapter description.
	 */
	public String description;
	/**
	 * The name of the entry branch.
	 */
	public String currentBranchName = "main";
	HashMap<String, Branch> branches;
	
	/**
	 * @param nBranches The HashMap of the branches. Structured in a branchName:value structure.
	 * @param nName The chapter name.
	 * @param nDescription The chapter description.
	 */
	public Chapter(HashMap<String, Branch> nBranches, String nName, String nDescription) {
		branches = nBranches;
		name = nName;
		description = nDescription;
	}

	public Dialog getCurrentDialog() {
		return branches.get(currentBranchName).getCurrentDialog();
	}

	public void advanceDialog() {
		branches.get(currentBranchName).advanceDialogIndex();
	}
	
	public void changeBranch(String nBranch) {
		currentBranchName = nBranch;
	}
	
	public int getDialogIndex() {
		return branches.get(currentBranchName).dialogIndex;
	}
	
	public void setDialogIndex(int index) {
		branches.get(currentBranchName).dialogIndex = index;
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

	public int getCurrentBranchLength() {
		return branches.get(currentBranchName).getThisBranchLength();
	}
}
