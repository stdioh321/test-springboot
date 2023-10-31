package com.example.securitytest.dtos.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPatchForm {
    private String name;
    private String username;
    private String email;
}
