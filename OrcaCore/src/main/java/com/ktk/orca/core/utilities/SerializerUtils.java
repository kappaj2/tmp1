package com.ktk.orca.core.utilities;

import java.io.*;
import java.util.Base64;

public class SerializerUtils {

    private SerializerUtils() {
    }

    /**
     * Serialize an Object into a String to save to DB for later use.
     *
     * @param obj
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> String serializeReportObjectToString(T obj) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        return Base64.getEncoder().encodeToString(bos.toByteArray());

    }

    /**
     * Deserialize string back to Object using Generic type.
     *
     * @param objectString
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deserializeToObject(String objectString) throws IOException, ClassNotFoundException {

        byte[] byteArr = Base64.getDecoder().decode(objectString);
        ByteArrayInputStream bai = new ByteArrayInputStream(byteArr);
        ObjectInputStream ois = new ObjectInputStream(bai);
        return (T) ois.readObject();

    }
}
