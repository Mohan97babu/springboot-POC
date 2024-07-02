package com.school.test.Exception;

public class InvalidUsernameException extends RuntimeException{
   public InvalidUsernameException(String message) {
	   super(message);
   }
}
