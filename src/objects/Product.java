package objects;

/*
    Product Class
    Object handler for Product
    Code by Tristen Stockley (T)
 */

public class Product
{
    //Product instance variables (T)
    private int productId;
    private String prodName;

    //Product constructor (T)
    public Product(int productId, String prodName)
    {
        this.productId = productId;
        this.prodName = prodName;
    }

    //Product getter and setter methods (T)
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProdName() { return prodName; }
    public void setProdName(String prodName) { this.prodName = prodName; }

    //Product toString method (T)
    @Override
    public String toString() { return prodName; }
}