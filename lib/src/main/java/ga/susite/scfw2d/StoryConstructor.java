package ga.susite.scfw2d;

import java.util.ArrayList;
import java.util.HashMap;

import ga.susite.scfw2d.events.Event;

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

public abstract class StoryConstructor {
    ArrayList<Chapter> chapters      = new ArrayList<Chapter>();
    HashMap<String, Branch> branches = new HashMap<String, Branch>();
    ArrayList<Dialog> dialogs        = new ArrayList<Dialog>();
    ChapterInfo currentChapter;
    String currentBranchName;

    public abstract void buildContent();

    public void chapter(String chapterName, String chapterDescription) {
        if(currentChapter != null) {
            if(currentBranchName != null) {
                if(dialogs.isEmpty()) {
                    throw new NullPointerException("No dialogs is created in a branch.");
                }
                branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
                dialogs = new ArrayList<Dialog>();
            }
            if(branches.isEmpty()) {
                throw new NullPointerException("No branch is created in a chapter.");
            }
            chapters.add(new Chapter(branches, currentChapter.chapterName, currentChapter.chapterDescription));
            branches = new HashMap<String, Branch>();
        }
        currentChapter = new ChapterInfo(chapterName, chapterDescription);
    }
    public void branch(String branchName) {
        if(currentBranchName != null) {
            if(dialogs.isEmpty()) {
                throw new NullPointerException("No dialogs is created in a branch.");
            }
            branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
            dialogs = new ArrayList<Dialog>();
        }
        currentBranchName = branchName;
    }
    public void dialog(String content, Event... events) {
        dialogs.add(new Dialog(content, events));
    }
    public StoryManager finalizeStory() {
        if(currentChapter != null) {
            if(currentBranchName != null) {
                if(dialogs.isEmpty()) {
                    throw new NullPointerException("No dialogs is created in a branch.");
                }
                branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
                dialogs = new ArrayList<Dialog>();
            }
            if(branches.isEmpty()) {
                throw new NullPointerException("No branch is created in a chapter.");
            }
            chapters.add(new Chapter(branches, currentChapter.chapterName, currentChapter.chapterDescription));
            branches = new HashMap<String, Branch>();
        }
        return new StoryManager(chapters.toArray(new Chapter[0]));
    }
}