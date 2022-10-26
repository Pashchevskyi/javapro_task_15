package ua.ithillel.lms.javapro.pavlopashchevskyi.task15.exception;

public class ProductNotFoundException extends Exception {

  public ProductNotFoundException(String type) {
    super("Product [category: \"" + type + "\"] not found");
  }
}
