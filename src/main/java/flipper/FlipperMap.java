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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class FlipperMap extends Application{
  Borders border;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    Joueur j1 = new Joueur("Joueur 1");
    Text score = new Text(35,875,j1.getPseudo()+" : "+Integer.toString(j1.getScore()));
    score.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 21));
    score.setFill(Color.BLACK);

    Quadrilatere q=new Quadrilatere(0.5,new Position(110,640),new Position(110,540),new Position(200,700),new Position(200,700));
    RandomShape r=new RandomShape(q);

    r.addCircle(15,2);
    r.addCircle(15,1);
    Ellipse e1=new Ellipse(r.getE().get(1).getPos().getX(),r.getE().get(1).getPos().getY(),r.getE().get(1).getMinor()/2,r.getE().get(1).getMajor()/2);
    Rotate rotate=new Rotate(90+r.getE().get(1).getRotate(),r.getE().get(1).getPos().getX(),r.getE().get(1).getPos().getY());
    e1.getTransforms().add(rotate);
    e1.setFill(Color.GREEN);
    Polygon p =new Polygon();
    p.setFill(Color.GREEN);
    p.getPoints().addAll(q.getAllPosition());

    Quadrilatere q2=new Quadrilatere(0.5,new Position(250,150),new Position(350,150),new Position(300,225),new Position(300,225));
    RandomShape r2=new RandomShape(q2);
    r2.addCircle(30,1);
    r2.addCircle(50,2);
    r2.addCircle(50,3);
    Ellipse e2=new Ellipse(r2.getE().get(1).getPos().getX(),r2.getE().get(1).getPos().getY(),r2.getE().get(1).getMinor()/2,r2.getE().get(1).getMajor()/2);
    e2.getTransforms().add(rotate);
    e2.setFill(Color.GREEN);
    Polygon p2 =new Polygon();
    p2.setFill(Color.GREEN);
    p2.getPoints().addAll(q2.getAllPosition());

    Quadrilatere q3=new Quadrilatere(0.5,new Position(100,300),new Position(139,300),new Position(139,450),new Position(100,450));
    RandomShape r3=new RandomShape(q3);
    r3.addCircle(30,1);
    r3.addCircle(50,2);
    r3.addCircle(50,3);
    Ellipse e3=new Ellipse(r3.getE().get(1).getPos().getX(),r3.getE().get(1).getPos().getY(),r3.getE().get(1).getMinor()/2,r3.getE().get(1).getMajor()/2);
    e3.getTransforms().add(rotate);
    e3.setFill(Color.GREEN);
    Polygon p3 =new Polygon();
    p3.setFill(Color.GREEN);
    p3.getPoints().addAll(q3.getAllPosition());

    Quadrilatere q4=new Quadrilatere(0.5,new Position(200,300),new Position(239,300),new Position(239,450),new Position(200,450));
    RandomShape r4=new RandomShape(q4);
    r4.addCircle(30,1);
    r4.addCircle(50,2);
    r4.addCircle(50,3);
    Ellipse e4=new Ellipse(r4.getE().get(1).getPos().getX(),r4.getE().get(1).getPos().getY(),r4.getE().get(1).getMinor()/2,r4.getE().get(1).getMajor()/2);
    e4.getTransforms().add(rotate);
    e4.setFill(Color.GREEN);
    Polygon p4 =new Polygon();
    p4.setFill(Color.GREEN);
    p4.getPoints().addAll(q4.getAllPosition());

    Quadrilatere q5=new Quadrilatere(0.5,new Position(350,300),new Position(389,300),new Position(389,450),new Position(350,450));
    RandomShape r5=new RandomShape(q5);
    r5.addCircle(30,1);
    r5.addCircle(50,2);
    r5.addCircle(50,3);
    Ellipse e5=new Ellipse(r5.getE().get(1).getPos().getX(),r5.getE().get(1).getPos().getY(),r5.getE().get(1).getMinor()/2,r5.getE().get(1).getMajor()/2);
    e5.getTransforms().add(rotate);
    e5.setFill(Color.GREEN);
    Polygon p5 =new Polygon();
    p5.setFill(Color.GREEN);
    p5.getPoints().addAll(q5.getAllPosition());

    Quadrilatere q6=new Quadrilatere(0.5,new Position(450,300),new Position(489,300),new Position(489,450),new Position(450,450));
    RandomShape r6=new RandomShape(q6);
    r6.addCircle(30,1);
    r6.addCircle(50,2);
    r6.addCircle(50,3);
    Ellipse e6=new Ellipse(r6.getE().get(1).getPos().getX(),r6.getE().get(1).getPos().getY(),r6.getE().get(1).getMinor()/2,r6.getE().get(1).getMajor()/2);
    e6.getTransforms().add(rotate);
    e6.setFill(Color.GREEN);
    Polygon p6 =new Polygon();
    p6.setFill(Color.GREEN);
    p6.getPoints().addAll(q6.getAllPosition());

    Quadrilatere q7=new Quadrilatere(0.5,new Position(100,100),new Position(150,50),new Position(200,100),new Position(150,150));
    RandomShape r7=new RandomShape(q7);
    r7.addCircle(30,1);
    r7.addCircle(50,2);
    r7.addCircle(50,3);
    Ellipse e7=new Ellipse(r7.getE().get(1).getPos().getX(),r7.getE().get(1).getPos().getY(),r7.getE().get(1).getMinor()/2,r7.getE().get(1).getMajor()/2);
    e7.getTransforms().add(rotate);
    e7.setFill(Color.GREEN);
    Polygon p7 =new Polygon();
    p7.setFill(Color.GREEN);
    p7.getPoints().addAll(q7.getAllPosition());

    Quadrilatere q8=new Quadrilatere(0.5,new Position(400,100),new Position(450,50),new Position(500,100),new Position(450,150));
    RandomShape r8=new RandomShape(q8);
    r8.addCircle(30,1);
    r8.addCircle(50,2);
    r8.addCircle(50,3);
    Ellipse e8=new Ellipse(r8.getE().get(1).getPos().getX(),r8.getE().get(1).getPos().getY(),r8.getE().get(1).getMinor()/2,r8.getE().get(1).getMajor()/2);
    e8.getTransforms().add(rotate);
    e8.setFill(Color.GREEN);
    Polygon p8 =new Polygon();
    p8.setFill(Color.GREEN);
    p8.getPoints().addAll(q8.getAllPosition());

    Balle balle=new Balle(new Position(100,200),10,5);
    border=new Borders();
    border.addBorder(new Border(new Position(480,540),new Position(390,690),0.9));
    Border boo = new Border(new Position(480,660),new Position(480,540),0.9);
    boo.setScoringTrue();
    boo.setBorderScore(150);
    border.addBorder(boo);
    border.addBorder(new Border(new Position(480,660),new Position(390,690),0.9));
    border.addBorder(new Border(new Position(20,680),new Position(220,750),0.9));
    border.addBorder(new Border(new Position(550,680),new Position(350,750),0.9));
    border.addBorder(new Border(new Position(20,815),new Position(550,845),0.9));
    border.addBorder(new Border(new Position(550,300),new Position(550,810),0.9));
    border.addBorder(new Border(new Position(580,0),new Position(580,860),0.9));
    border.addBorder(new Border(new Position(20,0),new Position(20,860),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());

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

    Pane pane=new Pane();
    pane.getChildren().add(circle);
    pane.getChildren().add(p); //triangle bas gauche
    pane.getChildren().add(e1);
    pane.getChildren().add(p2); //triangle haut
    pane.getChildren().add(p3); //rectanle 1(en partant de la gauche)
    pane.getChildren().add(p4); //rectangle 2
    pane.getChildren().add(p5); //rectangle 3
    pane.getChildren().add(p6); //rectangle 4
    pane.getChildren().add(p7); //losange 1(qui est censé etre un rond)
    pane.getChildren().add(p8); //losange
    pane.getChildren().add(iv); //image rect1
    pane.getChildren().add(iv2);
    pane.getChildren().add(iv3);
    pane.getChildren().add(iv4);
    pane.getChildren().add(iv5); //image triangle bas
    pane.getChildren().add(iv6);
    pane.getChildren().add(iv7);
    pane.getChildren().add(iv8);
    pane.getChildren().add(score);

    for(Border b:border.getBorders()){
      pane.getChildren().add(new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY()));
    }
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        Border b=border.isOnALine(balle);
        if(b!=null){
          pane.getChildren().add(new Circle(b.intersection(balle).getX(),b.intersection(balle).getY(),5));
          balle.setFutur(balle.collision(b));
          if(b.getScoring()){
            j1.addScore(b.getBorderScore());
            score.setText(j1.getPseudo()+" : "+Integer.toString(j1.getScore()));
          }
        }else {
          balle.setFutur(balle.futur());
        }
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
    Scene scene=new Scene(pane,600,900);
    scene.setFill(Color.BROWN);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Flipper");
    primaryStage.show();
  }

}
