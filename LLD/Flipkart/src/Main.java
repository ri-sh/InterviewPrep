import com.flipkart.models.Item;
import com.flipkart.models.User;
import com.flipkart.service.CartService;
import com.flipkart.service.InventoryService;
import com.flipkart.service.UserService;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

//    private static UserService userService = new UserService();
//    private static  InventoryService inventory = new InventoryService();
//    private static CartService cartService = new CartService(userService, inventoryService);




    public static void main(String[] args) {


        UserService userService = new UserService();
        InventoryService inventoryService = new InventoryService();
        CartService cartService = new CartService(userService, inventoryService);

//        User user1 = userService.addUser("user1", "user1@gmail.com", 10000);
//        User user2 = userService.addUser("user2", "user2@gmail.com", 10000);
//        inventoryService.addItem("Milk", "Nestle", 8, 10);
//        inventoryService.addInventory("Milk", "Nestle", 5);
//        List<Item> items = inventoryService.searchItems(null, Arrays.asList("Milk"), null, null);
//        System.out.println(items);
//        cartService.addToCart("Milk", "Nestle", "user1", 5);


        inventoryService.addItem("Milk", "Amul", 100, 120);
        inventoryService.addItem("Milk", "Nestle", 60, 70);
        inventoryService.addItem("Curd", "Amul", 50, 60);
        inventoryService.addItem("Curd", "Nestle", 90, 100);

        inventoryService.addInventory("Milk", "Amul", 10);
        inventoryService.addInventory("Milk", "Nestle", 5);
        inventoryService.addInventory("Curd", "Nestle", 10);
        inventoryService.addInventory("Milk", "Nestle", 30);
        inventoryService.addInventory("Curd", "Amul", 5);




        User johnny = userService.addUser("johnny", "user1@gmail.com", 1000);
        User naruto = userService.addUser("naruto", "user2@gmail.com", 1000);
        User goku = userService.addUser("goku", "user2@gmail.com", 1000);

        cartService.addToCart("Milk", "Nestle", "johnny", 5);
        cartService.addToCart("Curd", "Nestle", "johnny", 1);


        System.out.println("Search " + inventoryService.searchItems(null, Arrays.asList("Milk"), null, null));
        //cartService.addToCart("Curd1", "Nestle", "johnny", 1);

        System.out.println("Cart for user" + cartService.getAllItemsForAUser("johnny"));








    }
}