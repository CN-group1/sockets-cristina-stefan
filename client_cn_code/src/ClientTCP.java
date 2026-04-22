import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) {
        String serverIP = "100.81.209.100";
        int port = 1234;

        try {
            Socket socket = new Socket(serverIP, port);
            System.out.println("Conectat la server!");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // THREAD pentru primire mesaje
            Thread receiveThread = new Thread(() -> {
                try {
                    String mesaj;
                    while ((mesaj = reader.readLine()) != null) {
                        System.out.println("\n[Server]: " + mesaj);
                        System.out.print("Tu: ");
                    }
                } catch (IOException e) {
                    System.out.println("Conexiune închisă.");
                }
            });

            receiveThread.start();

            // trimite mesaje continuu
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Tu: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                writer.println(input);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}