package com.nuvio.tv.core.analytics

/**
 * Whether this device reports diagnostics, given what the user has previously chosen.
 *
 * This drives PostHog's opt-out, so it governs every event the app sends — crashes, ANRs, screens,
 * playback, abnormal exits — not only the crash reports the setting is named after.
 *
 * This fork ships privacy-first: NOTHING leaves the device unless the user explicitly opts in.
 * Upstream defaults to on (they burned themselves shipping it off blind in v1.4.19); that product
 * decision is deliberately reversed here. Users can still turn reporting on in settings — a stored
 * value always wins, so upstream's default logic stays intact.
 */
fun resolveDiagnosticsEnabled(stored: Boolean?): Boolean = stored ?: DIAGNOSTICS_DEFAULT_ENABLED

const val DIAGNOSTICS_DEFAULT_ENABLED = false
