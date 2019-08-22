package top.takefly.Jpa.uitls;

import java.io.*;

/**
 * @program: Jpa_Hibernate
 * @description: 克隆实体类
 * @author: 戴灵飞
 * @create: 2019-08-20 20:36
 **/
public class CloneUtil {

    public static <T extends Serializable> T clone(T srcObj) throws Exception{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
        objectOutputStream.writeObject(srcObj);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object targetObj = objectInputStream.readObject();
        return (T) targetObj;
    }
}
