package entities;

import com.google.gson.annotations.SerializedName;

public class Column {
    public String id;
    public String name;
    @SerializedName("closed")
    public Boolean closed;
    public String idBoard;

}
