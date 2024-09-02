package kr.co.wizbrain.tbn.comm;

import org.springframework.web.multipart.MultipartFile;

/**파라메터 DTO 클래스이다.
 * @author hjyoon
 *
 */
public class ParamsDto extends BaseDto {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 파라메터를 추가한다.
     * 
     * @param key 키
     * @param value 값
     * @return 기존 값
     */
    public Object add(Object key, Object value) {
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            if (values.length == 1) {
                return super.put(key, values[0]);
            }
        }
        
        return super.put(key, value);
    }
    
    /**
     * 논리값 배열을 반환한다.
     * 
     * @param key 키
     * @return 논리값 배열
     */
    public boolean[] getBooleanArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            
            boolean[] booleanValues = new boolean[values.length];
            
            for (int i = 0; i < values.length; i++) {
                booleanValues[i] = values[i].toString().equalsIgnoreCase("true");
            }
            
            return booleanValues;
        }
        
        if (value instanceof String) {
            return new boolean[] { value.toString().equalsIgnoreCase("true") };
        }
        
        return new boolean[0];
    }
    
    /**
     * 정수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 정수값 배열
     */
    public int[] getIntArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            
            int[] intValues = new int[values.length];
            
            for (int i = 0; i < values.length; i++) {
                intValues[i] = Integer.parseInt(values[i].toString());
            }
            
            return intValues;
        }
        
        if (value instanceof String) {
            return new int[] { Integer.parseInt(value.toString()) };
        }
        
        return new int[0];
    }
    
    /**
     * 정수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 정수값 배열
     */
    public long[] getLongArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            
            long[] longValues = new long[values.length];
            
            for (int i = 0; i < values.length; i++) {
                longValues[i] = Long.parseLong(values[i].toString());
            }
            
            return longValues;
        }
        
        if (value instanceof String) {
            return new long[] { Long.parseLong(value.toString()) };
        }
        
        return new long[0];
    }
    
    /**
     * 실수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 실수값 배열
     */
    public float[] getFloatArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            
            float[] floatValues = new float[values.length];
            
            for (int i = 0; i < values.length; i++) {
                floatValues[i] = Float.parseFloat(values[i].toString());
            }
            
            return floatValues;
        }
        
        if (value instanceof String) {
            return new float[] { Float.parseFloat(value.toString()) };
        }
        
        return new float[0];
    }
    
    /**
     * 실수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 실수값 배열
     */
    public double[] getDoubleArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            
            double[] doubleValues = new double[values.length];
            
            for (int i = 0; i < values.length; i++) {
                doubleValues[i] = Double.parseDouble(values[i].toString());
            }
            
            return doubleValues;
        }
        
        if (value instanceof String) {
            return new double[] { Double.parseDouble(value.toString()) };
        }
        
        return new double[0];
    }
    
    /**
     * 문자열 배열을 반환한다.
     * 
     * @param key 키
     * @return 문자열 배열
     */
    public String[] getStringArray(String key) {
        Object value = get(key);
        
        if (value instanceof String[]) {
            return (String[]) value;
        }
        
        if (value instanceof String) {
            return new String[] { (String) value };
        }
        
        return new String[0];
    }
    
    /**
     * 파일 배열을 반환한다.
     * 
     * @param key 키
     * @return 파일 배열
     */
    public MultipartFile[] getFileArray(String key) {
        Object value = get(key);
        
        if (value instanceof MultipartFile[]) {
            return (MultipartFile[]) value;
        }
        
        if (value instanceof MultipartFile) {
            return new MultipartFile[] { (MultipartFile) value };
        }
        
        return new MultipartFile[0];
    }
    
    /**
     * 페이지 번호를 반환한다.
     * 
     * @return 페이지 번호
     */
    public int getPage() {
        return getInt("page", 1);
    }
    
    /**
     * 페이지 크기를 반환한다.
     * 
     * @return 페이지 크기
     */
    public int getRows() {
        return getInt("rows", 100);
    }
    
    /**
     * 시작 row
     * 
     * @return 시작 row
     */
    public int getStartRow() {
        return (getPage()-1)*getRows()+1;
    }
    
    /**
     * 끝 row
     * 
     * @return 끝 row
     */
    public int getEndRow() {
        return getPage()*getRows();
    }
    
    public void setPage(){
    	super.put("startRow", getStartRow() );
    	super.put("endRow", getEndRow() );
    }
}