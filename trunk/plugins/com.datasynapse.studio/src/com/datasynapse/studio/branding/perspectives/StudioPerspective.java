// Copyright (c) 2007 DataSynapse, Inc. All Rights Reserved.
package com.datasynapse.studio.branding.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.ui.JavaUI;

import com.datasynapse.studio.project.newClassWizards.NewFeatureInfoWizard;
import com.datasynapse.studio.project.newClassWizards.NewStatisticsProviderWizard;
import com.datasynapse.studio.project.newProjectWizards.FSCommandLineDomain;
import com.datasynapse.studio.project.newProjectWizards.FSContainer;
import com.datasynapse.studio.project.newProjectWizards.FSDistribution;
import com.datasynapse.studio.project.newProjectWizards.FSDomain;
import com.datasynapse.studio.project.newProjectWizards.FSDomainType;
import com.datasynapse.studio.project.newProjectWizards.GenericGridLibrary;
import com.datasynapse.studio.project.views.DependencyView;
import com.datasynapse.studio.project.views.EngineTestFileView;
import com.datasynapse.studio.project.views.EngineTestWatcherView;

/**
 * Defines the DataSynapse Studio perspective.
 *
 * @author lvaldez
 */
public class StudioPerspective implements IPerspectiveFactory {

    /**
     * The ID for the Studio perspective.
     */
    public static final String ID = "com.datasynapse.studio.perspectives.StudioPerspective"; //$NON-NLS-1$

    private static final String BOTTOM_FOLDER = "bottom"; //$NON-NLS-1$
    private static final String LEFT_FOLDER = "left"; //$NON-NLS-1$
    // TODO Replace these following constants with the real constants if we can find them
    // Action sets
    private static final String PROFILE_ACTION_SET_ID = "org.eclipse.debug.ui.profileActionSet"; //$NON-NLS-1$
    private static final String JAVA_DEBUG_ACTION_SET_ID = "org.eclipse.jdt.debug.ui.JDTDebugActionSet"; //$NON-NLS-1$
    private static final String JUNIT_ACTION_SET_ID = "org.eclipse.jdt.junit.JUnitActionSet"; //$NON-NLS-1$
    private static final String TEAM_ACTION_SET_ID = "org.eclipse.team.ui.actionSet"; //$NON-NLS-1$
    private static final String CVS_ACTION_SET_ID = "org.eclipse.team.cvs.ui.CVSActionSet"; //$NON-NLS-1$
    private static final String ANT_ACTION_SET_ID = "org.eclipse.ant.ui.actionSet.presentation"; //$NON-NLS-1$
    // Perspectives
    private static final String SYNCHRO_PERSPECTIVE_ID = "org.eclipse.team.ui.TeamSynchronizingPerspective"; //$NON-NLS-1$
    private static final String RES_PERSPECTIVE_ID = "org.eclipse.ui.resourcePerspective"; //$NON-NLS-1$
    private static final String DEBUG_PERSPECTIVE_ID = "org.eclipse.debug.ui.DebugPerspective"; //$NON-NLS-1$
    // Wizards
    private static final String NEW_FOLDER_WIZARD_ID = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$
    private static final String NEW_FILE_WIZARD_ID = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$
    private static final String NEW_UNTITLED_TEXT_FILE_WIZARD_ID = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard"; //$NON-NLS-1$
    // Views
    private static final String ANT_VIEW_ID = "org.eclipse.ant.ui.views.AntView"; //$NON-NLS-1$
    private static final String ANNOTATE_VIEW_ID = "org.eclipse.team.ccvs.ui.AnnotateView"; //$NON-NLS-1$
    private static final String RESULT_VIEW_ID = "org.eclipse.jdt.junit.ResultView"; //$NON-NLS-1$
    private static final String HISTORY_VIEW_ID = "org.eclipse.team.ui.GenericHistoryView"; //$NON-NLS-1$
    private static final String ERROR_VIEW_ID = "org.eclipse.pde.runtime.LogView"; //$NON-NLS-1$
    private static final String JUNIT_RESULT_VIEW_ID = "org.eclipse.jdt.junit.ResultView"; //$NON-NLS-1$
    private static final String TASKS_VIEW_ID = "org.eclipse.ui.views.TaskList"; //$NON-NLS-1$

    private IPageLayout factory;

    public void createInitialLayout(IPageLayout factory) {
        this.factory = factory;
        addViews();
        addActionSets();
        addNewWizardShortcuts();
        addPerspectiveShortcuts();
        addViewShortcuts();
    }

