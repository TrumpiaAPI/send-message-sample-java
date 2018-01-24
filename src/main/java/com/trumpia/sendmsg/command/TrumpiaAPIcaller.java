package com.trumpia.sendmsg.command;

import com.trumpia.sendmsg.user.UserAccount;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.io.IOException;

public class TrumpiaAPIcaller {

    private static OkHttpClient client = new OkHttpClient();

    /**
     * @param mobileNumber
     * @param message
     * @param user
     * @return
     * @throws IOException
     * @see <a href="https://trumpia.com/api/docs/rest/functions/direct-sms.php">Trumpia API refrence</a>
     */
    public static JSONObject send(String mobileNumber, String message, UserAccount user) throws IOException {

        final String requestUrl = "http://api.trumpia.com/rest/v1/" + user.getUserId() + "/sms";

        JSONObject payload = new JSONObject();
        payload.put("mobile_number", mobileNumber);
        payload.put("message", message);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, payload.toString());

        Request request = new Request.Builder()
                .url(requestUrl)
                .put(body)
                .addHeader("x-apikey", user.getApikey())
                .addHeader("content-type", "application/json")
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        if (responseBody == null)
            return null;

        return new JSONObject(responseBody.string());
    }

    /**
     * @param requestId
     * @param user
     * @return
     * @throws IOException
     * @see <a href="https://trumpia.com/api/docs/rest/functions/verification.php">Trumpia API refrence</a>
     */
    public static JSONObject report(String requestId, UserAccount user) throws IOException {

        final String requestUrl = "http://api.trumpia.com/rest/v1/" + user.getUserId() + "/report/" + requestId;

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .addHeader("x-apikey", user.getApikey())
                .addHeader("content-type", "application/json")
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        if (responseBody == null)
            return null;

        return new JSONObject(responseBody.string());
    }
}	
