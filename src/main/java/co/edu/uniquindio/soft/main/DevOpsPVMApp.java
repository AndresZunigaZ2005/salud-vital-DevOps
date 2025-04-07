package co.edu.uniquindio.soft.main;

import co.edu.uniquindio.soft.model.CitaService;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class DevOpsPVMApp {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        CitaService service = new CitaService();

        server.createContext("/", exchange -> {
            String response = "📅 " + service.agendarCita("Andrés", "2025-04-03") +
                    "\n🧪 " + service.consultarResultado("123") +
                    "\n🔔 " + service.enviarAlertaSalud("Recuerda tu cita mañana a las 9 AM");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        server.start();
        System.out.println("Servidor escuchando en puerto " + port);
    }
}
