package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.j2objc.annotations.UsedByReflection;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../localDb/users.json";
    private static final String TRAINS_PATH = "../localDb/trains.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }


    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean signIn(User user1){
        for(User existingUser: userList){
            if(existingUser.getUserId().equals(user1.getUserId())&& existingUser.getHashPassword().equals(user1.getHashPassword())){
                return true;
            }

        }
        return false;
    }


    private void saveUserListToFile() throws IOException{
        objectMapper.writeValue(new File(USERS_PATH),userList);
    }

}

