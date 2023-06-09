public class Item {
    private int quantity;
    private final int id ;
    private final String name;
    private final String type;
    private final double price;

    public Item(int id, String name, String type , double price, int quantity)  // Parametrized constructor.
    {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
        this.type = type;
        this.price = price;
    }

    //==================================================================================================================

    public int getId() {    // return the item's id.

        return id;

    }

    //==================================================================================================================

    public String getName() {   // return the item's name.

        return name;

    }

    //==================================================================================================================

    public String getType() {   // return the item's type.

        return type;

    }

    //==================================================================================================================

    public double getPrice() {  // return the item's price

        return price;

    }

    //==================================================================================================================

    public int getQuantity() {  // return the item's quantity.

        return quantity;

    }

    //==================================================================================================================

    public void setQuantity(int quantity) { // Sets the item quantity.

        this.quantity -= quantity;

    }

    //==================================================================================================================
}
