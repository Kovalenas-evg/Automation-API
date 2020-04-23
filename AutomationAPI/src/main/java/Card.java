public class Card extends List{
    public String idList;

    // board
    public String getId() {
        return idBoard;
    }

    // list
    public String getListId() {
        return idList;
    }

    // card
    public  String getCardName(){
        return name;
    }

    public void setCardName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return id;
    }
}