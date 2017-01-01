package com.whut.smart.support.shiro.oAuth2.dao.impl;

import com.whut.smart.support.shiro.oAuth2.dao.OAuthClientDao;
import com.whut.smart.support.shiro.oAuth2.dto.OAuthClientDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * 采用JdbcTemplate实现
 *
 * Created by null on 2017/1/1.
 */
@Repository
public class OAuthClientDaoImpl implements OAuthClientDao {

    private static final String INSERT_SQL
            = "insert into oauth2_client(client_name, client_id, client_secret) values(?, ?, ?)";
    private static final String UPDATE_SQL
            = "update oauth2_client set client_name = ?, client_id = ?, client_secret = ? where id = ?";
    private static final String DELETE_SQL = "delete from oauth2_client where id = ?";
    private static final String GET_SQL = "select * from oauth2_client where id = ? ";
    private static final String GET_ALL_SQL = "select * from oauth2_client";
    private static final String GET_BY_CLIENT_ID_SQL = "select * from oauth2_client where client_id = ? ";
    private static final String GET_BY_CLIENT_SECRET_SQL = "select * from oauth2_client where client_secret = ? ";

    @Resource
    private JdbcTemplate jdbcTemplate;

    private RowMapper<OAuthClientDto> rowMapper = (rs, rowNum) -> {
        OAuthClientDto oAuthClientDto  = new OAuthClientDto();
        oAuthClientDto.setId(rs.getLong("id"));
        oAuthClientDto.setClientName(rs.getString("client_name"));
        oAuthClientDto.setClientId(rs.getString("client_id"));
        oAuthClientDto.setClientSecret(rs.getString("client_secret"));
        return oAuthClientDto;
    };

    @Override
    public int insert(OAuthClientDto OAuthClientDto) {
        return jdbcTemplate.update(INSERT_SQL, OAuthClientDto.getClientName(),
                OAuthClientDto.getClientId(), OAuthClientDto.getClientSecret());
    }

    @Override
    public int update(final OAuthClientDto OAuthClientDto) {
        return jdbcTemplate.update(UPDATE_SQL, ps -> {
            ps.setString(1, OAuthClientDto.getClientName());
            ps.setString(2, OAuthClientDto.getClientId());
            ps.setString(3, OAuthClientDto.getClientSecret());
            ps.setLong(4, OAuthClientDto.getId());
        });
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public OAuthClientDto getById(Long id) {
        return jdbcTemplate.queryForObject(GET_SQL, new Object[]{id}, new int[]{Types.BIGINT}, rowMapper);
    }

    @Override
    public List<OAuthClientDto> getAll() {
        return jdbcTemplate.queryForList(GET_ALL_SQL, OAuthClientDto.class);
    }

    @Override
    public OAuthClientDto getByClientId(String clientId) {
        return jdbcTemplate.queryForObject(GET_BY_CLIENT_ID_SQL, new Object[]{clientId},
                new int[]{Types.VARCHAR}, rowMapper);
    }

    @Override
    public OAuthClientDto getByClientSecret(String clientSecret) {
        return jdbcTemplate.queryForObject(GET_BY_CLIENT_SECRET_SQL, new Object[]{clientSecret},
                new int[]{Types.VARCHAR}, rowMapper);
    }
}
