package ga.susite.StablerCharacter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarkdownParser {
	public static StoryManager parseFile(String filePath) throws IOException, IllegalStateException {
		List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		ArrayList<Chapter> chapters = new ArrayList<Chapter>();
		String currentChapter = null;
		String currentChapterDescription = "";
		String currentBranchName = null;
		String currentDialogMessage = "";
		HashMap<String, Branch> branches = new HashMap<String, Branch>();
		List<Dialog> dialogs = new ArrayList<Dialog>();

		for(String i : lines) {
			if(i.startsWith("#")) {
				if(i.startsWith("##")) {
					if(currentChapter == null) {
						throw new IllegalStateException("Branch is being created - But no chapter is present in the context.");
					}
					if(currentBranchName != null) {
						branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
					}
					currentBranchName = i.substring(2, i.length()).trim();
				} else {
					if(currentChapter != null) {
						chapters.add(new Chapter(branches, currentChapter, currentChapterDescription));
						currentChapterDescription = "";
					}
					currentChapter = i.substring(1, i.length()).trim();
				}
			} else if(i.startsWith("-")) {
				currentChapterDescription += i.substring(1, i.length());
			} else {
				String[] events;
				if(i.trim().endsWith("]")) {
					String[] splittedString = i.trim().split("\\]\\[");
					String eventString = splittedString[1];
					eventString = eventString.substring(0, eventString.length() - 1);
					i = splittedString[0];
					events = eventString.split(" ");
					for(String j : events) {
						System.out.println("Referenced event: " + j);
					}
				}
				if(i.startsWith("|")) {
					Dialog dlg = dialogs.get(dialogs.size() - 1);
					dlg.message += "\n" + i.substring(1, i.length());
					dialogs.set(dialogs.size() - 1, dlg);
					// currentDialogMessage += i.substring(1, i.length());
				}
				if(i.startsWith("|")) continue;
				dialogs.add(new Dialog(i));
			}
		}
		branches.put(currentBranchName, new Branch(dialogs.toArray(new Dialog[0])));
		chapters.add(new Chapter(branches, currentChapter, currentChapterDescription));
		for(Chapter i : chapters) {
			System.out.println(i.name);
			System.out.println(i.description);
		}
		return new StoryManager(chapters.toArray(new Chapter[0]));
	}
}
