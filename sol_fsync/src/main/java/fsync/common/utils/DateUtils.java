package fsync.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * package : fsync.common.utils
 * file_name : DateUtils.java
 * summary : 날짜관련 utility class
 * history
 *      2022.07.20 최초작성
 * </pre>
 * @author mhj
 * @date 2022.07.20
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    static SimpleDateFormat format = null;
    static SimpleDateFormat currentTimeDf = null;
    static SimpleDateFormat fileTimeDf = null;
    static SimpleDateFormat df = null;
    static SimpleDateFormat simpleCurrTime = null;
    static SimpleDateFormat currTime = null;
    static SimpleDateFormat YMDCurrTime = null;

    static {
        try {
            format = new SimpleDateFormat("MM/dd/yyyy");
            currentTimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fileTimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            df = new SimpleDateFormat("yyyyMMddHH:mm:ss");
            simpleCurrTime = new SimpleDateFormat("");
            currTime = new SimpleDateFormat("HH:mm:ss");
            YMDCurrTime = new SimpleDateFormat("yyyyMMdd");
        }
        catch (NullPointerException nx) {
        	log.error("NullPointerException >> "+ nx.toString());        	 
        }
        catch (Exception ex) {
        	log.error("Exception >> "+ ex.toString());            
        }
    }

    /**
     * <pre>
     * 현재시각을 리턴
     * 2000-06-22 13:04:45 형태로 표시
     * </pre>
     *
     * @return String
     */
    public static String getFileTime() {
        return fileTimeDf.format(new Date());
    }

    public static String getCurrentDateTime() {
        return currentTimeDf.format(new Date());
    }

    public static String getCurrentTime(){
        return currTime.format(new Date());
    }

}