package kr.co.wizbrain.tbn.comm;

/**메시지 DTO 클래스이다.
 * @author hjyoon
 *
 */
public class MessageDto extends BaseDto {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 메시지를 반환한다.
     * 
     * @param key 키
     * @return 메시지
     */
    public String getMessage(String key) {
        return getString(key);
    }
    
    /**
     * 메시지를 설정한다.
     * 
     * @param message 메시지
     */
    public void setMessage(String key, String value) {
        put(key, value);
    }
}