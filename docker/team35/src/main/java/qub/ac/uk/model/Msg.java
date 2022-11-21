package qub.ac.uk.model;
import lombok.Data;
@Data
public class Msg<T> {
    private Integer code;//状态码
    private String msg;//信息
    private T data;//数据
    public static <T> Msg<T> success(T object){
        Msg<T> r = new Msg<>();
        r.data = object;
        r.code = 1;
        return r;
    }
    public static <T> Msg<T> error(String message){
        Msg r = new Msg();
        r.msg=message;
        r.code = 0;
        return r;
    }
}
