package qelit.a5cards;

/**
 * Created by Qelit on 24.10.2017.
 */

public class Contact{
    private String _name;
    private String _phone;
    private String _my_id;

    public void setName(String name) {
        _name = name;
    }

    public void setPhone(String phone) {
        _phone = phone;
    }

    public void setMyId(String my_id) {
        _my_id = my_id;
    }

    public String getName() {
        return _name;
    }

    public String getPhone() {
        return _phone;
    }

    public String getMyId() {
        return _my_id;
    }
}