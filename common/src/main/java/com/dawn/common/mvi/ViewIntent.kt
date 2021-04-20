package com.dawn.common.mvi


/**
 * MVI(Model-View-Intent) Intent.
 *
 * This is not an android Intent(android.content.Intent). This Intent
 * derives the intention of the View
 *
 * The Intent represents an intention to perform an action by the user like an API call or a new
 * query in your database.
 *
 * This base intent should be implemented by all the View intents.
 */
interface ViewIntent