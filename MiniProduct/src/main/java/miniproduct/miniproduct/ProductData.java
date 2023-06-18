package miniproduct.miniproduct;


  public class ProductData {
    private int id;
    private String name;
    private int qty;
    private int price;
    private int amount;

      public ProductData(int id, String name, int qty, int price, int amount) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
      }
    public ProductData() {
        super();
    }

      public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
  }
