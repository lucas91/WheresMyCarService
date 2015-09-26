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
           String s = "http://smartparking.somee.com/wcf/MobileService.svc/json/ListarAndares";

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

                    AndaresDTO[] produto1 = gson.fromJson(br, AndaresDTO[].class);

                    for (AndaresDTO andaresDTO : produto1) {
						
                    	System.out.println("ID: " + andaresDTO.getId());
                    	System.out.println("Nome: " + andaresDTO.getNome());
                    	System.out.println("Vagas Livres: " + andaresDTO.getQtdLivre() );
                    	System.out.println("Vagas Totais: " + andaresDTO.getQtdVagas());
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