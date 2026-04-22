import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientUDP {
    public static void main(String[] args) throws Exception {
        String serverIP = "100.81.209.100";
        int port = 5001;

        DatagramSocket socket = new DatagramSocket();
        InetAddress adresa = InetAddress.getByName(serverIP);

        for (int i = 1; i <= 100; i++) {
            String mesaj = "Mesaj UDP #" + i;
            byte[] buffer = mesaj.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, adresa, port);
            socket.send(packet);

            System.out.println("[CLIENT] Trimis: " + mesaj);
            Thread.sleep(50);
        }

        socket.close();
        System.out.println("[CLIENT] Terminat.");
    }
}