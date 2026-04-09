
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyShapes extends Application
{

    public void start(Stage stage) throws Exception
    {
        var ellipse = new Ellipse(110, 70);
        ellipse.setFill(Color.LIGHTBLUE);
        var text = new Text("My Shapes");
        text.setFont(new Font("Kurinto Type", 12));
        var stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);
        var scene = new Scene(stackPane, 1280, 720, Color.LIGHTYELLOW);
//        var group = new Group(ellipse, text);
//        ellipse.setCenterX(175);
//        ellipse.setCenterY(115);
//        text.setX(175 - (text.getLayoutBounds().getWidth() / 2));
//        text.setY(115 - (text.getLayoutBounds().getHeight() / 2));
//        var scene = new Scene(group, 1280, 720, Color.LINEN);
        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    void main()
    {
        launch();
    }
}
