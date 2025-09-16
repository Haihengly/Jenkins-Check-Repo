// def call() {
//     dir("${env.WORKSPACE}") {
//         echo 'Listing files to verify docker-compose.yml'
//         sh 'ls -l'

//         echo 'Building Docker images...'
//         sh 'docker-compose build'
//     }
// }

def call(Map config) { 
    echo "Building Docker images with docker-compose..."
    sh "docker-compose build --build-arg VERSION=${config.version}"
}


