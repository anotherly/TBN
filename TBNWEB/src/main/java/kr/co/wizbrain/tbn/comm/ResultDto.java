package kr.co.wizbrain.tbn.comm;

/**결과 DTO 클래스이다.
 * @author hjyoon
 *
 */
public class ResultDto extends BaseDto {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 페이지 번호를 반환한다.
     * 
     * @return 페이지 번호
     */
    public int getPage() {
        return getInt("page");
    }
    
    /**
     * 페이지 수를 반환한다.
     * 
     * @return 페이지 수
     */
    public int getPages() {
        return getInt("pages");
    }
    
    /**
     * 데이터를 반환한다.
     * 
     * @return 데이터
     */
    public Object getData() {
        return get("data");
    }
    
    /**
     * 데이터 수를 반환한다.
     * 
     * @return 데이터 수
     */
    public int getCount() {
        return getInt("count");
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @return 처리결과
     */
    public boolean getSuccess() {
        return getBoolean("success");
    }
    
    /**
     * 메시지를 반환한다.
     * 
     * @return 메시지
     */
    public MessageDto getMessages() {
        Object value = get("messages");
        
        if (value instanceof MessageDto) {
            return (MessageDto) value;
        }
        
        return null;
    }
}