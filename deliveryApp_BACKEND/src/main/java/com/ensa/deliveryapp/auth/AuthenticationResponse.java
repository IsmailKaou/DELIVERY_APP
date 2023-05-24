package com.ensa.deliveryapp.auth;

import com.ensa.deliveryapp.model.Role;
import com.ensa.deliveryapp.model.User;
import com.ensa.deliveryapp.token.Token;
import com.ensa.deliveryapp.token.TokenRepository;
import com.ensa.deliveryapp.token.TokenType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String token;
    private Role role;
    private final TokenRepository tokenRepository;

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
