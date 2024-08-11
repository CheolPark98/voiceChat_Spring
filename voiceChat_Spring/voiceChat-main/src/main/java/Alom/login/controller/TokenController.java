package Alom.login.controller;

import Alom.login.domain.exception.ApiResponse;
import Alom.login.response.TokenResponse;
import Alom.login.service.TokenService;
import Alom.login.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService authService;

    @GetMapping("/reissue/access-token")
    public ResponseEntity<ApiResponse<Object>> reissueAccessToken(
            @RequestHeader("Authorization") String authorizationHeader){
        TokenResponse accessToken = authService.reissueAccessToken(authorizationHeader);
        return ApiResponse.onSuccess(SuccessStatus._CREATED_ACCESS_TOKEN,accessToken);
    }

}
