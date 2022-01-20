package com.swempire.web.condition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * GET, POST HTTP 통신 util
 * 헤더값이 여러개 일 경우 get, post 메소드의 header 인자값으로 배열로 넘겨주면된다. 헤더의 key,value 구분은 공백으로 지정한다.<br/>
 * new String[]{"Content-Type:application/json","Authorization:key=AAAAHscIsBQ:"}<br/>
 * 서버와의 연결 타임아웃은 5초로 지정되어 있다.
 * </pre>
 * @since 2019.11.27
 * @author kimil
 * @version 1.0
 * 
 * <pre>
 * << 개정이력 >>
 * 
 * 		수정일					수정자											수정내용
 * ----------------		---------------------			--------------------------------------------------------
 * 	  2019.11.27				김효일					최초작성
 * 
 * </pre>
 */
public class Curl {
	
	public static void main(String[] args) {
		
	Curl curl = new Curl();
	System.out.println(curl.get("https://login.daegu.ac.kr/stonepass/sp/v1/fido/facets", null));
		
	}
	
	private final static Logger logger = LoggerFactory.getLogger(Curl.class);
	
	private final static int TIMEOUT = 1000 * 5;	// 연결 타임아웃(5초)
	
	/**
	 * get 통신
	 * @param strUrl: 통신할 서버 주소
	 * @param headers: 헤더 설정
	 * @return 서버에서 응답으로 넘겨준 데이터
	 */
	///*
	public String get(String strUrl, String[] headers) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		int retryCount = 3;
		String ret = "";
		for ( int idx = retryCount; idx > 0; idx-- ) {
			
			ret = connGet(strUrl, headers);
			if ( !"IOEXCEPTION".equals(ret) ) {
				break;
			}
			
		}
		
