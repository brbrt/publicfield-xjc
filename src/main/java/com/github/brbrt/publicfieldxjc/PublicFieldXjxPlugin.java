package com.github.brbrt.publicfieldxjc;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.Outline;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class PublicFieldXjxPlugin extends Plugin {

    @Override
    public String getOptionName() {
        return "publicfield";
    }

    @Override
    public String getUsage() {
        return "-publicfield";
    }

    @Override
    public boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException {
        return true;
    }

}
