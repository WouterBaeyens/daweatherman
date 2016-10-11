package be.ucll.ww.weatherman.domain.model;

public class FailedAccessException extends Exception {
	public FailedAccessException(Throwable e) {
		super(e);
	}

	public FailedAccessException() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
