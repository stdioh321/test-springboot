package com.example.securitytest.models;

import com.example.securitytest.common.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_users")
public class User {
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        handleSetTimesOnCreateOrUpdate(true);
        handleSetsOnCreateOrUpdate();
    }

    @PreUpdate
    public void onUpdate() {
        handleSetTimesOnCreateOrUpdate();
        handleSetsOnCreateOrUpdate();
    }


    private void handleSetsOnCreateOrUpdate() {
        if (this.getEmail() != null) this.setEmail(this.getEmail().toLowerCase().trim());
        if (this.getUsername() != null) this.setUsername(this.getUsername().toLowerCase().trim());
    }

    private void handleSetTimesOnCreateOrUpdate() {
        this.handleSetTimesOnCreateOrUpdate(false);
    }

    private void handleSetTimesOnCreateOrUpdate(boolean createdAt) {
        Date d = Utils.getCurrentDate();
        this.setUpdatedAt(d);
        if (createdAt == true) this.setCreatedAt(d);
    }

}
