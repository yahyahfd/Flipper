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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
import javafx.scene.layout.VBox;
public class TestGraphique extends Application{
  Borders border;
  int a=0;
  boolean flipLUP=false;
  boolean flipRUP=false;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Joueur j1 = new Joueur("Joueur 1");
    Flip f1=new Flip(new Position(110,500),new Position(175,520),0.9);
    Flip f2=new Flip(new Position(240,500),new Position(175,520),0.9);
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
    Balle balle=new Balle(new Position(100,50),10,5);
    Text score = new Text(100,100,Integer.toString(j1.getScore()));
    score.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
    score.setFill(Color.BLACK);
    border=new Borders();
    border.addBorder(f1);
    border.addBorder(f2);
    border.addBorder(new Border(new Position(400,100),new Position(500,600),0.9));
    border.addBorder(new Border(new Position(50,350),new Position(150,400),0.9));
    border.addBorder(new Border(new Position(110,550),new Position(150,500),0.9));
    border.addBorder(new Border(new Position(350,500),new Position(400,450),0.9));
    border.addBorder(new Border(new Position(50,100),new Position(100,600),0.9));
    border.addBorder(new Border(new Position(0,600),new Position(500,600),0.9));
    Triangle t1 = new Triangle(0.5,new Position(300,500),new Position(450,500),new Position(300,550));
    for(Border b:t1.turnIntoBorders()){
      b.setScoringTrue();
      b.setBorderScore(200);
      border.addBorder(b);
    }
    Quadrilatere q1 = new Quadrilatere(0.5, new Position(250,350), new Position(350,350), new Position(350,400), new Position(250,400));
    for(Border b:q1.turnIntoBorders()){
      b.setScoringTrue();
      b.setBorderScore(100);
      border.addBorder(b);
    }
    moteurEllipse e = new moteurEllipse(0,0.9,50,25,new Position(125,300));
    Ellipse e2 = new Ellipse(125,300,50,25);
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());
    Pane pane=new Pane();
    Scene scene=new Scene(pane,1080,900);
    pane.getChildren().add(circle);
    pane.getChildren().add(score);
    //pane.getChildren().add(p);
    //pane.getChildren().add(e1);
    pane.getChildren().add(e2);// décommmenter cette ligne pour voir l'ellipse
    for(Border b:border.getBorders()){
      if(!(b instanceof Flip)){
        pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
      }
    }
    Line lf=new Line(f1.getPosX().getX(),f1.getPosX().getY(),f1.getPosY().getX(),f1.getPosY().getY());
    Line rf=new Line(f2.getPosX().getX(),f2.getPosX().getY(),f2.getPosY().getX(),f2.getPosY().getY());
    pane.getChildren().add(lf);
    pane.getChildren().add(rf);
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        Border b=border.isOnALine(balle);
        Border s=border.isSliding(balle);
        Position t1=e.isInTheShape(balle);
        if(t1!=null){
          Vecteur t2 = new Vecteur(t1.getX()-e.getPos().getX(),t1.getY()-e.getPos().getY());
          Vecteur t3 = t2.vectNormUni();
          Border t4 = new Border(new Position(t1.getX()-t3.getX(),t1.getY()-t3.getY()),new Position(t1.getX()+t3.getX(),t1.getY()+t3.getY()),e.getRebond());
          pane.getChildren().add(new Line(t4.getPosX().getX(),t4.getPosX().getY(),t4.getPosY().getX(),t4.getPosY().getY())); // On trace les tangentes à chaque fois.
          balle.setFutur(balle.collision(t4));
        }else if(b!=null){
          pane.getChildren().add(new Circle(b.intersection(balle).getX(),b.intersection(balle).getY(),5,Color.WHITE));
          balle.setFutur(balle.collision(b));
            if(b.getScoring()){
              j1.addScore(b.getBorderScore());
              score.setText(Integer.toString(j1.getScore()));
            }
        }else if(s!=null){
          balle.setFutur(balle.sliding(s));
        }
        else{
          balle.setFutur(balle.futur());
        }
        if(flipLUP==true)f1.moveFlipUp();
        else f1.moveFlipDown();
        if(flipRUP==true)f2.moveFlipUp();
        else f2.moveFlipDown();
        lf.setEndX(f1.getPosY().getX());
        lf.setEndY(f1.getPosY().getY());
        rf.setEndX(f2.getPosY().getX());
        rf.setEndY(f2.getPosY().getY());
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
      }
    }));
    scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=true;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=true;
      }
    });
    scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=false;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=false;
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
