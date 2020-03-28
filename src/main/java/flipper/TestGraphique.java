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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
public class TestGraphique extends Application{
  Borders border;
  int a=0;
  boolean flipLUP=false;
  boolean flipRUP=false;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    GridPane mainmenu = new GridPane();
    Scene root = new Scene(mainmenu);
    GridPane left = new GridPane();
    GridPane right = new GridPane();
    left.setStyle("-fx-background-color: #3d4855");
    right.setStyle("-fx-background-color: #e79e6d");
    Label wlc = new Label("WELCOME");
    Label HS = new Label("HIGH SCORES");
    Label bot = new Label("flipper, by EKIP");
    wlc.setStyle("-fx-text-fill: white;");
    wlc.setFont(new Font("Arial Black", 22));
    HS.setStyle("-fx-text-fill: white;");
    HS.setFont(new Font("Arial Black", 22));
    mainmenu.add(left,0,0,1,3);
    mainmenu.add(right,1,0,1,3);
    left.add(wlc,1,0);
    right.add(HS,1,0);
    left.setHgap(10);
    left.setVgap(10);
    left.setPadding(new Insets(10, 10, 10, 10));
    right.setHgap(10);
    right.setVgap(10);
    right.setPadding(new Insets(10, 10, 10, 10));

    //Buttons
    Button btn1 = new Button("Play");
    Button btn2 = new Button("Settings");
    Button btn3 = new Button("Rules");
    btn1.setMaxWidth(Double.MAX_VALUE);
    btn2.setMaxWidth(Double.MAX_VALUE);
    btn3.setMaxWidth(Double.MAX_VALUE);
    VBox vbButtons = new VBox();
    vbButtons.setSpacing(10);
    vbButtons.setPadding(new Insets(0, 20, 10, 20));
    vbButtons.getChildren().addAll(btn1,btn2,btn3);
    left.add(vbButtons,1,1,1,3);

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
      border.addBorder(b);
    }
    Quadrilatere q1 = new Quadrilatere(0.5, new Position(250,350), new Position(350,350), new Position(350,400), new Position(250,400));
    for(Border b:q1.turnIntoBorders()){
      border.addBorder(b);
    }
    moteurEllipse e = new moteurEllipse(0,0.9,50,25,new Position(125,300));
    Ellipse e2 = new Ellipse(125,300,50,25);
    Circle circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());
    Pane pane=new Pane();
    Scene scene=new Scene(pane,1080,900);
    pane.getChildren().add(circle);
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
    primaryStage.setScene(root);
    btn1.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
          primaryStage.setScene(scene);
      }
    });
    primaryStage.setTitle("TestGraphique");
    primaryStage.show();
  }

}
