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
 * <p>holds the list of error types</p>
 *
 * @author mk25942
 * @version 1.0
 */

/**
 * List of Error Types
 * APPLICATION - If the error is related to application / buiness process.
 * DATABASE - If the error is related to failure in database operation
 * SYSTEM - If the error is related to system failure / unexpected behaviour of the system
 */
public final class ErrorType implements Comparable, Serializable {
	
	private static int m_size = 0;
	
	private static int m_nextOrd = 0;
	
	private static Map m_nameMap = new HashMap(5);
	
	private static ErrorType m_first = null;
	
	private static ErrorType m_last = null;
	
	private final int m_ord;
	
	private final String m_label;
	
	private final String m_display;
	
	private ErrorType m_prev;
	
	private ErrorType m_next;
	
	public static final ErrorType APPLICATION = new ErrorType("APPLICATION",
			"A", 10);
	
	public static final ErrorType DATABASE = new ErrorType("DATABASE", "D", 20);
	
	public static final ErrorType SYSTEM = new ErrorType("SYSTEM", "S", 30);
	
	/**
	 * Constructs a new ErrorType with its label and display.
	 * (Uses default value for ord.)
	 */
	private ErrorType(String p_display, String p_label) {
		this(p_display, p_label, m_nextOrd);
	}
	
	/**
	 * Constructs a new ErrorType with its label,display and ord value.
	 */
	private ErrorType(String p_display, String p_label, int p_ord) {
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
	 * Compares two ErrorType objects based on their ordinal values.
	 * Satisfies requirements of interface java.lang.Comparable.
	 */
	public int compareTo(Object p_obj) {
		return m_ord - ((ErrorType) p_obj).m_ord;
	}
	
	/**
	 * Compares two ErrorType objects for equality.  Returns true
	 * only if the specified ErrorType is equal to this one.
	 */
	public boolean equals(Object p_obj) {
		return super.equals(p_obj);
	}
	
	/**
	 * Returns a hash code value for this ErrorType.
	 */
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Resolves deserialized ErrorType objects.
	 * @throws InvalidObjectException if deserialization fails.
	 */
	private Object readResolve() throws InvalidObjectException {
		ErrorType e = get(m_label);
		if (e != null)
			return e;
		else {
			String msg = ErrorConstants.ERR_TYPE;
			throw new InvalidObjectException(msg + m_label);
		}
	}
	
	/**
	 * Returns ErrorType with the specified label.
	 * Returns null if not found.
	 */
	public static ErrorType get(String p_label) {
		return (ErrorType) m_nameMap.get(p_label);
	}
	
	/**
	 * Returns the label for this ErrorType.
	 */
	public String toString() {
		return m_label;
	}
	
	/**
	 * Returns the display value for this ErrorType.
	 */
	public String display() {
		return m_display;
	}
	
	/**
	 * Always throws CloneNotSupportedException;  guarantees that
	 * ErrorType objects are never cloned.
	 * @return (never returns)
	 */
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Returns an iterator over all ErrorType objects in declared order.
	 */
	public static Iterator iterator() {
		//annnonymous inner class
		return new Iterator() {
			private ErrorType current = m_first;
			
			public boolean hasNext() {
				return current != null;
			}
			
			public Object next() {
				ErrorType e = current;
				current = current.next();
				return e;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Returns the ordinal value of this ErrorType.
	 */
	public int ord() {
		return this.m_ord;
	}
	
	/**
	 * Returns the number of declared ErrorType objects.
	 */
	public static int size() {
		return m_size;
	}
	
	/**
	 * Returns the first declared ErrorType.
	 */
	public static ErrorType first() {
		return m_first;
	}
	
	/**
	 * Returns the last declared ErrorType.
	 */
	public static ErrorType last() {
		return m_last;
	}
	
	/**
	 * Returns the previous ErrorType before this one in declared order.
	 * Returns null for the first declared ErrorType.
	 */
	public ErrorType prev() {
		return this.m_prev;
	}
	
	/**
	 * Returns the next ErrorType after this one in declared order.
	 * Returns null for the last declared ErrorType.
	 */
	public ErrorType next() {
		return this.m_next;
	}
}
