package com.hyeonho.board.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {
    protected static final Map<String, Long> refreshTokens = new HashMap<>();

    /**
     * refresh token get
     *
     * @param refreshToken refresh token
     * @return id
     */
    public static Long getRefreshToken(final String refreshToken) {
        return Optional.ofNullable(refreshTokens.get(refreshToken))
                .orElseThrow(() -> new RuntimeException("getRefreshToken error"));
    }

    /**
     * refresh token put
     *
     * @param refreshToken refresh token
     * @param id id
     */
    public static void putRefreshToken(final String refreshToken, Long id) {
        refreshTokens.put(refreshToken, id);
    }

    /**
     * refresh token remove
     *
     * @param refreshToken refresh token
     */
    private static void removeRefreshToken(final String refreshToken) {
        refreshTokens.remove(refreshToken);
    }

    // user refresh token remove
    public static void removeUserRefreshToken(final long refreshToken) {
        for(Map.Entry<String, Long> entry : refreshTokens.entrySet()) {
            if(entry.getValue() == refreshToken) {
                removeRefreshToken(entry.getKey());
            }
        }
    }
}
