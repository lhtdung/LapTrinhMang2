import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientDung {
    public final static String SERVER_IP = "192.168.1.54";
    public final static int SERVER_PORT = 7;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // coonect to sever
            System.out.println("Connected :" + socket);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            System.out.print("so phan tu: ");
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("phan tu 1: " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0 ;i <n ;i++){
                stringBuilder.append(arr[i] + " ");
            }
            os.writeUTF("" + stringBuilder);
            String response = is.readUTF();
            System.out.println("From server: " + response);
            os.close();
            is.close();
        } catch (IOException e) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}