package com.project.shopapp.responses.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.User;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("user")
    private User user;
}
