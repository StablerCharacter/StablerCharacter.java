module ga.susite.StablerCharacter {
    requires fastj.library;
	requires java.desktop;
	requires com.google.gson;
	
	opens ga.susite.StablerCharacter to com.google.gson;
}