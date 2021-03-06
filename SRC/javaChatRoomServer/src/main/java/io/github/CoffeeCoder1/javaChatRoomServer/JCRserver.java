/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

// Import some libraries
import java.net.*;
import java.io.*;

public class JCRserver {
  public static void main(String[] args) throws IOException {
    // Create Commands Array
    String[] commandsTOrun = {"cmd", "/c runServer.bat"};
    
    // Defaults
    if (args.length < 1) {
      System.out.println("Using default port");
      args[0] = "8022";
    }
    else if (args.length == 1) {
      ProcessBuilder builder = new ProcessBuilder(commandsTOrun);
      builder.start();
    }
    
    // Create port number variable
    int portNumber = Integer.parseInt(args[0]);
    
    // Print status message
    System.out.println("Waiting for client...");
    
    // Try statement
    try (
      // Create serverSocket
      ServerSocket serverSocket = new ServerSocket(
        Integer.parseInt(args[0])
      );
      
      // Create clientSocket
      Socket clientSocket = serverSocket.accept();     
      PrintWriter out = new PrintWriter(
        clientSocket.getOutputStream(), true
      );           

      // Create client BufferedReader
      BufferedReader in = new BufferedReader(
        new InputStreamReader(
          clientSocket.getInputStream()
        )
      );
    ) {
      // Print status messages
      System.out.println("Connected to client...");
      System.out.println("Waiting for input from other programs...");
      out.println("Connected!");
      
      // Create messages array
      String[] messages;
      
      String inputLine;
      // Wait for client input, then add it to a String[] and print it to a prompt
      while((inputLine = in.readLine()) != null){
        //messages[messages.length] = inputLine;
        System.out.println(inputLine);
      }
    } catch (IOException e) {
      ProcessBuilder builder = new ProcessBuilder(commandsTOrun);
      System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
      System.out.println("Restarting program...");
      Process p = builder.start();
      System.out.println(e.getMessage());
    }
  }
  
  //public static void sendMSG(String message){
    //out.println(message);
  //}
  
  //public static String readMSG(){
    //return in.readLine();
  //}
}
