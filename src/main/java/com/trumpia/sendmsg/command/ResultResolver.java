package com.trumpia.sendmsg.command;

import com.trumpia.sendmsg.user.UserAccount;
import org.json.JSONObject;

import java.io.IOException;

public class ResultResolver {

    private UserAccount user;
    private String      requestId;

    public ResultResolver(String requestId, UserAccount user) {

        super();
        this.user = user;
        this.requestId = requestId;
    }

    public String getRequestId() {

        return requestId;
    }

    public void setRequestId(String requestId) {

        this.requestId = requestId;
    }

    /*
     * call report method and get status code.
     * return result message according to stauts code.
     *
     * if resultMessage has status field and value is "sent" then it means successfully done
     * MRME1054 : Too many characters
     * MRME0551 : Mobile number is blocked
     * MRME1251 : Message failed - Alphanumeric and the following characters are supported: \\@\\!\\#\\$\\%\\&\\(\\)\\*\\+\\,\\-\\.\\?\\/\\:\\;\\<\\=\\>\\'\\\"\"
     * MRME4001 : Processing
     * */
    public String getResultMessage() throws IOException, InterruptedException {

        String resultMessage;
        JSONObject reportResponse = TrumpiaAPIcaller.report(requestId, user);

        if (reportResponse == null) {
            return "Response error";
        }

        String statusCode = (String) reportResponse.remove("status_code");

        //Request is on progress, so sleep for 1 sec
        if (statusCode != null && statusCode.equals("MPCE4001")) {
            Thread.sleep(1000);
            return getResultMessage();
        } else if (reportResponse.has("status") && reportResponse.getString("status").equals("sent")) {
            resultMessage = "success";
        } else if (statusCode == null) {
            resultMessage = "Message error - " + reportResponse.toString();
        } else if (statusCode.equals("MRME1054")) {
            resultMessage = "[" + statusCode + "]" + " Too many characters";
        } else if (statusCode.equals("MRME0551")) {
            resultMessage = "[" + statusCode + "]" + " Mobile number is blocked";
        } else if (statusCode.equals("MRME1251")) {
            resultMessage = "[" + statusCode + "]" + " Message failed - Alphanumeric and the following characters are supported: \\@\\!\\#\\$\\%\\&\\(\\)\\*\\+\\,\\-\\.\\?\\/\\:\\;\\<\\=\\>\\'\\\"\"";
        } else {
            resultMessage = "[" + statusCode + "]" + " Message error - " + reportResponse.toString();
        }

        return resultMessage;
    }

}
