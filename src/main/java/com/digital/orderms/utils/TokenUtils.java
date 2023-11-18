package com.digital.orderms.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenUtils {

    public static String getAttribute(String key) {
        final Claims claims = getClaims();
        return String.valueOf(claims.get(key));
    }

    public static Claims getClaims() {
        final HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        final String token = getTokenFromHeader(request);
        final String withoutSignature = token.substring(0, token.lastIndexOf('.') + 1);
        return Jwts.parser()
                .parseClaimsJwt(withoutSignature).getBody();
    }

    private static String getTokenFromHeader(final HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }
}