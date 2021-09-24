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
package org.openrewrite.gradle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.properties.ChangePropertyValue;

import java.util.List;

import static java.util.Collections.singletonList;

@EqualsAndHashCode(callSuper = false)
@Getter
public class UpdateGradleWrapperProperties extends Recipe {

    @Option(displayName = "Gradle version",
            description = "Gradle releases are listed on [gradle.org](https://gradle.org/releases/).",
            example = "7.1.1")
    private final String gradleVersion;

    public UpdateGradleWrapperProperties(String gradleVersion) {
        this.gradleVersion = gradleVersion;
    }

    @Override
    public List<Recipe> getRecipeList() {
        return singletonList(
                new ChangePropertyValue("distributionUrl",
                "https\\://services.gradle.org/distributions/gradle-" + gradleVersion + "-bin.zip",
                null,
                "gradle/wrapper/gradle-wrapper.properties")
        );
    }

    @Override
    public String getDisplayName() {
        return "Update Gradle wrapper properties file";
    }

    @Override
    public String getDescription() {
        return "This is generally enough to update the Gradle version, unless the launch scripts have changed as well.";
    }
}
