package hello.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
public class FileObject extends AbstractPersistable<Long> {

    private String name;
    private String type;
    private Long size;

    /*@ManyToOne(optional=false)
@JoinColumn*/
    @ManyToOne
    @JoinColumn
    private Account account;

    @Lob
    @Basic()
    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
