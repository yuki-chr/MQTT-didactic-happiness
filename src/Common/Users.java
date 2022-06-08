package Common;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Users{
    public Map<String, String> users = new HashMap<String, String>();
    Gson gson = new Gson();

    public String getHashPw(String user){
        return this.users.get(user);
    }

    public boolean checkUname(String u){
        return users.containsKey(u);
    }

    public void newUser(String u, String pw){
        this.users.put(u, pw);
        //System.out.println(u + " " + pw); //CHECK
    }

    public String serialize(){
        //return gson.toJson(this);
        StringBuilder sb = new StringBuilder();
        for(String u : users.keySet()){
            //System.out.println(users.get(u)); //CHECK
            sb.append(u + ":" + users.get(u) + "\n");
        }
        return sb.toString();
    }

    public Users(){
        //initialize empty
    }

    public Users(String json){
        String[] set = json.split("\n");
        for(String s : set){
            String[] subset = s.split(":");
            users.put(subset[0], subset[1]);
        }
    }
}