package krasa.disableDecompiledFilesReopening;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.vfs.VirtualFile;

public class BeforeProjectCloseListener implements ProjectManagerListener {

	@Override
	public void projectClosingBeforeSave(@NotNull Project project) {
		FileEditorManager instance = FileEditorManager.getInstance(project);
		FileEditor[] allEditors = instance.getAllEditors();
		for (FileEditor allEditor : allEditors) {
			VirtualFile file = allEditor.getFile();
			// Document document = FileDocumentManager.getInstance().getDocument(file);
			if (file != null && "class".equals(file.getExtension())
			// && document!=null && document.getText().startsWith("//\n" +
			// "// Source code recreated from a .class file by IntelliJ IDEA")
			) {
				instance.closeFile(file);
			}
		}
	}
}
