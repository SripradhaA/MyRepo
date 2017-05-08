/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.PasswordVO;

public interface ChangePwdBI {
	public int updatePwd(PasswordVO objPasswordVO) throws EMDException,
	Exception;
}
