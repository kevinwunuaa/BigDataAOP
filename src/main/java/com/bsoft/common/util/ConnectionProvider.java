package com.bsoft.common.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * JDBC连接查询工具类，采用druid连接池. <br/>
 * <h3 style="display:inline">用法：</h3> <br/>
 * {@link #getInstance()}-
 * {@link #getConnection(String, String, String, String, String)}-
 * {@link #queryForList(String, Object[], Connection)}-
 * {@link #closeAll(Connection, Statement, ResultSet)}
 * 
 * @author Wuyong
 */
public final class ConnectionProvider {
	private static Logger log = Logger.getLogger(ConnectionProvider.class);
	private static Map<String, DruidDataSource> poolSet = new HashMap<String, DruidDataSource>();
	private static ConnectionProvider instance = null;

	private ConnectionProvider() {

	}

	/**
	 * 获取唯一实例.
	 * 
	 * @return ConnectionProvider 唯一实例对象
	 */
	public static synchronized ConnectionProvider getInstance() {
		if (instance == null) {
			instance = new ConnectionProvider();
		}

		return instance;
	}

	public static DruidDataSource getDataSource(String dsKey, String driver,
			String url, String username, String password) {
		log.info(String.format("创建数据源[key:%s,url:%s]", dsKey,url));
		if (poolSet.containsKey(dsKey)) {
			return poolSet.get(dsKey);
		}

		DruidDataSource ds = new DruidDataSource();

		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		// 指定数据源默认连接数大小
		ds.setInitialSize(1);
		ds.setMinIdle(5);
		ds.setMaxActive(20);

		ds.setMaxWait(3600000);

		ds.setTimeBetweenEvictionRunsMillis(60000);
		ds.setMinEvictableIdleTimeMillis(300000);

		ds.setPoolPreparedStatements(true);
		ds.setMaxPoolPreparedStatementPerConnectionSize(100);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(7200);

		try {
			ds.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		poolSet.put(dsKey, ds);

		return ds;
	}

	/**
	 * 获取数据库连接.
	 * 
	 * @param dsKey
	 *            自定义的唯一的连接标识符
	 * @param driver
	 *            驱动类
	 * @param url
	 *            连接url
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return Connection 连接对象
	 */
	public static Connection getConnection(String dsKey, String driver,
			String url, String username, String password) {
		DruidDataSource ds = getDataSource(dsKey, driver, url, username,
				password);
		Connection connection = null;

		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			System.out.println("获取连接失败 !");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 查询表信息.
	 * 
	 * @param connection
	 * @param schemaPattern
	 * @param tableNamePattern
	 * @return ResultSet
	 */
	public static ResultSet queryTables(Connection connection,
			String schemaPattern, String tableNamePattern) {
		ResultSet rs = null;
		try {
			DatabaseMetaData dbMetadata = connection.getMetaData();
			rs = dbMetadata.getTables(null, schemaPattern, tableNamePattern,
					new String[] { "TABLE", "VIEW" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 查询主键.
	 * 
	 * @param connection
	 * @param schema
	 * @param table
	 * @return ResultSet
	 */
	public static ResultSet queryPrimaryKeys(Connection connection,
			String schema, String table) {
		ResultSet rs = null;
		try {
			DatabaseMetaData dbMetadata = connection.getMetaData();

			rs = dbMetadata.getPrimaryKeys(null, schema, table);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 查询表列.
	 * 
	 * @param connection
	 * @param schemaPattern
	 * @param tableNamePattern
	 * @return ResultSet
	 */
	public static ResultSet queryColumns(Connection connection,
			String schemaPattern, String tableNamePattern) {
		ResultSet rs = null;
		try {
			DatabaseMetaData dbMetadata = connection.getMetaData();
			rs = dbMetadata.getColumns(null, schemaPattern.toUpperCase(),
					tableNamePattern.toUpperCase(), "%");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * 通用的查询方法.
	 * 
	 * @param sql
	 *            为需要执行的SQL语句
	 * @param obj
	 *            该SQL语句对应的参数列表
	 * @param connection
	 *            该次数据库操作对应的数据库连接参数
	 * @return ResultSet 返回查询之后的结果集
	 */
	public static ResultSet query(String sql, Object[] obj,
			Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// 创建预处理命令对象
			preparedStatement = connection.prepareStatement(sql);

			// 判断是否有占位符
			if (obj != null) {
				// 给占位符赋值
				for (int i = 0; i < obj.length; i++) {
					preparedStatement.setObject((i + 1),
							obj[i] != null ? obj[i] : "");
				}
			}
			// 执行
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 暂时不能关闭 , 因为结果集里面还保存着数据 。
		return resultSet;
	}

	/**
	 * 释放资源.
	 * 
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeAll(Connection connection, Statement statement,
			ResultSet resultSet) {

		try {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 释放资源.
	 * 
	 * @param rs
	 */
	public static void closeAll(ResultSet rs) {

		try {
			if (rs != null) {
				// 通过结果集找到对应的命令对象 , 再找到对应的连接 . 依次关闭
				Statement statement = rs.getStatement();
				Connection connection = statement.getConnection();
				ConnectionProvider.closeAll(connection, statement, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将结果集转换为List<Map<String,Object>>.
	 * 
	 * @param rs
	 * @return {@link List}
	 */
	public static List<Map<String, Object>> getList(ResultSet rs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= cols; i++) {
					String colName = md.getColumnName(i);
					Object value = rs.getObject(colName);
					map.put(colName.toUpperCase(), value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询列表.
	 * 
	 * @param sql
	 *            查询语句
	 * @param obj
	 *            查询参数
	 * @param connection
	 * @return {@link List}
	 */
	public static List<Map<String, Object>> queryForList(String sql,
			Object[] obj, Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Map<String, Object>> list = null;

		try {
			// 创建预处理命令对象
			preparedStatement = connection.prepareStatement(sql);

			// 判断是否有占位符
			if (obj != null) {
				// 给占位符赋值
				for (int i = 0; i < obj.length; i++) {
					preparedStatement.setObject((i + 1),
							obj[i] != null ? obj[i] : "");
				}
			}
			// 执行
			resultSet = preparedStatement.executeQuery();
			list = getList(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * Clob转为String.
	 * 
	 * @param clob
	 * @return {@link String}
	 */
	public static String ClobToString(Clob clob) {

		String reString = "";
		try {
			Reader is = clob.getCharacterStream(); // 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) { // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		} catch (Exception e) {

		}
		return reString;
	}

	public static DataSource getDataSource(String dsKey) {
		if (poolSet.containsKey(dsKey)) {
			return poolSet.get(dsKey);
		}
		return null;
	}
}
