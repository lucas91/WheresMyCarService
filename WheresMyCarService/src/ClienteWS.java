import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


    public class ClienteWS {

        public static void main ( String [] args ) {
           String s = "http://www.smartparking.somee.com/wcf/SensorService.svc/json/ListarVagasParaSinalizar";

            try {
                BufferedReader br;
                URL url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                InputStream in = conn.getInputStream();

                int responseCode = conn.getResponseCode();
                //aceita os status de 200 a 299
                if (responseCode / 100 != 2) {
                    System.out.println("RESPONSE CODE:" + responseCode);
                    return;
                }

                br = new BufferedReader(new InputStreamReader(in));
                try {

                    Gson gson = new Gson();

                    VagasDTO[] produto1 = gson.fromJson(br, VagasDTO[].class);

                    for (VagasDTO vagasDTO : produto1) {
						
                    	System.out.println("Deficiente: " + vagasDTO.getVagaDeficiente());
                    	System.out.println("Endereço do Sensor: " + vagasDTO.getId());
                    	System.out.println("Id do Carro: " + vagasDTO.getIdOcupante());
                    	System.out.println("Situação: " + vagasDTO.getSituacao());
                    	System.out.println("                                            ");
					}

                } finally {
                    conn.disconnect();
                    br.close();
                    in.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace(); //fixme
            } catch (IOException e) {
                e.printStackTrace(); //fixme
            }
        }
}
