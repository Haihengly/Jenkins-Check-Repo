def call(String branch, String repoUrl) {
    // Checkout the branch
    git branch: branch, url: repoUrl

    // Build & start Docker
    echo 'Listing files to verify docker-compose.yml'
    sh 'ls -l'

    echo 'Build and start Docker images...'
    sh 'docker-compose up -d --build'
}
