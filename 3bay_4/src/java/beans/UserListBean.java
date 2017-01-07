package beans;

import db.UserDB;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "users")
@RequestScoped
public class UserListBean {

    private DataModel<UserBean> userList;    
    private UserDB userDB;
    
    public DataModel<UserBean> getUserList() {
        return userList;
    }

    public UserListBean() {
        userDB = new UserDB();
        if (userList == null) {
            List list = userDB.getUsers();
            if (list != null) {
                ArrayList<UserBean> uList = new ArrayList<UserBean>();

                for (Object o : list) {

                    UserBean ub = new UserBean();
                    ub.setSocialSecurityNumber(((User) o).getSocialSecurityNumber());
                    ub.setUsername(((User) o).getUsername());
                    ub.setFirstName(((User) o).getFirstName());
                    ub.setLastName(((User) o).getLastName());
                    ub.setIsConfirmed(((User) o).getIsConfirmed());
                    uList.add(ub);

                }
                userList = new ListDataModel<UserBean>(uList);
            }
        }
    }


}
