package objects;

/*
    Supplier Class
    Object handler for Supplier
    Code by Tristen Stockley (T) AND JIN
 */

public class Supplier
{
    //Supplier instance variables (T)
    private int supplierId;
    private String supName;

    //Supplier Constructor (T)
    public Supplier(int supplierId, String supName)
    {
        this.supplierId = supplierId;
        this.supName = supName;
    }

    //Supplier getter and setters (T)
    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getSupName() { return supName; }
    public void setSupName(String supName) { this.supName = supName; }

    //Supplier toString (T)
    @Override
    public String toString() { return supName; }
}