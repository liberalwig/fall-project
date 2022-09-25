package Dao;

import Vo.UserVo;

public interface UserDao {
    public boolean userLogin(UserVo vo) throws Exception;

    public void userJoin(UserVo vo) throws Exception;
}
