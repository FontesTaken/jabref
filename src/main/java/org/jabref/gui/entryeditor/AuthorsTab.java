package org.jabref.gui.entryeditor;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.jabref.gui.DialogService;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.preferences.PreferencesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GUI for tab displaying article recommendations based on the currently selected BibEntry
 */
public class AuthorsTab extends EntryEditorTab {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorsTab.class);
    private final EntryEditorPreferences preferences;
    private final DialogService dialogService;
    private final PreferencesService preferencesService;

    public AuthorsTab(EntryEditor entryEditor, EntryEditorPreferences preferences, PreferencesService preferencesService, DialogService dialogService) {
        setText(Localization.lang("Authors"));
        setTooltip(new Tooltip(Localization.lang("Authors")));
        this.preferences = preferences;
        this.dialogService = dialogService;
        this.preferencesService = preferencesService;
    }

    /**
     * Gets a StackPane of author information to be displayed in the Authors tab
     *
     * @param entry The currently selected BibEntry on the JabRef UI.
     * @return A StackPane with Author information to be displayed in the Authors tab.
     */
    private StackPane getAuthorsPane(BibEntry entry) {
        StackPane root = new StackPane();
        root.setId("related-articles-tab");
        root.getChildren().add(getAuthorsInfo(entry));

        return root;
    }

    /**
     * Creates a VBox of the author information to be used in the StackPane displayed in the Authors tab
     *
     * @param entry currently selected entry
     * @return VBox of Author descriptions to be displayed in the Authors tab
     */
    private ScrollPane getAuthorsInfo(BibEntry entry) {
        ScrollPane scrollPane = new ScrollPane();

        VBox vBox = new VBox();
        vBox.setSpacing(20.0);

        String authors = entry.getField(StandardField.AUTHOR).orElse("");
        String [] authorArray = authors.split("and ");

        String affiliation = entry.getField(StandardField.AFFILIATION).orElse("");
        String [] affiliationArray = affiliation.split("and ");

        String areaOfStudy = entry.getField(StandardField.AREAOFSTUDY).orElse("");
        String [] areaOfStudyArray = areaOfStudy.split("and ");

        int i = 0;
        int j = 0;
        for(String s:authorArray){
            HBox hBox = new HBox();
            hBox.setSpacing(5.0);
            HBox zBox = new HBox();
            HBox wBox = new HBox();
            hBox.getStyleClass().add("recommendation-item");
            Text authorsText = new Text(s);

            Text affiliationText = new Text("           affiliation: ");
            if (i<affiliationArray.length){
                affiliationText = new Text("           affiliation: " + affiliationArray[i]);
                i++;
            }
            Text areaOfStudyText = new Text("           area of study: ");
            if (j<areaOfStudyArray.length){
                areaOfStudyText = new Text("           area of study: " + areaOfStudyArray[j]);
                j++;
            }

            authorsText.getStyleClass().add("heading");
            hBox.getChildren().addAll(authorsText);
            vBox.getChildren().add(hBox);
            zBox.getChildren().addAll(affiliationText);
            vBox.getChildren().add(zBox);
            wBox.getChildren().addAll(areaOfStudyText);
            vBox.getChildren().add(wBox);
        }
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    @Override
    public boolean shouldShow(BibEntry entry) {
        return preferences.shouldShowRecommendationsTab();
    }

    @Override
    protected void bindToEntry(BibEntry entry) {
        // Ask for consent to send data to Mr. DLib on first time to tab
        if (preferences.isMrdlibAccepted()) {
            setContent(getAuthorsPane(entry));
        } else {
            //setContent(getPrivacyDialog(entry));
        }
    }
}
