package br.gustavoakira.barber.security;

import br.gustavoakira.barber.config.ApplicationContextLoad;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.repository.BarberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class JWTTokenAuthenticationService {
    @Autowired
    BarberRepository repository;

    private static final Long EXPIRATION_TIME = 172800000L;

    private static final String SECRET = "**--*/4265a3-*-3*/e9s+w9/3e*-";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    public void authenticate(HttpServletResponse response, String username) throws IOException {
        String token = createToken(username);
        response.addHeader(HEADER_STRING,token);
        liberationCors(response);
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");
    }

    public String createToken(String username){
        String Jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        String token = TOKEN_PREFIX + " " + Jwt;
        return token;
    }

    public Authentication getAuthentication(HttpServletResponse response, HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        liberationCors(response);
        if(token != null){
            String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody().getSubject();
            if(username != null){
                Barber barber = ApplicationContextLoad.getApplicationContext().getBean(BarberRepository.class).getBarberByUsername(username).get();
                if(barber != null){
                    System.out.println(barber.getRoles());
                    return new UsernamePasswordAuthenticationToken(barber.getUsername(),barber.getPassword(), barber.getRoles());
                }
            }
        }
        return null;
    }

    private void liberationCors(HttpServletResponse response) {
        if(response.getHeader("Access-Control-Allow-Origin") == null){
            response.addHeader("Access-Control-Allow-Origin","*");
        }

        if(response.getHeader("Access-Control-Allow-Headers") == null){
            response.addHeader("Access-Control-Allow-Headers","*");
        }

        if(response.getHeader("Access-Control-Request-Headers") == null){
            response.addHeader("Access-Control-Request-Headers","*");
        }

        if(response.getHeader("Access-Control-Allow-Methods") == null){
            response.addHeader("Access-Control-Allow-Methods","*");
        }
    }
}
