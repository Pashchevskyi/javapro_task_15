package ua.ithillel.lms.javapro.pavlopashchevskyi.task15;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task15.exception.ProductNotFoundException;

public class ProductManager {

  private final List<Product> products;

  /**
   * Creates ProductManager instance by list of Product objects
   *
   * @param products list of Product instances
   */
  public ProductManager(List<Product> products) {
    this.products = products;
  }

  /**
   * Gets Product instances, which type is "book" and price is greater than 250
   *
   * @param minPrice books, which costs greater than this price, is considered expensive
   * @return List<Product>
   */
  public List<Product> getExpansiveBooks(double minPrice) {
    return products.stream()
        .filter(p -> p.getPrice() > minPrice && "Book".equalsIgnoreCase(p.getType()))
        .collect(Collectors.toList());
  }

  /**
   * Gets Product instances, which type is "book" and they are discountable. Makes discount and
   * returns list after that.
   *
   * @return List<Product>
   */
  public List<Product> applyDiscountToBooks() {
    return products.stream()
        .filter(p -> p.isDiscountable() && "Book".equalsIgnoreCase(p.getType()))
        .peek(p -> p.setPrice(p.getPrice() - p.getPrice() * p.getDiscount()))
        .collect(Collectors.toList());
  }

  /**
   * Gets Product instances, which type is "book" and price is minimal
   *
   * @return Product - an instance of the cheapest product.
   * @throws ProductNotFoundException when list of Product instances is empty.
   */
  public Product getTheCheapestBook() throws ProductNotFoundException {
    return products.stream()
        .filter(p -> "Book".equalsIgnoreCase(p.getType()))
        .min(Comparator.comparing(Product::getPrice))
        .orElseThrow(() -> new ProductNotFoundException("Book"));
  }

  /**
   * Gets three the latest added Product instances
   *
   * @return List<Product>
   */
  public List<Product> getTheLatestAddedProducts() {
    return products.stream()
        .sorted(Comparator.comparing(Product::getAdditionDate).reversed())
        .limit(3)
        .collect(Collectors.toList());
  }

  /**
   * Calculates total price of Product instances, which type is "book" and price is non-greater than
   * 75.
   *
   * @return double
   */
  public double calcCurrentYearCheapBooksTotalValue() {
    return products.stream()
        .filter(p -> "Book".equalsIgnoreCase(p.getType()) && p.getPrice() <= 75)
        .filter(p -> p.getAdditionDate().getYear() == LocalDateTime.now().getYear())
        .mapToDouble(Product::getPrice).sum();
  }

  /**
   * Groups Product instances by type. Returns map, which key is type and value is list of Product
   * instances.
   *
   * @return Map<String, List < Product>>
   */
  public Map<String, List<Product>> groupByType() {
    return products.stream()
        .collect(Collectors.groupingBy(Product::getType));
  }

  /**
   * Static method for output list of Product instances as String.
   *
   * @param products list of Product instances
   * @return String
   */
  public static String output(List<Product> products) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < products.size(); i++) {
      sb.append("Product#");
      sb.append(i + 1);
      sb.append(":\n");
      sb.append(products.get(i));
    }
    return sb.toString();
  }
}
