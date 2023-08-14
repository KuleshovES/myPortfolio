package entities;

import com.google.gson.annotations.SerializedName;

public class Card {

    public String id;
    public String name;
    public String idList;
    public String idBoard;
    @SerializedName("closed")
    public Boolean isClosed;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdList() {
        return idList;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public Boolean getClosed() {
        return isClosed;
    }
}


