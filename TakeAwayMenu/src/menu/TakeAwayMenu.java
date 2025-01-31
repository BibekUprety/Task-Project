package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TakeAwayMenu{

    public static void main(String[] args) {
    	
        List<Category> menu = createMenu();

        Scanner scanner = new Scanner(System.in);
        List<String> orderItems = new ArrayList<>();
        List<Integer> orderQuantities = new ArrayList<>();
        List<Double> orderSubtotals = new ArrayList<>();
        boolean isOrdering = true;

        while (isOrdering) {
            System.out.println("Choose an option:");
            System.out.println("1 - Show Menu");
            System.out.println("2 - Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            if (option == 2) {
                break;
            } else if (option == 1) {
                System.out.println("Categories:");
                
                for (int i = 0; i < menu.size(); i++) {
                	
                    System.out.println((i + 1) + ". " + menu.get(i).getName());
                }
                System.out.print("Choose a category: ");
                int categoryIndex = scanner.nextInt() - 1;
                scanner.nextLine(); 

                if (categoryIndex >= 0 && categoryIndex < menu.size()) {
                    Category chosenCategory = menu.get(categoryIndex);
                    System.out.println();
                    System.out.println("Dishes in " + chosenCategory.getName() + ":");
                    List<Dish> dishes = chosenCategory.getDishes();
              
                    
                    printDishMenu(dishes);
                    
                    
                    
                    System.out.println();
                    
                    
                    System.out.print("Choose a dish: ");
                    
                    int dishIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (dishIndex >= 0 && dishIndex < dishes.size()) {
                        Dish chosenDish = dishes.get(dishIndex);
                        int quantity = 0;
                        while (true) {
                            System.out.print("Enter quantity: ");
                            try {
                                quantity = scanner.nextInt();
                                if (quantity > 0) {
                                    break;
                                } else {
                                    System.out.println("Please enter a positive number.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); 
                            }
                        }

                        double subtotal = chosenDish.getPrice() * quantity;
                        orderItems.add(chosenDish.getName());
                        orderQuantities.add(quantity);
                        orderSubtotals.add(subtotal);

                        System.out.print("Do you want to continue ordering? (yes/no): ");
                        scanner.nextLine();
                        String continueOrdering = scanner.nextLine();
                        if (continueOrdering.equalsIgnoreCase("no")) {
                            isOrdering = false;
                        }
                    }
                }
            } else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }

        printInvoice(orderItems, orderQuantities, orderSubtotals);
        scanner.close();
    }

    private static List<Category> createMenu() {
        List<Category> menu = new ArrayList<>();

        Category appetisers = new Category("Appetisers");
        Category mainDishes = new Category("Main Dishes");
        Category friedRice = new Category("Fried Rice");

        appetisers.addDish(new Dish("Spring Rolls", "Crispy rolls filled with vegetables and sweet chili sauce.", 6.00));
        appetisers.addDish(new Dish("Thai Dumplings", "Steamed dumplings with chicken and vegetable filling.", 7.50));

        mainDishes.addDish(new Dish("Pad Thai", "Stir-fried rice noodles with shrimp, tofu, peanuts, and bean sprouts.", 12.00));
        mainDishes.addDish(new Dish("Basil Chicken", "Stir-fried chicken with Thai basil, chili, and vegetables, served with jasmine rice.", 12.00));

        friedRice.addDish(new Dish("Thai Fried Rice", "Fried rice with chicken, shrimp, or vegetables, seasoned with Thai spices.", 11.00));
        friedRice.addDish(new Dish("Pineapple Fried Rice", "Fried rice with pineapple, cashews, and choice of meat or tofu.", 12.50));


        menu.add(appetisers);
        menu.add(mainDishes);
        menu.add(friedRice);

        return menu;
    }

    private static void printInvoice(List<String> items, List<Integer> quantities, List<Double> subtotals) {
        System.out.println("\nINVOICE");
        System.out.printf("%-20s %10s %10s%n", "Dish", "Quantity", "Subtotal"); 

        double total = 0;

        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%-20s %10d %10s%n", items.get(i), quantities.get(i), String.format("$ %.2f", subtotals.get(i)));
            total += subtotals.get(i);
        }

        System.out.printf("%-20s %10s %10s%n", "Total:", "", String.format("$ %.2f", total));
    }
    
    
    
    private static void printDishMenu(List<Dish> dishes) {
        System.out.printf("%-4s %-25s %-50s %10s%n", "No.", "Name", "Description", "Price");

                for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            String[] descriptionLines = wrapText(dish.getDescription(), 50);
            System.out.printf("%-4d %-25s %-50s %10s%n", (i + 1), dish.getName(), descriptionLines[0], "$ " + String.format("%.2f", dish.getPrice()));
            for (int j = 1; j < descriptionLines.length; j++) {
                System.out.printf("%-4s %-25s %-50s %10s%n", "", "", descriptionLines[j], "");
                System.out.println();
            }
        }
    }

    private static String[] wrapText(String text, int lineLength) {
        if (text.length() <= lineLength) {
            return new String[]{text};
        }
        List<String> lines = new ArrayList<>();
        int start = 0;
        while (start < text.length()) {
            int end = Math.min(text.length(), start + lineLength);
            String line = text.substring(start, end);
            int lastSpace = line.lastIndexOf(' ');
            if (lastSpace > -1 && end < text.length()) {
                end = start + lastSpace;
                line = text.substring(start, end);
            }
            lines.add(line.trim());
            start = end;
        }
        return lines.toArray(new String[0]);
    }



}