		return ret;
		
	}

	/**
	 * get 통신
	 * @param strUrl: 통신할 서버 주소
	 * @param headers: 헤더 설정
	 * @return 서버에서 응답으로 넘겨준 데이터
	 */
	public static String connGet(String strUrl, String[] headers) {
		
		
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		String ret = "";
		
		HttpURLConnection hurlc = null;
		BufferedReader br = null;
		
		try {
			
			// 연결 설정
			hurlc = getHttpUrlConnection(strUrl);
			hurlc.setRequestMethod("GET");
			hurlc.setDoInput(true);
			hurlc.setDoOutput(true);
			hurlc.setConnectTimeout(TIMEOUT);
			
			// 헤더 설정
			if ( headers != null ) {
				for ( String s : headers ) {
					String[] split = s.split(" ");
					hurlc.setRequestProperty(split[0], split[1]);
				}
			}
			
			logger.info("[Get] Url::: " + strUrl);
			logger.info("[Get] Method::: " + hurlc.getRequestMethod());
			logger.info("[Get] Conn Timeout::: " + hurlc.getConnectTimeout());
			logger.info("[Get] Read Timeout::: " + hurlc.getReadTimeout());
			logger.info("[Get] Headers::: " + hurlc.getRequestProperties().toString());
			
			// 서버 연결
			hurlc.connect();
			
			int responseCode = hurlc.getResponseCode();
			String responseMessage = hurlc.getResponseMessage();
			
			logger.info("[Get] Response Code::: " + responseCode);
			logger.info("[Get] Response Message::: " + responseMessage);
			
			if ( responseCode == HttpURLConnection.HTTP_OK ) {
				
				StringBuffer sb = new StringBuffer();
				br = new BufferedReader( new InputStreamReader(hurlc.getInputStream(), "UTF-8") );
				String line = null;
				
				while ( (line = br.readLine()) != null ) {
					sb.append(line);
				}
				
				ret = sb.toString();
				
			}
			
			logger.info("[Get] Response::: " + ret);
			
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( br != null ) try { br.close(); }catch(IOException e) {}
			if ( hurlc != null ) hurlc.disconnect();
		}
		
		return ret;
		
	}
	
	/**
	 * post 통신
	 * @param strUrl: 통신할 서버 주소
	 * @param headers: 헤더 설정
	 * @param data: 서버전송 데이터
	 * @return 서버에서 응답으로 넘겨준 데이터
	 */
	///*
	public static String post(String strUrl, String[] headers, String data) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		int retryCount = 3;
		String ret = "";
		for ( int idx = retryCount; idx > 0; idx-- ) {
			
			ret = connPost(strUrl, headers, data);
			if ( !"IOEXCEPTION".equals(ret) ) {
				break;
			}
			
		}
		
		return ret;
		
	}
	//*/
	
		/**
		 * post 통신
		 * @param strUrl: 통신할 서버 주소
		 * @param headers: 헤더 설정
		 * @param data: 서버전송 데이터
		 * @return 서버에서 응답으로 넘겨준 데이터
		 */
		///*
	public static String connPost(String strUrl, String[] headers, String data) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		String ret = "";
		
		HttpURLConnection hurlc = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			
			// 연결 설정
			hurlc = getHttpUrlConnection(strUrl);
			hurlc.setRequestMethod("POST");
			hurlc.setDoInput(true);
			hurlc.setDoOutput(true);
			hurlc.setConnectTimeout(TIMEOUT);
			hurlc.setReadTimeout(TIMEOUT);
			
			// 헤더 설정
			if ( headers != null ) {
				for ( String s : headers ) {
					String[] split = s.split(" ");
					hurlc.setRequestProperty(split[0], split[1]);
				}
			}
			
			logger.info("[Post] Url::: " + strUrl);
			logger.info("[Post] Method::: " + hurlc.getRequestMethod());
			logger.info("[Post] Conn Timeout::: " + hurlc.getConnectTimeout());
			logger.info("[Post] Read Timeout::: " + hurlc.getReadTimeout());
			logger.info("[Post] Headers::: " + hurlc.getRequestProperties().toString());
			logger.info("[Post] Request::: " + data);
			
			// 데이터 설정
			bw = new BufferedWriter( new OutputStreamWriter(hurlc.getOutputStream()) );
			bw.write(data);
			bw.flush();
			bw.close();
			
			// 서버 연결
			hurlc.connect();
			
			int responseCode = hurlc.getResponseCode();
			String responseMessage = hurlc.getResponseMessage();
			
			logger.info("[Post] Response Code::: " + responseCode);
			logger.info("[Post] Response Message::: " + responseMessage);
			
			if ( responseCode == HttpURLConnection.HTTP_OK ) {
				
				StringBuffer sb = new StringBuffer();
				br = new BufferedReader( new InputStreamReader(hurlc.getInputStream(), "UTF-8") );
				String line = null;
				
				while ( (line = br.readLine()) != null ) {
					sb.append(line);
				}
				
				ret = sb.toString();
				
			}
			
			logger.info("[Post] Response::: " + ret);
			
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return "IOEXCEPTION";
		} finally {
			if ( bw != null ) try { bw.close(); }catch(IOException e) {}
			if ( br != null ) try { br.close(); }catch(IOException e) {}
			if ( hurlc != null ) hurlc.disconnect();
		}
		
		return ret;
		
	}
	//*/
	
		/**
		 * delete 통신
		 * @param strUrl: 통신할 서버 주소
		 * @param headers: 헤더 설정
		 * @return 서버에서 응답으로 넘겨준 데이터
		 */
	public static String delete(String strUrl, String[] headers, String data) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		String ret = "";
		
		HttpURLConnection hurlc = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			
			// 연결 설정
			hurlc = getHttpUrlConnection(strUrl);
			hurlc.setRequestMethod("DELETE");
			hurlc.setDoInput(true);
			hurlc.setDoOutput(true);
			hurlc.setConnectTimeout(TIMEOUT);
			
			// 헤더 설정
			if ( headers != null ) {
				for ( String s : headers ) {
					String[] split = s.split(" ");
					hurlc.setRequestProperty(split[0], split[1]);
				}
			}
			
			logger.info("[Delete] Url::: " + strUrl);
			logger.info("[Delete] Method::: " + hurlc.getRequestMethod());
			logger.info("[Delete] Conn Timeout::: " + hurlc.getConnectTimeout());
			logger.info("[Delete] Read Timeout::: " + hurlc.getReadTimeout());
			logger.info("[Delete] Headers::: " + hurlc.getRequestProperties().toString());
			
			// 데이터 설정
			bw = new BufferedWriter( new OutputStreamWriter(hurlc.getOutputStream()) );
			bw.write(data);
			bw.flush();
			bw.close();
			
			// 서버 연결
			hurlc.connect();
			
			int responseCode = hurlc.getResponseCode();
			String responseMessage = hurlc.getResponseMessage();
			
			logger.debug("[Delete] Response Code::: " + responseCode);
			logger.debug("[Delete] Response Message::: " + responseMessage);
			
			if ( responseCode == HttpURLConnection.HTTP_OK ) {
				
				StringBuffer sb = new StringBuffer();
				br = new BufferedReader( new InputStreamReader(hurlc.getInputStream(), "UTF-8") );
				String line = null;
				
				while ( (line = br.readLine()) != null ) {
					sb.append(line);
				}
				
				ret = sb.toString();
				
			}
			
			logger.info("[Delete] Response::: " + ret);
			
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( bw != null ) try { bw.close(); }catch(IOException e) {}
			if ( br != null ) try { br.close(); }catch(IOException e) {}
			if ( hurlc != null ) hurlc.disconnect();
		}
		
		return ret;
		
	}
	
	private static HttpURLConnection getHttpUrlConnection(String strUrl) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		if ( strUrl.toLowerCase().startsWith("https") ) {
			return getHttpsUrlConnection(strUrl);
		}
		
		HttpURLConnection hurlc = null;
		
		try {
			
			URL url = new URL(strUrl);
			hurlc = (HttpURLConnection) url.openConnection();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return hurlc;
		
	}
	
	private static HttpsURLConnection getHttpsUrlConnection(String strUrl) {
		
		if ( strUrl == null ) {
			throw new NullPointerException("url is null");
		}
		
		HttpsURLConnection hurlc = null;
		
		/*
		final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        } };
        */
		
		try {
			
			//final SSLContext sslContext = SSLContext.getInstance("SSL");
			//sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			
			URL url = new URL(strUrl);
			hurlc = (HttpsURLConnection) url.openConnection();
			/*
			hurlc.setSSLSocketFactory(sslContext.getSocketFactory());
			hurlc.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
				
			});
			*/
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return hurlc;
		
	}
}

