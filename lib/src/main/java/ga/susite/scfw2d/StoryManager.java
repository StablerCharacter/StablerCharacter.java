package ga.susite.scfw2d;

import tech.fastj.engine.FastJEngine;

/**
 * Stores information about the chapters.
 */
public class StoryManager {
	Chapter[] chapters;
	Integer chapterIndex = 0;
	
	/**
	 * @param nChapters All of the chapters.
	 */
	public StoryManager(Chapter[] nChapters) {
		chapters = nChapters;
	}

	/**
	 * Get the current dialog that the chapter is on.
	 * @return The current dialog.
	 */
	public Dialog getCurrentDialog() {
		return chapters[chapterIndex].getCurrentDialog();
	}
	
	/**
	 * Get the next dialog.
	 * @return The next dialog.
	 */
	public Dialog getNext() {
		chapters[chapterIndex].advanceDialog();
		return chapters[chapterIndex].getCurrentDialog();
	}
	
	/**
	 * Get the current dialog index.
	 * @return The current dialog index.
	 */
	public int getDialogIndex() {
		return chapters[chapterIndex].getDialogIndex();
	}
	
	/**
	 * Set the dialog index.
	 * @param index The target index.
	 */
	public void setDialogIndex(int index) {
		chapters[chapterIndex].setDialogIndex(index);
	}
	
	/**
	 * Get the length of the dialogs in the current branch.
	 * @return The length of the dialogs in the current branch.
	 */
	public int getCurrentBranchLength() {
		return chapters[chapterIndex].getCurrentBranchLength();
	}
	
	/**
	 * Print the Story tree.
	 * Mainly used in debugging.
	 */
	public void printStoryTree() {
		FastJEngine.log("- StoryManager - ");
		int currentChapterIndex = 0;
		for(Chapter i : chapters) {
			FastJEngine.log("- Chapter index " + currentChapterIndex);
			i.printChapterTree();
		}
	}

	public boolean equals(StoryManager other) {
		if(chapters.length != other.chapters.length) return false;
		for(int i = 0; i < chapters.length; i++) {
			if(!chapters[i].equals(other.chapters[i])) return false;
		}
		if(!chapterIndex.equals(other.chapterIndex)) return false;
		return true;
	}
}
