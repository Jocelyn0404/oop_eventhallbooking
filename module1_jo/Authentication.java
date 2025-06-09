package module1_jo;

import java.util.UUID;
import java.time.LocalDateTime;

public class Authentication {
    private String token;
    private LocalDateTime expiry;

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
        this.expiry = LocalDateTime.now().plusMinutes(30);
        System.out.println("Token generated: " + token + " (valid until " + expiry + ")");
    }

    public boolean verifyLogin(String email, String password, User user) {
        if (user.login(email, password)) {
            generateToken();
            return true;
        }
        return false;
    }

    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        System.out.println("Password reset successful for user: " + user.getName());
    }

    public boolean isTokenValid() {
        return LocalDateTime.now().isBefore(expiry);
    }

    public String getToken() {
        return token;
    }
}
