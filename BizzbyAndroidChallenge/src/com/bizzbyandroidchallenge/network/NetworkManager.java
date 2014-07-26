package com.bizzbyandroidchallenge.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

import com.bizzbyandroidchallenge.data.model.CurrencyCode;
import com.bizzbyandroidchallenge.view.BuildConfig;

/**
 * @author Abhi This class does all network operation. It fetches the content &
 *         parses the data
 */
public class NetworkManager {

	/** singleton */
	private static NetworkManager instance;

	/** Network time out */
	private final static int CONNECTION_TIMEOUT = 30000;

	/** tag for debug */
	private final static String TAG = NetworkManager.class.getSimpleName();

	/** End Point : URL for data with App_ID appended */
	private final static String CURRENCY_URL = "http://openexchangerates.org/api/currencies.json?app_id=2fa9ed264d5d4c409aa73617e11379d4";

	/** Singleton : private CTOR */
	private NetworkManager() {
	}

	public static NetworkManager getNetworkManagerInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}

	private DefaultHttpClient getClient() {
		DefaultHttpClient client = null;
		try {
			client = openHttpChannel();
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {

				Log.d(TAG, "Exception:", e);
			}
		}
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,
				CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

		return client;

	}

	/**
	 * creates DefaultHttpClient with basic properties
	 * 
	 * @return DefaultHttpClient
	 * @throws Exception
	 */
	private static DefaultHttpClient openHttpChannel() throws Exception {

		try {

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_0);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			return new DefaultHttpClient(params);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not establish the DefaultHttpClient");
		}
	}

	/**
	 * @return String : Response in JSON format
	 * @throws IOException
	 */
	public String fetchCurrencies() throws IOException {

		DefaultHttpClient client = getClient();

		HttpGet request = new HttpGet(CURRENCY_URL);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = client.execute(request);

		int statusCode = response.getStatusLine().getStatusCode();
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "statusCode : " + statusCode);
		}

		String responseData = EntityUtils.toString(response.getEntity());
		return responseData;

	}

	/**
	 * This method parses the JSON response into List of currency Codes.
	 * It uses Jackson APIs for parsing the content. 
	 * On exception empty list is returned. 
	 * @param json
	 * @return List : for CurrencyCode
	 */
	@SuppressWarnings("unchecked")
	public List<CurrencyCode> parseJSONtoCurrencyCodeList(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = null;
		List<CurrencyCode> currencycodeList = new ArrayList<CurrencyCode>();
		try {
			map = mapper.readValue(json, Map.class);

			if (BuildConfig.DEBUG) {

				for (Map.Entry<String, String> entry : map.entrySet()) {

					CurrencyCode currencyCode = new CurrencyCode();
					Log.d(TAG, "Country :" + entry.getKey() + "Value :"
							+ String.valueOf(entry.getValue()));

					currencyCode.setCurrencycodeSF(entry.getKey());
					currencyCode.setCurrencyCodeLF(String.valueOf(entry
							.getValue()));

					currencycodeList.add(currencyCode);

				}
			}
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return currencycodeList;

	}

}
