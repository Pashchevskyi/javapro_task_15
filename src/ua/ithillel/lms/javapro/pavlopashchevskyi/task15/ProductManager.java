package ua.ithillel.lms.javapro.pavlopashchevskyi.task15;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task15.exception.ProductNotFoundException;

public class ProductManager {

  private final List<Product> products;

  public ProductManager(List<Product> products) {
    this.products = products;
  }

  public List<Product> getExpansiveBooks() {
    return products.stream()
        .filter(p -> p.getPrice() > 250 && "Book".equalsIgnoreCase(p.getType()))
        .collect(Collectors.toList());
  }

  public List<Product> applyDiscountToBooks() {
    return products.stream()
        .filter(p -> p.isDiscountable() && "Book".equalsIgnoreCase(p.getType()))
        .peek(p -> p.setPrice(p.getPrice() - p.getPrice() * 0.1))
        .collect(Collectors.toList());
  }

  public Product getTheCheapestBook() throws ProductNotFoundException {
    if (products.stream().noneMatch(p -> "Book".equalsIgnoreCase(p.getType()))) {
      throw new ProductNotFoundException("Book");
    }
    return products.stream()
        .filter(p -> "Book".equalsIgnoreCase(p.getType()))
        .min(Comparator.comparing(Product::getPrice))
        .orElseGet(() -> new Product("000-000-000", "", 0.00));
  }

  public List<Product> getTheLatestAddedProducts() {
    return products.stream()
        .sorted(Comparator.comparing(Product::getAdditionDate).reversed())
        .limit(3)
        .collect(Collectors.toList());
  }

  public double calcCurrentYearCheapBooksTotalValue() {
    return products.stream()
        .filter(p -> "Book".equalsIgnoreCase(p.getType()) && p.getPrice() <= 75)
        .filter(p -> p.getAdditionDate().getYear() == LocalDateTime.now().getYear())
        .mapToDouble(Product::getPrice).sum();
  }

  public Map<String, List<Product>> groupByType() {
    return products.stream()
        .collect(Collectors.groupingBy(Product::getType));
  }

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
