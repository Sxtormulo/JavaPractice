package sxtormulo;

import module java.base;
import javafx.application.Application;
import javafx.collections.FXCollections;

import javafx.scene.Scene;

import javafx.scene.control.*;

import static javafx.scene.input.KeyCode.*;

import javafx.scene.layout.*;

import javafx.stage.Stage;

import module jdk.jshell;

/**
 *
 * @author Sxtormulo
 */
public class VisualJShell extends Application
{

    @Override
    public void start(Stage stage)
    {
        var shell = JShell.builder().build();
        var input = new TextField();
        var results = new ListView<String>();

        input.setOnKeyPressed((evt) ->
        {
            if(evt.getCode().equals(ENTER))
            {

                var eventsList = shell.eval(input.getText());
                results.setItems(Stream.concat(results.getItems().stream(), eventsList
                                               .stream().map(VisualJShell::convert))
                        .collect(Collectors.toCollection(
                                FXCollections::observableArrayList)));
            }
        });
        var borderPane = new BorderPane();
        borderPane.setTop(new VBox(input));
        borderPane.setCenter(results);
        var scene = new Scene(borderPane);
        stage.setTitle("VisualJshell");
        stage.setScene(scene);
        stage.show();
    }

    private static String convert(SnippetEvent event)
    {
        return switch(event.snippet())
        {
            case ExpressionSnippet expression ->
                String
                .join(" ", expression.typeName(), expression.name(), event.value());
            case ErroneousSnippet error ->
                String.join(" ", error.probableKind().name());
            case StatementSnippet statement ->
                String.join(" ", statement.kind().name(), event.value());
            case VarSnippet var ->
                String.join(" ", var.typeName(), var.name(), event.value());
            case PersistentSnippet persistent ->
                String.join(" ", persistent.name(), event.value());
            case Snippet generic ->
                String.join(" ", generic.kind().name(), generic.source());
        };
    }

    void main()
    {
        launch();
    }

}
