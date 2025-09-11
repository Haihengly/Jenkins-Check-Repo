def call() {
    script {
        echo 'Listing files to verify docker-compose.yml'
        sh 'ls -l'

        echo 'Build and start Docker images...'
        sh 'docker-compose up -d --build'

    }
}
