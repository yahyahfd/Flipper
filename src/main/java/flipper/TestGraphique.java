package flipper;
import moteur_physique.*;
import java.util.ArrayList;

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
public class TestGraphique extends Application{
  Borders border;
  int a=0;
  RandomShape r6;
  boolean flipLUP=false;
  boolean flipRUP=false;
  Border test=new Border(new Position(0,400),new Position(1400,400),0.9);
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Joueur j1 = new Joueur();
    Text score = new Text(100,100,Integer.toString(j1.getScore()));
    score.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
    score.setFill(Color.BLACK);

    Flip f1=new Flip(new Position(500,400),new Position(0,450),0.5);
    // Flip f2=new Flip(new Position(240,500),new Position(145,520),0.5);
    Balle balle=new Balle(new Position(100,515),10);
    border=new Borders();
    // border.addBorder(f1);
    // border.addBorder(f2);
    // border.addBorder(test);
    // border.addBorder(new Border(new Position(1100,0),new Position(1000,700),0.9));
    // // border.addBorder(new Border(new Position(50,350),new Position(150,400),0.9));
    // border.addBorder(new Border(new Position(110,550),new Position(150,500),0.9));
    // border.addBorder(new Border(new Position(350,500),new Position(400,450),0.9));
    // border.addBorder(new Border(new Position(50,100),new Position(100,600),0.9));
    // border.addBorder(new Border(new Position(0,600),new Position(500,600),0.9));
    // Triangle t1 = new Triangle(0.5,new Position(300,500),new Position(450,500),new Position(300,550));
    // for(Border b:t1.turnIntoBorders()){
    //   border.addBorder(b);
    // }
    // Quadrilatere q1 = new Quadrilatere(0.5, new Position(250,350), new Position(350,350), new Position(350,400), new Position(250,400));
    // for(Border b:q1.turnIntoBorders()){
    //   border.addBorder(b);
    // }
    Ellipse e2 = new Ellipse(300,300,50,50);
    e2.setStrokeWidth(3);
    e2.setFill(Color.BROWN);
    e2.setStroke(Color.BLACK);
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());
    Pane pane=new Pane();
    Scene scene=new Scene(pane,1080,900);
    //pane.getChildren().add(p);e
    //pane.getChildren().add(e1);
    // pane.getChildren().add(e2);// décommmenter cette ligne pour voir l'ellipse
    pane.getChildren().add(score);
    pane.getChildren().add(e2);
    pane.getChildren().add(circle);
    for(Border b:border.getBorders()){
      if(!(b instanceof Flip)){
        pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
      }
    }
    // Moteur_Polygone q6=new Moteur_Polygone(0.5,50);
    // q6.addPos(new Position(360,520));
    // q6.addPos(new Position(380,520));
    // q6.addPos(new Position(380,580));
    // q6.addPos(new Position(360,580));
    // Polygon pq=new Polygon();
    // pq.getPoints().addAll(q6.getAllPosition());
    // pq.setFill(Color.BROWN);
    // pq.setStroke(Color.BLACK);
    // pane.getChildren().add(pq);
    // r6=new RandomShape(q6);
    // r6.addRoundedBorder(10,0);
    // r6.addRoundedBorder(10,2);
    // for(Moteur_Polygone_Inscribed qqp : r6.getRoundedBorder()){
    //   if(qqp!=null){
    //     Polygon pqr=new Polygon();
    //     pqr.getPoints().addAll(qqp.getAllPosition());
    //     pane.getChildren().add(pqr);
    //     pqr.setFill(Color.BROWN);
    //     pqr.setStroke(Color.BLACK);
    //   }
    // }
    Moteur_Polygone q=new Moteur_Polygone(0.5,50);       //Triangle gauche
    q.addPos(new Position(127,629));
    q.addPos(new Position(145,621));
    q.addPos(new Position(216,681));
    q.addPos(new Position(205,698));
    q.addPos(new Position(145,686));
    q.addPos(new Position(128,662));
    Polygon pq=new Polygon();
    pq.getPoints().addAll(q.getAllPosition());
    pane.getChildren().add(pq);
    RandomShape r=new RandomShape(q);
    // r.addRoundedBorder(10,0);
    // r.addRoundedBorder(10,2);
    r.addRoundedBorder(10,4);
    for(Moteur_Polygone_Inscribed mq:r.getRoundedBorder()){
      if(mq!=null){
        Polygon mqp=new Polygon();
        mqp.getPoints().addAll(mq.getAllPosition());
        pane.getChildren().add(mqp);
      }
    }

    Line lf=new Line(f1.getPosX().getX(),f1.getPosX().getY(),f1.getPosY().getX(),f1.getPosY().getY());
    // Line rf=new Line(f2.getPosX().getX(),f2.getPosX().getY(),f2.getPosY().getX(),f2.getPosY().getY());
    // pane.getChildren().add(lf);
    // pane.getChildren().add(rf);
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        // Border s=r6.isInTheShape(balle);
        // if(s!=null&&ss!=null){
        //   if(s.isSuccessive(ss))
        //   balle.setFutur(balle.sliding(s));
        //   else
        //   balle.setFutur(balle.slidingColliding(ss,s));
        //
        //  if(s!=null){
        //    Line tl=new Line(s.getPosX().getX(),s.getPosX().getY(),s.getPosY().getX(),s.getPosY().getY());
        //    pane.getChildren().add(tl);
        //    Position p2=balle.pointOfCollision(s);
        //    tl.setStroke(Color.WHITE);
        //   if(balle.getV().norme()>80)System.out.println(balle.getV().norme());
        //   balle.setFutur(balle.collision(s));
        // }
        //   // pane.getChildren().add(new Line(s.getPosX().getX(),s.getPosX().getY(),s.getPosY().getX(),s.getPosY().getY()));
        //   // Position pc=p2.closestToPoint(s.getPosX(),s.getPosY());
        //   // System.out.println(pc);
        // else{
        //   balle.setFutur(balle.futur());
        // }
        // Position a=test.intersection(balle);
        // if(a!=null){
        //   pane.getChildren().add(new Circle(a.getX(),a.getY(),3,Color.GREEN));
        // }else{
        //   System.out.println("null");
        // }
        // pane.getChildren().add(new Circle(balle.getPos().getX(),balle.getPos().getY(),3,Color.WHITE));

        // System.out.println(balle.getPos());
        // System.out.println(balle.getV().norme());
        circle.setCenterX(balle.getPos().getX());
        circle.setCenterY(balle.getPos().getY());
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
