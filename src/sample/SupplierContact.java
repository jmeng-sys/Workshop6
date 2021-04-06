package sample;

public class SupplierContact {
    private int supplierContactId;
    private String supConFirstName;
    private String supConLastName;
    private String supConCompany;
    private String supConAddress;
    private String supConCity;
    private String supConProv;
    private String supConPostal;
    private String supConCountry;
    private String supConBusPhone;
    private String supConFax;
    private String supConEmail;
    private String supConUrl;
    private String affiliationId;
    private int supplierId;
    private String supName;

    public SupplierContact(int supplierContactId, String supConFirstName, String supConLastName, String supConCompany
            , String supConAddress, String supConCity, String supConProv, String supConPostal, String supConCountry,
                           String supConBusPhone, String supConFax, String supConEmail, String supConUrl,
                           String affiliationId, int supplierId, String supName) {
        this.supplierContactId = supplierContactId;
        this.supConFirstName = supConFirstName;
        this.supConLastName = supConLastName;
        this.supConCompany = supConCompany;
        this.supConAddress = supConAddress;
        this.supConCity = supConCity;
        this.supConProv = supConProv;
        this.supConPostal = supConPostal;
        this.supConCountry = supConCountry;
        this.supConBusPhone = supConBusPhone;
        this.supConFax = supConFax;
        this.supConEmail = supConEmail;
        this.supConUrl = supConUrl;
        this.affiliationId = affiliationId;
        this.supplierId = supplierId;
        this.supName = supName;
    }

    public int getSupplierContactId() {
        return supplierContactId;
    }
    public void setSupplierContactId(int supplierContactId) {
        this.supplierContactId = supplierContactId;
    }

    public String getSupConFirstName() {
        return supConFirstName;
    }
    public void setSupConFirstName(String supConFirstName) {
        this.supConFirstName = supConFirstName;
    }

    public String getSupConLastName() {
        return supConLastName;
    }
    public void setSupConLastName(String supConLastName) {
        this.supConLastName = supConLastName;
    }

    public String getSupConCompany() {
        return supConCompany;
    }
    public void setSupConCompany(String supConCompany) {
        this.supConCompany = supConCompany;
    }

    public String getSupConAddress() {
        return supConAddress;
    }
    public void setSupConAddress(String supConAddress) {
        this.supConAddress = supConAddress;
    }

    public String getSupConCity() {
        return supConCity;
    }
    public void setSupConCity(String supConCity) {
        this.supConCity = supConCity;
    }

    public String getSupConProv() {
        return supConProv;
    }
    public void setSupConProv(String supConProv) {
        this.supConProv = supConProv;
    }

    public String getSupConPostal() {
        return supConPostal;
    }
    public void setSupConPostal(String supConPostal) {
        this.supConPostal = supConPostal;
    }

    public String getSupConCountry() {
        return supConCountry;
    }
    public void setSupConCountry(String supConCountry) {
        this.supConCountry = supConCountry;
    }

    public String getSupConBusPhone() {
        return supConBusPhone;
    }
    public void setSupConBusPhone(String supConBusPhone) {
        this.supConBusPhone = supConBusPhone;
    }

    public String getSupConFax() {
        return supConFax;
    }
    public void setSupConFax(String supConFax) {
        this.supConFax = supConFax;
    }

    public String getSupConEmail() {
        return supConEmail;
    }
    public void setSupConEmail(String supConEmail) {
        this.supConEmail = supConEmail;
    }

    public String getSupConUrl() {
        return supConUrl;
    }
    public void setSupConUrl(String supConUrl) {
        this.supConUrl = supConUrl;
    }

    public String getAffiliationId() {
        return affiliationId;
    }
    public void setAffiliationId(String affiliationId) {
        this.affiliationId = affiliationId;
    }

    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupName() {
        return supName;
    }
    public void setSupName(String supName) {
        this.supName = supName;
    }

    @Override
    public String toString() {
        return supConFirstName + ' ' + supConLastName;
    }

    public SupplierContact(int supplierId, String supName) {
        this.supplierId = supplierId;
        this.supName = supName;
    }
}
