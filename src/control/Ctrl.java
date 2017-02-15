package control;

import java.util.ArrayList;
import java.util.HashMap;

import model.IDataStorage;
import model.JSONStorage;
import model.UserDAO;
import model.UserDTO;

public class Ctrl {

    private UserDAO userDAO;

    public Ctrl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDTO getUser(HashMap<String, Object> hashMap){
        for (int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getUserID() == Integer.parseInt(hashMap.get("ID").toString())){
                return this.users.get(i);
            }
        }
        return null;
    }

    public ArrayList<UserDTO> getUserList(){
        return this.users;
    }

    public String createUser(HashMap<String, Object> hashMap){
        try {
            UserDTO user = new UserDTO(hashMap);
            this.users.add(user);
            this.save();
            return user.getPassword();
        }catch (UserDTO.DTOException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(HashMap<String, Object> hashMap){
        users.remove(getUser(hashMap));
        try {
            users.add(new UserDTO(hashMap));
            return true;
        }catch (UserDTO.DTOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(HashMap<String, Object> hashMap){
        for (int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getUserID() == Integer.parseInt(hashMap.get("ID").toString())){
                this.users.remove(i);
                this.save();
                return true;
            }
        }
        return false;
    }

    public boolean editUser(HashMap<String, Object> hashMap){
        UserDTO user = this.getUser(hashMap);
        if (user == null){
            return false;
        }else{
            if (hashMap.containsKey("cpr")){
                user.setCpr(hashMap.get("cpr").toString());
            }
            if (hashMap.containsKey("ini")){
                user.setIni(hashMap.get("ini").toString());
            }
            if (hashMap.containsKey("password")){
                user.setPassword(hashMap.get("password").toString());
            }
            if(hashMap.containsKey("ID")){
                user.setUserID(Integer.parseInt(hashMap.get("ID").toString()));
            }
            return true;
        }
    }

    public boolean exists(HashMap<String, Object> hashMap){
        for (UserDTO user: users) {
            if(user.getUserID() == Integer.parseInt(hashMap.get("ID").toString())){
                return true;
            }
        }
        return false;
    }

    private void save(){
        dataPersistence.write(this.users);
    }
}
