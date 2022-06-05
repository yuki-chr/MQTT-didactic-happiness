package Common;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Users{
    Map<String, String> users = new HashMap<String, String>();
    Gson gson = new Gson();

    public String getHashPw(String user){
        return this.users.get(user);
    }

    public boolean checkUname(String u){
        return users.containsKey(u);
    }

    public void newUser(String u, String pw){
        this.users.put(u, pw);
    }

    public String serialize(){
        return gson.toJson(this);
    }

    public Users(){
        //initialize empty
    }

    public Users(String json){
        Users temp = new Users();
        temp = gson.fromJson(json, Users.class);
        this.users = temp.users;
    }
}