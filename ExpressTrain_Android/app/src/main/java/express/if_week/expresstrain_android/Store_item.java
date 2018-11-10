package express.if_week.expresstrain_android;



public class Store_item {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Store_item(int Type,String Title,String address,String phone){
        this.title=Title;
        this.Address=address;
        this.phone=phone;
        this.Type=Type;
        expend=false;
    }
    boolean expend;
    int Type;
    String title;
    String Address;
    String phone;
}
