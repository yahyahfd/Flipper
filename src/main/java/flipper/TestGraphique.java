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
import javafx.scene.layout.Pane;
public class TestGraphique extends Application{
  Borders border;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    border=new Borders();
    border.addBorder(new Border(new Position(20,800),new Position(580,800),1));
    border.addBorder(new Border(new Position(20,200),new Position(20,800),1));
    border.addBorder(new Border(new Position(580,200),new Position(580,800),1));
    Circle circle=new Circle(200,200,100);
    Pane pane=new Pane();
    pane.getChildren().add(circle);
    for(Border b:border.getBorders()){
      pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
    }
    //Adding all the elements to the path
    TranslateTransition translateTransition=new TranslateTransition();
    translateTransition.setDuration(Duration.millis(1020));
    translateTransition.setNode(circle);
    translateTransition.setByY(500);
    translateTransition.setCycleCount(50);
    translateTransition.setAutoReverse(false);
    translateTransition.play();
    Scene scene=new Scene(pane,600,900);
    scene.setFill(Color.BROWN);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TestGraphique");
    primaryStage.show();
  }
}
