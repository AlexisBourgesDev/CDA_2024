package fr.cda.bookstore.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    // Clé secrète récupéré par SecurityConfig (dans les properties)
    private String secret;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, String secret) {
        super(authenticationManager);
        this.secret = secret;
    }

    // Permet d'intercepter la requête afin de vérifier qu'elle contient bien un token valide dans ses headers
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Récupération de la valeur associé à la clé AUTHORIZATION dans le header
        String header = request.getHeaders(HttpHeaders.AUTHORIZATION).nextElement();
        // Si aucune Authorization trouvé dans le header ou si il s'agit d'un autre type de token qu'un JWT (Bearer), alors throw JwtException
        if (null == header || !header.startsWith("Bearer")) {
            throw new JwtException("Header with Authorization not found");
        }
        // Extraction des informations du JWT pour créer un UsernamePasswordAuthenticationToken (utilisé par spring)
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) throw new JwtException("Auth not valid");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    // Extraction des informations du JWT
    UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        // Récupération du token en enlevant le préfixe Bearer
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer", "").trim();
        // Récupération des claims (payload du JWT)
        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(secret.getBytes())
            .build()
            .parseClaimsJws(token);
        // Récupération du subject (SUB) dans le payload
        String user = claims.getBody().getSubject();
        // Récupération des autorités (= ROLE)
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority((String)claims.getBody().get("auth"));
        return new UsernamePasswordAuthenticationToken(user, null, List.of(authorities));
    }
}