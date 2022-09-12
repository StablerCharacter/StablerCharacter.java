package ga.susite.scfw2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

import tech.fastj.engine.FastJEngine;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a chapter of a game. Stores branches.
 */
public class Chapter {
	/**
	 * The chapter name.
	 */
	@Getter @Setter String name;
	/**
	 * The chapter description.
	 */
	@Getter @Setter String description;
	/**
	 * The name of the entry branch.
	 */
	@Getter @Setter String currentBranchName = "main";
	Map<String, Branch> branches;
	
	/**
	 * @param nBranches The HashMap of the branches. Structured in a branchName:value structure.
	 * @param nName The chapter name.
	 * @param nDescription The chapter description.
	 */
	public Chapter(Map<String, Branch> nBranches, String nName, String nDescription) {
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
		return branches.get(currentBranchName).getDialogIndex();
	}
	
	/**
	 * Set the dialog index.
	 * @param index The target dialog index.
	 */
	public void setDialogIndex(int index) {
		branches.get(currentBranchName).setDialogIndex(index);
	}
	
	/*
	 * Print the chapter story tree.
	 * Mainly used in Debugging feature.
	 */
	public void printChapterTree() {
		for(Entry<String, Branch> set : branches.entrySet()) {
			FastJEngine.log("-- Branch " + set.getKey());
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

	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || getClass() != other.getClass()) return false;
		
		Chapter chapter = (Chapter)other;

		if(!name.equals(chapter.name)) return false;
		if(!description.equals(chapter.description)) return false;
		if(!currentBranchName.equals(chapter.currentBranchName)) return false;
		if(branches.size() != chapter.branches.size()) return false;
		
		final Set<Entry<String, Branch>> branchEntries = branches.entrySet();
		final Set<Entry<String, Branch>> otherBranchEntries = chapter.branches.entrySet();
		ArrayList<Boolean> branchEquals = new ArrayList<>();
		branchEntries.forEach(entry -> 
			otherBranchEntries.forEach(otherEntry -> {
				if(otherEntry.getKey().equals(entry.getKey()) &&
					entry.getValue().equals(otherEntry.getValue())) {
					branchEquals.add(true);
				}
			})
		);
		return branchEquals.size() == branches.size();
	}
}
