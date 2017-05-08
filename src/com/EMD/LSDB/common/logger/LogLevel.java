/*
 * Created on Jun 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.logger;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//class level javadocs
/**
 * <p><Description></p>
 * 
 * @author na25828
 * @version 1.0
 */

/**
 * List of Log Levels
 * INFO - Log any custom error information
 * DEBUG - Log messages used during development (debugging) stage
 * WARNING - Log messages that would altert the user about some minor problems (which would not stop the funtional flow of the applicaiton)
 * ERROR - Log messages indicating that an error has occurred, and it has to be resolved before continuing the current operation
 * FATAL - Log messages indicating that some system related errors has occurred and the applicaiton has stopped processing.  Such kind of error could not resolved by the end-user of the applicaiton
 */
public final class LogLevel implements Comparable, Serializable {
	
	private static int size = 0;
	
	private static int nextOrd = 0;
	
	private static Map nameMap = new HashMap(7);
	
	private static LogLevel first = null;
	
	private static LogLevel last = null;
	
	private final int ord;
	
	private final String label;
	
	private final String display;
	
	private LogLevel prev;
	
	private LogLevel next;
	
	public static final LogLevel INFO = new LogLevel("INFO_VAL", "I", 10);
	
	public static final LogLevel DEBUG = new LogLevel("DEBUG_VAL", "D", 20);
	
	public static final LogLevel WARNING = new LogLevel("WARNING_VAL", "W", 30);
	
	public static final LogLevel ERROR = new LogLevel("ERROR_VAL", "E", 40);
	
	public static final LogLevel FATAL = new LogLevel("FATAL_VAL", "F", 50);
	
	/**
	 * Constructs a new LogLevel with its label.<br>
	 * (Uses default value for ord.)
	 */
	/**
	 * Constructor takes label which is  String as Input and constructs new LogLevel 
	 */
	private LogLevel(String display, String label) {
		this(display, label, nextOrd);
	}
	
	/**
	 * Constructs a new LogLevel with its label and ord value.
	 */
	/**
	 *Constructor takes label and ord which are String and int, constructs new LogLevel .
	 */
	private LogLevel(String display, String label, int ord) {
		this.display = display;
		this.label = label;
		this.ord = ord;
		++size;
		nextOrd = ord + 1;
		nameMap.put(label, this);
		if (first == null)
			first = this;
		if (last != null) {
			this.prev = last;
			last.next = this;
		}
		
		last = this;
	}
	
	/**
	 * Compares two LogLevel objects based on their ordinal values.
	 * Satisfies requirements of interface java.lang.Comparable.
	 */
	/*
	 * This method takes Object as Input and returns int value.
	 */
	public int compareTo(Object obj) {
		return ord - ((LogLevel) obj).ord;
	}
	
	/**
	 * Compares two LogLevel objects for equality.  Returns true
	 * only if the specified LogLevel is equal to this one.
	 */
	/**
	 * Method that takes Object as Input and returns boolean value
	 */
	/*
	 * This method takes Object as Input and returns boolean value.
	 */
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	/**
	 * Returns a hash code value for this LogLevel.
	 */
	/*
	 * This method returns hashcode of this LogLevel.
	 */
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Resolves deserialized LogLevel objects.
	 * @throws InvalidObjectException if deserialization fails.
	 */
	private Object readResolve() throws InvalidObjectException {
		LogLevel l = get(label);
		if (l != null)
			return l;
		else {
			String msg = LoggerConstants.Log_Level;
			throw new InvalidObjectException(msg + label);
		}
	}
	
	/**
	 * Returns LogLevel with the specified label.
	 * Returns null if not found.
	 */
	/** 
	 * This method takes label as input and returns LogLevel Object
	 */
	public static LogLevel get(String label) {
		return (LogLevel) nameMap.get(label);
	}
	
	/**
	 * Returns the label for this LogLevel.
	 */
	/**
	 * This Method Returns label for this  LogLevel 
	 */
	public String toString() {
		return label;
	}
	
	/**
	 * Returns the display value for this ShippingFormStatus.
	 */
	public String display() {
		return display;
	}
	
	/**
	 * Always throws CloneNotSupportedException;  guarantees that
	 * LogLevel objects are never cloned.
	 *
	 * @return (never returns)
	 */
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Returns an iterator over all LogLevel objects in declared order.
	 */
	public static Iterator iterator() {
		// annnonymous inner class
		return new Iterator() {
			private LogLevel current = first;
			
			public boolean hasNext() {
				return current != null;
			}
			
			public Object next() {
				LogLevel l = current;
				current = current.next();
				return l;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Returns the ordinal value of this LogLevel.
	 */
	/**
	 * This Method Returns ordinal of this  LogLevel 
	 */
	public int ord() {
		return this.ord;
	}
	
	/**
	 * Returns the number of declared LogLevel objects.
	 */
	public static int size() {
		return size;
	}
	
	/**
	 * Returns the first declared LogLevel.
	 */
	/**
	 * This Method Returns the first declared LogLevel 
	 */
	public static LogLevel first() {
		return first;
	}
	
	/**
	 * Returns the last declared LogLevel.
	 */
	/**
	 * This Method Returns the last declared LogLevel 
	 */
	public static LogLevel last() {
		return last;
	}
	
	/**
	 * Returns the previous LogLevel before this one in declared order.
	 * Returns null for the first declared LogLevel.
	 */
	/**
	 * This Method Returns the prev LogLevel 
	 */
	public LogLevel prev() {
		return this.prev;
	}
	
	/**
	 * Returns the next LogLevel after this one in declared order.
	 * Returns null for the last declared LogLevel.
	 */
	/**
	 * This Method Returns the next LogLevel 
	 */
	public LogLevel next() {
		return this.next;
	}
}
