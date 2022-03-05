package ga.susite.scfw2d;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Represents a chapter of a game. Stores branches.
 */
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

	/**
	 * Get the current dialog.
	 * @return The current dialog.
	 */
	public Dialog getCurrentDialog() {
		return branches.get(currentBranchName).getCurrentDialog();
	}

	/**
	 * Advance the dialog index.
	 */
	public void advanceDialog() {
		branches.get(currentBranchName).advanceDialogIndex();
	}
	
	/**
	 * Change the current branch.
	 * @param nBranch The name of the target branch.
	 */
	public void changeBranch(String nBranch) {
		currentBranchName = nBranch;
	}
	
	/**
	 * Get the current dialog index.
	 * @return The current dialog index.
	 */
	public int getDialogIndex() {
		return branches.get(currentBranchName).dialogIndex;
	}
	
	/**
	 * Set the dialog index.
	 * @param index The target dialog index.
	 */
	public void setDialogIndex(int index) {
		branches.get(currentBranchName).dialogIndex = index;
	}
	
	/*
	 * Print the chapter story tree.
	 * Mainly used in Debugging feature.
	 */
	public void printChapterTree() {
		for(Entry<String, Branch> set : branches.entrySet()) {
			System.out.println("-- Branch " + set.getKey());
			set.getValue().printBranchTree();
		}
	}

	/**
	 * Get the length of the current branch.
	 * @return The length of the current branch.
	 */
	public int getCurrentBranchLength() {
		return branches.get(currentBranchName).getThisBranchLength();
	}
}