package hello.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.File;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileObject> fileObjectList;

    public Account(String username, String password, String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.fileObjectList = null;
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

    public List<FileObject> getFileObjectList() {
        return fileObjectList;
    }

    public void setFileObjectList(List<FileObject> fileObjectList) {
        this.fileObjectList = fileObjectList;
    }

    public void addFileObject(FileObject fileObject) {
        this.fileObjectList.add(fileObject);
    }

    public void removeFileObject(FileObject fileObject) {
        this.fileObjectList.remove(fileObject);
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
