package cl.rs.project.bice.lab.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import cl.rs.project.bice.lab.model.BiceLabModelSalida;

public class BiceLabService {

	public List<BiceLabModelSalida>obtenerTodosLosDatos(){
		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
//		if(IniProperties.inicializador()) {
//			urlAux = IniProperties.getPropiedad("prop.url");
//		}
		String url = "http://www.indecon.online/last";
//    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<BiceLabModelSalida> lista = new ArrayList<BiceLabModelSalida>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			JSONObject jo = new JSONObject(result);
			for(int i = 0; i<jo.names().length(); i++){
			    JSONObject jObj2 = jo.getJSONObject(jo.names().getString(i));
			    BiceLabModelSalida modelSalida = new BiceLabModelSalida();
			    String fecha = String.valueOf(jObj2.get("date"));
			    Long convertirALong = Long.valueOf(fecha);
			    
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Date fechaDate = new Date(convertirALong * 1000);
			    String fechaFormato = dateFormat.format(fechaDate);
			    modelSalida.setFecha(fechaFormato);
			    modelSalida.setUnidadMedida(jObj2.getString("unit"));
			    modelSalida.setNombre(jObj2.getString("name"));
			    modelSalida.setValor(String.valueOf(jObj2.getDouble("value")));
			    modelSalida.setNombreUnidad(jObj2.getString("key"));
			    lista.add(modelSalida);
			    
			}			
		} catch (IOException e) {
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getMessage());
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getCause());
		}
//		logger.info("Tamano de la lista: "+lista.size());
		return lista;
	}
	
	public List<BiceLabModelSalida>obtenerSoloValor(String valor){
		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
//		if(IniProperties.inicializador()) {
//			urlAux = IniProperties.getPropiedad("prop.url");
//		}
		String url = "https://www.indecon.online/values/"+valor;
//    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<BiceLabModelSalida> lista = new ArrayList<BiceLabModelSalida>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			JSONObject jo = new JSONObject(result);
			JSONObject jObj2 = new JSONObject(String.valueOf(jo.get("values")));
		    for(int i = 0; i<jObj2.names().length(); i++){
//		    	JSONObject jObj3 = jObj2.getJSONObject(jObj2.names().getString(i));
		    	BiceLabModelSalida modelSalida = new BiceLabModelSalida();
			    modelSalida.setUnidadMedida(jo.getString("unit"));
			    modelSalida.setNombre(jo.getString("name"));
			    modelSalida.setNombreUnidad(jo.getString("key"));
		    	String fecha = String.valueOf(jObj2.names().getString(i));
		    	Long convertirALong = Long.valueOf(fecha);
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Date fechaDate = new Date(convertirALong * 1000);
			    String fechaFormato = dateFormat.format(fechaDate);
			    modelSalida.setFecha(fechaFormato);
			    modelSalida.setValor(String.valueOf(jObj2.get(jObj2.names().getString(i))));
			    lista.add(modelSalida);
		    }
//		    JSONArray msg = jo.getJSONArray("values");
//		    for(int i=0; i< msg.length(); i++) {
//		    	JSONObject jObj = msg.getJSONObject(i);
//		    	System.out.println(jObj.get(jObj.names().getString(i)));
//		    }
//		    modelSalida.setValor(String.valueOf(jo.getDouble("value")));
			
//			for(int i = 0; i<jo.names().length(); i++){
//			    JSONObject jObj2 = jo.getJSONObject(jo.names().getString(i));
//			    BiceLabModelSalida modelSalida = new BiceLabModelSalida();
//			    String fecha = String.valueOf(jObj2.get("date"));
//			    Long convertirALong = Long.valueOf(fecha);
//			    
//			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//			    Date fechaDate = new Date(convertirALong * 1000);
//			    String fechaFormato = dateFormat.format(fechaDate);
//			    modelSalida.setFecha(fechaFormato);
//			    modelSalida.setUnidadMedida(jObj2.getString("unit"));
//			    modelSalida.setNombre(jObj2.getString("name"));
//			    modelSalida.setValor(String.valueOf(jObj2.getDouble("value")));
//			    modelSalida.setNombreUnidad(jObj2.getString("key"));
//			    lista.add(modelSalida);
			    
//			}			
		} catch (IOException e) {
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getMessage());
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getCause());
		}
//		logger.info("Tamano de la lista: "+lista.size());
		return lista;
	}
	
	public List<BiceLabModelSalida>obtenerValorFecha(String valor, String fecha){
		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
//		if(IniProperties.inicializador()) {
//			urlAux = IniProperties.getPropiedad("prop.url");
//		}
		String url = "https://www.indecon.online/date/"+valor+"/"+fecha;
//    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<BiceLabModelSalida> lista = new ArrayList<BiceLabModelSalida>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			JSONObject jo = new JSONObject(result);
		    BiceLabModelSalida modelSalida = new BiceLabModelSalida();
		    String fechaAux = String.valueOf(jo.get("date"));
		    Long convertirALong = Long.valueOf(fechaAux);
			    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Date fechaDate = new Date(convertirALong * 1000);
		    String fechaFormato = dateFormat.format(fechaDate);
		    modelSalida.setFecha(fechaFormato);
		    modelSalida.setUnidadMedida(jo.getString("unit"));
		    modelSalida.setNombre(jo.getString("name"));
		    modelSalida.setValor(String.valueOf(jo.getDouble("value")));
		    modelSalida.setNombreUnidad(jo.getString("key"));
		    lista.add(modelSalida);
			    
//			}			
		} catch (IOException e) {
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getMessage());
//			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getCause());
		}
//		logger.info("Tamano de la lista: "+lista.size());
		return lista;
	}
}
