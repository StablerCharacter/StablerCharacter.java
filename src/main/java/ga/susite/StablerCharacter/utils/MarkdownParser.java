package ga.susite.StablerCharacter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import ga.susite.StablerCharacter.Branch;
import ga.susite.StablerCharacter.Chapter;
import ga.susite.StablerCharacter.Dialog;
import ga.susite.StablerCharacter.StoryManager;

public class MarkdownParser {
	ArrayList<Chapter> chapters = new ArrayList<Chapter>();
	String currentChapter = null;
	String currentChapterDescription = "";
	String currentBranchName = null;
	HashMap<String, Branch> branches = new HashMap<String, Branch>();
	List<Dialog> dialogs = new ArrayList<Dialog>();
	
	public StoryManager parseFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner fileReader = new Scanner(file);
		while (fileReader.hasNextLine()) {
			parseSingle(fileReader.nextLine());
		}
		fileReader.close();
		branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
		chapters.add(new Chapter(branches, currentChapter, currentChapterDescription));
		return new StoryManager(chapters.toArray(new Chapter[0]));
	}
	
	public StoryManager parseLines(List<String> lines) {
		for(String line : lines) {
			parseSingle(line);
		}
		branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
		chapters.add(new Chapter(branches, currentChapter, currentChapterDescription));
		return new StoryManager(chapters.toArray(new Chapter[0]));
	}
	
	void parseSingle(String line) {
		if(line.startsWith("#")) {
			if(line.startsWith("##")) {
				if(currentChapter == null) {
					throw new IllegalStateException("Branch is being created - But no chapter is present in the context.");
				}
				if(currentBranchName != null) {
					branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
				}
				currentBranchName = line.substring(2, line.length()).trim();
			} else {
				if(currentChapter != null) {
					chapters.add(new Chapter(branches, currentChapter, currentChapterDescription));
					currentChapterDescription = "";
				}
				currentChapter = line.substring(1, line.length()).trim();
			}
		} else if(line.startsWith("-")) {
			currentChapterDescription += line.substring(1, line.length());
		} else {
			String[] events;
			if(line.trim().endsWith("]")) {
				String[] splittedString = line.trim().split("\\]\\[");
				String eventString = splittedString[1];
				eventString = eventString.substring(0, eventString.length() - 1);
				line = splittedString[0];
				events = eventString.split(" ");
				for(String event : events) {
					System.out.println("Referenced event: " + event);
				}
			}
			if(line.startsWith("|")) {
				Dialog dlg = dialogs.get(dialogs.size() - 1);
				dlg.message += "\n" + line.substring(1, line.length());
				dialogs.set(dialogs.size() - 1, dlg);
			}
			if(line.startsWith("|")) return;
			dialogs.add(new Dialog(line));
		}
	}
}
