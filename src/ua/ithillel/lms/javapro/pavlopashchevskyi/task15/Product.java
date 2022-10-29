package ua.ithillel.lms.javapro.pavlopashchevskyi.task15;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

  private final String ID;

  private final String type;
  private double price;

  private boolean isDiscountable;

  private LocalDateTime additionDate;

  /**
   * Creates the Product object by its ID, type ("Book", "Magazine" etc.) and initial price)
   *
   * @param ID    identifier of product
   * @param type  type of product (book, magazine etc.)
   * @param price initial price of product
   */
  public Product(String ID, String type, double price) {
    this.ID = ID;
    this.type = type;
    this.price = price;
    this.isDiscountable = false;
    this.additionDate = LocalDateTime.now();
  }

  public String getType() {
    return type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Checks if Product is discountable
   *
   * @return boolean
   */
  public boolean isDiscountable() {
    return isDiscountable;
  }

  public void setDiscountable(boolean discountable) {
    isDiscountable = discountable;
  }

  public LocalDateTime getAdditionDate() {
    return additionDate;
  }

  public void setAdditionDate(LocalDateTime additionDate) {
    this.additionDate = additionDate;
  }

  public String toString() {
    String str = "ID: " + this.ID + "\n";
    str += "Type: " + this.type + "\nPrice: " + this.price + "\nIs discountable?: ";
    str += (this.isDiscountable) ? "Yes" : "No";
    str += "\nAddition date: " + this.additionDate + "\n\n";

    return str;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return ID.equals(product.ID) && type.equals(product.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID, type);
  }
}
