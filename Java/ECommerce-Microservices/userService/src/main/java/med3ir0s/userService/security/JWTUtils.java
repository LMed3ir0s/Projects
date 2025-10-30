package med3ir0s.userService.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

@Component
public class JWTUtils {

    private static final String SECRET = "minha_chave_super_secreta"; // segredo para assinar o token
    private static final long EXPIRATION_TIME = Duration.ofMinutes(10).toMillis();

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JWTUtils(){
        this.algorithm = Algorithm.HMAC256(SECRET);
        this.verifier = JWT.require(algorithm).build();
    }

    // => Gera token JWT a partir do username e roles
    public String generateToken(String username, Set<String> roles){
        return JWT.create()
                .withSubject(username) // assunto do token = username
                .withArrayClaim("roles", roles.toArray(new String[0]))  // adiciona roles como claim
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);  // assina com o algoritmo HMAC
    }

    // => Valida se o token é válido
    public boolean isTokenValid(String token){
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false; // token inválido ou expirado
        }
    }

    // => Extrai username do token
    public String extractUsername(String token){
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    // => Recupera roles do token
    public Set<String> getRolesFromToken(String token){
        DecodedJWT decodedJWT = verifier.verify(token);
        String[] rolesArray = decodedJWT.getClaim("roles").asArray(String.class);
        return Set.of(rolesArray);
    }
}
