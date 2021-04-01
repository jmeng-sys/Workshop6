package objects;


import java.sql.Timestamp;

public class Packages {

  private long packageId;
  private String pkgName;
  private java.sql.Timestamp pkgStartDate;
  private java.sql.Timestamp pkgEndDate;
  private String pkgDesc;
  private double pkgBasePrice;
  private double pkgAgencyCommission;


  public long getPackageId() {
    return packageId;
  }

  public void setPackageId(long packageId) {
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


  public Packages(long packageId, String pkgName, Timestamp pkgStartDate, Timestamp pkgEndDate, String pkgDesc, double pkgBasePrice, double pkgAgencyCommission) {
    this.packageId = packageId;
    this.pkgName = pkgName;
    this.pkgStartDate = pkgStartDate;
    this.pkgEndDate = pkgEndDate;
    this.pkgDesc = pkgDesc;
    this.pkgBasePrice = pkgBasePrice;
    this.pkgAgencyCommission = pkgAgencyCommission;
  }
}
