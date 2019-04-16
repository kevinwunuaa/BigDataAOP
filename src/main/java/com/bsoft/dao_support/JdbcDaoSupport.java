package com.bsoft.dao_support;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import com.bsoft.common.pagination.PageObject;

/**
 * jdbc查询支持类.
 * <br/>
 * 注入容器标识：jdbcService
 * <br/>
 * 依赖资源:JdbcTemplate
 * @author Wuyong
 *
 */
@Service(value = "jdbcDaoSupport")
public class JdbcDaoSupport {
	public static final Logger log = Logger.getLogger(JdbcDaoSupport.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询oracle序列.
	 * @param seqName 序列名
	 * @return 序列
	 */
	@SuppressWarnings({ "deprecation" })
	public int getSeq(String seqName) {
		String sql = "select " + seqName + ".nextval seqno from dual";
		int seq = jdbcTemplate.queryForInt(sql);
		return seq;
	}
	
	/**
	 * 查询数据.
	 * @param sql 查询语句
	 * @param args 查询参数
	 * @return {@link List}对象，元素类型为Map<String,Object>
	 */
	public List<Map<String,Object>> queryForList(String sql, Object... args) {
		return jdbcTemplate.queryForList(sql, args);
	}
	
	/**
	 * 分布查询.
	 * @param objectName 表名
	 * @param args map参数
	 * @param orderField 排序字段
	 * @param orderBy desc或者asc
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return {@link PageObject}页对象
	 */
	public PageObject findByPage(String objectName,Map<String, Object> args, String orderField,String orderBy,int pageNo,
			int pageSize) {

		String sql = "select rownum rn,t.* from " + objectName + " t where 1 = 1";
		String countSql = "select count(1) totalRows from " + objectName + " t where 1 = 1";

		int startRow = (pageNo - 1) * pageSize + 1;
		int endRow = pageNo * pageSize;
		Object[] argsArray = null;

		if (args != null && args.size() > 0) {
			int len = args.size();
			argsArray = new Object[len];
			int i = 0;
			for (Map.Entry<String, Object> entry : args.entrySet()) {
				String f = entry.getKey();
				Object v = entry.getValue();
				sql += " and " + f + " = ?";
				countSql += " and " + f + " = ?";
				argsArray[i++] = v;
			}
		}

		sql += " order by " + orderField + " " + orderBy;

		sql = "select * from (" + sql + ") where rn between " + startRow
				+ " and " + endRow;

		log.info(String.format("分页sql[%s]", sql));

		int total = jdbcTemplate.queryForInt(countSql, argsArray);

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				argsArray);

		PageObject page = new PageObject();

		page.setTotal(total);
		page.setRows(list);

		return page;
	}
	
	/**
	 * 执行更新.
	 * @param sql SQL
	 * @param args 参数
	 * @return 成功行数
	 */
	public int executeUpdate(String sql,Object... args){
		return jdbcTemplate.update(sql, args);
	}
	
	/**
	 * 调用存储过程.
	 * @param creator 存储过程构造
	 * @param params 参数
	 * @return Map结果
	 */
	public Map<String,Object> Call(CallableStatementCreator creator,List<SqlParameter> params){
		CallableStatementCreator s = new CallableStatementCreator()
        {

			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				String storedProc = "{call sp_list_table(?,?)}";// 调用的sql   
	            CallableStatement cs = conn.prepareCall(storedProc);

	            return cs;	
			}
        };
        
		return jdbcTemplate.call(s, params);
	}
	
}
