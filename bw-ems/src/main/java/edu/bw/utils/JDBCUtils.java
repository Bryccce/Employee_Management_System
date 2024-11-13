package edu.bw.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
	/*构造函数私有化
	不允许在类外创建对象*/
	private JDBCUtils() {

	}

	private static BasicDataSource bs;

	static {
		Properties properties = new Properties();
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			/*从流中将配置文件中的数据加载到集合中*/
			properties.load(is);

			//创建一个连接池
			bs = new BasicDataSource();

			//设置连接池参数
			/*必备的四个参数*/
			bs.setDriverClassName(properties.getProperty("driver"));
			bs.setUrl(properties.getProperty("url"));
			bs.setUsername(properties.getProperty("user"));
			bs.setPassword(properties.getProperty("password"));
			/*设置可选参数*/
			bs.setInitialSize(12);
			bs.setMaxTotal(12);
			bs.setMaxIdle(2);
			bs.setMinIdle(1);
			bs.setMaxWaitMillis(30000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con=null;//提升作用域
		try {
			con=bs.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static DataSource getDataSource() {
		return bs;
	}



}
