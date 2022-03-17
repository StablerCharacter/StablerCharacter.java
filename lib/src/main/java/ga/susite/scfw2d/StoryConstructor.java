package ga.susite.scfw2d;

import java.util.ArrayList;
import java.util.HashMap;

import ga.susite.scfw2d.events.Event;

/**
 * An information about a chapter.
 * @author lines-of-codes
 */
class ChapterInfo {
    public String chapterName;
    public String chapterDescription;

    public ChapterInfo(String newChapterName, String newChapterDescription) {
        if(newChapterName == null)
            throw new NullPointerException("Chapter name cannot be null!");
        if(newChapterDescription == null)
            newChapterDescription = "";
        chapterName = newChapterName;
        chapterDescription = newChapterDescription;
    }
}

/**
 * The StoryConstructor. An easier way to code story in StablerCharacter.java.
 * @author lines-of-codes
 */
public abstract class StoryConstructor {
    ArrayList<Chapter> chapters      = new ArrayList<Chapter>();
    HashMap<String, Branch> branches = new HashMap<String, Branch>();
    ArrayList<Dialog> dialogs        = new ArrayList<Dialog>();
    ChapterInfo currentChapter;
    String currentBranchName;

    /**
     * Builds the content of the story.
     */
    public abstract void buildContent();

    private void finalizeBranch() {
        if(dialogs.isEmpty()) {
            throw new NullPointerException("No dialogs is created in a branch.");
        }
        branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
        dialogs = new ArrayList<Dialog>();
    }

    private void finalizeChapter() {
        if(currentBranchName != null) {
            finalizeBranch();
        }
        if(branches.isEmpty()) {
            throw new NullPointerException("No branch is created in a chapter.");
        }
        chapters.add(new Chapter(branches, currentChapter.chapterName, currentChapter.chapterDescription));
        branches = new HashMap<String, Branch>();
    }

    /**
     * Creates a new chapter with the specified name and description.
     * @param chapterName The name of the chapter.
     * @param chapterDescription The description of the chapter.
     */
    public void chapter(String chapterName, String chapterDescription) {
        if(currentChapter != null) {
            finalizeChapter();
        }
        currentChapter = new ChapterInfo(chapterName, chapterDescription);
    }

    /**
     * Create a new branch with the specified name.
     * @param branchName The name of the branch.
     */
    public void branch(String branchName) {
        if(currentBranchName != null) {
            finalizeBranch();
        }
        currentBranchName = branchName;
    }

    /**
     * Adds a dialog to the current branch.
     */
    public void dialog(String content, Event... events) {
        dialogs.add(new Dialog(content, events));
    }
    
    /**
     * Finalizes the story and then return the story.
     * @return The StoryManager instance from the story created.
     */
    public StoryManager finalizeStory() {
        if(currentChapter != null) {
            finalizeChapter();
        }
        return new StoryManager(chapters.toArray(new Chapter[0]));
    }
}