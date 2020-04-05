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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.*;
import javafx.scene.layout.RowConstraints;
public class FlipperMap extends Application{
  Borders border;
  Shapes shape;
  boolean flipLUP=false;
  boolean flipRUP=false;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    /**menu principal*/
    GridPane mainmen = new GridPane();
    //on resize les colonnes
    ColumnConstraints col1 = new ColumnConstraints();
    ColumnConstraints col2 = new ColumnConstraints();
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(100);
    col1.setPercentWidth(30);
    col2.setPercentWidth(70);
    mainmen.getColumnConstraints().addAll(col1,col2);
    mainmen.getRowConstraints().addAll(row1);
    //on se charge d'abord du panneau de gauche avec les boutons "play" etc
    GridPane leftgp = new GridPane();
    RowConstraints row2 = new RowConstraints();
    RowConstraints row3 = new RowConstraints();
    RowConstraints row4 = new RowConstraints();
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(100);
    leftgp.getColumnConstraints().add(column1);
    row2.setPercentHeight(15);
    row3.setPercentHeight(70);
    row4.setPercentHeight(15);
    leftgp.getRowConstraints().addAll(row2,row3,row4);
    leftgp.setStyle("-fx-background-color: #3d4855");
    Label wlc = new Label("WELCOME");
    wlc.setStyle("-fx-text-fill: white;");
    wlc.setFont(new Font("Arial Black", 22));
    leftgp.add(wlc,0,0);
    Label botl = new Label("flipper, by EKIP  ");
    leftgp.add(botl,0,2);
    //Buttons
    Button btnn1 = new Button("Play");
    Button btnn2 = new Button("Settings");
    Button btnn3 = new Button("Rules");
    VBox vbButtons = new VBox(10);
    vbButtons.setPrefWidth(150);
    btnn1.setMinWidth(vbButtons.getPrefWidth());
    btnn2.setMinWidth(vbButtons.getPrefWidth());
    btnn3.setMinWidth(vbButtons.getPrefWidth());
    vbButtons.getChildren().addAll(btnn1,btnn2,btnn3);
    leftgp.add(vbButtons,0,1);
    leftgp.setHalignment(wlc,HPos.CENTER);
    vbButtons.setAlignment(Pos.CENTER);
    leftgp.setHalignment(botl,HPos.RIGHT);
    // puis de la partie droite avec les meilleurs scores (pour l'instant vide)
    GridPane rightgp = new GridPane();
    RowConstraints row5 = new RowConstraints();
    RowConstraints row6 = new RowConstraints();
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(100);
    rightgp.getColumnConstraints().add(column2);
    row5.setPercentHeight(15);
    row6.setPercentHeight(85);
    rightgp.getRowConstraints().addAll(row5,row6);
    rightgp.setStyle("-fx-background-color: #e79e6d");
    Label HS = new Label("HIGH SCORES");
    HS.setStyle("-fx-text-fill: white;");
    HS.setFont(new Font("Arial Black", 22));
    rightgp.add(HS,0,0);
    leftgp.setHalignment(HS,HPos.CENTER);
    //on ajoute maintenant ces deux parties au menu principal
    mainmen.add(leftgp,0,0,1,3);
    mainmen.add(rightgp,1,0,1,3);

    //On crée maintenant la scène affichée qui regroupe tout ça
    Scene scene1 = new Scene(mainmen,1000,400);

