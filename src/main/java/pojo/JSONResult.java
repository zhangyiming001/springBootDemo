package pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JSONResult {
    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //相应业务状态
    private  Integer status;

    //响应中的数据
    private String msg;

    //响应中的数据
    private Object data;

    public static JSONResult build(Integer status, String msg, Object data){
        return new JSONResult(status, msg, data);
    }

    public static  JSONResult responseData(Object data){
        return new JSONResult(data);
    }

    public static  JSONResult response(){
        return new JSONResult(null);
    }

    public static  JSONResult errorMsg(String msg){
        return new JSONResult(500, msg, null);
    }

    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "error", data);
    }

    public static JSONResult errorTokenMsg(String msg) {
        return new JSONResult(502, msg, null);
    }

    public static JSONResult errorException(String msg) {
        return new JSONResult(555, msg, null);
    }
    public JSONResult() {

    }
    public JSONResult(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public  JSONResult(Object data){
        this.status = 200;
        this.msg = "Success";
        this.data = data;
    }

    public Boolean responseData(){
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public  void  setStatus(Integer status){
        this.status = status;
    }

    public  String getMsg(){
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    /**
     *
     * @Description: 将json结果集转化为LeeJSONResult对象
     * 				需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     *
     * @author zhangy_iming
     * @date 2019-8-6 23:48:37
     */
    public static JSONResult formatToPojo(String jsonData, Class<?> clazz){
        try {
            if (clazz == null)
                return MAPPER.readValue(jsonData,JSONResult.class);
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if(clazz != null)
                if ((data.isObject()))
                    obj = MAPPER.readValue(data.traverse(),clazz);
                else if (data.isTextual())
                    obj = MAPPER.readValue(data.asText(),clazz);
            return build(jsonNode.get("status").intValue(),jsonNode.get(("msg")).asText(),obj);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @Description: 没有object对象的转化
     * @param json
     * @return
     *
     * @author zhangy_iming
     * @date 2019-8-6 23:51:07
     */
    public static JSONResult format(String json) {
        try {
            return MAPPER.readValue(json, JSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @Description: Object是集合转化
     * 				需要转换的对象是一个list
     * @param jsonData
     * @param clazz
     * @return
     *
     * @author zhangy_iming
     * @date 2019-8-7 00:17:17
     */
    public static JSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0)
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));

            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


}
