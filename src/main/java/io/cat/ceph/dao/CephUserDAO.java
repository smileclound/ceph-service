package io.cat.ceph.dao;

import io.cat.ceph.dao.mybatis.providers.CephUserDAOSqlProvider;
import io.cat.ceph.domain.CephUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author GodzillaHua
 **/
@Mapper
public interface CephUserDAO {

    @Select("SELECT * FROM " + TableNames.CEPH_USER_TALBLE + " where name = #{name}")
    @ResultMap("cephUserResultMap")
    CephUser getCephUserByName(@Param("name") String name);

    @Insert({"INSERT INTO " + TableNames.CEPH_USER_TALBLE, "(name, display_name, email, access_key, secret_key, suspend, create_time, update_time)", "VALUES", "(#{name}, #{displayName}, #{email}, #{accessKey}, #{secretKey}, #{suspend}, #{createTime}, #{updateTime})"})
    void saveCephUser(CephUser cephUser);

    @UpdateProvider(type = CephUserDAOSqlProvider.class, method = "updateCephUserSql")
    void updateCephUser(CephUser cephUser);

    @Select("SELECT * FROM " + TableNames.CEPH_USER_TALBLE)
    @ResultMap("cephUserResultMap")
    List<CephUser> getAll();

    @Delete("DELETE FROM " + TableNames.CEPH_USER_TALBLE + " WHERE id = #{id}")
    void deleteCephUser(@Param("id") Integer id);

}
