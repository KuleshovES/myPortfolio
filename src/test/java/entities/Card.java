package entities;

import com.google.gson.annotations.SerializedName;

public class Card {

    public String id;
    public String name;
    public String idList;
    public String idBoard;
    @SerializedName("closed")
    public Boolean isClosed;

}
