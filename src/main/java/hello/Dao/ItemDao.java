package hello.Dao;

import hello.Entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ItemDao {
    List<Item> getItems();

    Item getItemById(Long id);

    void removeItemById(Long id);

    void insertItemToDb(Item item);
}
