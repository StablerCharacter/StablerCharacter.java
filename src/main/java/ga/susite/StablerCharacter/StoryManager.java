package ga.susite.StablerCharacter;

public class StoryManager {
	Chapter[] chapters;
	Integer chapterIndex = 0;
	
	/**
	 * @param nChapters All of the chapters.
	 */
	public StoryManager(Chapter[] nChapters) {
		chapters = nChapters;
	}

	public Dialog getCurrentDialog() {
		return chapters[chapterIndex].getCurrentDialog();
	}
	
	public Dialog getNext() {
		chapters[chapterIndex].advanceDialog();
		return chapters[chapterIndex].getCurrentDialog();
	}
	
	public int getDialogIndex() {
		return chapters[chapterIndex].getDialogIndex();
	}
	
	public void setDialogIndex(int index) {
		chapters[chapterIndex].setDialogIndex(index);
	}
	
	public int getCurrentBranchLength() {
		return chapters[chapterIndex].getCurrentBranchLength();
	}
	
	/**
	 * Print the Story tree.
	 * Mainly used in debugging.
	 */
	public void printStoryTree() {
		System.out.println("- StoryManager - ");
		int currentChapterIndex = 0;
		for(Chapter i : chapters) {
			System.out.println("- Chapter index " + currentChapterIndex);
			i.printChapterTree();
		}
	}
}
