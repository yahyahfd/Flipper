package flipper;
import java.io.File;
import moteur_physique.*;
import javafx.scene.layout.BackgroundImage;
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
import javafx.scene.control.TextField;
import javafx.stage.Screen;
public class FlipperMap extends Application{
  Balle balle;
  boolean flipLUP=false;
  boolean flipRUP=false;
  boolean launchUp=false;
  boolean gameon=false;
  Borders border, endB;
  Button btnn1, btnn2, btnn3, btnnn;
  Circle circle;
  ColumnConstraints col1, col2, column1, column2, columna1;
  Flip flipLeft, flipRight;
  GridPane mainmen, leftgp, rightgp, aScore, endSc;
  Image rect, triangle, triangle2, rond;
  ImageView iv, iv2, iv3, iv4, iv5, iv6, iv7, iv8;
  Joueur j1;
  Label wlc, botl, pl0, HS, finalScore;
  Launcher launcher;
  Line leftFlip, rightFlip, lineLauncher;
  Moteur_Polygone qq, q, q3, q4, q5, q6;
  Moteur_Polygone_Inscribed r7, r8;
  Pane pane;
  Polygon pp, p;
  RandomShape rr, r, r3, r4, r5, r6;
  RowConstraints row1, row2, row3, row4, row5, row6;
  Scene scene1, scene2, scene3;
  Shapes shape;
  ShapeBorder shapeBorder;
  Text score;
  TextField pl1;
  VBox vbButtons, vbfinal;

  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage){
    //center into screen
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

    shape=new Shapes();
    Leaderboard.load();
    /**menu principal*/
    mainmen = new GridPane();
    //on resize les colonnes
    col1 = new ColumnConstraints();
    col2 = new ColumnConstraints();
    row1 = new RowConstraints();
    row1.setPercentHeight(100);
    col1.setPercentWidth(30);
    col2.setPercentWidth(70);
    mainmen.getColumnConstraints().addAll(col1,col2);
    mainmen.getRowConstraints().addAll(row1);
    //on se charge d'abord du panneau de gauche avec les boutons "play" etc
    leftgp = new GridPane();
    row2 = new RowConstraints();
    row3 = new RowConstraints();
    row4 = new RowConstraints();
    column1 = new ColumnConstraints();
    column1.setPercentWidth(100);
    leftgp.getColumnConstraints().add(column1);
    row2.setPercentHeight(15);
    row3.setPercentHeight(70);
    row4.setPercentHeight(15);
    leftgp.getRowConstraints().addAll(row2,row3,row4);
    leftgp.setStyle("-fx-background-color: #3d4855");
    wlc = new Label("WELCOME");
    wlc.setStyle("-fx-text-fill: white;");
    wlc.setFont(new Font("Arial Black", 22));
    leftgp.add(wlc,0,0);
    botl = new Label("flipper, by EKIP  ");
    leftgp.add(botl,0,2);
    //Username
    pl0 = new Label("Play as: ");
    pl0.setStyle("-fx-text-fill: white;");
    pl1 = new TextField("Guest");
    //Buttons
    btnn1 = new Button("Start");
    btnn2 = new Button("Settings");
    btnn3 = new Button("Rules");
    vbButtons = new VBox(10);
    vbButtons.setPrefWidth(150);
    pl1.setMaxWidth(vbButtons.getPrefWidth());
    btnn1.setMinWidth(vbButtons.getPrefWidth());
    btnn2.setMinWidth(vbButtons.getPrefWidth());
    btnn3.setMinWidth(vbButtons.getPrefWidth());
    vbButtons.getChildren().addAll(pl0,pl1,btnn1,btnn2,btnn3);
    leftgp.add(vbButtons,0,1);
    leftgp.setHalignment(wlc,HPos.CENTER);
    vbButtons.setAlignment(Pos.CENTER);
    leftgp.setHalignment(botl,HPos.RIGHT);
    // puis de la partie droite avec les meilleurs scores
    rightgp = new GridPane();
    row5 = new RowConstraints();
    row6 = new RowConstraints();
    column2 = new ColumnConstraints();
    column2.setPercentWidth(100);
    rightgp.getColumnConstraints().add(column2);
    row5.setPercentHeight(15);
    row6.setPercentHeight(85);
    rightgp.getRowConstraints().addAll(row5,row6);
    rightgp.setStyle("-fx-background-color: #e79e6d");
    HS = new Label("HIGH SCORES");
    HS.setStyle("-fx-text-fill: white;");
    HS.setFont(new Font("Arial Black", 22));
    rightgp.add(HS,0,0);
    rightgp.setHalignment(HS,HPos.CENTER);
    //Highscores
    aScore = new GridPane();
    for (int i = 0; i < 10; i++) {
      RowConstraints rConstraint = new RowConstraints();
      rConstraint.setPercentHeight(10);
      aScore.getRowConstraints().add(rConstraint);
    }
    columna1 = new ColumnConstraints();
    columna1.setPercentWidth(100);
    aScore.getColumnConstraints().add(columna1);
    for(int i=0;i<Leaderboard.players.size();i++){
      Label tmp = new Label(Leaderboard.players.get(i).getPseudo()+" "+Leaderboard.players.get(i).getScore());
      aScore.add(tmp,0,i);
    }
    rightgp.add(aScore,0,1);
    //on ajoute maintenant ces deux parties au menu principal
    mainmen.add(leftgp,0,0,1,3);
    mainmen.add(rightgp,1,0,1,3);

    //On crée maintenant la scène affichée qui regroupe tout ça
    scene1 = new Scene(mainmen,1000,400);

    primaryStage.setScene(scene1);
    primaryStage.setTitle("Flipper");
    primaryStage.show();
    buildPane();
    //Adding all the elements to the path
    Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent t){
        if(gameon==true){
          Border s=shapeBorder.isSliding(balle);
          Border b=shapeBorder.isOnALine(balle);
          /////////Mouvement du launcher/////////
          boolean collisionLauncher=false;
          if(launchUp==true){
            boolean u=launcher.isOnTop(balle);
            launcher.moveLauncherUp();
            if(u){
              balle.setFutur(balle.collisionLauncher(launcher));
              collisionLauncher=true;
            }else collisionLauncher=false;
          }else{
            boolean u=launcher.isOnTop(balle);
            launcher.moveLauncherDown();
            if(u){
              balle.setFutur(balle.collisionLauncher(launcher));
              collisionLauncher=true;
            }else collisionLauncher=false;
          }

          /////////Mouvement des flipper/////////
          boolean collision=false;
          if(flipLUP==true){
            flipLeft.moveFlipUp();
          }
          else flipLeft.moveFlipDown();

          if(flipRUP==true){
            flipRight.moveFlipUp();
          }
          else flipRight.moveFlipDown();

          /////////Collision/////////
          if(collisionLauncher==false){
            if(b!=null&&s!=null){
              if(b.isSuccessive(s))
              balle.setFutur(balle.sliding(b));
              else
              balle.setFutur(balle.slidingColliding(s,b));
            }
            else if(s!=null){
              balle.setFutur(balle.sliding(s));
            }
            else if(b!=null){
              balle.setFutur(balle.collision(b));
              if(b.getScoring()){
                j1.addScore(b.getBorderScore());
                score.setText("SCORE : "+Integer.toString(j1.getScore()));
              }
            }else{
              balle.setFutur(balle.futur());
            }
          }

          /////////Actualisation des Position/////////
          circle.setCenterX(balle.getPos().getX());
          circle.setCenterY(balle.getPos().getY());
          leftFlip.setEndX(flipLeft.getPosY().getX());
          leftFlip.setEndY(flipLeft.getPosY().getY());
          lineLauncher.setStartY(launcher.getPosX().getY());
          lineLauncher.setEndY(launcher.getPosY().getY());
          rightFlip.setEndX(flipRight.getPosY().getX());
          rightFlip.setEndY(flipRight.getPosY().getY());

          /////////Fin du jeu/////////
          if(endB.endGamex(balle)==true){
            Leaderboard.save(j1);
            finalScore.setText("Score Finale en tant que "+j1.getPseudo()+(": ")+Integer.toString(j1.getScore()));
            primaryStage.setScene(scene3);
            gameon=false;
          }
        }else{
          System.out.println("hehe");
        }
      }
    }));
    scene2=new Scene(pane,600,900);
    pane.setId("pane");
    scene2.getStylesheets().addAll("file:style.css");
    scene2.setFill(Color.BROWN);
    scene2.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=true;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=true;
      }
      if(key.getCode()==KeyCode.SPACE) {
        launchUp=true;
      }
    });
    scene2.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
      if(key.getCode()==KeyCode.LEFT) {
        flipLUP=false;
      }
      if(key.getCode()==KeyCode.RIGHT) {
        flipRUP=false;
      }
      if(key.getCode()==KeyCode.SPACE) {
        launchUp=false;
      }
    });
    btnn1.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        j1.setPseudo(pl1.getText());
        primaryStage.setScene(scene2);
        gameon=true;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
      }
    });
    btnnn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        primaryStage.setScene(scene1);
        buildPane();
      }
    });
  }

  public void buildPane(){
    /**JEU*/
    pane=new Pane();
    j1 = new Joueur();
    score = new Text(35,875,Integer.toString(j1.getScore()));
    score.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 21));
    score.setFill(Color.BLACK);

    //Ecran d'après jeu
    endSc = new GridPane();
    endSc.setAlignment(Pos.CENTER);
    finalScore = new Label();
    btnnn = new Button("Menu Principal");
    vbfinal = new VBox(10);
    vbfinal.getChildren().addAll(finalScore,btnnn);
    vbfinal.setAlignment(Pos.CENTER);
    endSc.add(vbfinal,0,0);
    scene3 = new Scene(endSc,1000,400);

    //

    qq=new Moteur_Polygone(0.5,0);
    qq.addPos(new Position(480,540));
    qq.addPos(new Position(390,680));
    qq.addPos(new Position(480,660));
    rr=new RandomShape(qq);
    pp=new Polygon();
    pp.getPoints().addAll(qq.getAllPosition());
    pane.getChildren().add(pp);
    //

    q=new Moteur_Polygone(0.5,50);
    q.addPos(new Position(110,640));
    q.addPos(new Position(110,540));
    q.addPos(new Position(200,700));
    r=new RandomShape(q);
    p=new Polygon();
    p.getPoints().addAll(q.getAllPosition());
    pane.getChildren().add(p);
    //

    q3=new Moteur_Polygone(0.5,50);
    q3.addPos(new Position(100,325));
    q3.addPos(new Position(139,325));
    q3.addPos(new Position(139,425));
    q3.addPos(new Position(100,425));
    r3=new RandomShape(q3);
    r3.addRoundedBorder(25,0);
    r3.addRoundedBorder(25,2);

    //

    q4=new Moteur_Polygone(0.5,50);
    q4.addPos(new Position(200,325));
    q4.addPos(new Position(239,325));
    q4.addPos(new Position(239,425));
    q4.addPos(new Position(200,425));
    r4=new RandomShape(q4);
    r4.addRoundedBorder(25,0);
    r4.addRoundedBorder(25,2);

    //

    q5=new Moteur_Polygone(0.5,50);//polygone
    q5.addPos(new Position(350,325));//on ajoute chaque bordure
    q5.addPos(new Position(389,325));
    q5.addPos(new Position(389,425));
    q5.addPos(new Position(350,425));//ici on ajoute 4 bordure pour créer un Quadrilatere
    r5=new RandomShape(q5);
    r5.addRoundedBorder(25,0);//on ajoute une "ellipse" avec une "courbure" 50 a la premiere bordure
    r5.addRoundedBorder(25,2);//on ajoute une "ellipse" avec une "courbure" 50 a la troisieme bordure

    //

    q6=new Moteur_Polygone(0.5,50);
    q6.addPos(new Position(450,325));
    q6.addPos(new Position(489,325));
    q6.addPos(new Position(489,425));
    q6.addPos(new Position(450,425));
    r6=new RandomShape(q6);
    r6.addRoundedBorder(25,0);
    r6.addRoundedBorder(25,2);

    //

    r7=new Moteur_Polygone_Inscribed(0.9,50,0,30,new Position(150,100),50,50);

    //

    r8=new Moteur_Polygone_Inscribed(0.9,50,0,30,new Position(450,100),50,50);

    balle=new Balle(new Position(40,100),10,5);
    circle=new Circle(balle.getPos().getX(),balle.getPos().getY(),balle.getR());


    border=new Borders();
    border.addBorder(new Border(new Position(153,10),new Position(447,10),0.9));
    border.addBorder(new Border(new Position(20,680),new Position(220,750),0.9));
    border.addBorder(new Border(new Position(550,680),new Position(350,750),0.9));
    border.addBorder(new Border(new Position(550,135),new Position(550,810),0.9));
    border.addBorder(new Border(new Position(580,135),new Position(580,860),0.9));
    border.addBorder(new Border(new Position(20,135),new Position(20,860),0.9));
    border.addBorder(new Border(new Position(20,815),new Position(550,845),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));
    border.addBorder(new Border(new Position(0,850),new Position(590,850),0.9));

    ////Arc en haut a droite////
    border.addBorder(new Border(new Position(580,135),new Position(576,110),0.9));
    border.addBorder(new Border(new Position(576,110),new Position(569,86),0.9));
    border.addBorder(new Border(new Position(569,86),new Position(557,66),0.9));
    border.addBorder(new Border(new Position(557,66),new Position(540,46),0.9));
    border.addBorder(new Border(new Position(540,46),new Position(518,30),0.9));
    border.addBorder(new Border(new Position(518,30),new Position(496,21),0.9));
    border.addBorder(new Border(new Position(496,21),new Position(447,10),0.9));

    ////Arc en haut a gauche////
    border.addBorder(new Border(new Position(20,135),new Position(24,110),0.9));
    border.addBorder(new Border(new Position(24,110),new Position(31,86),0.9));
    border.addBorder(new Border(new Position(31,86),new Position(43,66),0.9));
    border.addBorder(new Border(new Position(43,66),new Position(60,46),0.9));
    border.addBorder(new Border(new Position(60,46),new Position(82,30),0.9));
    border.addBorder(new Border(new Position(82,30),new Position(104,21),0.9));
    border.addBorder(new Border(new Position(104,21),new Position(153,10),0.9));

    flipLeft=new Flip(new Position(220,750),new Position(275,760),0.9);
    flipRight=new Flip(new Position(350,750),new Position(294,760),0.9);
    leftFlip=new Line(flipLeft.getPosX().getX(),flipLeft.getPosX().getY(),flipLeft.getPosY().getX(),flipLeft.getPosY().getY());
    rightFlip=new Line(flipRight.getPosX().getX(),flipRight.getPosX().getY(),flipRight.getPosY().getX(),flipRight.getPosY().getY());
    launcher=new Launcher(new Position(550,780),new Position(580,780),0.9);
    lineLauncher=new Line(launcher.getPosX().getX(),launcher.getPosX().getY(),launcher.getPosY().getX(),launcher.getPosY().getY());
    border.addBorder(flipLeft);
    border.addBorder(flipRight);
    leftFlip.setStrokeWidth(5);
    rightFlip.setStrokeWidth(5);
    lineLauncher.setStrokeWidth(5);

    rect = new Image("file:rectangle_arrondi.png");
    triangle = new Image("file:triangle_bas.png");
    triangle2 = new Image("file:triangle_bas2.png");
    rond = new Image("file:rond.png");

    iv = new ImageView();
    iv.setImage(rect);
    iv.setPreserveRatio(true);
    iv.setFitHeight(150);
    iv.setX(100);iv.setY(300);

    iv2 = new ImageView();
    iv2.setImage(rect);
    iv2.setPreserveRatio(true);
    iv2.setFitHeight(150);
    iv2.setX(200);iv2.setY(300);

    iv3 = new ImageView();
    iv3.setImage(rect);
    iv3.setPreserveRatio(true);
    iv3.setFitHeight(150);
    iv3.setX(350);iv3.setY(300);

    iv4 = new ImageView();
    iv4.setImage(rect);
    iv4.setPreserveRatio(true);
    iv4.setFitHeight(150);
    iv4.setX(450);iv4.setY(300);

    iv5 = new ImageView();
    iv5.setImage(triangle);
    iv5.setPreserveRatio(true);
    iv5.setX(110);iv5.setY(540);

    iv6 = new ImageView();
    iv6.setImage(triangle2);
    iv6.setPreserveRatio(true);
    iv6.setFitHeight(150);
    iv6.setX(390);iv6.setY(540);

    iv7 = new ImageView();
    iv7.setImage(rond);
    iv7.setX(100);iv7.setY(50);

    iv8 = new ImageView();
    iv8.setImage(rond);
    iv8.setX(400);iv8.setY(50);

    pane.getChildren().add(lineLauncher);//flip gauche
    pane.getChildren().add(leftFlip);//flip gauche
    pane.getChildren().add(rightFlip);//flip droite
    pane.getChildren().add(circle);
    pane.getChildren().add(iv); //image rect1
    pane.getChildren().add(iv2);
    pane.getChildren().add(iv3);
    pane.getChildren().add(iv4);
    // pane.getChildren().add(iv5); //image triangle bas
    // pane.getChildren().add(iv6);
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
        Line l=new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY());
        l.setStrokeWidth(5);
        pane.getChildren().add(l);
      }
    }
    shapeBorder=new ShapeBorder(border,shape);

    //Zone en dessous des flips
    endB = new Borders();
    endB.addBorder(new Border(new Position(220,765),new Position(275,775),0.9));
    endB.addBorder(new Border(new Position(350,765),new Position(294,775),0.9));
    endB.addBorder(new Border(new Position(275,775),new Position(294,775),0.9));
  }
}
