public class Product {
    private int price;
    private String warranty;
    private String description;
    private String id;

    public void setPrice(int price){
        this.price=price;
    }
    public int getPrice() {
        return this.price;
    }

    public void setWarranty(String warranty){
        this.warranty=warranty;
    }
    public String getWarranty(){
        return this.warranty;
    }

    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }

    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
}
