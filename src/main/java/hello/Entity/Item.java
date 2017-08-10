package hello.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Item extends AbstractPersistable<Long> {
    private String name;
    private String type;
    private int numOfVisits;
    private boolean borrowed;

    public Item() {
    }

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
        this.numOfVisits = 0;
        this.borrowed = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void visitIncrement() {
        this.numOfVisits++;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", numOfVisits=" + numOfVisits +
                ", borrowed=" + borrowed +
                '}';
    }
}
