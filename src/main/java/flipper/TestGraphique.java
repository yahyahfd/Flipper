package flipper;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
public class TestGraphique extends Application{
  Borders border;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Quadrilatere q=new Quadrilatere(0.5,new Position(241,232),new Position(573,158),new Position(581,64),new Position(225,56));
    RandomShape r=new RandomShape(q);
    r.addCircle(15,3);
    r.addCircle(40,1);
    Ellipse e1=new Ellipse(r.getE().get(1).getPos().getX(),r.getE().get(1).getPos().getY(),r.getE().get(1).getMinor()/2,r.getE().get(1).getMajor()/2);
    Rotate rotate=new Rotate(90+r.getE().get(1).getRotate(),r.getE().get(1).getPos().getX(),r.getE().get(1).getPos().getY());
    e1.getTransforms().add(rotate);
    e1.setFill(Color.GREEN);
    Polygon p =new Polygon();
    p.setFill(Color.GREEN);
    p.getPoints().addAll(q.getAllPosition());
    Balle balle=new Balle(new Position(100,0),10,5);
    border=new Borders();
    border.addBorder(new Border(new Position(600,100),new Position(500,600),0.9));
    border.addBorder(new Border(new Position(0,600),new Position(500,600),0.9));
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());
    Pane pane=new Pane();
    pane.getChildren().add(circle);
    pane.getChildren().add(p);
    pane.getChildren().add(e1);
    for(Border b:border.getBorders()){
      pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
    }
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        balle.setFutur(balle.collision(b));
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
    Scene scene=new Scene(pane,600,900);
    scene.setFill(Color.BROWN);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TestGraphique");
    primaryStage.show();
  }

}
