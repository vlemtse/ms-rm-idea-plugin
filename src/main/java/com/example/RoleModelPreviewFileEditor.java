package com.example;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class RoleModelPreviewFileEditor extends UserDataHolderBase implements FileEditor, Disposable {
    private final Project project;
    private final VirtualFile file;
    private final RoleModelPreviewPanel previewPanel;

    public RoleModelPreviewFileEditor(Project project, VirtualFile file) {
        this.project = project;
        this.file = file;
        this.previewPanel = new RoleModelPreviewPanel(project, file);
    }

    @Override
    public @Nullable VirtualFile getFile() {
        return file;
    }

    public @NotNull JComponent getComponent() {
        return previewPanel;
    }

    public @Nullable JComponent getPreferredFocusedComponent() {
        return previewPanel;
    }

    public @NotNull String getName() {
        return "Role Model Preview";
    }

    public void setState(@NotNull FileEditorState state) {
        // No state to set
    }

    public boolean isModified() {
        return false;
    }

    public boolean isValid() {
        return true;
    }

    public void selectNotify() {
        // Refresh the preview when selected
        previewPanel.refresh();
    }

    public void deselectNotify() {
        // No action needed
    }

    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
        // No properties to listen to
    }

    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
        // No properties to listen to
    }

    public @Nullable FileEditorLocation getCurrentLocation() {
        return null;
    }

    public @Nullable AnAction[] getActions() {
        return AnAction.EMPTY_ARRAY;
    }

    public @Nullable AnAction[] getAdditionalActions() {
        return AnAction.EMPTY_ARRAY;
    }

    public void dispose() {
        // Clean up resources if needed
    }
}