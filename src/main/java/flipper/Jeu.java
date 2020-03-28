package flipper;
import moteur_physique.*;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Jeu extends Application{
  Borders border;
  Shapes shape;
  boolean flipLUP=false;
  boolean flipRUP=false;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Pane pane=new Pane();

    //

    Moteur_Polygone qq=new Moteur_Polygone(0.5);
    qq.addPos(new Position(480,540));
    qq.addPos(new Position(390,680));
    qq.addPos(new Position(480,660));
    RandomShape rr=new RandomShape(qq);

    Moteur_Polygone q=new Moteur_Polygone(0.5);
    q.addPos(new Position(110,640));
    q.addPos(new Position(110,540));
    q.addPos(new Position(200,700));
    RandomShape r=new RandomShape(q);

    //

    Moteur_Polygone q3=new Moteur_Polygone(0.5);
    q3.addPos(new Position(100,325));
    q3.addPos(new Position(139,325));
    q3.addPos(new Position(139,425));
    q3.addPos(new Position(100,425));
    RandomShape r3=new RandomShape(q3);
    r3.addCircle(50,0);
    r3.addCircle(50,2);


    Moteur_Polygone q4=new Moteur_Polygone(0.5);
    q4.addPos(new Position(200,325));
    q4.addPos(new Position(239,325));
    q4.addPos(new Position(239,425));
    q4.addPos(new Position(200,425));
    RandomShape r4=new RandomShape(q4);
    r4.addCircle(50,0);
    r4.addCircle(50,2);

    //

    Moteur_Polygone q5=new Moteur_Polygone(0.5);
    q5.addPos(new Position(350,325));
    q5.addPos(new Position(389,325));
    q5.addPos(new Position(389,425));
    q5.addPos(new Position(350,425));
    RandomShape r5=new RandomShape(q5);
    r5.addCircle(50,0);
    r5.addCircle(50,2);

    //

    Moteur_Polygone q6=new Moteur_Polygone(0.5);
    q6.addPos(new Position(450,325));
    q6.addPos(new Position(489,325));
    q6.addPos(new Position(489,425));
    q6.addPos(new Position(450,425));
    RandomShape r6=new RandomShape(q6);
    r6.addCircle(50,0);
    r6.addCircle(50,2);

    //

    Moteur_Polygone q7=new Moteur_Polygone(0.5);
    q7.addPos(new Position(100,100));
    q7.addPos(new Position(150,50));
    q7.addPos(new Position(200,100));
    q7.addPos(new Position(150,150));
    RandomShape r7=new RandomShape(q7);
    //
    Moteur_Polygone q8=new Moteur_Polygone(0.5);
    q8.addPos(new Position(400,100));
    q8.addPos(new Position(450,50));
    q8.addPos(new Position(500,100));
    q8.addPos(new Position(450,150));
    RandomShape r8=new RandomShape(q8);
    Polygon p8 =new Polygon();
    p8.setFill(Color.GREEN);
    p8.getPoints().addAll(q8.getAllPosition());

    Balle balle=new Balle(new Position(20,200),10,5);
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());

    border=new Borders();
    border.addBorder(new Border(new Position(0,10),new Position(850,10),0.9));
    border.addBorder(new Border(new Position(20,680),new Position(220,750),0.9));
    border.addBorder(new Border(new Position(550,680),new Position(350,750),0.9));
    border.addBorder(new Border(new Position(550,300),new Position(550,810),0.9));
    border.addBorder(new Border(new Position(580,0),new Position(580,860),0.9));
    border.addBorder(new Border(new Position(20,0),new Position(20,860),0.9));
    border.addBorder(new Border(new Position(20,815),new Position(550,845),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));

    Flip flipLeft=new Flip(new Position(215,750),new Position(270,760),0.5);
    Flip flipRight=new Flip(new Position(355,750),new Position(300,760),0.5);
    Line leftFlip=new Line(flipLeft.getPosX().getX(),flipLeft.getPosX().getY(),flipLeft.getPosY().getX(),flipLeft.getPosY().getY());
    Line rightFlip=new Line(flipRight.getPosX().getX(),flipRight.getPosX().getY(),flipRight.getPosY().getX(),flipRight.getPosY().getY());
    border.addBorder(flipLeft);
    border.addBorder(flipRight);

    Image rect = new Image("file:rectangle_arrondi.png");
    Image triangle = new Image("file:triangle_bas.png");
    Image triangle2 = new Image("file:triangle_bas2.png");
    Image rond = new Image("file:rond.png");

    ImageView iv = new ImageView();
    iv.setImage(rect);
    iv.setPreserveRatio(true);
    iv.setFitHeight(150);
    iv.setX(100);iv.setY(300);

    ImageView iv2 = new ImageView();
    iv2.setImage(rect);
    iv2.setPreserveRatio(true);
    iv2.setFitHeight(150);
    iv2.setX(200);iv2.setY(300);

    ImageView iv3 = new ImageView();
    iv3.setImage(rect);
    iv3.setPreserveRatio(true);
    iv3.setFitHeight(150);
    iv3.setX(350);iv3.setY(300);

    ImageView iv4 = new ImageView();
    iv4.setImage(rect);
    iv4.setPreserveRatio(true);
    iv4.setFitHeight(150);
    iv4.setX(450);iv4.setY(300);

    ImageView iv5 = new ImageView();
    iv5.setImage(triangle);
    iv5.setPreserveRatio(true);
    iv5.setX(110);iv5.setY(540);

    ImageView iv6 = new ImageView();
    iv6.setImage(triangle2);
    iv6.setPreserveRatio(true);
    iv6.setFitHeight(150);
    iv6.setX(390);iv6.setY(540);

    ImageView iv7 = new ImageView();
    iv7.setImage(rond);
    iv7.setX(100);iv7.setY(50);

    ImageView iv8 = new ImageView();
    iv8.setImage(rond);
    iv8.setX(400);iv8.setY(50);

    pane.getChildren().add(leftFlip);//flip gauche
    pane.getChildren().add(rightFlip);//flip droite
    pane.getChildren().add(circle);
    pane.getChildren().add(iv); //image rect1
    pane.getChildren().add(iv2);
    pane.getChildren().add(iv3);
    pane.getChildren().add(iv4);
    pane.getChildren().add(iv5); //image triangle bas
    pane.getChildren().add(iv6);
    pane.getChildren().add(iv7);
    pane.getChildren().add(iv8);

    shape=new Shapes();
    shape.addShape(r);
    shape.addShape(rr);
    shape.addShape(r3);
    shape.addShape(r4);
    shape.addShape(r5);
    shape.addShape(r6);
    shape.addShape(r7);
    shape.addShape(r8);
    for(Border b:border.getBorders()){
      if(!(b instanceof Flip)){
        pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
      }
    }
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        Border b=border.isOnALine(balle);
        Border s=border.isSliding(balle);
        Border sh=shape.isOnALine(balle);
        boolean  flipLeftuP=flipLeft.isOntheFlip(balle);
        boolean  flipRightUp=flipRight.isOntheFlip(balle);
        if(flipLUP==true)flipLeft.moveFlipUp();
        else flipLeft.moveFlipDown();
        if(flipRUP==true)flipRight.moveFlipUp();
        else flipRight.moveFlipDown();
        if(flipLeftuP==true){
          balle.setFutur(balle.collisionFlip(flipLeft));
        }
        else if(flipRightUp==true){
          balle.setFutur(balle.collisionFlip(flipRight));
        }
        else if(sh!=null&&b!=null){
          balle.setFutur(balle.collision(sh.isCloser(b,balle)));
        }else if(sh!=null){
          balle.setFutur(balle.collision(sh));
        }
        else if(b!=null){
          balle.setFutur(balle.collision(b));
        }
        else if(s!=null){
          balle.setFutur(balle.sliding(s));
        }else{
          balle.setFutur(balle.futur());
        }
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
        leftFlip.setEndX(flipLeft.getPosY().getX());
        leftFlip.setEndY(flipLeft.getPosY().getY());
        rightFlip.setEndX(flipRight.getPosY().getX());
        rightFlip.setEndY(flipRight.getPosY().getY());
      }
    }));
    Scene scene=new Scene(pane,600,900);
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
    primaryStage.setTitle("Flipper");
    primaryStage.show();
  }

}
