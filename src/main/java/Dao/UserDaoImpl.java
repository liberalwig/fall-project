package Dao;

import Vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSession sqlSession;

    private static final String nameSpace = "mappers.userMapper";

    @Override
    public boolean userLogin(UserVo vo) throws Exception {
        String id = sqlSession.selectOne(nameSpace + ".userLogin", vo);
        return (id == null) ? false : true;
    }

    @Override
    public void userJoin(UserVo vo) throws Exception {
        sqlSession.insert(nameSpace + ".userJoin", vo);
    }
}
