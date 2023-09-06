
// --== CS400 Project Three File Header ==--
// Name: Avery Crane
// CSL Username: crane
// Email: adcrane@wisc.edu
// Lecture #:  004 @4:00pm
// Notes to Grader: N/A
import java.util.List;

public interface ILengthGraph<T> extends GraphADT<T> {
	/**
	 * Returns a list of sorted vertices.
	 * 
	 * @return a list of sorted vertices.
	 */
	public List<LengthGraph<T>.Path> returnAll();
}
