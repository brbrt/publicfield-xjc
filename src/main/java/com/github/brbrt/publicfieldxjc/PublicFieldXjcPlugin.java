package com.github.brbrt.publicfieldxjc;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PublicFieldXjcPlugin extends Plugin {

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
        for (ClassOutline clazz : outline.getClasses()) {
            JDefinedClass implClass = clazz.implClass;

            removeGettersSetters(implClass);
            makePropertiesPublic(implClass);
        }

        return true;
    }

    private void removeGettersSetters(JDefinedClass clazz) {
        Collection<JMethod> methods = clazz.methods();
        Iterator<JMethod> it = methods.iterator();

        while (it.hasNext()) {
            JMethod method = it.next();
            if (isGetterSetterMethod(method)) {
                it.remove();
            }
        }
    }

    private boolean isGetterSetterMethod(JMethod method) {
        final List<String> prefixes = ImmutableList.of("get", "is", "set");
        final String methodName = method.name();

        return Iterables.any(prefixes, new Predicate<String>() {
            @Override
            public boolean apply(String prefix) {
                return methodName.startsWith(prefix);
            }
        });
    }

    private void makePropertiesPublic(JDefinedClass clazz) {
        for (JFieldVar field : clazz.fields().values()) {
            field.mods().setPublic();
        }
    }

}