    /**
     * Creates the overall layout of views on the screen. There are two main areas
     * in addition to the editor area in the center: on the left and at the bottom.
     */
    private void addViews() {
        IFolderLayout left = factory.createFolder(LEFT_FOLDER, IPageLayout.LEFT, 0.20f, factory.getEditorArea());
        left.addView(JavaUI.ID_PACKAGES);
        left.addPlaceholder(JavaUI.ID_TYPE_HIERARCHY);
        left.addPlaceholder(IPageLayout.ID_RES_NAV);
        left.addPlaceholder(JUNIT_RESULT_VIEW_ID);
        left.addPlaceholder(EngineTestFileView.VIEW_ID);

        IFolderLayout bottom = factory.createFolder(BOTTOM_FOLDER, IPageLayout.BOTTOM, 0.80f, factory.getEditorArea());
        bottom.addView(DependencyView.VIEW_ID);
        bottom.addPlaceholder(TASKS_VIEW_ID);
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(ERROR_VIEW_ID);
        bottom.addPlaceholder(HISTORY_VIEW_ID);
        bottom.addPlaceholder(IPageLayout.ID_PROGRESS_VIEW);
        bottom.addPlaceholder(IPageLayout.ID_PROP_SHEET);
        bottom.addPlaceholder(EngineTestWatcherView.VIEW_ID);
    }

    /**
     * Add action sets to the perspective. These are mostly from the default
     * new perspective wizard.
     */
    private void addActionSets() {
        factory.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        factory.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
        factory.addActionSet(PROFILE_ACTION_SET_ID);
        factory.addActionSet(JAVA_DEBUG_ACTION_SET_ID);
        factory.addActionSet(JUNIT_ACTION_SET_ID);
        factory.addActionSet(TEAM_ACTION_SET_ID);
        factory.addActionSet(CVS_ACTION_SET_ID);
        factory.addActionSet(ANT_ACTION_SET_ID);
        factory.addActionSet(JavaUI.ID_ACTION_SET);
        factory.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
        factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    /**
     * Add perspective shortcuts. These appear in the Window->Open Perspective sub-menu.
     */
    private void addPerspectiveShortcuts() {
        factory.addPerspectiveShortcut(ID); // Ourselves
        factory.addPerspectiveShortcut(SYNCHRO_PERSPECTIVE_ID);
        factory.addPerspectiveShortcut(RES_PERSPECTIVE_ID);
        factory.addPerspectiveShortcut(JavaUI.ID_PERSPECTIVE);
        factory.addPerspectiveShortcut(DEBUG_PERSPECTIVE_ID);
    }

    /**
     * Add shortcuts for the new wizards. These appear in the File->New sub-menu.
     */
    private void addNewWizardShortcuts() {
        // DS Project Types
        factory.addNewWizardShortcut(GenericGridLibrary.ID);
        factory.addNewWizardShortcut(FSDistribution.ID);
        factory.addNewWizardShortcut(FSContainer.ID);
        factory.addNewWizardShortcut(FSDomainType.ID);
        factory.addNewWizardShortcut(FSDomain.ID);
        factory.addNewWizardShortcut(FSCommandLineDomain.ID);
        // Non-Project Types
        factory.addNewWizardShortcut(NewStatisticsProviderWizard.ID);
        factory.addNewWizardShortcut(NewFeatureInfoWizard.ID);
        factory.addNewWizardShortcut(NEW_FOLDER_WIZARD_ID);
        factory.addNewWizardShortcut(NEW_FILE_WIZARD_ID);
        factory.addNewWizardShortcut(NEW_UNTITLED_TEXT_FILE_WIZARD_ID);
    }

    /**
     * Add shortcuts for the opening views. These appear in the Window->Show View
     * sub-menu, in alphabetical order.
     */
    private void addViewShortcuts() {
        factory.addShowViewShortcut(DependencyView.VIEW_ID);
        factory.addShowViewShortcut(EngineTestWatcherView.VIEW_ID);
        factory.addShowViewShortcut(EngineTestFileView.VIEW_ID);
        factory.addShowViewShortcut(DependencyView.VIEW_ID);
        factory.addShowViewShortcut(ANT_VIEW_ID);
        factory.addShowViewShortcut(ANNOTATE_VIEW_ID);
        factory.addShowViewShortcut(RESULT_VIEW_ID);
        factory.addShowViewShortcut(HISTORY_VIEW_ID);
        factory.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
        factory.addShowViewShortcut(JavaUI.ID_PACKAGES);
        factory.addShowViewShortcut(IPageLayout.ID_RES_NAV);
        factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        factory.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
        factory.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
    }
}
