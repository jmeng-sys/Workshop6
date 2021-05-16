package objects;


import java.sql.Timestamp;

/* =====================================================================================================================
PACKAGES CODED PRIMARILY BY HOLLY
===================================================================================================================== */
public class Packages {

  private int packageId;
  private String pkgName;
  private java.sql.Timestamp pkgStartDate;
  private java.sql.Timestamp pkgEndDate;
  private String pkgDesc;
  private double pkgBasePrice;
  private double pkgAgencyCommission;
  private String prodName;
  private String suppName;

  public String getProdName() { return prodName; }
  public void setProdName(String prodName) { this.prodName = prodName; }

  public String getSuppName() { return suppName; }
  public void setSuppName(String suppName) { this.suppName = suppName; }

  public Packages(int packageId, String pkgName, Timestamp pkgStartDate, Timestamp pkgEndDate, String pkgDesc, double pkgBasePrice, double pkgAgencyCommission) {
    this.packageId = packageId;
    this.pkgName = pkgName;
    this.pkgStartDate = pkgStartDate;
    this.pkgEndDate = pkgEndDate;
    this.pkgDesc = pkgDesc;
    this.pkgBasePrice = pkgBasePrice;
    this.pkgAgencyCommission = pkgAgencyCommission;
  }

  public long getPackageId() {
    return packageId;
  }
  public void setPackageId(int packageId) {
    this.packageId = packageId;
  }


  public String getPkgName() {
    return pkgName;
  }
  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
  }


  public java.sql.Timestamp getPkgStartDate() {
    return pkgStartDate;
  }
  public void setPkgStartDate(java.sql.Timestamp pkgStartDate) {
    this.pkgStartDate = pkgStartDate;
  }


  public java.sql.Timestamp getPkgEndDate() {
    return pkgEndDate;
  }
  public void setPkgEndDate(java.sql.Timestamp pkgEndDate) {
    this.pkgEndDate = pkgEndDate;
  }


  public String getPkgDesc() {
    return pkgDesc;
  }
  public void setPkgDesc(String pkgDesc) {
    this.pkgDesc = pkgDesc;
  }


  public double getPkgBasePrice() {
    return pkgBasePrice;
  }
  public void setPkgBasePrice(double pkgBasePrice) {
    this.pkgBasePrice = pkgBasePrice;
  }


  public double getPkgAgencyCommission() {
    return pkgAgencyCommission;
  }
  public void setPkgAgencyCommission(double pkgAgencyCommission) {
    this.pkgAgencyCommission = pkgAgencyCommission;
  }

  @Override
  public String toString() {
    return pkgName;
  }
}
