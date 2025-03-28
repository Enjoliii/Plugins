/*
 * Copyright 2015-2023 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritense.valtimo.kvk.autoconfigure

import com.ritense.plugin.service.PluginService
import com.ritense.valtimo.kvk.client.SimpleKvkHandelsregisterClient
import com.ritense.valtimo.kvk.plugin.KvkHandelsregisterPluginFactory
import com.ritense.valtimo.kvk.service.KvkHandelsregisterService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class HandelsregisterAutoConfiguration {

    @Bean
    fun handelsregisterService(
        simpleKvkHandelsregisterClient: SimpleKvkHandelsregisterClient
    ): KvkHandelsregisterService {
        return KvkHandelsregisterService(
            simpleKvkHandelsregisterClient
        )
    }

    @Bean
    fun kvkPluginFactory(
        pluginService: PluginService,
        kvkHandelsregisterService: KvkHandelsregisterService
    ): KvkHandelsregisterPluginFactory {
        return KvkHandelsregisterPluginFactory(
            pluginService,
            kvkHandelsregisterService
        )
    }

    @Bean
    fun simpleKvkClient(): SimpleKvkHandelsregisterClient {
        return SimpleKvkHandelsregisterClient()
    }
}