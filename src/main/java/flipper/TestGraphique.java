import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
public class TestGraphique extends Application{
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Circle circle=new Circle(200,200,100);
    //Adding all the elements to the path
    Group root=new Group(circle);
    TranslateTransition translateTransition=new TranslateTransition();
    translateTransition.setDuration(Duration.millis(1020));
    translateTransition.setNode(circle);
    translateTransition.setByY(500);
    translateTransition.setCycleCount(50);
    translateTransition.setAutoReverse(false);
    translateTransition.play();
    Scene scene=new Scene(root,600,900);
    scene.setFill(Color.BROWN);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TestGraphique");
    primaryStage.show();
  }
}
