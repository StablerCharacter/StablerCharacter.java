module ga.susite.StablerCharacter {
    requires fastj.library;
	requires java.desktop;
	requires com.google.gson;
	requires jdk.compiler;
	
	opens ga.susite.StablerCharacter to com.google.gson;
}