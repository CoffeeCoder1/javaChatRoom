/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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

// Import a few libraries
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        
        // Print error if not all arguments are supplied [default hostname and port coming soon]
        if (args.length != 2) {
            System.err.println(
                "Usage: java Client <host name> <port number>");
            System.exit(1);
        }
        
        // Print status message
        System.out.println("Connecting to server " + args[0] + " using port " + args[1] + "...");

        // Save arguments to variables
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        // Try statement
        try (
            // Create Socket
            Socket echoSocket = new Socket(hostName, portNumber);
            
            // Create PrintWriter
            PrintWriter out = new PrintWriter(
              echoSocket.getOutputStream(),
              true
            );
            
            // Create BufferedReader for connection to server
            BufferedReader in = new BufferedReader(
              new InputStreamReader(echoSocket.getInputStream())
            );
            
            // Create BufferedReader for local input
            BufferedReader stdIn = new BufferedReader(
              new InputStreamReader(System.in)
            );
        ) {
            // Print status message
            System.out.println("Connected to server");
            
            // Use input from user
            String inputLine;
            while ((inputLine = stdIn.readLine()) != null) {
              // Use Switch to add commands like CLS
              switch(inputLine){
                case "cls":
                  // Clear screen
                  System.out.println("CLS not implemented yet, please contat the developer to give them an idea of how many people actually need this. Currenly may work on Windows though.");
                default:
                  // Default command just sends output to server
                  out.println(inputLine);
              }
            }
        } catch (UnknownHostException e) {
            // Catch UnknownHostException
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            // Catch IOException
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } 
    }
}