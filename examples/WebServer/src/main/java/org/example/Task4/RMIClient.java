package Task4;

import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);
            for(int i = 0; i<100; i++) {
                int num = service.getMessage();
                System.out.println("Received: " + num);
                service.storeCalculatedMessage(calculatePrimes(num));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static int calculatePrimes(int num) {
        int counterOfPrimes = 0;
        for (int i = 1; i < num; i++) {
            if (isPrime(i)) {
                counterOfPrimes++;
            }
        }
        return counterOfPrimes;
    }
    public static boolean isPrime(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        return !flag;
    }
}