package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.PasswordUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList;
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    private void loadUsers() throws IOException {
        File file = new File(USERS_PATH);
        if (!file.exists()) {
            userList = new ArrayList<>();
        } else {
            userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
        }
    }

    public boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean signIn(User user) {
        return userList.stream().anyMatch(u ->
                u.getName().equals(user.getName()) && PasswordUtil.verifyPassword(user.getPassword(), u.getHashPassword())
        );
    }

    private void saveUserListToFile() {
        try {
            objectMapper.writeValue(new File(USERS_PATH), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchBookings(User loggedInUser) {
        userList.stream()
                .filter(u -> u.getName().equals(loggedInUser.getName()))
                .findFirst()
                .ifPresent(User::printTickets);
    }

    public boolean cancelBooking(User user, String ticketId) {
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTickedId().equals(ticketId));
        if (removed) {
            saveUserListToFile();
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return true;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return false;
        }
    }

    public List<Train> getTrain(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
