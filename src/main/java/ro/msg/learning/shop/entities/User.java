package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "roles")
@ToString(doNotUseGetters = true, exclude = "roles")
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "USERS_JOIN_ROLES",
        joinColumns = @JoinColumn(name = "USER_ID", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false)
    )
    private List<Role> roles = new ArrayList<>();

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
