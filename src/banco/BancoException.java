package banco;

public class BancoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BancoException(String msg) {
		super(msg);
	}
}
