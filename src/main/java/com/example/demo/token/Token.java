package com.example.demo.token;

import com.example.demo.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private  Integer   id;
    private String   token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean  expired;
    private boolean revoked;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
