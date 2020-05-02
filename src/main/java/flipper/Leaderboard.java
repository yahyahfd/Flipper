package flipper;
import moteur_physique.*;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Leaderboard{
  static ArrayList<Joueur> players = new ArrayList<Joueur>();

  public static void load(){
    File tmp = new File("src/main/java/flipper/leaderboard.json");
    try(InputStream in = new FileInputStream(tmp)){
      String content = IOUtils.toString(in, StandardCharsets.UTF_8);
      JSONParser parser = new JSONParser();
      JSONArray json = (JSONArray) parser.parse(content);
      Iterator i = json.iterator();
      while (i.hasNext()) {
        JSONObject slide = (JSONObject) i.next();
        String nom = (String)slide.get("nom");
        String score = (String)slide.get("score");
        Joueur tmp1 = new Joueur();
        tmp1.setPseudo(nom);
        tmp1.setScore(Integer.parseInt(score));
        players.add(tmp1);
      }
    }catch(FileNotFoundException e1){
      System.out.println("Leaderboard file not found");
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public static void sort(){
    ArrayList<Joueur> tmp = new ArrayList<Joueur>();
    int i;
    if(players.size()>10){
      i=10;
    }else{
      i=players.size();
    }
    for(int j=0;j<i;j++){

    }
  }

  public void save(Joueur j){
    File tmp = new File("src/main/java/flipper/leaderboard.json");
    try(InputStream in = new FileInputStream(tmp)){
      String content = IOUtils.toString(in, StandardCharsets.UTF_8);
      JSONParser parser = new JSONParser();
      JSONArray json = (JSONArray) parser.parse(content);
      JSONObject tmp1 = new JSONObject();
      tmp1.put("nom",j.getPseudo());
      tmp1.put("score",j.getScore());
      json.add(tmp1);
      FileWriter fw = new FileWriter (tmp);
      fw.write(json.toJSONString());
      fw.close();
    }catch(FileNotFoundException e){
      try{
        JSONArray json = new JSONArray();
        JSONObject tmp1 = new JSONObject();
        tmp1.put("nom",j.getPseudo());
        tmp1.put("score",j.getScore());
        json.add(tmp1);
        FileWriter fw = new FileWriter (tmp);
        fw.write(json.toJSONString());
        fw.close();
      }catch(Exception ee){
        ee.printStackTrace();
      }
    }catch(Exception e1){
      e1.printStackTrace();
    }
  }
}
