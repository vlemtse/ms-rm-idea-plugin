package com.example;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class RoleModelPreviewEditorProvider extends UserDataHolderBase implements FileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        // Check if the file is a YAML file and has the expected structure
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".yaml") || fileName.endsWith(".yml");
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return new RoleModelPreviewFileEditor(project, file);
    }

    @Override
    public void disposeEditor(@NotNull FileEditor editor) {
        // Dispose resources if needed
    }

    @Override
    public @NotNull FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
        return FileEditorState.INSTANCE;
    }

    @Override
    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
        // No state to write
    }

    @Override
    public @NotNull String getEditorTypeId() {
        return "RoleModelPreview";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_BEFORE_DEFAULT_EDITOR;
    }
}