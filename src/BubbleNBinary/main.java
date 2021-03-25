package BubbleNBinary;
import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("For just sorting input 1, for just search input 2, for sorting then searching input 3");
        int selection = input.nextInt();

        if (selection == 1 ) {
            sortIPs();
        }else if(selection==2 ){
            searchIPs();
        }else if(selection==3){
            sortIPs();
            searchIPs();
        }

    }
    public static void sortIPs() throws IOException{
        try {
            File file = new File("/sorted.csv");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                file.delete();
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        FileWriter fw = new FileWriter("sorted.csv");

        File addresses = new File("IP-COUNTRY-REGION-CITY-SHUFFLED.csv");

        Scanner scnr = new Scanner(addresses);



        System.out.println("Creating unsorted array");

        IPAddress[] ipAddresses = new IPAddress[100000];

        int i = 0;

        System.out.println("Importing to array");

        while (scnr.hasNextLine() && i < 100000) {
            String scanned = scnr.nextLine();
            String[] arr = new String[6];
            String[] arr1 = scanned.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            for (int j = 0; j < arr1.length; j++) {
                arr[j] = arr1[j].replaceAll("^\"|\"$", "");
            }

            ipAddresses[i] = new IPAddress(Long.parseLong(arr[0]), Long.parseLong(arr[1]), arr[2], arr[3], arr[4], arr[5]);

            i++;
        }
        System.out.println("Import done");

        System.out.println("Sorting");
        long start = System.currentTimeMillis();
        BubbleSort.sort(ipAddresses);
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("-----------------");
        System.out.println("Writing to file");
        for (i = 0; i < ipAddresses.length; i++) {
            fw.write(ipAddresses[i].toString() + "\n");
        }
        fw.close();
    }
    public static void searchIPs() throws IOException{

        Scanner sorted = new Scanner(new File("sorted.csv"));

        Scanner target=new Scanner(System.in);
        System.out.println("Enter target ip");

        String IP = target.nextLine();

        Integer [] octet = new Integer[4];
        String[] octetString=IP.split("\\.");
        for(int z=0;z<4;z++){
            octet[z]= Integer.parseInt(octetString[z]);
        }

        long IPnumber;
        IPnumber=(16777216L *octet[0]) + (65536L *octet[1])+(256L *octet[2])+octet[3];

        System.out.println("Loading sorted array");
        int n =0;
        IPAddress[] sortedIpAddresses= new IPAddress[100000];

        while (sorted.hasNextLine() && n < 100000) {
            String scanned1 = sorted.nextLine();
            String[] arr2 = new String[6];
            String[] arr3 = scanned1.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            for (int j = 0; j < arr3.length; j++) {
                arr2[j] = arr3[j].replaceAll("^\"|\"$", "");
            }

            sortedIpAddresses[n] = new IPAddress(Long.parseLong(arr2[0]), Long.parseLong(arr2[1]), arr2[2], arr2[3], arr2[4], arr2[5]);

            n++;
        }

        System.out.println("Searching for IP");


        int index=BinarySearch.search(sortedIpAddresses,IPnumber);

        System.out.println(sortedIpAddresses[index]);



    }
}
