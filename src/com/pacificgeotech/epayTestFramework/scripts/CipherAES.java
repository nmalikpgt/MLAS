package com.pacificgeotech.epayTestFramework.scripts;


import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
	
	
	/**
	 * Demonstrate use of CipherOutputStream and CipherInputStream to encipher and
	 * decipher a message. <p/> This particular version uses AES/CBC/PKCS5Padding
	 * but it fairly easy to convert it to use other algorithms. Requires a shared
	 * secret key.
	 */
	public class CipherAES {
		// ------------------------------ CONSTANTS ------------------------------

		/**
		 * random, not particularly secret, but stable bytes to begin the chained
		 * AES-CBC with - don't change this value.
		 */
		static final byte[] IV = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a,
				0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		static final int PW_HASH_ITERATIONS = 50;
		static final int KEY_SIZE = 128;

		/**
		 * configure with encryption algorithm to use.
		 */
		private static final String ALGORITHM = "AES";

		/**
		 * configure with block mode to use.
		 */
		private static final String BLOCK_MODE = "CBC";

		/**
		 * configure with padding method to use
		 */
		private static final String PADDING = "PKCS5Padding";

		/**
		 * the encoding to use when converting bytes <--> String
		 */
		private static final Charset CHARSET = Charset.forName("UTF-8");

		// -------------------------- STATIC METHODS --------------------------

		public static byte[] hexStringToByteArray(String s) {
			byte[] r = new byte[s.length() / 2];
			for (int i = 0; i < s.length(); i += 2) {
				r[i / 2] = (byte) Integer.parseInt(s.substring(i, 2 + i), 16);
			}
			return r;
		}

		public static String byteArrayToHexString(byte[] b) {
			StringBuffer sb1 = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				if (((int) b[i] & 0xff) < 0x10)
					sb1.append("0");
				sb1.append(Long.toString((int) b[i] & 0xff, 16));
			}
			return sb1.toString();
		}

		/**
		 * generate a password based AES style Key
		 *
		 * @param encryptionKey
		 *            use standard strong password guidelines for this value; at
		 *            least 8 characters and keep the value private!
		 * @param salt
		 *            must be at least BLOCKSIZE (16) characters, but no greater
		 *            than twice the BLOCKSIZE - but doesn't need to be very private
		 * @return SecretKeySpec the AES key generated.
		 * @throws NoSuchAlgorithmException
		 *             if AES is not supported.
		 * @throws InvalidKeyException
		 */
		private static SecretKeySpec generateKey(String encryptionKey, String salt)
				throws NoSuchAlgorithmException, InvalidKeyException {

			if (salt == null || salt.length() < 8)
				throw new IllegalArgumentException("Encrypting salt must be at least 8 characters");
			if (encryptionKey == null || encryptionKey.length() < 8)
				throw new IllegalArgumentException("Encrypting secret key must be at least 8 characters");

			// clean up the input, clear spaces
			salt = salt.trim().replace(' ', '_');
			encryptionKey = encryptionKey.trim().replace(' ', '_');
			// too long salt doesn't benefit encryption (just slows it down).
			if (salt.length() > 32)
				salt = salt.substring(0, 32);
			byte[] keyBytes = PBKDF2.deriveKey(encryptionKey.getBytes(CHARSET), salt.getBytes(CHARSET),
					PW_HASH_ITERATIONS, KEY_SIZE / 8);
			//out.println("key.byteArrayToHexString: " + byteArrayToHexString(keyBytes));
			return new SecretKeySpec(keyBytes, ALGORITHM);
		}
	
		/**
		 * read an enciphered value and retrieve its plaintext message.
		 * 
		 * @param cipher
		 *            method used to encrypt the file
		 * @param key
		 *            secret key used to encrypt the file
		 * @param inputBase64Encrypted
		 *            the encrypted and base64 encoded ciphered value.
		 * 
		 * @return the reconstituted decrypted message.
		 * @throws PidCryptoException
		 */
		private static String readCiphered(Cipher cipher, SecretKeySpec key, String inputBase64Encrypted)
				throws PidCryptoException {

			try {
				IvParameterSpec CBC_SALT = new IvParameterSpec(IV);
				cipher.init(Cipher.DECRYPT_MODE, key, CBC_SALT);

				byte[] binaryEncrypted = Base64.getDecoder().decode(inputBase64Encrypted);

				// create a temporary buffer to decode into (binaryEncrypted include
				// padding)
				byte[] buf = new byte[cipher.getOutputSize(binaryEncrypted.length)];
				int len = cipher.update(binaryEncrypted, 0, binaryEncrypted.length, buf, 0);
				len += cipher.doFinal(buf, len);
				// remove padding
				byte[] out = new byte[len];
				System.arraycopy(buf, 0, out, 0, len);
				// return string representation of decoded bytes
				return new String(out, "UTF-8");
			} catch (Throwable e) {
				throw new PidCryptoException(e);
			}
		}

		/**
		 * write a plaintext message to a byte array enciphered
		 * 
		 * @param cipher
		 *            the method to use to encrypt the file.
		 * @param key
		 *            the secret key to use to encrypt the file.
		 * @param plainText
		 *            the plaintext of the message to write.
		 * @return byte[] encrypted binary
		 */
		private static byte[] writeCiphered(Cipher cipher, SecretKeySpec key, String plainText)
				throws PidCryptoException {

			try {

				IvParameterSpec CBC_SALT = new IvParameterSpec(IV);
				cipher.init(Cipher.ENCRYPT_MODE, key, CBC_SALT);
				final byte[] plainTextBytes = plainText.getBytes(CHARSET);

				// create a temporary buffer to decode into (it'll include padding)
				byte[] buf = new byte[cipher.getOutputSize(plainTextBytes.length)];
				int len;
				len = cipher.update(plainTextBytes, 0, plainTextBytes.length, buf, 0);
				len += cipher.doFinal(buf, len);
				// remove padding
				byte[] out = new byte[len];
				System.arraycopy(buf, 0, out, 0, len);
				return out;
			} catch (Throwable e) {
				throw new PidCryptoException(e);
			}

		}

		// --------------------------- public api methods----------------------
		/**
		 * Generate an AES key using the Password-based-key-derivation-function-2
		 * (PBKDF2) from the provided encryption key and salt value. Using AES-CBC
		 * cipher, encrypt the plain text and return a base-64 encoded version of
		 * the encrypted bytes.
		 * 
		 * @param plainText
		 * @param encryptionKey
		 * @param encryptionSalt
		 * @return String base64 encoded string containing the encrypted byte array.
		 * @throws PidCryptoException
		 */
		/*
		public static String encrypt(String plainText, String encryptionKey, String encryptionSalt)
				throws PidCryptoException {
			try {
				SecretKeySpec key = generateKey(encryptionKey, encryptionSalt);
				final Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + BLOCK_MODE + "/" + PADDING);

				// write out the ciphered message
				byte[] binaryEncrypted = writeCiphered(cipher, key, plainText);
				return Base64.getEncoder().encodeToString(binaryEncrypted);
				//return Base64.encode(binaryEncrypted);

			} catch (NoSuchAlgorithmException nsa) {
				throw new PidCryptoException("No such algorithm - check your providers", nsa);
			} catch (Throwable t) {
				throw new PidCryptoException(t);
			}
		}
		*/

		/**
		 * @param encryptedBase64Encoded
		 *            base64 encoded encrypted value.
		 * @param encryptionKey
		 *            the original password used to encrypt the data.
		 * @param encryptionSalt
		 *            the original password used to encrypt the data.
		 * @return String the original plain text string.
		 * @throws PidCryptoException
		 */
		public static String decrypt(String encryptedBase64Encoded, String encryptionKey, String encryptionSalt)
				throws PidCryptoException {
			try {
				SecretKeySpec key = generateKey(encryptionKey, encryptionSalt);
				final Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + BLOCK_MODE + "/" + PADDING);

				// write out the ciphered message
				return readCiphered(cipher, key, encryptedBase64Encoded);

			} catch (NoSuchAlgorithmException nsa) {
				throw new PidCryptoException("No such algorithm - check your providers", nsa);
			} catch (Throwable t) {
				throw new PidCryptoException(t);
			}
		}
	
		// -------------- example main method --------------------
		/**
		 * Demonstrate use of CipherOutputStream and CipherInputStream to encipher
		 * and decipher a message.
		 * 
		 * @param args
		 *            not used
		 * 
		 * @throws NoSuchAlgorithmException
		 *             if AES is not supported
		 * @throws NoSuchPaddingException
		 *             if PKCS5 padding is not supported.
		 * @throws InvalidKeyException
		 *             if there is something wrong with the key.
		 * @throws IOException
		 *             if there are problems reading or writing the file.
		 * @throws java.security.InvalidAlgorithmParameterException
		 *             if problems with CBC_SALT.
		 */
		/*
		public static void main(String[] args) throws InvalidAlgorithmParameterException, InvalidKeyException,
				IOException, NoSuchAlgorithmException, NoSuchPaddingException {

			// The secret message we want to encrypt - i.e. the account id.

			final String plainText = "accountId123";

			// create a secret encryption key password; longer is better
			String encryptionKey = "create a password";
			// use at least 24 characters to salt your encryption! no more than 32 chars, 
			// no spaces, they will be converted to underscore.
			String encryptionSalt = "create a salt";

			try {
				out.println("plainText: " + plainText + "(" + plainText.length() + ")");
				
				String encryptedAccountID = CipherAES.encrypt(plainText, encryptionKey, encryptionSalt);
				
				System.out.println(" The ENCRYTION result for text:"+plainText +
						" is:"+ encryptedAccountID);
				
				String accountId = CipherAES.decrypt(encryptedAccountID, encryptionKey, encryptionSalt);
				
				System.out.println(" The DECRYPTION result:"+accountId );

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		*/
	}

