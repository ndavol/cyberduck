package ch.cyberduck.core.aquaticprime;

import ch.cyberduck.core.AbstractTestCase;
import ch.cyberduck.core.Local;
import ch.cyberduck.core.local.LocalTouchFactory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version $Id$
 */
public class DonationKeyTest extends AbstractTestCase {

    @Test
    public void testVerifyInvalidFile() throws Exception {
        final Local f = new Local(System.getProperty("java.io.tmpdir"), "f.cyberducklicense");
        DonationKey r = new DonationKey(f);
        assertFalse(r.verify());
        LocalTouchFactory.get().touch(f);
        assertFalse(r.verify());
        f.delete();
    }

    @Test
    public void testVerifyFailure() throws Exception {
        final Local f = new Local("test/ch/cyberduck/core/aquaticprime/test.cyberducklicense");
        DonationKey r = new DonationKey(f);
        assertFalse(r.verify());
    }

    @Test
    public void testGetValue() throws Exception {
        final Local f = new Local("test/ch/cyberduck/core/aquaticprime/test.cyberducklicense");
        DonationKey r = new DonationKey(f);
        assertEquals("test@cyberduck.io", r.getValue("Email"));
    }
}