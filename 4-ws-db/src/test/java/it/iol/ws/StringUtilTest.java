package it.iol.ws;

import it.iol.ws.util.StringUtil;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void empty() {
        assert (StringUtil.isBlank(null));
        assert (StringUtil.isBlank(""));
        assert (StringUtil.isBlank("\n"));
        assert (StringUtil.isBlank("\r"));
        assert (StringUtil.isBlank("\t"));
        assert (StringUtil.isBlank(" "));
        assert (StringUtil.isBlank("\t \n \r"));


        assert (!StringUtil.isBlank("\t \n \r hi"));
        assert (!StringUtil.isBlank("bob"));
        assert (!StringUtil.isBlank("  bob  "));
    }
}
