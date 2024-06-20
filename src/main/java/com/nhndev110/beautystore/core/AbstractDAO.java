package com.nhndev110.beautystore.core;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> implements GenericDAO<T> {

  private static Connection connect;

  private static Connection getConnection() {
    try {
      FileReader reader = new FileReader(Constants.APP_PATH + "/config.ini");
      Properties properties = new Properties();
      properties.load(reader);
      String dbURL = "jdbc:%s://%s:%s/%s".formatted(
        properties.getProperty("DB_CONNECTION"),
        properties.getProperty("DB_HOST"),
        properties.getProperty("DB_PORT"),
        properties.getProperty("DB_DATABASE")
      );

      Class.forName("com.mysql.cj.jdbc.Driver");

      return DriverManager.getConnection(
        dbURL,
        properties.getProperty("DB_USERNAME"),
        properties.getProperty("DB_PASSWORD")
      );
    } catch (ClassNotFoundException | SQLException | IOException ex) {
      Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null;
  }

  protected static Connection getInstanceConnection() {
    if (connect == null) {
      connect = getConnection();
    }

    return connect;
  }

  @Override
  public List<T> queryDAO(String sql, RowMapper<T> rowMapper, Object... params) {
    List<T> list = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      Connection conn = getInstanceConnection();
      ps = conn.prepareStatement(sql);
      setParams(ps, params);
      rs = ps.executeQuery();
      while (rs.next()) {
        list.add(rowMapper.mapRow(rs));
      }
    } catch (SQLException ex) {
      Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ex) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
//      if (conn != null) {
//        try {
//          conn.close();
//        } catch (SQLException ex) {
//          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    }

    return list;
  }

  @Override
  public Long insertDAO(String sql, Object... params) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Long id = null;

    try {
      conn = getInstanceConnection();
      conn.setAutoCommit(false);
      ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      setParams(ps, params);
      ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      if (rs.next()) {
        id = rs.getLong(1);
      }
      conn.commit();
    } catch (SQLException ex) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (SQLException ex1) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
      }
      Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ex) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
//      if (conn != null) {
//        try {
//          conn.close();
//        } catch (SQLException ex) {
//          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    }

    return id;
  }

  @Override
  public boolean updateDAO(String sql, Object... params) {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
      conn = getInstanceConnection();
      conn.setAutoCommit(false);
      ps = conn.prepareStatement(sql);
      setParams(ps, params);
      ps.executeUpdate();
      conn.commit();
      return true;
    } catch (SQLException ex) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (SQLException ex1) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
      }
      Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ex) {
          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
//      if (conn != null) {
//        try {
//          conn.close();
//        } catch (SQLException ex) {
//          Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    }

    return false;
  }

  public void setParams(PreparedStatement ps, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      Object param = params[i];
      int paramIndex = i + 1;

      if (param instanceof Long) {
        ps.setLong(paramIndex, (long) param);
      } else if (param instanceof Integer) {
        ps.setInt(paramIndex, (int) param);
      } else if (param instanceof String) {
        ps.setString(paramIndex, (String) param);
      } else if (param instanceof Double) {
        ps.setDouble(paramIndex, (double) param);
      } else if (param instanceof Boolean) {
        ps.setBoolean(paramIndex, (boolean) param);
      } else if (param instanceof Timestamp) {
        ps.setTimestamp(paramIndex, (Timestamp) param);
      } else if (param == null) {
        ps.setNull(paramIndex, Types.NULL);
      }
    }
  }

}
