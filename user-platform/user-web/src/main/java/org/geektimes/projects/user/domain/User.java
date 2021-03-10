package org.geektimes.projects.user.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 用户领域对象
 *
 * @since 1.0
 */
@Table(name = "users")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotNull(message = "id不能为空")
//    @Min(message = "id为必须大于 {value} 的整数", value = 0)
    private Long id;

    @Column
    private String name;

    @Column
//    @Max(32)
//    @Min(6)
    @Size(min = 6, max = 32, message = "密码需 {min} - {max} 位 ")
    private String password;

    @Column
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;

    @Column
    @Pattern(regexp = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\\\d{1}-?\\\\d{8}$)|(^0[3-9] {1}\\\\d{2}-?\\\\d{7,8}$)|(^0[1,2]{1}\\\\d{1}-?\\\\d{8}-(\\\\d{1,4})$)|(^0[3-9]{1}\\\\d{2}-? \\\\d{7,8}-(\\\\d{1,4})$))", message = "采用中国大陆11位手机号方式")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
