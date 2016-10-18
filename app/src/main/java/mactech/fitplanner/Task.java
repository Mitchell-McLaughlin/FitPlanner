package mactech.fitplanner;

/**
 * Created by Mitchell on 8/3/2016.
 */
public class Task {
    private int _id;
    private String _taskName;
    public String _day;

    public Task()
    {}

    public Task(String taskName) {
        this._taskName = taskName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public void set_day(String _day) { this._day = _day; }
    public void set_taskName(String _taskName) {
        this._taskName = _taskName;
    }

    public int get_id() {
        return _id;
    }
    public String get_taskName() {
        return _taskName;
    }
    public String get_Day() {return _day;}
}
