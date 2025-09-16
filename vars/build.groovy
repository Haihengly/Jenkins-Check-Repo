// def call() {
//     dir("${env.WORKSPACE}") {
//         echo 'Listing files to verify docker-compose.yml'
//         sh 'ls -l'

//         echo 'Building Docker images...'
//         sh 'docker-compose build'
//     }
// }

def call(Map config) { 
    // Set default values if not passed
    def version   = config.version ?: "1.0.0"

    def envName   = config.envName ?: "staging"

    echo "Version: ${version}"
    echo "Environment: ${envName}"

    // Build Docker images using docker-compose
    echo "➡️ Building Docker images with docker-compose..."
    sh "docker-compose build --build-arg VERSION=${version}"
}


