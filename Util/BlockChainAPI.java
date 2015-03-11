package Util;


import org.apache.commons.io.IOUtils;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Double;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Stateless
public class BlockchainApi{

 /**
     *  Generate a Unique payment address for a User to send payment to
     * @param myAddress Your bitcoin address
     * @param callback The callback URL which will be notified when a payment is received
     * @param anonymous Whether the transaction should be anonymous or not
     * @param params Extra parameters to be passed to the callback URL
     * @return
     * @throws Exception
     */
    public static synchronized String generatePaymentAddress(String myAddress, String callback, boolean anonymous, Map<String, String> params) throws Exception {
        String url = ROOT +  "api/receive?method=create&callback="+ URLEncoder.encode(callback, "UTF-8")+"&anonymous="+anonymous+"&address="+myAddress;

        //Append any custom parameters to the callback
        for (Map.Entry<String, String> param : params.entrySet()) {
            url += "&"+param.getKey()+"="+URLEncoder.encode(param.getValue(), "UTF-8");
        }

        String response = fetchURL(url);

        if (response == null)
            throw new Exception("Server Returned NULL Response");

        Map<String, Object> obj = (Map<String, Object>) JSONValue.parse(response);

        if (obj.get("error") != null)
            throw new Exception((String) obj.get("error"));

        return (String)obj.get("input_address");
    }
	////////////
	 private static synchronized String fetchURL(String URL) throws Exception {
        URL url = new URL(URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        connection.setInstanceFollowRedirects(false);

        connection.connect();

        if (connection.getResponseCode() != 200) {
            throw new Exception("Invalid HTTP Response code " + connection.getResponseCode());
        }

        return IOUtils.toString(connection.getInputStream(), "UTF-8");
    }
	
	//Convert an amount in local currency to BTC
    //e.g. convertToBTC("USD", 1) returns the value of 1 U.S dollar in BTC
    public static synchronized double convertToBTC(String countryCode, double amount) throws Exception {
        String response = fetchURL(ROOT +  "tobtc?currency="+countryCode+"&value="+amount);

        if (response != null)
            return Double.valueOf(response);
        else
            return throw new Exception("Unknown Response");
    }
	
	
/////////////// Servlet callBack Handler //////////////////
@WebServlet("/callback_handler")
public class CallbackHandlerServlet extends HttpServlet {
	private static final String ROOT = "https://blockchain.info/";
	private static final String CALLBACK_URL = "https://mydomain.com/callback_handler";
	private static final String MY_BITCOIN_ADDRESS = "1A8JiWcwvpY7tAopUkSnGuEYHmzGYfZPiq";

	@Inject
	private BetDao betDao;
	@Inject
	private TransactionDao transactionDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String value = req.getParameter("value");
		String transaction_hash = req.getParameter("transaction_hash");
		String guid = req.getParameter("guid");

		boolean authorized = false;

		//Chekc the ip address of the request matches blockhain.info
		InetAddress[] ips = InetAddress.getAllByName("blockchain.info");
		for (InetAddress address : ips) {
			if (req.getRemoteAddr().equals(address.getHostAddress())) {
				authorized = true;
				break;
			}
		}

		if (!authorized)
			return;
			
		 TransactionEntity tr = new TransactionEntity();
		 tr.setTransactionHash(transaction_hash);
		 tr.setGuid(guid);
		 tr.setValue(Double.valueOf(value));
		 int  insert = transactionDao.createTransaction(tr);
		
		 if (insert == 1) {
			res.getOutputStream().print("*ok*");
		 }
	 }
		 
	   

}

}