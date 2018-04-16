package io.github.oscarmaestre.jxmltool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import oracle.xml.xquery.OXQEntity;
import oracle.xml.xquery.OXQEntityKind;
import oracle.xml.xquery.OXQEntityLocator;
import oracle.xml.xquery.OXQEntityResolver;
import oracle.xml.xquery.OXQEntityResolverRequestOptions;

public class ResolutorEntidades extends OXQEntityResolver {
        @Override
        public OXQEntity resolveEntity(OXQEntityKind kind, OXQEntityLocator locator,
                OXQEntityResolverRequestOptions options) throws IOException {
            if (kind == OXQEntityKind.DOCUMENT) {
                URI systemId = locator.getSystemIdAsURI();
                if ("file".equals(systemId.getScheme())) {
                    File file = new File(systemId);
                    FileInputStream input = new FileInputStream(file);
                    OXQEntity result = new OXQEntity(input);
                    result.enlistCloseable(input);
                    return result;
                }
            }
            return null;
        }
    }