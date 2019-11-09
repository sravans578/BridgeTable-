package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// Reading the given text file
        // /Users/sravan/Documents/BridgeFDB.txt
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter file path ");
//        String file_path = scanner.next();
        File file = new File("/Users/sravan/Documents/BridgeFDB.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Hashmap for storing the table

        HashMap<String, String> BridgeTable = new HashMap<String, String>();
        int flag =0;
        String line;
        while ((line = br.readLine()) != null){
            BridgeTable.put(line,br.readLine());

        }


        // Reading the incoming frames
        String Source_mac ="";
        String Dest_mac="";
        String Arrival_port="";
        String filepath = " ";
        String Decision ="";
        File file_incoming = new File("/Users/sravan/Documents/network/RandomFrames.txt");
        BufferedReader b = new BufferedReader(new FileReader(file_incoming));
        //File for writing output
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("/Users/sravan/Documents/BridgeFDBOutput.txt"));

        while ((Source_mac = b.readLine()) != null){
            Dest_mac = b.readLine();
            Arrival_port = b.readLine();

//            System.out.println(Source_mac);
//            System.out.println(Dest_mac);
//            System.out.println(Arrival_port);
//
//            System.out.println("--------");


             //Check if source mac is there on the table or not and update if it is not there

            if(!BridgeTable.containsKey(Source_mac)){

                BridgeTable.put(Source_mac,Arrival_port);
                System.out.println("Found new one!!");
            }
            // Updating existing entries
            else if(Arrival_port.compareTo(BridgeTable.get(Source_mac))!=0) {

                BridgeTable.put(Source_mac,Arrival_port);
                System.out.println("Updated entry");

            }

            if(BridgeTable.containsKey(Dest_mac)){

                if(Arrival_port.compareTo(BridgeTable.get(Dest_mac))!=0){

                    Decision = "Forwarded on port" + BridgeTable.get(Dest_mac);
                }

                else{
                    Decision = "Discarded";
                }

            }

            else {

               Decision = "Broadcast";
            }

            String  output = Source_mac + " :: "+ Dest_mac+ " :: " + Arrival_port + " :: " +Decision + "\n";
            System.out.println(output);
            writer1.write(output);



        }
        writer1.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/sravan/Documents/BridgeFDBUpdated.txt"));

        // Writing updated Bridgetable to a file

        BridgeTable.forEach((k, v) -> {
            try {
                String temp = k + " : " + v +"\n";
                System.out.println(temp);
                writer.write(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();



    }

}

