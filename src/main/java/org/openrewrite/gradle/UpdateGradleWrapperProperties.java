package org.openrewrite.gradle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.properties.ChangePropertyValue;

@EqualsAndHashCode(callSuper = false)
@Getter
public class UpdateGradleWrapperProperties extends Recipe {

    @Option(displayName = "Gradle version",
            description = "Gradle releases are listed on [gradle.org](https://gradle.org/releases/).",
            example = "7.1.1")
    private final String gradleVersion;

    public UpdateGradleWrapperProperties(String gradleVersion) {
        this.gradleVersion = gradleVersion;
        doNext(new ChangePropertyValue("distributionUrl",
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
