package hello.Service;

import hello.Dao.ItemDao;
import hello.Dao.ItemDaoImpl;
import hello.Entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    // create an instance of the DaoImpl
    @Autowired
    private ItemDaoImpl itemDaoImpl;

    // make methods 'insertItem', 'deleteItem', 'updateItem', 'getItemById', 'getItems'
    // that call corresponding DaoImpl -methods
    public void insertItem(Item item) {
        itemDaoImpl.insertItemToDb(item);
    }

    public Item getItemById(Long id) {
        return itemDaoImpl.getItemById(id);
    }

    public List<Item> getItems() {
        return itemDaoImpl.getItems();
    }

    public void removeItem(Long id) {
        itemDaoImpl.removeItemById(id);
    }

    public void updateItem(Item item) {
        itemDaoImpl.updateItem(item);
    }
}
