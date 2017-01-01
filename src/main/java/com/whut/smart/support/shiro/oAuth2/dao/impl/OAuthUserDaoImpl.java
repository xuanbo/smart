package com.whut.smart.support.shiro.oAuth2.dao.impl;

import com.whut.smart.support.shiro.oAuth2.dao.OAuthUserDao;
import com.whut.smart.support.shiro.oAuth2.dto.OAuthUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采用JdbcTemplate实现
 *
 * Created by null on 2017/1/1.
 */
@Repository
public class OAuthUserDaoImpl implements OAuthUserDao {

    private static final String INSERT_SQL = "insert into oauth2_user(username, password, salt) values(?, ?, ?)";
    private static final String UPDATE_SQL = "update oauth2_user set username = ?, password = ?, salt = ? where id = ?";
    private static final String DELETE_SQL = "delete from oauth2_user where id = ?";
    private static final String GET_SQL = "select * from oauth2_user where id = ? ";
    private static final String GET_ALL_SQL = "select * from oauth2_user";
    private static final String GET_BY_USERNAME_SQL = "select * from oauth2_user where username = ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(OAuthUserDto oAuthUserDto) {
        return jdbcTemplate.update(INSERT_SQL, oAuthUserDto.getUsername(),
                oAuthUserDto.getPassword(), oAuthUserDto.getSalt());
    }

    @Override
    public int update(final OAuthUserDto oAuthUserDto) {
        return jdbcTemplate.update(UPDATE_SQL, ps -> {
            ps.setString(1, oAuthUserDto.getUsername());
            ps.setString(2, oAuthUserDto.getPassword());
            ps.setString(3, oAuthUserDto.getSalt());
            ps.setLong(4, oAuthUserDto.getId());
        });
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public OAuthUserDto getById(Long id) {
        return jdbcTemplate.queryForObject(GET_SQL, new Object[]{id}, OAuthUserDto.class);
    }

    @Override
    public List<OAuthUserDto> getAll() {
        return jdbcTemplate.queryForList(GET_ALL_SQL, OAuthUserDto.class);
    }

    @Override
    public OAuthUserDto getByUsername(String username) {
        return jdbcTemplate.queryForObject(GET_BY_USERNAME_SQL, new Object[]{username}, OAuthUserDto.class);
    }
}
