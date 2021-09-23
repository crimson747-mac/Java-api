package geocoding;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class StaticImage {
    public static void main(String[] args) throws Exception {
        String requestUrl = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        String clientID = "";
        String clientSecret = "";
        String address = "서울특별시 강남구 역삼로33길 8";
        String x = "127.0418339";
        String y = "37.4977391";

        String pos = URLEncoder.encode(x + " " + y, "UTF-8");
        requestUrl += "center=" + x + "," + y;
        requestUrl += "&level=16&w=700&h=500";
        requestUrl += "&markers=type:t|size:mid|pos:" + pos + "|label:" + URLEncoder.encode(address, "UTF-8");

        URL u = new URL(requestUrl);

        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientID);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

        int responseCode = conn.getResponseCode();

        BufferedReader br;

        if(responseCode == 200) {
            InputStream is = conn.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];

            String tempname = Long.valueOf(new Date().getTime()).toString();

            File file = new File(tempname + ".jpg");
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            while((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            is.close();
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            System.out.println(response.toString());
        }
    }
}
