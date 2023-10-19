package com.pluralsight;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SearchInventoryMap {
    public static void main(String[] args) throws IOException {
        HashMap<String, InventoryItem> inventory = getInventory();
        BufferedReader readFile = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
        String input;
        int product_id;
        String product_name;
        double product_price;
        while((input = readFile.readLine()) != null){
            String[] temp = input.split("\\|");
            product_name = temp[1];
            product_price = Double.parseDouble(temp[2]);
            product_id = Integer.parseInt(temp[0]);
            inventory.put(product_name, new InventoryItem(product_id, product_name, product_price));
        }
        readFile.close();
        Scanner scanner = new Scanner(System.in);
        System.out.println("We carry the following inventory: ");
        for (InventoryItem prod : inventory.values()) {
            System.out.printf("id: %d %s - Price: $%.2f\n",
                    prod.getId(), prod.getName(),prod.getPrice());
        }
        while(true) {
            System.out.print("What item are you interested in? ");
            int id = Integer.parseInt(scanner.nextLine());
            InventoryItem matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
                return;
            }
            System.out.printf("We carry %s and the price is $%.2f\n",
                    matchedProduct.getName(), matchedProduct.getPrice());
            System.out.println();
            scanner.nextLine();
            System.out.println("Do you want to search again?(Yes or No): ");
            input = scanner.nextLine();

            while (input.equalsIgnoreCase("Yes"));
                break;
        }

    }
    public static HashMap<String, InventoryItem> getInventory() {
        HashMap<String, InventoryItem> inventory = new HashMap<>();
        return inventory;
    }
}