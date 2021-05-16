package objects;

/* =====================================================================================================================
PRODUCT SUPPLIER CODED PRIMARILY BY TRISTEN
===================================================================================================================== */
public class ProductsSuppliers {

    private long productSupplierId;
    private long productId;
    private long supplierId;
    private String productName;
    private String supplierName;


    public long getProductSupplierId() { return productSupplierId; }
    public void setProductSupplierId(long productSupplierId) { this.productSupplierId = productSupplierId; }


    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }


    public long getSupplierId() { return supplierId; }
    public void setSupplierId(long supplierId) { this.supplierId = supplierId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public ProductsSuppliers(long productSupplierId, String productName, String supplierName) {
        this.productSupplierId = productSupplierId;
        this.productName = productName;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return productName + " by " + supplierName;
    }
}