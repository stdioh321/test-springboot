package com.example.securitytest.dtos.user;


import com.example.securitytest.models.User;
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

    public UserPatchForm(User user) {
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
    }
}
