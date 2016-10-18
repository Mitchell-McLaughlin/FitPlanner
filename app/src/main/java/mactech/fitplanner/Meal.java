package mactech.fitplanner;

/**
 * Created by Mitchell on 10/12/2016.
 */

public class Meal {
    private int _id;
    private String _name;

    public Meal(){    }

    public Meal(String name){
        _name = name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
