package com.nvrs.test.first

@Grapes([
	@Grab(group='com.google.cloud', module='libraries-bom', version='26.1.0', type='pom'),
	@Grab(group='com.google.cloud', module='google-cloud-secretmanager', version='2.3.1')])

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

import java.io.IOException;
import java.util.zip.CRC32C;
import java.util.zip.Checksum;

import com.google.protobuf.ByteString;

class FirstGroovy {
	@NonCPS
	static void main(def args){
		String projectId = args[0] //  "Project ID"
		String secretId = args[1]  //  "Secret ID"
		String versionId = args[2] //  "Secret Version ID"

		accessSecretVersion(projectId, secretId, versionId)
	}

	// Access the payload for the given secret version if one exists. The version
	// can be a version number as a string (e.g. "5") or an alias (e.g. "latest").
	@NonCPS
	//public static void accessSecretVersion(String projectId, String secretId, String versionId)  throws IOException {
	def accessSecretVersion(String projectId, String secretId, String versionId)  throws IOException {	
		SecretManagerServiceClient client = SecretManagerServiceClient.create()

		if(client !=null) {
			SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

			AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);

			byte[] data = response.getPayload().getData().toByteArray();
			Checksum checksum = new CRC32C();
			checksum.update(data, 0, data.length);
			if (response.getPayload().getDataCrc32C() != checksum.getValue()) {
				System.out.printf("Data corruption detected.");
				return;
			}

			// Print the secret payload.
			// WARNING: Do not print the secret in a production environment - this
			// snippet is showing how to access the secret material.
			String payload = response.getPayload().getData().toStringUtf8();
			System.out.printf("Plaintext: %s\n", payload);
		}
	}
}
return this

