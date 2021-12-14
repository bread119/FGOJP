package cn.mcfun.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.Base64;

public class FileExtractor {
    public FileExtractor() {
    }

    public String fileExtractor(String data) throws Exception {
        byte[] key = new byte[]{98, 53, 110, 72, 106, 115, 77, 114, 113, 97, 101, 78, 108, 105, 83, 115, 51, 106, 121, 79, 122, 103, 112, 68};
        byte[] keyiv = new byte[]{119, 117, 68, 54, 107, 101, 86, 114};
        byte[] dataByte = Base64.getDecoder().decode(data);
        byte[] str = new Des3().des3DecodeCBC(key, keyiv, dataByte);
        String json = new String(str, "UTF-8");
        return json;
    }
}
