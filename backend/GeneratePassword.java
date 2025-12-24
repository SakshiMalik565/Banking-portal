import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "Admin@123";
        String hash = encoder.encode(password);
        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + hash);
        System.out.println("Verification: " + encoder.matches(password, hash));
        
        // Test the existing hash
        String existingHash = "$2a$10$vXJyKZlEfNdPL5bSQXz6s.GtH7VKqLXPXPvzrPJxXpJFZKfCZSHHe";
        System.out.println("\nTesting existing hash:");
        System.out.println("Matches 'Admin@123': " + encoder.matches("Admin@123", existingHash));
        System.out.println("Matches 'admin123': " + encoder.matches("admin123", existingHash));
    }
}
