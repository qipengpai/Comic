package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qin.crxl.comic.tool.DateUtil;

import net.sf.json.JSONObject;



public class Test {
	public static void main(String[] args) throws ParseException {
		String start = "2018-05-01 00:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 日期格式
		Date date = dateFormat.parse(start); // 指定日期
		Date end = addDate(date, 1023); // 指定日期加上31天
		System.out.println(dateFormat.format(end));
    }
	public static Date addDate(Date date, long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}

}
