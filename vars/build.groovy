// def call() {
//     dir("${env.WORKSPACE}") {
//         echo 'Listing files to verify docker-compose.yml'
//         sh 'ls -l'

//         echo 'Building Docker images...'
//         sh 'docker-compose build'
//     }
// }

def call(Map params = [:]) {
    // Set default values if not passed
    def version   = params.VERSION ?: "1.0.0"
    def buildTool = params.BUILD_TOOL ?: "npm"
    def envName   = params.ENV ?: "staging"

    echo "➡️ Version: ${version}"
    echo "➡️ Build Tool: ${buildTool}"
    echo "➡️ Environment: ${envName}"

    // Install dependencies and build
    if (buildTool.toLowerCase() == "npm") {
        echo "➡️ Installing dependencies and building with npm..."
        sh "npm install && npm run build"
    } else {
        error "❌ Unknown build tool: ${buildTool}"
    }

    // Build Docker images using docker-compose
    echo "➡️ Building Docker images with docker-compose..."
    // Pass version as build-arg if your Dockerfile uses ARG VERSION
    sh "docker-compose build --build-arg VERSION=${version}"
}

