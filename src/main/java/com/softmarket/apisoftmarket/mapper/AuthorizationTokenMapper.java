package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class AuthorizationTokenMapper {
  public AuthorizationToken createTokenAuth(Authentication authentication, FactusTokenResponse factusTokenResponse) {
    ZonedDateTime nowInColombia = ZonedDateTime.now(ZoneId.of("America/Bogota"));
    ZonedDateTime expirationZoned = nowInColombia.plusSeconds(factusTokenResponse.getExpires_in());
    LocalDateTime timeColombia = expirationZoned.toLocalDateTime();
    return new AuthorizationToken(
            factusTokenResponse.getAccess_token(),
            factusTokenResponse.getExpires_in(),
            factusTokenResponse.getRefresh_token(),
            factusTokenResponse.getToken_type(),
            timeColombia,
            authentication
    );
  }
}
