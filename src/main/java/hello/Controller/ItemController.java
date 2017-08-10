package hello.Controller;

import hello.Entity.Item;
import hello.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ItemController {

    private List<Item> items = new ArrayList<>();

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/itembook", method = RequestMethod.GET)
    public String showItems(Model model) {

        System.out.println("XXXXXXXXXX: " + itemService.getItems());
        if (itemService.getItems() != null) {

            //check all borrowed items
            List<Item> borrowedItems = new ArrayList<>();
            for (Item item : itemService.getItems()) {
                if(item.isBorrowed()){
                    borrowedItems.add(item);
                }
            }
            model.addAttribute("items", itemService.getItems());
            model.addAttribute("borrowedItems", borrowedItems);
        }

        return "item-form-template";
    }

    @RequestMapping(value="/itembook", method = RequestMethod.POST)
    public String addItem(@RequestParam(required=false) String name, @RequestParam(required=false) String type){
        Item item = new Item(name, type);
        System.out.println("XXXXXXXXXXX " + item.toString());
        itemService.insertItem(item);

        return "redirect:/itembook";
    }

    @RequestMapping(value="/itembook/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Long id) {
        itemService.removeItem(id);
        return "redirect:/itembook";
    }

    @RequestMapping(value="/itembook/{id}", method = RequestMethod.POST)
    public String markItemBorrowed(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        item.setBorrowed(true);
        itemService.insertItem(item);
        return "redirect:/itembook";
    }

    @RequestMapping(value="/itembook/{id}")
    public String showItem(Model model, @PathVariable Long id){
        Item item = itemService.getItemById(id);
        item.visitIncrement();
        itemService.updateItem(item);

        model.addAttribute("item", itemService.getItemById(id));

        return "item-view";
    }


}
