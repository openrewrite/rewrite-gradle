/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.gradle

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.openrewrite.Recipe
import org.openrewrite.properties.PropertiesRecipeTest
import java.nio.file.Path

class UpdateGradleWrapperPropertiesTest : PropertiesRecipeTest {
    override val recipe: Recipe
        get() = UpdateGradleWrapperProperties("7.1.1")

    @Test
    fun gradleVersion(@TempDir tempDir: Path) = assertChanged(
        before = tempDir.resolve("gradle/wrapper/gradle-wrapper.properties").toFile().apply {
            parentFile.mkdirs()
            writeText(
                """
                    distributionBase=GRADLE_USER_HOME
                    distributionPath=wrapper/dists
                    distributionUrl=https\://services.gradle.org/distributions/gradle-7.0.1-bin.zip
                    zipStoreBase=GRADLE_USER_HOME
                    zipStorePath=wrapper/dists
                """.trimIndent()
            )
        },
        relativeTo = tempDir,
        after = """
            distributionBase=GRADLE_USER_HOME
            distributionPath=wrapper/dists
            distributionUrl=https\://services.gradle.org/distributions/gradle-7.1.1-bin.zip
            zipStoreBase=GRADLE_USER_HOME
            zipStorePath=wrapper/dists
        """
    )
}
