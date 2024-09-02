package kr.co.wizbrain.tbn.comm;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

/**기본 DTO 클래스이다.
 * @author hjyoon
 *
 */
public class BaseDto extends HashMap<Object, Object> {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 논리값을 반환한다.
     * 
     * @param key 키
     * @return 논리값
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    
    /**
     * 논리값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 논리값
     * @return 논리값
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return value.toString().equalsIgnoreCase("true");
        }
        
        return defaultValue;
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @return 정수값
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 정수값
     * @return 정수값
     */
    public int getInt(String key, int defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Integer.parseInt(value.toString());
        }
        
        return defaultValue;
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @return 정수값
     */
    public long getLong(String key) {
        return getLong(key, 0L);
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 정수값
     * @return 정수값
     */
    public long getLong(String key, long defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Long.parseLong(value.toString());
        }
        
        return defaultValue;
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @return 실수값
     */
    public float getFloat(String key) {
        return getFloat(key, 0F);
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 실수값
     * @return 실수값
     */
    public float getFloat(String key, float defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Float.parseFloat(value.toString());
        }
        
        return defaultValue;
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @return 실수값
     */
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 실수값
     * @return 실수값
     */
    public double getDouble(String key, double defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Double.parseDouble(value.toString());
        }
        
        return defaultValue;
    }
    
    /**
     * 문자열을 반환한다.
     * 
     * @param key 키
     * @return 문자열
     */
    public String getString(String key) {
        return getString(key, "");
    }
    
    /**
     * 문자열을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 문자열
     * @return 문자열
     */
    public String getString(String key, String defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return value.toString();
        }
        
        return defaultValue;
    }
    
    /**
     * 파일을 반환한다.
     * 
     * @param key 키
     * @return 파일
     */
    public MultipartFile getFile(String key) {
        Object value = get(key);
        
        if (value instanceof MultipartFile) {
            return (MultipartFile) value;
        }
        
        return null;
    }
    
    /**
     * 바이너리를 반환한다.
     * 
     * @param key 키
     * @return 바이너리
     */
    public byte[] getBinary(String key) {
        Object value = get(key);
        
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        
        return null;
    }
}