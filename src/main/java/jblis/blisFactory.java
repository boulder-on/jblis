package jblis;

import jblis.oapi.blis_oapi;
import jblis.tapi.blis_tapi;
import jpassport.PassportFactory;

public class blisFactory {

    private static blis_tapi blis_tapi = null;
    private static blis_oapi blis_oapi = null;
    private static final String lib_name = "libblis.so";

    public static synchronized blis_tapi getBlisTAPI()
    {
        if (blis_tapi == null)
        {
            try {
                blis_tapi = PassportFactory.link(lib_name, blis_tapi.class);
            }
            catch (Throwable th)
            {
                th.printStackTrace();
                throw new RuntimeException(th);
            }
        }

        return blis_tapi;
    }

    public static synchronized blis_oapi getBlisOAPI()
    {
        if (blis_oapi == null)
        {
            try {
                blis_oapi = PassportFactory.link(lib_name, blis_oapi.class);
            }
            catch (Throwable th)
            {
                th.printStackTrace();
                throw new RuntimeException(th);
            }
        }

        return blis_oapi;
    }
}
