package entities;

import com.google.gson.annotations.SerializedName;

public class Column {
    public String id;
    public String name;
    @SerializedName("closed")
    public Boolean closed;
    public String idBoard;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getClosed() {
        return closed;
    }

    public String getIdBoard() {
        return idBoard;
    }
}
