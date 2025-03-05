package com.ritense.valtimoplugins.xential.service

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder
import org.apache.hc.core5.ssl.SSLContextBuilder
import java.io.ByteArrayInputStream
import java.security.KeyFactory
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import javax.net.ssl.SSLContext

object HttpClientHelper {

    fun createDefaultHttpClient(): CloseableHttpClient {
        return HttpClients.createDefault()
    }

    fun createSecureHttpClient(
        sslContext: SSLContext
    ): CloseableHttpClient =
        PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(
                SSLConnectionSocketFactoryBuilder.create()
                    .setSslContext(sslContext)
                    .build()
            )
            .build().let { connectionManager ->
                HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .build()
            }
}
