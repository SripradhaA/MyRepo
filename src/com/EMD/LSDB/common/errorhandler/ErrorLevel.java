/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.errorhandler;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//import statements
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//class level javadocs
/**
 * <p>Holds the list of error levels</p>
 * 
 * @author mk25942
 * @version 1.0
 */

/**
 * List of Error Levels
 * WARNING - Error has occurred, but still the application can proceed processing
 * ERROR - Error has occurred, and it needs to be communicated to the user 
 *         (application cannot proceed further processing until the error is resolved)
 * FATAL - An unexpected error (like system related error) has occurred.  
 *         The user might not be in a position to resolve the error. 
 */
public final class ErrorLevel implements Comparable, Serializable {
	
	private static int m_size = 0;
	
	private static int m_nextOrd = 0;
	
	private static Map m_nameMap = new HashMap(5);
	
	private static ErrorLevel m_first = null;
	
	private static ErrorLevel m_last = null;
	
	private final int m_ord;
	
	private final String m_label;
	
	private final String m_display;
	
	private ErrorLevel m_prev;
	
	private ErrorLevel m_next;
	
	public static final ErrorLevel WARNING = new ErrorLevel("WARNING", "W", 10);
	
	public static final ErrorLevel ERROR = new ErrorLevel("ERROR", "E", 20);
	
	public static final ErrorLevel FATAL = new ErrorLevel("FATAL", "F", 30);
	
	/**
	 * Constructs a new ErrorLevel with its label and display.
	 * (Uses default value for ord.)
	 */
	private ErrorLevel(String p_display, String p_label) {
		this(p_display, p_label, m_nextOrd);
	}
	
	/**
	 * Constructs a new ErrorLevel with its label,display and ord value.
	 */
	private ErrorLevel(String p_display, String p_label, int p_ord) {
		this.m_display = p_display;
		this.m_label = p_label;
		this.m_ord = p_ord;
		++m_size;
		m_nextOrd = m_ord + 1;
		m_nameMap.put(m_label, this);
		if (m_first == null)
			m_first = this;
		if (m_last != null) {
			this.m_prev = m_last;
			m_last.m_next = this;
		}
		m_last = this;
	}
	
	/**
	 * Compares two ErrorLevel objects based on their ordinal values.
	 * Satisfies requirements of interface java.lang.Comparable.
	 */
	public int compareTo(Object p_obj) {
		return m_ord - ((ErrorLevel) p_obj).m_ord;
	}
	
	/**
	 * Compares two ErrorLevel objects for equality.  Returns true
	 * only if the specified ErrorLevel is equal to this one.
	 */
	public boolean equals(Object p_obj) {
		return super.equals(p_obj);
	}
	
	/**
	 * Returns a hash code value for this ErrorLevel.
	 */
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Resolves deserialized ErrorLevel objects.
	 * @throws InvalidObjectException if deserialization fails.
	 */
	private Object readResolve() throws InvalidObjectException {
		ErrorLevel e = get(m_label);
		if (e != null)
			return e;
		else {
			String msg = ErrorConstants.ERR_LVL;
			throw new InvalidObjectException(msg + m_label);
		}
	}
	
	/**
	 * Returns ErrorLevel with the specified label.
	 * Returns null if not found.
	 */
	public static ErrorLevel get(String p_label) {
		return (ErrorLevel) m_nameMap.get(p_label);
	}
	
	/**
	 * Returns the label for this ErrorLevel.
	 */
	public String toString() {
		return m_label;
	}
	
	/**
	 * Returns the display value for this ErrorLevel.
	 */
	public String display() {
		return m_display;
	}
	
	/**
	 * Always throws CloneNotSupportedException;  guarantees that
	 * ErrorLevel objects are never cloned.
	 *
	 * @return (never returns)
	 */
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Returns an iterator over all ErrorLevel objects in declared order.
	 */
	public static Iterator iterator() {
		// annnonymous inner class
		return new Iterator() {
			private ErrorLevel current = m_first;
			
			public boolean hasNext() {
				return current != null;
			}
			
			public Object next() {
				ErrorLevel e = current;
				current = current.next();
				return e;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Returns the ordinal value of this ErrorLevel.
	 */
	public int ord() {
		return this.m_ord;
	}
	
	/**
	 * Returns the number of declared ErrorLevel objects.
	 */
	public static int size() {
		return m_size;
	}
	
	/**
	 * Returns the first declared ErrorLevel.
	 */
	public static ErrorLevel first() {
		return m_first;
	}
	
	/**
	 * Returns the last declared ErrorLevel.
	 */
	public static ErrorLevel last() {
		return m_last;
	}
	
	/**
	 * Returns the previous ErrorLevel before this one in declared order.
	 * Returns null for the first declared ErrorLevel.
	 */
	public ErrorLevel prev() {
		return this.m_prev;
	}
	
	/**
	 * Returns the next ErrorLevel after this one in declared order.
	 * Returns null for the last declared ErrorLevel.
	 */
	public ErrorLevel next() {
		return this.m_next;
	}
	
}
