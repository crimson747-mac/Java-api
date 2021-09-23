package geocoding;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Extract {
    public static void main(String[] args) {
        String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String clientID = "";
        String clientSecret = "";

        //System.in: 바이트 스트림
        //InputStreamReader: 브릿지
        //BufferedReader: 문자 스트림
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("주소를 입력하세요");
            String address = io.readLine();
            String addr = URLEncoder.encode(address, "UTF-8");
            String requestUrl = apiUrl + addr;

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientID);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            BufferedReader br;

            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader((new InputStreamReader(conn.getErrorStream())));
            }

            String line;

            StringBuffer response = new StringBuffer();
            while((line=br.readLine()) != null) {
                response.append(line);
            }
            
            br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object.toString(2));

            JSONArray arr = object.getJSONArray("addresses");
            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = (JSONObject) arr.get(0);
                System.out.println("address: " + obj.get("roadAddress"));
                System.out.println("jibun: " + obj.get("jibunAddress"));
                System.out.println("x: " + obj.get("x"));
                System.out.println("y: " + obj.get("y"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
