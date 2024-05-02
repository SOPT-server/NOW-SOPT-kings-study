package com.example.seojin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.example.seojin.domain.enums.Role;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="user_table")
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @Column
  private String picture;

  @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
  @NotNull
  private Role role;

  @Builder
  public User(String name, String email, String picture, Role role) {
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.role = role;
  }

  public User update(String name, String picture) {
    this.name = name;
    this.picture = picture;

    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }


}
