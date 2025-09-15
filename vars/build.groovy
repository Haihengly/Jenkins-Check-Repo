// def call() {
//     dir("${env.WORKSPACE}") {
//         echo 'Listing files to verify docker-compose.yml'
//         sh 'ls -l'

//         echo 'Building Docker images...'
//         sh 'docker-compose build'
//     }
// }

// build.groovy
def call(Map params = [:]) {
    // Set default values if not passed
    def version     = params.version ?: "1.0.0"
    def buildTool   = params.buildTool ?: "npm"
    def dockerImage = params.dockerImage ?: "myapp:${version}"
    def envName     = params.envName ?: "staging"

    echo "Version: ${version}"
    echo "Build Tool: ${buildTool}"
    echo "Docker Image: ${dockerImage}"
    echo "Environment: ${envName}"

    // Install dependencies and build
    if (buildTool == "npm") {
        sh "npm install && npm run build"
    } else {
        error "❌ Unknown build tool: ${buildTool}"
    }

    // Build Docker image
    sh "docker build -t ${dockerImage} ."
}
