package com.gcp;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.Operation;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

    public class StartStopInstance {
        public static void main(String args[]) throws IOException, GeneralSecurityException {
            // Project ID for this request.
            String project = Constants.PROJECT; // TODO: Update placeholder value.

            // The name of the zone for this request.
            String zone = Constants.ZONE; // TODO: Update placeholder value.

            // Name of the instance resource to start.
            String instance = Constants.INSTANCE; // TODO: Update placeholder value.

            Compute computeService = createComputeService();

            if (args[0].equalsIgnoreCase("start")) {
                Compute.Instances.Start startRequest = computeService.instances().start(project, zone, instance);
                Operation response = startRequest.execute();
                // TODO: Change code below to process the `response` object:
                System.out.println(response);

            }
            else if (args[0].equalsIgnoreCase("stop")) {
                Compute.Instances.Stop stopRequest = computeService.instances().stop(project, zone, instance);
                Operation response = stopRequest.execute();
                // TODO: Change code below to process the `response` object:
                System.out.println(response);
            }
        }

        public static Compute createComputeService() throws IOException, GeneralSecurityException {
          //  HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            GoogleCredential credential = GoogleCredential.getApplicationDefault(HTTP_TRANSPORT, jsonFactory);
            if (credential.createScopedRequired()) {
                credential =
                        credential.createScoped(Arrays.asList(DriveScopes.DRIVE));
            }

          //  HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
            return new Compute.Builder(HTTP_TRANSPORT, jsonFactory, credential)
                    .setApplicationName("Google-ComputeSample/0.1")
                    .build();
         /*   return new Compute.Builder(httpTransport, jsonFactory, credential)
                    .setApplicationName("Google-ComputeSample/0.1")
                    .build(); */
        }
    }

