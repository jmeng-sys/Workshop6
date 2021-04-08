package objects;

public class Product
{
    private int productId;
    private String prodName;

    //Constructor
    public Product(int productId, String prodName)
    {
        this.productId = productId;
        this.prodName = prodName;
    }

    //Getter and setter methods
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProdName() { return prodName; }
    public void setProdName(String prodName) { this.prodName = prodName; }

    @Override
    public String toString() { return prodName; }
}
