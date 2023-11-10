package org.main;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.model.Endereco;

public class CorreiosAPIConsumer {

        public static void main(String[] args) {

            // CEP que você quer consultar
            String cep = "88119428";
            // URL da API dos Correios
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            // Criar um cliente HTTP
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Criar uma requisição GET
                HttpGet request = new HttpGet(url);

                // Enviar a requisição e obter a resposta
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    // Extrair a resposta como uma String JSON
                    String jsonResponse = EntityUtils.toString(response.getEntity());

                    Gson gson = new Gson();
                    Endereco endereco = new Endereco();

                    // Convertendo o Json de string para objeto da classe Endereco
                    endereco = gson.fromJson(jsonResponse, Endereco.class);

                    if (endereco != null) {
                        // Imprimir a resposta
                        System.out.println("Resposta da API dos Correios:");
                        System.out.println(endereco);
                        //System.out.println(jsonResponse);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


