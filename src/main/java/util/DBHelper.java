package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 数据连接工具类
 */
public class DBHelper {

	//数据库地址URL
	private static final String URL  = "jdbc:mysql://localhost:3306/mydb?"
			+ "useUnicode=true&characterEncoding=utf8"
			+ "&serverTimezone=Asia/Shanghai&useSSL=false";
	//数据库账户名
	private static final String USER = "root";
	//数据库账户密码
	private static final String PASS = "123456";

	//数据库连接类
	static Connection conn = null;

	//预编译SQL语句的对象
	static PreparedStatement ps = null;
	
	//接收查询返回的结果集
	static ResultSet rs = null;
	
	//1 连接初始化：建立连接
	public static void init() {
		if (conn == null) {
			try {
				//反射获取连接驱动类，根据包路径加载连接驱动类
				Class.forName("com.mysql.cj.jdbc.Driver");
				//建立连接
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (Exception e) {
				System.out.println("database connection fail");
				e.printStackTrace();
			}
			System.out.println("*****build connection success*****");
		} else {
			System.out.println("*****already build connection success*****");
		}
	}
	/*
	 * 泛型
	 * <T>声明此方法持有一个类型T，也可以理解为此方法为一个泛型方法
	 * List<T> 声明该方法的返回值类型为List集合，且集合中的元素是泛型T类型
	 * Class<T> cls 指明泛型T的具体类型
	 */
	public static <T> List<T> queryAll(String sql, Class<T> cls, Object... params) {
		//建了连接
		init();
		
		try {
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			if(params != null) {
				for (int i = 0; i < params.length; i++) {
					//替换指定位置的占位符
					ps.setObject(i+1, params[i]);
					System.out.println(params[i]);
				}
			}
			rs = ps.executeQuery();
			
			//检索此ResultSet对象的列数、类型和字段。
			ResultSetMetaData rsd = rs.getMetaData();
			List<T> list = new ArrayList<T>();
			
			while (rs.next()) {//向下读一行
				//实例化泛型类
				T t = cls.newInstance();
				
				for(int i = 0; i < rsd.getColumnCount(); i++){
					try {
						//获取指定列的别名，如果sql语句中没有指定别名，则返回值与getColumnName方法相同
	            		String column = rsd.getColumnLabel(i+1);
	            		
	            		Object value = rs.getObject(column);

	            		//通过反射获取变量Field对象
	            		Field field = cls.getDeclaredField(getParam(column));
						//开启允许访问私有变量的权限
	            		field.setAccessible(true);
						//给变量赋值
						field.set(t, value);
					} catch (Exception e) {
						// TODO: handle exception
					}
                }
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//关闭连接资源
			if (conn != null) conn = null;
			if(ps != null) ps = null;
			if(rs != null) rs = null;
		}
	}
	
	//计算总数
	public static int count(String sql) {
		//建了连接
		init();
		try {
			int totalCount = 0;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println(sql);
			while (rs.next()) {//向下读一行
				//读取第一个参数
				totalCount = rs.getInt(1);
			}
			return totalCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//数据库字段（下划线命名）转变量（驼峰命名）
	public static String getParam(String column) {
		String[] arr = column.split("_");
		
		StringBuffer sf = new StringBuffer(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			sf.append(arr[i].substring(0,1).toUpperCase()+arr[i].substring(1));
		}
		return sf.toString();
	}
	
	//insert/update/delete
	public static int deal(String sql,Object... params) {
		init();
		try {
			ps = conn.prepareStatement(sql);
			//替换指定位置的占位符
			if(params != null) {
				for (int i = 0; i < params.length; i++) {
					//替换指定位置的占位符
					ps.setObject(i+1, params[i]);
				}
			}
			//executeUpdate()返回受影响行数
			return ps.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			return 0;
		}
		
	}
	
}
