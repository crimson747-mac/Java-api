package json.jsonObj;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JsonB {
    public static void main(String[] args) throws FileNotFoundException {
        JSONObject student = new JSONObject();
        student.put("name", "홍길동");
        student.put("phone", "010-1111-1111");
        student.put("address", "서울");
        System.out.println(student);

        String src = "/Users/zeros/IdeaProjects/java-api/src/main/resources/json/text.json";
        InputStream is = new FileInputStream(src);

        if(is == null) {
            throw new NullPointerException("Cannot find resource file");
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONObject obj = new JSONObject(tokener);
        JSONArray students = obj.getJSONArray("students");
        students.forEach( it -> System.out.println("student = " + it));
    }
}
