package com.nuvio.tv.core.analytics

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DiagnosticsConsentPolicyTest {

    /**
     * Privacy fork: an untouched install reports NOTHING until the user explicitly opts in.
     * Upstream defaults to on (see their v1.4.19 measurement); this default is deliberately
     * reversed so no diagnostics, crashes, or playback events leave the device by default.
     */
    @Test
    fun `an untouched install reports nothing by default`() {
        assertFalse(resolveDiagnosticsEnabled(stored = null))
    }

    /** An explicit no is a decision, and survives the change of default. */
    @Test
    fun `an explicit opt-out is preserved`() {
        assertFalse(resolveDiagnosticsEnabled(stored = false))
    }

    @Test
    fun `an explicit opt-in is preserved`() {
        assertTrue(resolveDiagnosticsEnabled(stored = true))
    }

    /** Only an absent value may be defaulted; a stored value is authoritative either way. */
    @Test
    fun `a stored value always wins over the default`() {
        assertEquals(false, resolveDiagnosticsEnabled(stored = false))
        assertEquals(true, resolveDiagnosticsEnabled(stored = true))
    }
}
