package Service;

import Dao.UserDao;
import Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Hashtable;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    private static Hashtable<String, String> loginUsers = new Hashtable<String, String>();

    @Override
    public boolean userLogin(UserVo vo, HttpSession session) throws Exception {
        boolean isLogin = isLogin(vo.getId());
        if (!isLogin) {
            boolean result = dao.userLogin(vo);
            if (result) {
                setSession(session, vo);
            }
            return result;
        }
        return !isLogin;
    }

    @Override
    public void userJoin(UserVo vo) throws Exception {
        vo.setId(vo.getId());
        vo.setPassword(vo.getPassword());
        vo.setEmail(vo.getEmail());
        vo.setPhone(vo.getPhone());

        dao.userJoin(vo);
    }

    public boolean isLogin(String id) {
        boolean isLogin = false;

        Enumeration<String> e = loginUsers.keys();
        String key = "";

        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            if (id.equals(loginUsers.get(key)))
                isLogin = true;
        }

        return isLogin;
    }

    public void setSession(HttpSession session, UserVo vo) throws Exception {
        loginUsers.put(session.getId(), vo.getId());
        session.setAttribute("id", vo.getId());
    }
}