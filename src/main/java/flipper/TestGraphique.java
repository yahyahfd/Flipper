package flipper;
import moteur_physique.*;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
  int a=0;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Balle balle=new Balle(new Position(110,200),10,5);
    Flip f1=new Flip(new Position(250,500),new Position(300,520),0.9);
    border=new Borders();
    border.addBorder(new Border(new Position(400,100),new Position(500,600),1));
    border.addBorder(new Border(new Position(50,350),new Position(150,400),1));
    border.addBorder(new Border(new Position(110,550),new Position(150,500),1));
    border.addBorder(new Border(new Position(350,500),new Position(400,450),1));
    border.addBorder(new Border(new Position(50,100),new Position(100,600),1));
    border.addBorder(new Border(new Position(0,600),new Position(500,600),1));
    border.addBorder(f1);
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());
    Pane pane=new Pane();
    Scene scene=new Scene(pane,600,900);
    pane.getChildren().add(circle);
    Line lf=new Line(f1.getPosX().getX(),f1.getPosX().getY(),f1.getPosY().getX(),f1.getPosY().getY());
    pane.getChildren().add(lf);
    for(Border b:border.getBorders()){
      if(!(b instanceof Flip)){
        pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
      }
    }
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        Border b=border.isOnALine(balle);
        if(b!=null){
          pane.getChildren().add(new Circle(b.intersection(balle).getX(),b.intersection(balle).getY(),5));
          balle.setFutur(balle.collision(b));
        }else {
          balle.setFutur(balle.futur());
        }
        lf.setEndX(f1.getPosY().getX());
        lf.setEndY(f1.getPosY().getY());
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
      }
    }));
    scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
          f1.moveFlipUp();
      }
    });
    scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
          f1.moveFlipDown();
      }
    });
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
    scene.setFill(Color.BROWN);
    primaryStage.setScene(scene);
    primaryStage.setTitle("TestGraphique");
    primaryStage.show();
  }

}
