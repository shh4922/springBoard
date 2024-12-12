package com.hyeonho.board.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

// implements RefreshTokenService
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl{

    private final JwtTokenProvider jwtProvider;

    /**
     * refresh token을 이용하여 access token, refresh token 재발급
     *
     * @param refreshToken refresh token
     * @return RefreshTokenResponseDTO
     */
//    @Override
    public HashMap<String, String> refreshToken(final String refreshToken) {
        // refresh token 유효성 검증
//        checkRefreshToken(refreshToken);

        // refresh token id 조회
        var id = RefreshToken.getRefreshToken(refreshToken);

        // 새로운 access token 생성
        String newAccessToken = jwtProvider.generateAccessToken(id);

        // 기존에 가지고 있는 사용자의 refresh token 제거
        RefreshToken.removeUserRefreshToken(id);

        // 새로운 refresh token 생성 후 저장
        String newRefreshToken = jwtProvider.generateRefreshToken(id);
        RefreshToken.putRefreshToken(newRefreshToken, id);

        HashMap<String, String> result = new HashMap<>();
        result.put("accessToken", newAccessToken);
        result.put("refreshToken", newRefreshToken);

        return result;
    }

//    /**
//     * refresh token 검증
//     *
//     * @param refreshToken refresh token
//     */
//    private void checkRefreshToken(final String refreshToken) {
//        if(Boolean.FALSE.equals(jwtProvider.validateToken(refreshToken)))
//            throw new RefreshTokenException(RefreshTokenExceptionResult.INVALID);
//    }

}