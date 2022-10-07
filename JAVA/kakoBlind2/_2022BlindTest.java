package kakoBlind2;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;


public class _2022BlindTest {

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String DATA = "test data";
    static String x_auth_token = "0c5765eb7e69f40af2179ac6f39e1d26";
    static String auth_key;
    static int problem;
    static String plus = "/user_info";

    public static void main(String[] args) throws IOException {


        String BASE_URL = " https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";


        URL url = new URL(BASE_URL+"/waiting_line");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(GET);
        connection.setRequestProperty("Authorization", x_auth_token);
        connection.setRequestProperty("Content-Type","application/json");

//        httpGetConnection(BASE_URL,plus);


        httpPostConnection(BASE_URL, "/start");
        httpGetConnection(BASE_URL+"/locations","");
//        httpGetConnection(BASE_URL+"/game_result","");
    }



    public static void httpGetConnection(String UrlData, String ParamData) {

        //http 요청 시 url 주소와 파라미터 데이터를 결합하기 위한 변수 선언
        String totalUrl = "";
        if(ParamData != null && ParamData.length() > 0 &&
                !ParamData.equals("") && !ParamData.contains("null")) { //파라미터 값이 널값이 아닌지 확인
            totalUrl = UrlData.trim().toString() + "?" + ParamData.trim().toString();
        }
        else {
            totalUrl = UrlData.trim().toString();
        }

        //http 통신을 하기위한 객체 선언 실시
        URL url = null;
        HttpURLConnection conn = null;

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";

        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", auth_key);
            conn.setRequestMethod("GET");

            int responseCode1 = conn.getResponseCode();
            System.out.println(responseCode1);

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"GET");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+UrlData);
            System.out.println("http 요청 데이터 : "+ParamData);
            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(responseCode1);
            System.out.println("http 응답 코드 : "+responseCode);
            System.out.println("http 응답 데이터 : "+returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void httpPostConnection(String UrlData, String ParamData) throws IOException {

        URL url = new URL(UrlData+ParamData+"?problem=2");
        //호출할url
        System.out.println(url);
        Map<String, Object> params = new LinkedHashMap<>();//파라미터 세팅
        params.put("problem", "1");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        System.out.println(postData);

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("X-Auth-Token", x_auth_token);

        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);//POST호출
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine;
        String result="";


        while ((inputLine = in.readLine()) != null)  //response 출력
            result += inputLine;
        in.close();

        System.out.println(result);

        JSONObject Object = new JSONObject(result);

        System.out.println(Object);
        auth_key = (String)Object.get("auth_key");
        problem = (Integer) Object.get("problem");
        System.out.println(auth_key);
        System.out.println(problem);

    }

//    JSONObject jObject = new JSONObject(jsonString);
//    // 배열을 가져옵니다.
//    JSONArray jArray = jObject.getJSONArray("posts");
}//클래스 종료

