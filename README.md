After doing mvn clean install: 

To START the GCP VM instance, run the jar like shown below:

   nohup java -jar gcp-instance-start-stop-1.0-SNAPSHOT-gcp-fat.jar start &

To STOP the GCP VM instance, run the jar like shown below:

   nohup java -jar gcp-instance-start-stop-1.0-SNAPSHOT-gcp-fat.jar stop &


 Note: Update your Project Name, Zone & Instance Name in the config.properties file that is put in resources/conf folder


