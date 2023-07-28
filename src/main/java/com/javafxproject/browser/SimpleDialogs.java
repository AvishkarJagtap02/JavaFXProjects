package com.javafxproject.browser;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;

/**
 * Contains static methods for showing simple, unadorned dialog boxes
 * of several types.  The dialogs are based on the JavaFX Dialog class,
 * except for SimpleDialogs.prompt(), which uses the JavaFX TextInputDialog.
 * These dialogs do not have graphics (i.e. the icons that appear in
 * standard Alert dialogs).
 */
public class SimpleDialogs {

    /**
     * Shows a simple dialog to the user containing a message and
     * an OK button.
     *
     * @param text  the message that appears in the dialog box.  Unlike the
     *              messages for most of the other dialog boxes, this text is
     *              automatically wrapped to a maximum width of 450 pixels.  There is
     *              no need to include line feeds in the text.
     * @param title text to appear in the title bar of the dialog;
     *              can be null.
     */
    public static void message(String text, String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setGraphic(null);
        dialog.setTitle(title);
        dialog.getDialogPane().setContent(makeText(text));
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().setPadding(new Insets(10, 10, 0, 10));
        dialog.showAndWait();
    }

    /**
     * Calls SimpleDialogs.prompt(promptText,title,null);
     */
    public static String prompt(String promptText, String title) {
        return prompt(promptText, title, null);
    }

    /**
     * Shows an input dialog box where the user can enter one line of text.
     * The dialog box has an "OK" button and a "Cancel" button.
     *
     * @param promptText   the prompt (such as a question) that is displayed
     *                     in the dialog box above the text input box.  This is the
     *                     headerText for the dialog.  If it is to be displayed as more than
     *                     one line, it should contain \n characters to separate the lines.
     *                     Should not be null.
     * @param title        text to appear in the title of the dialog box; can be null
     * @param defaultValue if non-null, this is the initial text in the input box
     * @return null if the user cancels the dialog, or the contents of the input
     * box if the user dismisses the dialog with the OK button.  Note that
     * the return value can be an empty string, if the user hits "OK" while
     * the input box is empty.
     */
    public static String prompt(String promptText, String title, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setGraphic(null);
        dialog.setHeaderText(promptText);
        Optional<String> reply = dialog.showAndWait();
        return reply.orElse(null);
    }

    /**
     * Shows a dialog box with a message and three buttons: "yes", "no", and
     * "cancel".  The only thing the user can do is click one of the buttons.
     *
     * @param message the prompt (such as a question) that is displayed
     *                in the dialog box above the buttons.  This is the
     *                contentText for the dialog.  (The headerText is null.)
     *                If it is to be displayed as more than one line, it should
     *                contain \n characters to separate the lines.  Should not be null.
     * @param title   text to appear in the title of the dialog box; can be null.
     * @return "yes", "no", or "cancel".  The return value is always one of these
     * three strings, depending on which button the user clicks to dismiss
     * the dialog box.  If the user closes the dialog box in some other
     * way than clicking a button, the return value is "cancel".
     * The return value cannot be null.
     */
    public static String confirm(String message, String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.CANCEL, ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES)
            return "yes";
        else if (result.isPresent() && result.get() == ButtonType.NO)
            return "no";
        else
            return "cancel";
    }


    // ---------------------- private implementation -------------------------------------------

    private final static int TEXT_WIDTH = 450;

    /**
     * Makes a Text node to represent the text in a "message" dialog.
     */
    private static Text makeText(String str) {
        Text text = new Text(str);
        text.setWrappingWidth(TEXT_WIDTH);
        text.setFont(Font.font(Font.getDefault().getSize() * 1.2));
        return text;
    }

}