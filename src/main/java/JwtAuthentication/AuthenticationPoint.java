package JwtAuthentication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationPoint {
    private static final String SECRET_KEY = "Ieytsak7eq";
    private static final long EXPIRATION_TIME = 900000; // 5 minute in milliseconds
    public static final Set<String> invalidatedTokens = new HashSet<>();

    public static String generateToken(String username, String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) 
    {
        if (invalidatedTokens.contains(token)) 
        {  	
            return false; // Token is invalidated
        }
        try 
        {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true; // Token is valid
        } 
        catch (Exception e) 
        {
            return false; // Token is invalid
        }
    }

    public static Claims getClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static void invalidateToken(String token) 
    {	
        invalidatedTokens.add(token);
    }
}
