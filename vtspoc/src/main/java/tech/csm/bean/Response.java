package tech.csm.bean;



import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String message;
    private Object data;

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