    primaryStage.setScene(scene1);
    primaryStage.setTitle("Flipper");
    primaryStage.show();
    /**JEU*/
    Pane pane=new Pane();
    Joueur j1 = new Joueur("Joueur 1");
    Text score = new Text(35,875,j1.getPseudo()+" : "+Integer.toString(j1.getScore()));
    score.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 21));
    score.setFill(Color.BLACK);


    //

    Moteur_Polygone qq=new Moteur_Polygone(0.5,0);
    qq.addPos(new Position(480,540));
    qq.addPos(new Position(390,680));
    qq.addPos(new Position(480,660));
    RandomShape rr=new RandomShape(qq);

    Moteur_Polygone q=new Moteur_Polygone(0.5,50);
    q.addPos(new Position(110,640));
    q.addPos(new Position(110,540));
    q.addPos(new Position(200,700));
    RandomShape r=new RandomShape(q);

    //

    Moteur_Polygone q3=new Moteur_Polygone(0.5,50);
    q3.addPos(new Position(100,325));
    q3.addPos(new Position(139,325));
    q3.addPos(new Position(139,425));
    q3.addPos(new Position(100,425));
    RandomShape r3=new RandomShape(q3);
    r3.addCircle(50,0);
    r3.addCircle(50,2);


    Moteur_Polygone q4=new Moteur_Polygone(0.5,50);
    q4.addPos(new Position(200,325));
    q4.addPos(new Position(239,325));
    q4.addPos(new Position(239,425));
    q4.addPos(new Position(200,425));
    RandomShape r4=new RandomShape(q4);
    r4.addCircle(50,0);
    r4.addCircle(50,2);

    //

    Moteur_Polygone q5=new Moteur_Polygone(0.5,50);
    q5.addPos(new Position(350,325));
    q5.addPos(new Position(389,325));
    q5.addPos(new Position(389,425));
    q5.addPos(new Position(350,425));
    RandomShape r5=new RandomShape(q5);
    r5.addCircle(50,0);
    r5.addCircle(50,2);

    //

    Moteur_Polygone q6=new Moteur_Polygone(0.5,50);
    q6.addPos(new Position(450,325));
    q6.addPos(new Position(489,325));
    q6.addPos(new Position(489,425));
    q6.addPos(new Position(450,425));
    RandomShape r6=new RandomShape(q6);
    r6.addCircle(50,0);
    r6.addCircle(50,2);

    //

    Moteur_Polygone q7=new Moteur_Polygone(0.5,0);
    q7.addPos(new Position(100,100));
    q7.addPos(new Position(150,50));
    q7.addPos(new Position(200,100));
    q7.addPos(new Position(150,150));
    RandomShape r7=new RandomShape(q7);
    //
    Moteur_Polygone q8=new Moteur_Polygone(0.5,0);
    q8.addPos(new Position(400,100));
    q8.addPos(new Position(450,50));
    q8.addPos(new Position(500,100));
    q8.addPos(new Position(450,150));
    RandomShape r8=new RandomShape(q8);
    Polygon p8 =new Polygon();
    p8.setFill(Color.GREEN);
    p8.getPoints().addAll(q8.getAllPosition());

    Balle balle=new Balle(new Position(30,200),4,5);
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

    Flip flipLeft=new Flip(new Position(220,750),new Position(283,760),0.5);
    Flip flipRight=new Flip(new Position(350,750),new Position(287,760),0.5);
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
    pane.getChildren().add(score);

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
        Border s=border.isSliding(balle);
        boolean collision=false;
        if(flipLUP==true){
          boolean u=flipLeft.isOnTop(balle)||s==flipLeft;
          flipLeft.moveFlipUp();
          if(u&&(flipLeft.willBeUnder(balle)||flipLeft.isUnder(balle))){
            balle.setFutur(balle.collisionFlip(flipLeft));
            collision=true;
          }
        }
        else flipLeft.moveFlipDown();

        if(flipRUP==true){
          boolean u=flipRight.isOnTop(balle)||s==flipRight;
          flipRight.moveFlipUp();
          if(u&&(flipRight.willBeUnder(balle)||flipRight.isUnder(balle))){
            balle.setFutur(balle.collisionFlip(flipRight));
            collision=true;
          }
        }
        else flipRight.moveFlipDown();
        s=border.isSliding(balle);
        Border b=border.isOnALine(balle);
        Border sh=shape.isOnALine(balle);
        if(sh!=null&&b!=null){
          balle.setFutur(balle.collision(sh.isCloser(b,balle)));
        }else if(b!=null&&s!=null){
          if(b.isSuccessive(s))
            balle.setFutur(balle.sliding(b));
          else
            balle.setFutur(balle.slidingColliding(s,b));

        }else if(sh!=null){
          balle.setFutur(balle.collision(sh));
          j1.addScore(sh.getBorderScore());
          score.setText(j1.getPseudo()+" : "+Integer.toString(j1.getScore()));

        }
        else if(b!=null){
          balle.setFutur(balle.collision(b));
          if(b.getScoring()){
            j1.addScore(b.getBorderScore());
            score.setText(j1.getPseudo()+" : "+Integer.toString(j1.getScore()));
          }
        }
        else if(s!=null){
          balle.setFutur(balle.sliding(s));
        }else{
          if(collision==false){
            balle.setFutur(balle.futur());
          }
        }
        pane.getChildren().add(new Circle(balle.getPos().getX(),balle.getPos().getY(),1,Color.BLACK));
        circle.relocate(balle.getPos().getX(),balle.getPos().getY());
        leftFlip.setEndX(flipLeft.getPosY().getX());
        leftFlip.setEndY(flipLeft.getPosY().getY());
        rightFlip.setEndX(flipRight.getPosY().getX());
        rightFlip.setEndY(flipRight.getPosY().getY());
      }
    }));
    Scene scene2=new Scene(pane,600,900);
    scene2.setFill(Color.BROWN);
    scene2.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=true;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=true;
      }
    });
    scene2.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=false;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=false;
      }
    });
    btnn1.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        primaryStage.setScene(scene2);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
      }
    });
  }

}
