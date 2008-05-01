package ch.cyberduck.core.ssl;

/*
 *  Copyright (c) 2008 David Kocher. All rights reserved.
 *  http://cyberduck.ch/
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Bug fixes, suggestions and comments should be sent to:
 *  dkocher@cyberduck.ch
 */

import org.apache.log4j.Logger;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @version $Id:$
 */
public abstract class AbstractX509TrustManager implements X509TrustManager {
    protected static Logger log = Logger.getLogger(AbstractX509TrustManager.class);

    /**
     * A set of all X509 certificates accepted by the user that contains
     * no duplicate elements
     */
    private Set acceptedCertificates
            = new HashSet();

    protected void acceptCertificate(final X509Certificate[] certs) {
        if(log.isDebugEnabled()) {
            for(int i = 0; i < certs.length; i++) {
                log.debug("Certificate trusted:" + certs[i].toString());
            }
        }
        acceptedCertificates.addAll(Arrays.asList(certs));
    }

    protected void acceptCertificate(final X509Certificate cert) {
        if(log.isDebugEnabled()) {
            log.debug("Certificate trusted:" + cert.toString());
        }
        acceptedCertificates.add(cert);
    }

    /**
     * @return All accepted certificates
     */
    public X509Certificate[] getAcceptedIssuers() {
        return (X509Certificate[]) this.acceptedCertificates.toArray(
                new X509Certificate[this.acceptedCertificates.size()]);
    }
}
