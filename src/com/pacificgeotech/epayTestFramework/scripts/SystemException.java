package com.pacificgeotech.epayTestFramework.scripts;

	
	/**
	 * Exception which will carry a system message. 
	 *
	 * @author  Cristian Cerb, Pacific GeoTech Systems Ltd
	 * @created Mar 30, 2011
	 * @version 1.0.0
	 */
	public class SystemException extends RuntimeException {

		private static final long serialVersionUID = -5307205523383341080L;

		public SystemException(String message, Throwable cause) {
			super(message, cause);
			
		}

		public SystemException(String message) {		
			super(message);
		}

	}

