package hello.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class Account extends AbstractPersistable<Long> {

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    //@Column(name="permissions")
    @Column(name="role")
    private String permission;

    public Account(String username, String password, String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public Account() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
