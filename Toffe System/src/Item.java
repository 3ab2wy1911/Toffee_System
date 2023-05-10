public class Item {
    private int quantity, id ;

    private String name , type;
    private double price;

    public Item(int id, String name, String type , double price, int quantity)  // Parametrized constructor.
    {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
