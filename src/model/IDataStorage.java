package model;

import java.util.ArrayList;

/**
 *
 */
public interface IDataStorage {
    /**
     * Updates memory to persistent data. Returns false on error
     * @param users
     * @return boolean Error
     */
    boolean write(ArrayList<UserDTO> users);
    //TODO lav noget exception throwing handling i stedet for boolean return

    /**
     * Reads data input to memory
     * @return ArrayList<UserDTO>
     */
    ArrayList<UserDTO> read();
    
  	public class DALException extends Exception {
  		
  		private static final long serialVersionUID = 7355418246336739229L;

  		public DALException(String msg, Throwable e) {
  			super(msg,e);
  		}

  		public DALException(String msg) {
  			super(msg);
  		}
  	}
}