package kr.co.wizbrain.tbn.comm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import oracle.sql.BLOB;

import org.springframework.util.FileCopyUtils;

/**레코드 DTO 클래스이다.
 * @author hjyoon
 *
 */

public class RecordDto extends BaseDto {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * BLOB을 처리한다.
     * 
     * @param blob BLOB
     * @return 바이트배열
     */
    private byte[] handleBlob(BLOB blob) {
        ByteArrayOutputStream baos = null;
        
        try {
            baos = new ByteArrayOutputStream();
            
            FileCopyUtils.copy(blob.getBinaryStream(), baos);
            
            return baos.toByteArray();
        }
        catch (SQLException sqle) {
            return null;
        }
        catch (IOException ioe) {
            return null;
        }
        finally {
            if (baos != null) {
                try {
                    baos.close();
                }
                catch (IOException ioe) {
                    // Nothing to do.
                }
            }
            if (blob != null) {
                try {
                    blob.close();
                }
                catch (SQLException sqle) {
                    // Nothing to do.
                }
            }
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        if (value instanceof byte[]) {
            return super.put(key, new String((byte[]) value));
        }
        
        if (value instanceof char[]) {
            return super.put(key, new String((char[]) value));
        }
        
        if (value instanceof BLOB) {
            return super.put(key, handleBlob((BLOB) value));
        }
        
        return super.put(key, value);
    }
}