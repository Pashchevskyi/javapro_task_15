package ua.ithillel.lms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task15.Product;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task15.ProductManager;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task15.exception.ProductNotFoundException;

public class Main {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Product book1 = new Product("123-456-789", "Book", 51);
    Product book2 = new Product("123-456-790", "Book", 75);
    Product magazine3 = new Product("234-567-890", "Magazine", 251);
    Product book4 = new Product("123-456-791", "Book", 300);
    Product book5 = new Product("123-456-792", "Book", 74.9);
    products.add(book1);
    products.add(book2);
    products.add(magazine3);
    products.add(book4);
    products.add(book5);
    ProductManager pm = new ProductManager(products);

    System.out.println("Expensive books:"); // Task 1.2
    List<Product> filteredProducts = pm.getExpansiveBooks();
    System.out.println(ProductManager.output(filteredProducts));

    System.out.println("Discounted books"); // Task 2.1
    book1.setDiscountable(true);
    book4.setDiscountable(true);
    List<Product> discountedProducts = pm.applyDiscountToBooks();
    System.out.println(ProductManager.output(discountedProducts));

    System.out.println("The cheapest book:");// Task 3.2
    try {
      Product theCheapestBook = pm.getTheCheapestBook();
      System.out.println(theCheapestBook);
    } catch (ProductNotFoundException e) { // Task 3.3
      System.out.println(e.getMessage());
    }

    System.out.println("The latest added products:");// Task 4.2
    book1.setAdditionDate(LocalDateTime.of(2021, 9, 24, 15, 0));
    book2.setAdditionDate(LocalDateTime.of(2022, 10, 24, 15, 0));
    magazine3.setAdditionDate(LocalDateTime.of(2022, 9, 8, 1, 0));
    List<Product> theLatestAddedProducts = pm.getTheLatestAddedProducts();
    System.out.println(ProductManager.output(theLatestAddedProducts));

    System.out.println("Value of current year cheap books: ");// Task 5.2
    double sum = pm.calcCurrentYearCheapBooksTotalValue();
    System.out.println(sum);

    System.out.println("Products, grouped by category"); // Task 6.2
    Map<String, List<Product>> groupedProducts = pm.groupByType();
    System.out.println(groupedProducts);
  }
}