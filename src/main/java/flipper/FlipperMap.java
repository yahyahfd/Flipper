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
  Button btnn1, btnn2, btnnn;
  Circle circle;
  ColumnConstraints col1, col2, column1, column2, columna1, columna2, columna3;
  Flip flipLeft, flipRight;
  GridPane mainmen, leftgp, rightgp, aScore, endSc;
  Image rect, triangle, triangle2, rond, forme1, forme2, paroi1, paroi2;
  ImageView iv, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13;
  Joueur j1;
  Label wlc, botl, pl0, HS, finalScore;
  Launcher launcher;
  Line leftFlip, rightFlip, lineLauncher;
  Moteur_Polygone qq, q, q3, q4, q5, q6, q7;
  Moteur_Polygone_Inscribed r8, r9, r10,r77;
  Pane pane;
  RandomShape rr, r, r3, r4, r5, r6, r7;
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
    btnn2 = new Button("Exit");
    vbButtons = new VBox(10);
    vbButtons.setPrefWidth(150);
    pl1.setMaxWidth(vbButtons.getPrefWidth());
    btnn1.setMinWidth(vbButtons.getPrefWidth());
    btnn2.setMinWidth(vbButtons.getPrefWidth());
    vbButtons.getChildren().addAll(pl0,pl1,btnn1,btnn2);
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
    buildHS();
    //on ajoute maintenant ces deux parties au menu principal
    mainmen.add(leftgp,0,0,1,3);
    mainmen.add(rightgp,1,0,1,3);

    //On crée maintenant la scène affichée qui regroupe tout ça
    scene1 = new Scene(mainmen,1000,400);

    primaryStage.setScene(scene1);
    primaryStage.setTitle("Flipper");
    primaryStage.show();
    buildPane(primaryStage);
    //Adding all the elements to the path

        Timeline timeline=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
          public void handle(ActionEvent t){
            if(gameon==true){
              Border b=shapeBorder.isOnALine(balle);
              /////////Mouvement du launcher/////////
              boolean collisionLauncher=false;
              if(launchUp==true){
                launcher.moveLauncherUp();
              }else{
                launcher.moveLauncherDown();
              }
              if(b instanceof Launcher){
                balle.setFutur(balle.collision(b));
                collisionLauncher=true;
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
                if(b!=null){
                  balle.setFutur(balle.collision(b));
                  j1.addScore(b.getBorderScore());
                  score.setText("SCORE : "+Integer.toString(j1.getScore()));
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
            }
          }
        }));

    btnn1.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        buildPane(primaryStage);
        j1.setPseudo(pl1.getText());
        primaryStage.setScene(scene2);
        gameon=true;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
      }
    });

    btnn2.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  public void buildHS(){
    //On importe les données du ficiers JSON puis on les ajoutes sur le GridPane des Highscores
    Leaderboard.load();
    aScore = new GridPane();
    for (int i = 0; i < 10; i++) {
      RowConstraints rConstraint = new RowConstraints();
      rConstraint.setPercentHeight(10);
      aScore.getRowConstraints().add(rConstraint);
    }
    columna1 = new ColumnConstraints();
    columna1.setPercentWidth(15);
    columna2 = new ColumnConstraints();
    columna2.setPercentWidth(70);
    columna3 = new ColumnConstraints();
    columna3.setPercentWidth(15);
    Text lbtmp0 = new Text("Name");
    Text lbtmp1 = new Text("Rank");
    Text lbtmp2 = new Text("Score");
    lbtmp0.setStyle("-fx-font-weight: bold");
    lbtmp1.setStyle("-fx-font-weight: bold");
    lbtmp2.setStyle("-fx-font-weight: bold");
    aScore.add(lbtmp0,0,0);
    aScore.add(lbtmp1,1,0);
    aScore.add(lbtmp2,2,0);
    aScore.getColumnConstraints().addAll(columna1, columna2, columna3);
    for(int i=0;i<Leaderboard.players.size();i++){
      Label tmp = new Label(Leaderboard.players.get(i).getPseudo());
      int k = i+1;
      Label tmp2 = new Label(""+k);
      Label tmp3 = new Label(""+Leaderboard.players.get(i).getScore());
      aScore.add(tmp,0,i+1);
      aScore.add(tmp2,1,i+1);
      aScore.add(tmp3,2,i+1);
    }
    aScore.setGridLinesVisible(true);
    columna1.setHalignment(HPos.CENTER);
    columna2.setHalignment(HPos.CENTER);
    columna3.setHalignment(HPos.CENTER);
    rightgp.getChildren().clear();
    rightgp.add(HS,0,0);
    rightgp.add(aScore,0,1);
  }

  public void buildPane(Stage primaryStage){ //Méthode qui permet de créer la partie en buildant la scene2 consacrée au flipper
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


    btnnn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        buildHS();
        primaryStage.setScene(scene1);
        flipLUP=false;
        flipRUP=false;
        launchUp=false;
      }
    });

    //

    qq=new Moteur_Polygone(0.5,0);      //Triangle droite
    qq.addPos(new Position(419,622));
    qq.addPos(new Position(438,627));
    qq.addPos(new Position(438,670));
    qq.addPos(new Position(430,682));
    qq.addPos(new Position(360,696));
    qq.addPos(new Position(349,679));
    rr=new RandomShape(qq);
    rr.addRoundedBorder(10,0);
    rr.addRoundedBorder(10,2);
    rr.addRoundedBorder(10,4);
    //

    q=new Moteur_Polygone(0.5,50);       //Triangle gauche
    q.addPos(new Position(127,629));
    q.addPos(new Position(145,621));
    q.addPos(new Position(216,681));
    q.addPos(new Position(205,698));
    q.addPos(new Position(145,686));
    q.addPos(new Position(128,662));
    r=new RandomShape(q);
    r.addRoundedBorder(10,0);
    r.addRoundedBorder(10,2);
    r.addRoundedBorder(10,4);
    //

    q3=new Moteur_Polygone(0.5,50);   //rect haut
    q3.addPos(new Position(300,100));
    q3.addPos(new Position(320,100));
    q3.addPos(new Position(320,160));
    q3.addPos(new Position(300,160));
    r3=new RandomShape(q3);
    r3.addRoundedBorder(10,0);
    r3.addRoundedBorder(10,2);

    //

    q4=new Moteur_Polygone(0.5,50);
    q4.addPos(new Position(280,440));
    q4.addPos(new Position(300,440));
    q4.addPos(new Position(300,500));
    q4.addPos(new Position(280,500));
    r4=new RandomShape(q4);
    r4.addRoundedBorder(10,0);
    r4.addRoundedBorder(10,2);

    //

    q5=new Moteur_Polygone(0.5,50);//polygone
    q5.addPos(new Position(200,520));//on ajoute chaque bordure
    q5.addPos(new Position(220,520));
    q5.addPos(new Position(220,580));
    q5.addPos(new Position(200,580));//ici on ajoute 4 bordure pour créer un Quadrilatere
    r5=new RandomShape(q5);
    r5.addRoundedBorder(10,0);//on ajoute une "ellipse" avec une "courbure" 50 a la premiere bordure
    r5.addRoundedBorder(10,2);//on ajoute une "ellipse" avec une "courbure" 50 a la troisieme bordure

    //

    q6=new Moteur_Polygone(0.5,50);
    q6.addPos(new Position(360,520));
    q6.addPos(new Position(380,520));
    q6.addPos(new Position(380,580));
    q6.addPos(new Position(360,580));
    r6=new RandomShape(q6);
    r6.addRoundedBorder(10,0);
    r6.addRoundedBorder(10,2);

    q7=new Moteur_Polygone(0.5,50);
    q7.addPos(new Position(117,93));
    q7.addPos(new Position(181,59));
    q7.addPos(new Position(181,99));
    q7.addPos(new Position(144,143));
    q7.addPos(new Position(117,125));
    r7=new RandomShape(q7);

    r77=new Moteur_Polygone_Inscribed(0.9,50,0,20,new Position(82,222),25,25);

    //

    r8=new Moteur_Polygone_Inscribed(0.9,50,0,20,new Position(240,340),25,25);

    //

    r9=new Moteur_Polygone_Inscribed(0.9,50,0,20,new Position(360,260),25,25);

    r10=new Moteur_Polygone_Inscribed(0.9,50,0,20,new Position(240,200),25,25);

    balle=new Balle(new Position(565,769),10);
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

    //paroi de gauche
    border.addBorder(new Border(new Position(20,503),new Position(31,489),0.9));
    border.addBorder(new Border(new Position(31,489),new Position(37,484),0.9));
    border.addBorder(new Border(new Position(37,484),new Position(47,475),0.9));
    border.addBorder(new Border(new Position(47,475),new Position(58,466),0.9));
    border.addBorder(new Border(new Position(58,466),new Position(72,458),0.9));
    border.addBorder(new Border(new Position(72,458),new Position(93,451),0.9));
    border.addBorder(new Border(new Position(93,451),new Position(106,449),0.9));
    border.addBorder(new Border(new Position(106,449),new Position(106,339),0.9));
    border.addBorder(new Border(new Position(106,339),new Position(79,321),0.9));
    border.addBorder(new Border(new Position(79,321),new Position(46,284),0.9));
    border.addBorder(new Border(new Position(46,284),new Position(28,248),0.9));
    border.addBorder(new Border(new Position(28,248),new Position(20,216),0.9));
    border.addBorder(new Border(new Position(20,216),new Position(20,503),0.9));

    //paroi droite
    border.addBorder(new Border(new Position(550,474),new Position(480,440),0.9));
    border.addBorder(new Border(new Position(480,440),new Position(480,332),0.9));
    border.addBorder(new Border(new Position(480,332),new Position(533,226),0.9));
    border.addBorder(new Border(new Position(533,226),new Position(543,173),0.9));
    border.addBorder(new Border(new Position(543,173),new Position(550,135),0.9));
    border.addBorder(new Border(new Position(550,135),new Position(540,112),0.9));
    border.addBorder(new Border(new Position(540,112),new Position(530,93),0.9));
    border.addBorder(new Border(new Position(530,93),new Position(516,83),0.9));
    border.addBorder(new Border(new Position(516,83),new Position(500,79),0.9));
    border.addBorder(new Border(new Position(500,79),new Position(483,81),0.9));
    border.addBorder(new Border(new Position(483,81),new Position(464,86),0.9));

    //paroi droite 2 (trait courbé)
    border.addBorder(new Border(new Position(440,300),new Position(473,241),0.9));
    border.addBorder(new Border(new Position(473,241),new Position(489,207),0.9));
    border.addBorder(new Border(new Position(489,207),new Position(489,207),0.9));
    border.addBorder(new Border(new Position(489,207),new Position(501,174),0.9));
    border.addBorder(new Border(new Position(501,174),new Position(505,143),0.9));
    border.addBorder(new Border(new Position(505,143),new Position(490,127),0.9));
    border.addBorder(new Border(new Position(490,127),new Position(467,131),0.9));

    flipLeft=new Flip(new Position(220,750),new Position(275,760),0.9);
    flipRight=new Flip(new Position(350,750),new Position(294,760),0.9);
    leftFlip=new Line(flipLeft.getPosX().getX(),flipLeft.getPosX().getY(),flipLeft.getPosY().getX(),flipLeft.getPosY().getY());
    rightFlip=new Line(flipRight.getPosX().getX(),flipRight.getPosX().getY(),flipRight.getPosY().getX(),flipRight.getPosY().getY());
    launcher=new Launcher(new Position(550,780),new Position(580,780),0.9);
    lineLauncher=new Line(launcher.getPosX().getX(),launcher.getPosX().getY(),launcher.getPosY().getX(),launcher.getPosY().getY());
    border.addBorder(flipLeft);
    border.addBorder(flipRight);
    border.addBorder(launcher);
    leftFlip.setStrokeWidth(5);
    rightFlip.setStrokeWidth(5);
    lineLauncher.setStrokeWidth(5);

    rect = new Image("file:rectangle_arrondi.png");
    triangle = new Image("file:triangle_bas.png");
    triangle2 = new Image("file:triangle_bas2.png");
    rond = new Image("file:rond.png");
    forme1 = new Image("file:forme_courbe.png");
    forme2 = new Image("file:forme2.png");
    paroi1 = new Image("file:paroi.png");
    paroi2 = new Image("file:paroi2.png");

    iv = new ImageView();
    iv.setImage(rect);
    iv.setFitHeight(80);
    iv.setPreserveRatio(true);
    iv.setX(300);iv.setY(90);

    iv2 = new ImageView();
    iv2.setImage(rect);
    iv2.setPreserveRatio(true);
    iv2.setFitHeight(80);
    iv2.setX(280);iv2.setY(430);

    iv3 = new ImageView();
    iv3.setImage(rect);
    iv3.setPreserveRatio(true);
    iv3.setFitHeight(80);
    iv3.setX(200);iv3.setY(510);

    iv4 = new ImageView();
    iv4.setImage(rect);
    iv4.setPreserveRatio(true);
    iv4.setFitHeight(80);
    iv4.setX(360);iv4.setY(510);

    iv5 = new ImageView();
    iv5.setImage(triangle);
    iv5.setPreserveRatio(true);
    iv5.setFitHeight(80);
    iv5.setX(347);iv5.setY(617);

    iv6 = new ImageView();
    iv6.setImage(triangle2);
    iv6.setPreserveRatio(true);
    iv6.setFitHeight(80);
    iv6.setX(127);iv6.setY(618);

    iv7 = new ImageView();
    iv7.setImage(rond);
    iv7.setPreserveRatio(true);
    iv7.setFitHeight(50);
    iv7.setX(215);iv7.setY(315);

    iv8 = new ImageView();
    iv8.setImage(rond);
    iv8.setPreserveRatio(true);
    iv8.setFitHeight(50);
    iv8.setX(335);iv8.setY(235);

    iv9 = new ImageView();
    iv9.setImage(rond);
    iv9.setPreserveRatio(true);
    iv9.setFitHeight(50);
    iv9.setX(215);iv9.setY(175);

    iv10 = new ImageView();
    iv10.setImage(rond);
    iv10.setPreserveRatio(true);
    iv10.setFitHeight(50);
    iv10.setX(57);iv10.setY(197);

    iv11 = new ImageView();
    iv11.setImage(forme2);
    iv11.setX(117);iv11.setY(59);

    iv12 = new ImageView();
    iv12.setImage(paroi1);
    iv12.setX(20);iv12.setY(230);

    iv13 = new ImageView();
    iv13.setImage(paroi2);
    iv13.setX(480);iv13.setY(155);

    pane.getChildren().add(lineLauncher);//flip gauche
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
    pane.getChildren().add(iv9);
    pane.getChildren().add(iv10);
    pane.getChildren().add(iv11);
    pane.getChildren().add(iv12);
    pane.getChildren().add(iv13);
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
    shape.addShape(r9);
    shape.addShape(r10);
    shape.addShape(r77);
    for(Border b:border.getBorders()){
      if(!(b instanceof Flip)&&!(b instanceof Launcher)){
        Line l=new Line(b.getPosX().getX(),b.getPosX().getY(),b.getPosY().getX(),b.getPosY().getY());
        l.setStrokeWidth(5);
        pane.getChildren().add(l);
      }
    }
    shapeBorder=new ShapeBorder(border,shape);
    //Zone en dessous des flips
    endB = new Borders();
    endB.addBorder(new Border(new Position(220,770),new Position(275,780),0.9));
    endB.addBorder(new Border(new Position(350,770),new Position(294,780),0.9));
    endB.addBorder(new Border(new Position(275,780),new Position(294,780),0.9));
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
  }
}
