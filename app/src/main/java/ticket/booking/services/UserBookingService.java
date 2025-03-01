package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.j2objc.annotations.UsedByReflection;
import ticket.booking.entities.User;
import ticket.booking.utils.PasswordUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "/home/mritunjay/Desktop/IRCTC/app/src/main/java/ticket/booking/localDb/users.json";
    private static final String TRAINS_PATH = "../localDb/trains.json";

    public UserBookingService() throws IOException {
        File usersFile = new File(USERS_PATH);
        if (usersFile.exists()) {
            userList = objectMapper.readValue(usersFile, new TypeReference<List<User>>() {});
        } else {
            userList = new ArrayList<>();
        }
    }


    public Boolean signUp(String name, String userId, String rawPassword) {
        try {
            String hashedPassword = PasswordUtil.hashPassword(rawPassword);
            User user = new User(name,userId,hashedPassword);

            //Check if user already exist
            for(User existingUser: userList){

                if(existingUser.getUserId().equals(userId)){
                    return false;
                }
            }


            userList.add(user);
            saveUserListToFile();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean signIn(String userId, String rawPassword) {
        for (User existingUser : userList) {
            if (existingUser.getUserId().equals(userId) && PasswordUtil.verifyPassword(rawPassword,existingUser.getHashPassword())){
                return true;
            }

        }
        return false;
    }


    void saveUserListToFile() {
        try {
            File file = new File("../localDb/users.json");
            file.getParentFile().mkdirs(); // Ensure directory exists
            if (!file.exists()) file.createNewFile(); // Ensure file exists

            // Your existing file writing logic...
        } catch (IOException e) {
            e.printStackTrace();
        }


    }}

