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
      JSONArray json2 = sortncut(json);
      Iterator i = json2.iterator();
      players = new ArrayList<Joueur>();
      while (i.hasNext()) {
        JSONObject slide = (JSONObject) i.next();
        String nom = (String)slide.get("nom");
        String score = String.valueOf(slide.get("score"));
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

  public static JSONArray sortncut(JSONArray jsonArr){ //on trie les scores par ordre croissant puis on s'arrête à la neuvième valeur (le reste n'est pas affichée masi reste présent dans le fichier JSON)
     JSONArray sortedJsonArray = new JSONArray();
     //On transforme notre JSONArray en List/ArrayList
     List<JSONObject> jsonValues = new ArrayList<JSONObject>();
     for (int i = 0; i < jsonArr.size(); i++) {
         jsonValues.add((JSONObject) jsonArr.get(i));
     }
     Collections.sort( jsonValues, new Comparator<JSONObject>() { //On trie le jsonValues
         public int compare(JSONObject a, JSONObject b) {
             String valA = new String();
             String valB = new String();

             try {
                 valA = String.valueOf(a.get("score"));
                 valB = String.valueOf(b.get("score"));
             }catch(Exception e){
               e.printStackTrace();
             }
             return Integer.parseInt(valB)-Integer.parseInt(valA);
         }
     });
     for (int i = 0; i < jsonValues.size(); i++) {
         sortedJsonArray.add(jsonValues.get(i));
     }
     if(sortedJsonArray.size()>9){
       JSONArray sortedJsonArraybis = new JSONArray();
       for(int i=0;i<10;i++){
         sortedJsonArraybis.add(sortedJsonArray.get(i));
       }
     }
     return sortedJsonArray;
  }
  public static void save(Joueur j){
    File tmp = new File("src/main/java/flipper/leaderboard.json");
    try(InputStream in = new FileInputStream(tmp)){ //le fichier existe déjà
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
    }catch(FileNotFoundException e){ //la toute première partie donc pas de fichier de highscores
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
