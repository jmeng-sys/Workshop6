package objects;

/* =====================================================================================================================
PACKAGESPRODUCTSSUPPLIERS CODED PRIMARILY BY HOLLY
===================================================================================================================== */
public class PackagesProductsSuppliers {

  private int packageId;
  private int productSupplierId;
  private String productName;
  private String supplierName;


  public long getPackageId() {
    return packageId;
  }
  public void setPackageId(int packageId) {
    this.packageId = packageId;
  }

  public long getProductSupplierId() {
    return productSupplierId;
  }
  public void setProductSupplierId(int productSupplierId) {
    this.productSupplierId = productSupplierId;
  }

  public String getProductName() { return productName; }
  public void setProductName(String productName) { this.productName = productName; }

  public String getSupplierName() { return supplierName; }
  public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

  public PackagesProductsSuppliers(int packageId, int productSupplierId) {
    this.packageId = packageId;
    this.productSupplierId = productSupplierId;
  }

  public PackagesProductsSuppliers(int packageId, int productSupplierId, String productName, String supplierName) {
    this.packageId = packageId;
    this.productSupplierId = productSupplierId;
    this.productName = productName;
    this.supplierName = supplierName;
  }

  @Override
  public String toString() {
    return  packageId + " " + productName + " by " + supplierName;
  }

}
