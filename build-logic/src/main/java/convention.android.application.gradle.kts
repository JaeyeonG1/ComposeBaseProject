import com.tambi.common.config.configureCodeVerify
import com.tambi.project.config.configureAndroidApplication
import com.tambi.project.config.configureAppFlavors

// Configure application module settings for a specific app
configureAndroidApplication()
configureAppFlavors()

// Configure code verify plugins and dependencies
configureCodeVerify()

// Create a task to copy Git hooks from /scripts to .git/hooks path
val installGitHook by tasks.register<Copy>("installGitHooks") {
    from("${rootProject.rootDir}/scripts")
    into("${rootProject.rootDir}/.git/hooks")
    fileMode = 0b111101101
}

// Set "installGitHooks" task run after :app:preBuild
tasks.getByPath(":app:preBuild").finalizedBy("installGitHooks")
